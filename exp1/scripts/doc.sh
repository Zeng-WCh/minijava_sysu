#!/bin/bash

if [ $# -le 0 ];
then
    CLASS='Postfix.java'
else
    CLASS=$1.java
fi

OUTPUTDIR=../doc
SRCDIR=../src

javadoc -private -d ${OUTPUTDIR} --source-path ${SRCDIR} ${SRCDIR}/${CLASS} ${SRCDIR}/Token.java ${SRCDIR}/Lexer.java
