#!/bin/bash

mkdir -p results

for i in {1..15}
do
    a=$(printf "%03d" $i)
    ./run.sh ./testcases/fib.${a} | tee -a results/fib.${a}.out
done
