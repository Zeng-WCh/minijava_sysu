#!/bin/bash
CLASSPATH='../class'
[ -d ${CLASSPATH} ]
if [ $? -ne 0 ];
then
    exit 0
fi
echo y | rm ${CLASSPATH}/*.class