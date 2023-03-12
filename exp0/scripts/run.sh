#!/bin/bash
CLASSPATH='../class'
JAVA='java'
MAINCLASS='TaxCalculator'

${JAVA} --class-path ${CLASSPATH} ${MAINCLASS} $*