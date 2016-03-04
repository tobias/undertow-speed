# Sample undertow app for speed comparison

This app is used to demonstrate the speed difference between Undertow
versions 1.3.0.Beta9 and 1.3.18.Final in a micro-benchmark.

To run it with 1.3.18.Final, use:

    mvn compile exec:java

To use 1.3.0.Beta9, try:

    mvn compile exec:java -Pbeta9

I then ran `ab` against it with:

    ab -c 50 -n 100000 http://localhost:8181/

throwing away the results of the first run, then averaging the req/s
of the next six runs. Comparing that value for both versions, I saw a
~7% decrease in req/s.

This was with (on linux):

    openjdk version "1.8.0_71"
    OpenJDK Runtime Environment (build 1.8.0_71-b15)
    OpenJDK 64-Bit Server VM (build 25.71-b15, mixed mode)



