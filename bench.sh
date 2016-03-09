#!/bin/bash

ab -c 50 -n 100000 http://localhost:8181/ &> /dev/null

for i in {1..5}; do
    sleep 5
    ab -c 50 -n 100000 http://localhost:8181/ 2>&1 | grep 'Requests per second:'
done



