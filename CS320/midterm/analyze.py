#####################################################################
#
# CAS CS 320, Spring 2015
# Midterm (skeleton code)
# analyze.py
#
#  ****************************************************************
#  *************** Modify this file for Problem #5. ***************
#  ****************************************************************
#

exec(open("parse.py").read())

Node = dict
Leaf = str

def typeExpression(env, e):
    if type(e) == Leaf:
        if e == 'True':
            return 'Boolean'
        if e == 'False':
            return 'Boolean'
    if type(e) == Node:
        for label in e:
            children = e[label]
            if label == 'Number':
                return 'Number'
            elif label == 'Variable':
                x = children[0]['Variable'][0]
                if x in env and env[x] == 'Number':
                    return 'Number'
            elif label == 'Plus':
                [e1, e2] = children
                if typeExpression(env, e1) == 'Number' and typeExpression(env, e2) == 'Number':
                    return 'Number'
            elif label == 'Array':
                [x, e] = children
                x = x['Variable'][0]
                if x in env and env[x] == 'Array' and typeExpression(env, e) == 'Number':
                    return 'Number'
            return None

def typeProgram(env, s):
    if type(s) == Leaf:
        if s == 'End':
            return 'Void'
    elif type(s) == Node:
        for label in s:
            if label == 'Print':
                [e, p] = s[label]
                if typeProgram(env, p) == 'Void':
                    if typeExpression(env, e) == 'Boolean' or\
                       typeExpression(env, e) == 'Number':
                        return 'Void'
            elif label == 'Assign':
                [x, e0, e1, e2, p] = s[label]
                x = x['Variable'][0]
                if typeExpression(env, e0) == 'Number' and\
                   typeExpression(env, e1) == 'Number' and\
                   typeExpression(env, e2) == 'Number':
                     env[x] = 'Array'
                     if typeProgram(env, p) == 'Void':
                           return 'Void'
            elif label == 'For':
                [x, p1, p2] = s[label]
                x = x['Variable'][0]
                env[x] = 'Number'
                if typeProgram(env, p1) == 'Void' and typeProgram(env, p2) == 'Void':
                    return 'Void'
            return None

#eof