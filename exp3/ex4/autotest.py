#!/usr/bin/python3

import os

TEST_DIR = "testcases"
RESULT_DIT = "results"

def main():
    os.system("./gen.sh")
    os.system("./build.sh")
    if not os.path.exists(RESULT_DIT):
        os.system("mkdir {}".format(RESULT_DIT))
    testfilelist = os.listdir(TEST_DIR)
    for tf in testfilelist:
        os.system("./run.sh " + TEST_DIR + "/" + tf + " >" + RESULT_DIT + "/" + tf + ".result" + " 2>&1")

if __name__ == '__main__':
    main()