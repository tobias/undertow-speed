package org.tcrawley.undertowspeed;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionAttachmentHandler;
import io.undertow.server.session.SessionCookieConfig;

public class Main {

    static void startUndertow(final String host, final int port) {
        Undertow.builder()
                .addHttpListener(port, host)
                .setHandler(new SessionAttachmentHandler(HANDLER,
                                                         new InMemorySessionManager("foo", -1),
                                                         new SessionCookieConfig()))
                .build()
                .start();
    }

    static final String DATA = "hello-hello";

    static final HttpHandler HANDLER = new HttpHandler() {
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            if (exchange.isInIoThread()) {
                exchange.dispatch(this);
            } else {
                exchange.startBlocking();
                exchange.setResponseCode(200)
                        .setResponseContentLength(DATA.length())
                        .getResponseSender()
                        .send(DATA);
                }
            }
        };

    public static void main(String[] args) {
        startUndertow("localhost", 8181);
    }

}
