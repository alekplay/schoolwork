######################################################################
#
# CAS CS 320, Spring 2015
# Assignment 3 (skeleton code)
# interpret.py
#

exec(open("parse.py").read())

Node = dict
Leaf = str

def evalTerm(env, e):
    if type(e) == Node:
        for label in e:
            children = e[label]
            if label == 'Number':
                return children[0]
            elif label == 'Variable':
                m = children[0]
                return env[m]
            elif label == 'Add':
                m1 = children[0]
                m2 = children[1]
                v1 = evalTerm(env, m1)
                v2 = evalTerm(env, m2)
                return v1 + v2

def evalFormula(env, e):
    if type(f) == Node:
        for label in f:
            children = f[label]
            if label == 'Variable':
                print("variable")
                m = children[0]
                return env[m]
            elif label == 'Bool':
                m = children[0]
                v = evalFormula(env, m)
                if v == 0:
                    return "False"
                else:
                    return "True"
            
            elif label == 'Xor':
                m1 = children[0]
                m2 = children[1]
                v1 = evalFormula(env, m1)
                v2 = evalFormula(env, m2)
                return str(v1 != v2)
    elif type(f) == Leaf:
        if f == 'True':
            return "True"
        elif f == 'False':
            return "False"      

def evalExpression(env, e): # Useful helper function.
    pass

def execProgram(env, s):
    pass # Complete for Problem #1, part (c).
                    
def interpret(s):
    (env, o) = execProgram({}, tokenizeAndParse(s))
    return o

#eof