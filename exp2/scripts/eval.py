#!/usr/bin/python3

import sys
import os

def evalF(fin: str): 
    with open(fin, 'r') as f:
        buf = f.read()
    return eval(buf)

def main():
    if len(sys.argv) < 3:
        print("usage: " + sys.argv[0] + " [filename] [val]")
        sys.exit(1)
    fin = sys.argv[1]
    valE = evalF(fin)
    val = float(sys.argv[2])
    if os.path.isfile(fin):
        if abs(val - valE) < 1e-6:
            print("Test pass for {}".format(fin))
        else:
            print("Test fail for {}".format(fin))
    else:
        print(fin + ' is not a file')
        sys.exit(1)

if __name__ == '__main__':
    main()
