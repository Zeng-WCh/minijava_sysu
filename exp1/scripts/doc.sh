#!/bin/bash

OUTPUTDIR=../doc
SRCDIR=../src

javadoc -d ${OUTPUTDIR} --source-path ${SRCDIR} ${SRCDIR}/*.java
