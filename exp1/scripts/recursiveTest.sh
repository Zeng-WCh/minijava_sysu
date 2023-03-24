#!/bin/bash

TESTDIR=../test
RESULTDIR=../result/recursive
LOG=${RESULTDIR}/test_$(date +%s).log

[ -d ${TESTDIR} ]
if [ $? -ne 0 ];
then
    mkdir -p ${TESTDIR}
    ./generate.py
fi

[ -d ${RESULTDIR} ]
if [ $? -ne 0 ];
then
  mkdir -p ${RESULTDIR}
fi

# Run the test
for i in $(ls ${TESTDIR}/*.in)
do
    echo "Running test: "${i}
    r=${i/test/result}
    { time ./run.sh < ${i} > ${r}; } 2>> ${LOG}
done