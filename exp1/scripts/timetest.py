#!/usr/bin/python3

import os
import sys
import subprocess

TESTDIR = '../test/infix'
TESTFMT = TESTDIR + '/{}.in'
RESULTDIR = '../result/time/'
NONRECURSION = 'nonrecursion'
RECURSION = 'recursion'
CASENUM = 1000
TESTLEN = 3500 if len(sys.argv) < 2 else int(sys.argv[1])

def test(idx: int):
    print("Now Test No.{}".format(idx))
    with open(TESTFMT.format(idx), 'r') as f:
        with open(RESULTDIR + NONRECURSION + '/{}.time'.format(idx), 'w') as fe:
            p = subprocess.Popen(['./run.sh', 'Postfix', '-t', '--time'], stdin = f, stdout = subprocess.PIPE, stderr = fe)
            p.wait()
    with open(TESTFMT.format(idx), 'r') as f:
        with open(RESULTDIR + RECURSION + '/{}.time'.format(idx), 'w') as fe:
            p = subprocess.Popen(['./run.sh', 'PostfixRecursion', '-t', '--time'], stdin = f, stdout = subprocess.PIPE, stderr = fe)
            p.wait()
    print("Finish Test No.{}".format(idx))


def main():
    os.system('./build.sh Postfix')
    os.system('./build.sh PostfixRecursion')
    if not os.path.exists(TESTDIR):
        os.system('./generate.py {}'.format(TESTLEN))
    if not os.path.exists(RESULTDIR):
        os.system('mkdir -p {}'.format(RESULTDIR))
    if not os.path.exists(RESULTDIR + NONRECURSION):
        os.system('mkdir -p {}'.format(RESULTDIR + NONRECURSION))
    if not os.path.exists(RESULTDIR + RECURSION):
        os.system('mkdir -p {}'.format(RESULTDIR + RECURSION))
    for i in range(CASENUM):
        test(i)

if __name__ == '__main__':
    main()
    