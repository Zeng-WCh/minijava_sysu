#!/bin/bash
sh build.sh > /dev/null
TESTDIR='../test/'
RESULTDIR='../result'
[ -d ${RESULTDIR} ] || mkdir ${RESULTDIR}