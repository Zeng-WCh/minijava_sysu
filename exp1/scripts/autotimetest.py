#!/usr/bin/python3

import re
import copy
import subprocess
import numpy as np
import matplotlib.pyplot as plt

NONRECURSIONFMT = '../result/time/{}/nonrecursion/{}.time'
RECURSIONFMT = '../result/time/{}/recursion/{}.time'
CASENUM = 1000
TESTLEN = [1000, 2000, 3000, 4000, 5000]
RESULT = '../result/time/result.txt'
MEANDICT = {'nonrec': [], 'rec': []}
MEDDIANDICT = {'nonrec': [], 'rec': []}

def visualize(data: dict, l: int):
    nonre = copy.deepcopy(data['nonrec'])
    rec = copy.deepcopy(data['rec'])
    assert len(nonre) == len(rec) == CASENUM
    nonre.sort()
    rec.sort()
    redatadict = dict()
    nodatadict = dict()
    for i in range(len(rec)):
        if redatadict.get(rec[i]):
            redatadict[rec[i]] += 1
        else:
            redatadict[rec[i]] = 1

        if nodatadict.get(nonre[i]):
            nodatadict[nonre[i]] += 1
        else:
            nodatadict[nonre[i]] = 1

    keys = list(redatadict.keys())
    for key in nodatadict.keys():
        if key in keys:
            continue
        else:
            keys.append(key)
    
    keys.sort()

    yr = []
    ynr = []

    for key in keys:
        if redatadict.get(key):
            yr.append(redatadict[key])
        else:
            yr.append(0)
        if nodatadict.get(key):
            ynr.append(nodatadict[key])
        else:
            ynr.append(0)
    
    xr = np.arange(len(keys))
    width = 0.35
    plt.bar(xr, yr, width, label = 'Recursion', color = 'r')
    plt.bar(xr + width, ynr, width, label = 'Nonrecursion', color = 'b')
    plt.xticks(xr + width / 2, keys)
    plt.title('Recursion VS Nonrecursion Time Test of Length {}'.format(l))
    plt.legend()
    plt.savefig('../result/time/{}.png'.format(l))
    plt.clf()
    return
    
    
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
    rmean, rmedian = getMeanAndMedian(rec)
    nrmean, nrmedian = getMeanAndMedian(nonrec)
    MEANDICT['nonrec'].append(nrmean)
    MEANDICT['rec'].append(rmean)
    MEDDIANDICT['nonrec'].append(nrmedian)
    MEDDIANDICT['rec'].append(rmedian)
    with open(RESULT, 'a') as f:
        f.write('--------------------------------------\n')
        f.write("Test length: {}\n".format(l))
        f.write("Cases Num: {}\n".format(CASENUM))
        f.write("Recursion faster: {}, which is {}%\n".format(recfaster, round(recfaster / CASENUM * 100)))
        f.write("Recursion Mean: {}ms, Median: {}ms\n".format(rmean, rmedian))
        f.write("Nonrecursion Mean: {}ms, Median: {}ms\n".format(nrmean, nrmedian))
        f.write('--------------------------------------\n')
    print('--------------------------------------')
    print("Test length: {}".format(l))
    print("Cases Num: {}".format(CASENUM))
    print("Recursion faster: {}, which is  {}%".format(recfaster, round(recfaster / CASENUM * 100)))
    print("Recursion Mean: {}ms, Median: {}ms".format(rmean, rmedian))
    print("Nonrecursion Mean: {}ms, Median: {}ms".format(nrmean, nrmedian))
    print('--------------------------------------')

def read(l: int, idx: int):
    with open(NONRECURSIONFMT.format(l, idx), 'r') as f:
        nonrec = f.read()
    with open(RECURSIONFMT.format(l, idx), 'r') as f:
        rec = f.read()
    return nonrec.strip(), rec.strip()


def readresult(l: int):
    data = {'nonrec': [], 'rec': []}
    for i in range(CASENUM):
        nonrec, rec = read(l, i)
        nonrec = int(re.findall(r'(\d)+', nonrec)[0])
        rec = int(re.findall(r'(\d)+', rec)[0])
        data['nonrec'].append(nonrec)
        data['rec'].append(rec)
    return data

def main():
    with open(RESULT, 'w') as f:
        pass
    data = {'nonrec': [], 'rec': []}
    for l in TESTLEN:
        print("Now Test length: {}".format(l))
        test(l)
        data = readresult(l)
        visualize(data, l)
        compare(data, l)

if __name__ == '__main__':
    main()
