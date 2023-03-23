#!/bin/bash
MAIN='Postfix.java'
SRCPATH='../src'
CLASSPATH='../class'
JAVACOMPILER='javac'
[ -d ${SRCPATH} ]
if [ $? -ne 0 ];
then
    echo "Unable to find src, exiting..."
    exit 1
fi
# Make sure class path exist
[ -d ${CLASSPATH} ] || mkdir ${CLASSPATH}

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
