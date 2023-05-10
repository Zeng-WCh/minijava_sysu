#!/usr/bin/python3

import os
import sys

def scannertest():
    os.system('./build.sh')
    os.system('./test_self.sh ScannerTest.xml')
    return

def parsertest(testfile: str = 'ParserTest.xml'):
    os.system('./build.sh')
    os.system('./test_self.sh {}'.format(testfile))
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
    if len(sys.argv) == 2:
        testfile = None
    else:
        testfile = sys.argv[2]
    if testp not in TESTPROGRAM.keys():
        print("Usage: " + sys.argv[0] + gethelp())
        sys.exit(1)
    if testfile is not None:
        TESTPROGRAM[testp](testfile)
    else:
        TESTPROGRAM[testp]()
    sys.exit(0)

if __name__ == '__main__':
    main()
