"""
    CS320 - Homework 2
    Aleksander Skjoelsvik - U54904431
"""

# Task 2
Node = dict
Leaf = str

def evalTerm(env, t):
    if type(t) == Node:
        for label in t:
            children = t[label]
            if label == 'Number':
                return children[0]
            elif label == 'Variable':
                print("variable")
                m = children[0]
                return env[m]
            elif label == 'Parens':
                m = children[0]
                return evalTerm(env, m)
            elif label == 'Int':
                m = children[0]
                v = evalTerm(env, m)
                if v == 'True':
                    return ({'Number':[1]})
                else:
                    return ({'Number':[0]})
            elif label == 'Add':
                m1 = children[0]
                m2 = children[1]
                v1 = evalTerm(env, m1)
                v2 = evalTerm(env, m2)
                return v1 + v2
            elif label == 'Multiply':
                m1 = children[0]
                m2 = children[1]
                v1 = evalTerm(env, m1)
                v2 = evalTerm(env, m2)
                return v1 * v2

def evalFormula(env, f):
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

def execProgram(env, s):
    if type(s) == Node:
        for label in s:
            children = s[label]
            if label == 'Print':
                print("print")
                m1 = children[0]
                m2 = children[1]
                v = evalTerm(env, m1)
                if v is None:
                    v = evalFormula(env, m1)
                (env, o) = execProgram(env, m2)
                return (env, [v] + o)
            elif label == 'Assign':
                print("assign")
                m1 = children[0]['Variable'][0]
                m2 = children[1]
                m3 = children[2]
                v = evalFormula(env, m2)
                if v is None:
                    v = evalTerm(env, m2)
                env[m1] = v
                return execProgram(env, m3)
            elif label == 'If':
                m1 = children[0]
                m2 = children[1]
                m3 = children[2]
                v = evalTerm(env, m1)
                if v is None:
                    v = evalFormula(env, m1)

                if v == "False":
                    (env2, o1) = execProgram(env, m3)
                    return (env2, o1)
                if v == "True":
                    (env2, o1) = execProgram(env, m2)
                    (env3, o2) = execProgram(env2, m3)
                    return (env3, o1 + o2)
            elif label == 'While':
                answ = []
                m1 = children[0]
                m2 = children[1]
                m3 = children[2]
                v = evalTerm(env, m1)
                if v is None:
                    v = evalFormula(env, m1)

                if v == 'False':
                    (env2, o) = execProgram(env, m3)
                    return (env2, o)
                if v == 'True':
                    (env2, o1) = execProgram(env, m2)
                    v = evalTerm(env2, m1)
                    if v is None:
                        v = evalFormula(env2, m1)
                        answ = o1
                    else:
                        answ += o1
                (env3, o2) = execProgram(env2, m3)
                return (env3, answ + o2)
    elif type(s) == Leaf:
        if s == 'End':
            return (env, [])

def interpret(s):
    keywords = ["number", "variable", "parens", "int", "add", "multiply", "true", "false", "bool", "xor", "print", "assign", "if", "while", "end", "{", "}", ";", "+", "*"]
    t = program(tokenize(keywords, s))
    (e1, tokens) = t
    if not t is None:
        (e1, tokens) = t
        r = execProgram({}, e1)
        if not r is None:
            (env, o) = r
            return o
    return (None)

import re
def tokenize(terms, s):
    sTerms = [re.escape(t) for t in terms]
    sTerms = "|".join(sTerms)
    sTerms = r"(\s+|" + sTerms + r"|)"
    tokens = [t for t in re.split(sTerms, s)]
    return [t for t in tokens if not t.isspace() and not t==""]