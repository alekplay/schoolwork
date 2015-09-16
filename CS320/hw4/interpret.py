#####################################################################
#
# CAS CS 320, Spring 2015
# Assignment 4 (skeleton code)
# interpret.py
#

exec(open("parse.py").read())

def subst(s, a):
    if type(a) == dict:
        dic = {}
        for key in a:
            if key == "Variable":
                if a[key][0] in s.keys():
                    return s[a[key][0]]
                else:
                    return a
            else:
                x = subst(s, a[key])
                dic[key] = x
                return dic
    if type(a) == list:
        lis = []
        for x in a:
            for key in x:
                y = subst(s, x)
                lis.append(y)
        return lis

def unify(a, b):
    s = {}
    if type(a) == Leaf and type(b) == Leaf and a == b:
        return {}

    if type(a) == Node and type(b) == Node:
        k1 = list(list(a.items())[0])
        k2 = list(list(b.items())[0])
        if k1[0] == 'Variable':
            s[k1[1][0]] = b
            return s
        if k2[0] == 'Variable':
            s[k2[1][0]] = a
            return s
        if k1[0] == k2[0] and len(k1[1]) == len(k2[1]):
            for i in range(len(k1[1])):
                if k1[1][i] != k2[1][i]:
                    s.update(unify(k1[1][i], k2[1][i]))
            return s

def build(m, d):
    for label in d:
        if label == 'Function':
            [var, p, e, dec] = d[label]
            f = var['Variable'][0]
            if f in m:
                m[f] = m[f],[p,e]
            else:
                m[f] = [p,e]
            if dec == 'End':
                return m
            else:
                return build(m, dec)

def evaluate(m, env, e):
    pass

def interact(s):
    # Build the module definition.
    m = build({}, parser(grammar, 'declaration')(s))

    # Interactive loop.
    while True:
        # Prompt the user for a query.
        s = input('> ') 
        if s == ':quit':
            break
        
        # Parse and evaluate the query.
        e = parser(grammar, 'expression')(s)
        if not e is None:
            print(evaluate(m, {}, e))
        else:
            print("Unknown input.")

#eof