#!/usr/bin/python3

import os
import sys
import subprocess


def scannertest():
    inputstr = ['1+1+6-10+sin(3.14)-cos(1)+max(1,2, 3)+1e6+-sin(1-3.2)', '1+ sin(2>3?4:5)', '1+-5']
    os.system('./build.sh')
    p = subprocess.Popen(['java', '-classpath', './bin', 'ScannerTest'] + inputstr)
    p.wait()
    return

def parsertest():
    return

TESTPROGRAM = {
    'scanner': scannertest,
    'parser': parsertest,
}

def gethelp():
    ret = " ["
    for p in TESTPROGRAM.keys():
        ret += p
        ret += ' | '
    ret = ret.rstrip(" | ")
    ret += ']'
    return ret

def main():
    if len(sys.argv) == 1:
        print("Usage: " + sys.argv[0] + gethelp())
        sys.exit(1)
    testp = sys.argv[1]
    if testp not in TESTPROGRAM.keys():
        print("Usage: " + sys.argv[0] + gethelp())
        sys.exit(1)
    TESTPROGRAM[testp]()
    sys.exit(0)

if __name__ == '__main__':
    main()