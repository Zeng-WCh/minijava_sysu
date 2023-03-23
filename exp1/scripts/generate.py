#!/usr/bin/python3

import random

LENGTH = 100
ROUND = 20

def random_num():
    return random.randint(0, 9)

def random_op():
    return random.choice(['+', '-'])

def main():
    for r in range(ROUND):
        with open('../test/{}.in'.format(r), 'w') as f:
            result = ""
            for i in range(LENGTH):
                result += str(random_num())
                if i < LENGTH - 1:
                    result += random_op()
            f.write(result)


if __name__ == '__main__':
    main()