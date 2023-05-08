#!/bin/bash
java --class-path ./bin test.ExprEvalTest ./testcases/standard.xml > ./testcases/report.txt
cat ./testcases/report.txt
rm ./testcases/report.txt
