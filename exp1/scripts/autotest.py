#!/usr/bin/python3

import subprocess
import sys


TESTDIR = '../test/infix'
TESTFMT = TESTDIR + '/{}.in'
ANSDIR = '../test/postfix'
ANSFMT = ANSDIR + '/{}.ans'
CASENUM = 1000
PROGRAM = 'Postfix' if len(sys.argv) == 1 else sys.argv[1]

def test(index: int):
    with open(TESTFMT.format(index), 'r') as f:
        p = subprocess.Popen(['./run.sh', PROGRAM, '-t'], stdin = f, stdout = subprocess.PIPE)
    res = p.communicate()[0]
    p.wait()
    res = res.decode().strip()
    with open(ANSFMT.format(index), 'r') as f:
        ans = f.read()
    return ans == res
        


def main():
    fail = []
    for i in range(CASENUM):
        print("Now testing {}.in".format(i))
        if not test(i):
            print('Test No.{} failed'.format(i))
            fail.append(i)
        else:
            print('Test No.{} success'.format(i))
    if len(fail) == 0:
        print('All test passed')



if __name__ == '__main__':
    main()