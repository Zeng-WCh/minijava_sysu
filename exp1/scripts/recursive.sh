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
    echo "Running test: "${i}
    ./run.sh < ${i} > ${i%.in}.out
done
