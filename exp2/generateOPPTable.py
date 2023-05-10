#!/usr/bin/python3

import sys
import datetime

class OPPTable:
    def __init__(self, classname: str, table, opToToken):
        self.classname = classname
        self.table = table
        self.opToToken = opToToken
        self.keys = list(self.opToToken.keys())
        self.info = '/**\n* OPPTable generate by ' + sys.argv[0]+ '\n* \n* @author Weichao Zeng\n* @version 1.00 (Last update: ' + str(datetime.datetime.now()).split()[0].replace('-', '/') + ')\n*/\n'
        pass

    def setOp(self, table, opToToken):
        self.table = table
        self.opToToken = opToToken

    def genConst(self):
        return 'public static final int shift = 1;\npublic static final int reduce = 2;\npublic static final int accept = 3;\npublic static final int error = -1;'

    def genopToToken(self):
        base = 'public static int opToToken(TokenType op) {\n'
        for i in range(len(self.keys)):
            base += 'if(op == TokenType.' + self.opToToken[self.keys[i]] + ') return ' + str(i) + ';\n'
        base += 'return -1;'
        return base + '\n}'

    def genOPPTable(self):
        base = 'public static int[][] OPPTable = {\n'
        for i in range(len(self.keys)):
            base += '{'
            for j in range(len(self.keys)):
                if self.keys[i] == '$' and self.keys[j] == '$':
                    tmp = '3'
                # elif (self.keys[i] == '~' or self.keys[i] == '+' or self.keys[i] == '-' or self.keys[i] == '*' or self.keys[i] == '/' or self.keys[i] == '^') and self.keys[j] == '?':
                #     tmp = '2'
                else:
                    tmp = str(self.table[self.keys[i]][self.keys[j]])
                    if tmp == '<' or tmp == '=':
                        # shift
                        tmp = '1'
                    elif tmp == '>':
                        # reduce
                        tmp = '2'
                    else:
                        # error
                        tmp = '-1'
                base += tmp + ','
            base = base.rstrip(',') + '},\n'
        return base + '\n};'

    def genCode(self):
        base = self.info
        base += 'class ' + self.classname + ' {\n'
        base += self.genConst()
        base += '\n\n'
        base += self.genopToToken()
        base += '\n\n'
        base += self.genOPPTable()
        return base + '\n}'



def check(a, b) -> bool:
    for i in b:
        if i not in a:
            return True
    return False
    

def getRules(f) -> list:
    rules = list()
    for line in f:
        if len(line.strip()) == 0:
            continue
        if line.strip() == '##':
            break
        rules.append(line.strip())
    return rules

def isTriple(op):
    return op == '?' or op == ':'

def getTriple(opstack, opstring, opPriority, opAssociativity):
    assert isTriple(opstack) and isTriple(opstring)
    if opstack == ':' and opstring == ':':
        return '>'
    if opPriority[opstack] < opPriority[opstring]:
        return '>'
    elif opPriority[opstack] > opPriority[opstring]:
        return '<'
    elif opPriority[opstack] == opPriority[opstring]:
        if opAssociativity[opstack] == 'left':
            return '>'
        else:
            return '<'


def getPriority(f) -> tuple[dict, dict, dict]:
    opPriority = dict()
    opAssociativity = dict()
    opToToken = dict()
    for line in f:
        if line.strip() == '##':
            break
        tmp = line.strip().split()
        if len(tmp) != 4:
            continue
        opPriority[tmp[0]] = int(tmp[1])
        opAssociativity[tmp[0]] = tmp[2]
        opToToken[tmp[0]] = tmp[3]
    return opPriority, opAssociativity, opToToken

def getOthers(f) -> dict:
    others = dict()
    for line in f:
        tmp = line.strip().split()
        if len(tmp) != 2:
            continue
        others[tmp[0]] = tmp[1]
    return others

def readConfig(filename):
    with open(filename, 'r') as f:
        rules = getRules(f)
        opPriority, opAssociativity, opToToken = getPriority(f)
        others = getOthers(f)
        for k in others:
            opToToken[k] = others[k]
    return rules, opPriority, opAssociativity, opToToken


def getOpPriority(opPriority, opAssociativity, opstack, opstring):
    if isTriple(opstack) and isTriple(opstring):
        return getTriple(opstack, opstring, opPriority, opAssociativity)
    if opPriority[opstack] < opPriority[opstring]:
        return '>'
    elif opPriority[opstack] > opPriority[opstring]:
        return '<'
    elif opPriority[opstack] == opPriority[opstring]:
        if opAssociativity[opstack] == 'left':
            return '>'
        else:
            return '<'



