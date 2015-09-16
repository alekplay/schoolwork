#####################################################################
#
# CAS CS 320, Spring 2015
# Midterm (skeleton code)
# compile.py
#
#  ****************************************************************
#  *************** Modify this file for Problem #3. ***************
#  ****************************************************************
#

from random import randint
exec(open('parse.py').read())
exec(open('interpret.py').read())
exec(open('optimize.py').read())
exec(open('machine.py').read())
exec(open('analyze.py').read())

Leaf = str
Node = dict

def freshStr():
    return str(randint(0,10000000))

def compileExpression(env, e, heap):
    if type(e) == Node:
        for label in e:
            children = e[label]
            if label == 'Number':
                n = children[0]
                heap = heap + 1
                return (['set ' + str(heap) + ' ' + str(n)], heap, heap)
            if label == 'Variable':
                x = children[0]
                n = env[x]
                heap = heap + 1
                inst = 'set ' + str(heap) + ' ' + str(n)
                return([inst], heap, heap)
            if label == 'Array':
                x = children[0]['Variable'][0]
                e = children[1]
                #(insts1, addr1, heap2) = compileExpression(env, x, heap)
                (insts2, addr2, heap3) = compileExpression(env, e, heap)
                a = env[x]
                heap4 = heap3 + 1
                insts =     insts2 \
                        +   copy(a, 1) \
                        +   copy(addr2, 2) \
                        +   [\
                            'add'\
                            ]\
                        +   copy(0, heap4)
                return(insts, heap4, heap4)
            if label == 'Plus':
                [f1, f2] = children
                (insts1, addr1, heap2) = compileExpression(env, f1, heap)
                (insts2, addr2, heap3) = compileExpression(env, f2, heap2)
                heap4 = heap3 + 1
                insts =     insts1 \
                        +   insts2 \
                        +   copy(addr1, 1) \
                        +   copy(addr2, 2) \
                        +   [ \
                            'add' \
                            ] \
                        +   copy(0, heap4)
                return(insts, heap4, heap4)
    if type(e) == Leaf:
        if e == 'True':
            heap = heap + 1
            inst = 'set ' + str(heap) + ' 1'
            return([inst], heap, heap)
        if e == 'False':
            heap = heap + 1
            inst = 'set ' + str(heap) + ' 0'
            return([inst], heap, heap)

def compileProgram(env, s, heap = 7): # Set initial heap default address.
    if type(s) == Leaf:
        if s == 'End':
            return (env, [], heap)

    if type(s) == Node:
        for label in s:
            children = s[label]
            if label == 'Print':
                [e, p] = children
                (instsE, addr, heap) = compileExpression(env, e, heap)
                (env, instsP, heap) = compileProgram(env, p, heap)
                return (env, instsE + copy(addr, 5) + instsP, heap)
            if label == 'Assign':
                x = children[0]['Variable'][0]
                f1 = children[1]
                f2 = children[2]
                f3 = children[3]
                p = children[4]
                (insts1, addr1, heap2) = compileExpression(env, f1, heap)
                (insts2, addr2, heap3) = compileExpression(env, f2, heap2)
                (insts3, addr3, heap4) = compileExpression(env, f3, heap3)
                a = heap4 + 1
                a2 = a + 1
                a3 = a2 + 1
                env[x] = a
                (env, instsP, heap5) = compileProgram(env, p, a3)
                insts =     insts1 \
                        +   insts2 \
                        +   insts3 \
                        +   copy(addr1, a) \
                        +   copy(addr2, a2) \
                        +   copy(addr3, a3) \
                        +   instsP
                return(env, insts, heap5)

def compile(s):
    p = tokenizeAndParse(s)
    if not typeProgram({}, p) is None:
        p = foldConstants(p)
        p = unrollLoops(p)

        (env, insts, heap) = compileProgram({}, p)
        return insts

def compileAndSimulate(s):
    return simulate(compile(s))

def copy(frm, to):
    return [\
        'set 3 ' + str(frm),\
        'set 4 ' + str(to),\
        'copy'\
        ]

#eof