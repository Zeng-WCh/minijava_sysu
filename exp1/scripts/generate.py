#!/usr/bin/python3

import random
import copy

LENGTH = 100
ROUND = 20
TESTFMT = '../test/{}.in'
MAXNUM = 40

def random_num():
    return random.randint(1, MAXNUM)

def random_op():
    ret = random.choice(['+', '-', '*', '/'])
    return ret

def random_p():
    global ISDIV
    r = random.randint(0, 100)
    tmp = None
    if r >= 40:
        tmp = '('
        length = random.randint(3, 10)
        for i in range(length):
            tmp += str(random_num())
            if i < length - 1:
                tmp += random_op()
        tmp += ')'
    if tmp is None:
        tmp = ''
    else:
        if eval(tmp) == 0:
            tmp.replace(')', '+' + str(random.randint(10, 20)) + ')')
        tmp += random_op()
    return tmp

def main():
    for r in range(ROUND):
        with open(TESTFMT.format(r), 'w') as f:
            result = ""
            for i in range(LENGTH):
                result += random_p()
                result += str(random_num())
                if i < LENGTH - 1:
                    result += random_op()
            f.write(result)


if __name__ == '__main__':
    main()
