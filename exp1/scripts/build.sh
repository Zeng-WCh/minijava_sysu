#!/bin/bash

if [ $# -le 0 ];
then
    echo "Usage: $0 <main class name>"
    exit 1
fi

MAIN=$1.java
SRCPATH='../src'
CLASSPATH='../class/'${MAIN%.*}
JAVACOMPILER='javac'
shift 1

[ -d ${SRCPATH} ]
if [ $? -ne 0 ];
then
    echo "Unable to find src, exiting..."
    exit 1
fi
# Make sure class path exist
[ -d ${CLASSPATH} ] || mkdir -p ${CLASSPATH}

# Clean the CLASSPATH
if [ "$(ls -A ${CLASSPATH})" ];
then
    echo y | rm ${CLASSPATH}/*.class
fi

${JAVACOMPILER} -d ${CLASSPATH} --source-path ${SRCPATH} ${SRCPATH}/${MAIN}
if [ $? -eq 0 ];
then
    echo "Compile finish"
    echo "Try to run it by run.sh"
else
    echo "Failed"
    exit 1
fi
