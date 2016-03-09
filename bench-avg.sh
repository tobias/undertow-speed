#!/bin/bash

f=`mktemp`
avg=`./bench.sh |tee $f | sed -r 's/^.*[[:space:]]+([0-9]+)\..*$/\1/' | awk '{ sum += $1 } END { if (NR > 0) print sum / NR }'`

cat $f
echo "Average: $avg"


