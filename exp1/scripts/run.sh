#!/bin/bash
JAVA='java'

if [ $# -le 0 ];
then
    MAINCLASS='Postfix'
    CLASSPATH='../class/Postfix'
else
    MAINCLASS=$1
    CLASSPATH='../class/'${MAINCLASS}
    shift 1
fi

${JAVA} --class-path ${CLASSPATH} ${MAINCLASS} $*
