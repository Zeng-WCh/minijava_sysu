#!/usr/bin/python3

import os
import re
import copy
import subprocess

NONRECURSIONFMT = '../result/time/nonrecursion/{}.time'
RECURSIONFMT = '../result/time/recursion/{}.time'
CASENUM = 1000
TESTLEN = [1000, 2000, 3000, 4000, 5000]

def test(l: int):
    p = subprocess.Popen(['./generate.py', str(l)], stdout = subprocess.PIPE)
    p.wait()
    p = subprocess.Popen(['./timetest.py', str(l)], stdout = subprocess.PIPE)
    p.wait()
    return

def getMeanAndMedian(data: list):
    data = copy.deepcopy(data)
    data.sort()
    mean = sum(data) / len(data)
    median = data[len(data) // 2]
    return mean, median

def compare(data: dict, l: int):
    nonrec = data['nonrec']
    rec = data['rec']
    assert len(nonrec) == len(rec) == CASENUM
    recfaster = 0
    for i in range(len(nonrec)):
        if nonrec[i] > rec[i]:
            recfaster += 1
    print('--------------------------------------')
    print("Test length: {}".format(l))
    print("Cases Num: {}".format(CASENUM))
    print("Recursion faster: {}, which is {}%".format(recfaster, recfaster / CASENUM * 100))
    print("Recursion Mean: {}, Median: {}".format(*getMeanAndMedian(rec)))
    print("Nonrecursion Mean: {}, Median: {}".format(*getMeanAndMedian(nonrec)))
    print('--------------------------------------')

def read(idx: int):
    with open(NONRECURSIONFMT.format(idx), 'r') as f:
        nonrec = f.read()
    with open(RECURSIONFMT.format(idx), 'r') as f:
        rec = f.read()
    return nonrec.strip(), rec.strip()


def readresult():
    data = {'nonrec': [], 'rec': []}
    for i in range(CASENUM):
        nonrec, rec = read(i)
        nonrec = int(re.findall(r'(\d)+', nonrec)[0])
        rec = int(re.findall(r'(\d)+', rec)[0])
        data['nonrec'].append(nonrec)
        data['rec'].append(rec)
    return data

def main():
    data = {'nonrec': [], 'rec': []}
    for l in TESTLEN:
        print("Now Test length: {}".format(l))
        test(l)
        data = readresult()
        compare(data, l)

if __name__ == '__main__':
    main()
