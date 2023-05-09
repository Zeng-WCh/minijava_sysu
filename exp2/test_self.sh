#!/bin/bash

if [ $# -ne 1 ]; then
    echo "Usage: ./test_self.sh <testcasefile>"
    exit 1
fi

java --class-path ./bin test.ExprEvalTest ./testcases/$1 > ./testcases/report.txt
cat ./testcases/report.txt
rm ./testcases/report.txt
