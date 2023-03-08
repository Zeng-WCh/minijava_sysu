#!/bin/bash
sh build.sh > /dev/null
TESTDIR='../test/'
RESULTDIR='../result'
CLASSPATH='../class'
JAVA='java'
MAINCLASS='TaxCalculator'
[ -d ${TESTDIR} ]
if [ $? -ne 0 ];
then
    echo "Unable to find test dir, exiting..."
    exit 1
fi
[ -d ${RESULTDIR} ] || mkdir ${RESULTDIR}
LOG=${RESULTDIR}/test_$(date +%s).log
echo "Result will be written to: "${LOG}
for file in `ls ${TESTDIR}`;
do
    echo "Now testing file: "${file}
    echo "Now testing file: "${file} >> ${LOG}
    for idx in {1..5};
    do
        SALARY_INT=$[ ${RANDOM}+1 ]
        SALARY_FLOAT=$[ (${RANDOM}%10)*10 ]
        SALARY=${SALARY_INT}.${SALARY_FLOAT}
        START=$[ (${RANDOM}%10)*1000 ]
        echo "Test Round No."${idx}
        echo "Salary: "${SALARY}
        echo "Start: "${START}
        echo "Test Round No."${idx} >> ${LOG}
        echo "Salary: "${SALARY} >> ${LOG}
        echo "Start: "${START} >> ${LOG}
        ${JAVA} --class-path ${CLASSPATH} ${MAINCLASS} --start ${START} --salary ${SALARY} --config ${TESTDIR}/${file} --detail true >> ${LOG}
    done
done

