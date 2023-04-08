#!/usr/bin/python3


import random
import os
import sys
import copy


TESTDIR = '../test/infix'
TESTFMT = TESTDIR + '/{}.in'
ANSDIR = '../test/postfix'
ANSFMT = ANSDIR + '/{}.ans'
CASENUM = 1000
MAXLEN = 3500 if len(sys.argv) < 2 else int(sys.argv[1])
MAXNUM = 9
MINNUM = 0
OPERATORS = ['+', '-',]


def generate():
    case = ""
    for i in range(MAXLEN):
        case += str(random.randint(MINNUM, MAXNUM))
        if i != MAXLEN - 1:
            case += random.choice(OPERATORS)
    return case, generateans(case)


def generateans(case):
    stackOp = []
    stackNum = []
    for i in range(len(case)):
        if case[i] in OPERATORS:
            stackOp.append(case[i])
        else:
            stackNum.append(case[i])
            if len(stackNum) == 2:
                num1 = stackNum.pop()
                num2 = stackNum.pop()
                op = stackOp.pop()
                stackNum.append(num2 + num1 + op)
    while len(stackNum) > 1:
        num1 = stackNum.pop()
        num2 = stackNum.pop()
        op = stackOp.pop()
        stackNum.append(num2 + num1 + op)
    return stackNum[0]


def main():
    if not os.path.exists(TESTDIR):
        os.makedirs(TESTDIR)
    if not os.path.exists(ANSDIR):
        os.makedirs(ANSDIR)
    for i in range(CASENUM):
        with open(TESTFMT.format(i), 'w') as f:
            t, a = generate()
            f.write(t)
        with open(ANSFMT.format(i), 'w') as f:
            f.write(a)


if __name__ == '__main__':
    main()
