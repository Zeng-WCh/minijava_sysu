#!/bin/bash
java --class-path ./bin test.StandardTest ./testcases/standard.xml > ./testcases/report.txt
cat ./testcases/report.txt
rm ./testcases/report.txt
