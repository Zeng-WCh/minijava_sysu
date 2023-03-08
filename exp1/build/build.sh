#!/bin/bash
MAIN='TaxCalculator.java'
SRCPATH='../src'
CLASSPATH='../class'
JAVACOMPILER='javac'
[ -d ${SRCPATH} ]
if [ $? -ne 0 ];
then
    echo "No src path find"
    exit 1
fi
# Make sure class path exist
[ -d ${CLASSPATH} ] || mkdir ${CLASSPATH}
echo y | rm ${CLASSPATH}/*.class
${JAVACOMPILER} -d ${CLASSPATH} --source-path ${SRCPATH} ${SRCPATH}/${MAIN}
if [ $? -eq 0 ];
then
    echo "Successfully to compile to .class file\nTry to run it by run.sh"
else
    echo "Failed"
    exit 1
fi
