#!/bin/bash

TESTDIR=../test/recursive

[ -d ${TESTDIR} ]
if [ $? -ne 0 ];
then
    mkdir -p ${TESTDIR}
    ./generate.py
fi

# Run the test
for i in $(ls ${TESTDIR}/*.in)
do
    echo ${i}
    echo ${i%.in}.out
    #echo "Running test: ${i}"
    #java -cp ../class TaxCalculator < ${i} > ${i%.in}.out
done