def main():
    rules = list()
    opPriority = dict()
    opAssociativity = dict()
    if len(sys.argv) == 1:
        print("Usage: " + sys.argv[0] + " [filename]")
        sys.exit(1)
    filename = sys.argv[1]

    rules, opPriority, opAssociativity, opToTok = readConfig(filename)

    TERMINALSTR = list()
    NONTERMINALSTR = list()
    for line in rules:
        tmp = line.split('::=')
        if len(tmp) == 0:
            continue
        nonterminal = tmp[0].strip()
        if nonterminal not in NONTERMINALSTR:
            NONTERMINALSTR.append(nonterminal)
        
    for line in rules:
        right = line.split('::=')[1].strip().split()
        for t in right:
            if t not in NONTERMINALSTR and t not in TERMINALSTR:
                TERMINALSTR.append(t)
    
    FIRSTVT = dict()
    LASTVT = dict()
    for nonterminal in NONTERMINALSTR:
        FIRSTVT[nonterminal] = list()
        LASTVT[nonterminal] = list()
    
    while True:
        changed = False
        for line in rules:
            left, right = line.split('::=')
            left = left.strip()
            right = right.strip().split()
            
            if right[0] in TERMINALSTR and right[0] not in FIRSTVT[left]:
                FIRSTVT[left].append(right[0])
                changed = True
            if right[0] in NONTERMINALSTR and check(FIRSTVT[left], FIRSTVT[right[0]]):
                FIRSTVT[left] += FIRSTVT[right[0]]
                FIRSTVT[left] = list(set(FIRSTVT[left]))
                changed = True
            if len(right) >= 2 and right[0] in NONTERMINALSTR and right[1] in TERMINALSTR and right[1] not in FIRSTVT[left]:
                FIRSTVT[left].append(right[1])
                changed = True

            if right[-1] in TERMINALSTR and right[-1] not in LASTVT[left]:
                LASTVT[left].append(right[-1])
                changed = True
            elif right[-1] in NONTERMINALSTR and check(LASTVT[left], LASTVT[right[-1]]):
                LASTVT[left] += LASTVT[right[-1]]
                LASTVT[left] = list(set(LASTVT[left]))
                changed = True
            if len(right) >= 2 and right[-1] in NONTERMINALSTR and right[-2] in TERMINALSTR and right[-2] not in LASTVT[left]:
                LASTVT[left].append(right[-2])
                changed = True
            
        if not changed:
            break
    # print("FIRSTVT:")
    # for k, v in FIRSTVT.items():
    #     print(k, v)
    # print("LASTVT:")
    # for k, v in LASTVT.items():
    #     print(k, v)
    table = dict()
    for nt in TERMINALSTR:
        table[nt] = dict()
        for n in TERMINALSTR:
            table[nt][n] = None


    for production in rules:
        right = production.split('::=')[-1].strip().split()

        for i in range(len(right) - 1):
            if right[i] in TERMINALSTR and right[i+1] in TERMINALSTR:
                if table[right[i]][right[i+1]] is None or table[right[i]][right[i+1]] == '=':
                    table[right[i]][right[i+1]] = '='
                else:
                    table[right[i]][right[i+1]] = 'undefined'
            
            if i < len(right) - 2 and right[i] in TERMINALSTR and right[i+2] in TERMINALSTR and right[i+1] in NONTERMINALSTR:
                if table[right[i]][right[i+2]] is None or table[right[i]][right[i+2]] == '=':
                    table[right[i]][right[i+2]] = '='
                else:
                    table[right[i]][right[i+2]] = 'undefined'

            if right[i] in TERMINALSTR and right[i+1] in NONTERMINALSTR:
                for fv in FIRSTVT[right[i+1]]:
                    if table[right[i]][fv] is None or table[right[i]][fv] == '<':
                        table[right[i]][fv] = '<'
                    else:
                        table[right[i]][fv] = 'undefined'
            
            if right[i] in NONTERMINALSTR and right[i+1] in TERMINALSTR:
                for lv in LASTVT[right[i]]:
                    if table[lv][right[i+1]] is None or table[lv][right[i+1]] == '>':
                        table[lv][right[i+1]] = '>'
                    else:
                        table[lv][right[i+1]] = '='

    for k in table.keys():
        for v in table[k].keys():
            if table[k][v] is not None and k in opPriority.keys() and v in opPriority.keys():
                table[k][v] = getOpPriority(opPriority, opAssociativity, k, v)
    
    opp = OPPTable('OPPTable', table, opToTok)
    print(opp.genCode())
    
    with open('table.csv', 'w') as f:
        print(',', end='', file=f)
        headers = list(table.keys())
        for i in headers:
            if i == ',':
                i = 'comma'
            print(i, end=',', file=f)
        
        for l in table.keys():
            print(file=f)
            if l == ',':
                l = 'comma'
            print(l, end=',', file=f)
            if l == 'comma':
                l = ','
            for r in headers:
                print(table[l][r] if table[l][r] is not None else '', end=',', file=f)

    return

if __name__ == '__main__':
    main()