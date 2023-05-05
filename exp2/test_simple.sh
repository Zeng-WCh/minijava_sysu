#!/bin/bash
java -classpath ./bin test.ExprEvalTest ./testcases/simple.xml > ./testcases/report.txt
cat ./testcases/report.txt
rm ./testcases/report.txt
