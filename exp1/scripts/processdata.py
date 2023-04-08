#!/usr/bin/python3

import os
import subprocess

NONRECURSIONFMT = '../result/time/nonrecursion/{}.time'
RECURSIONFMT = '../result/time/recursion/{}.time'
CASENUM = 1000

def read(idx: int):
    with open(NONRECURSIONFMT.format(idx), 'r') as f:
        nonrec = f.read()
    with open(RECURSIONFMT.format(idx), 'r') as f:
        rec = f.read()
    return nonrec.strip(), rec.strip()


def main():
    data = {'nonrec': [], 'rec': []}
    for i in range(CASENUM):
        nonrec, rec = read(i)
        nonrec = nonrec.match(r'(\d+)')
        print(nonrec)
        return
        data['nonrec'].append(nonrec)
        data['rec'].append(rec)
    print(data)

if __name__ == '__main__':
    main()
