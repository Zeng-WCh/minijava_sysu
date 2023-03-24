#!/bin/bash

TESTDIR=../test
RESULTDIR=../result
LOG=${RESULTDIR}/result_$(date +%s).log

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

./build.sh

for i in $(ls ${TESTDIR}/*.in)
do
    res=`./run.sh -t < ${i}`
    ./eval.py ${i} ${res}
done

