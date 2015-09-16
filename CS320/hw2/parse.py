"""
    CS320 - Homework 2
    Aleksander Skjoelsvik - U54904431
"""

# Task 1
import re
def number(tokens):
    if re.match(r"^[0-9]+$", tokens[0]):
        return (int(tokens[0]), tokens[1:])
    else:
        return (None)

def variable(tokens):
    if re.match(r'[a-z]+', tokens[0]):
        return (tokens[0], tokens[1:])
    else:
        return (None)

def formula(tmp, top = True):
    tokens = tmp[0:]
    r = left(tokens)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) > 0:
            if tokens[0] == 'xor':
                tokens = tokens[1:]
                r = formula(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if not top or len(tokens) == 0:
                        return ({'Xor':[e1, e2]}, tokens)
    if not top or len(tokens) == 0:
        return (r) 
    return (None)

def left(tokens):
    if tokens[0] == 'true':
        return ('True', tokens[1:])
    if tokens[0] == 'false':
        return ('False', tokens[1:])
    if tokens[0] == 'bool' and tokens[1] == '(':
        tokens = tokens[2:]
        r = term(tokens, False)
        if not r is None:
            (e1, tokens) = r
            if tokens[0] == ')':
                tokens = tokens[1:]
                return ({'Bool': [e1]}, tokens)
    
    r = variable(tokens)
    if not r is None:
        (e1, tokens) = r
        return ({'Variable': [e1]}, tokens)

def term(tmp, top = True):
    tokens = tmp[0:]
    r = factor(tokens, False)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) > 0:
            if tokens[0] == '+':
                tokens = tokens[1:]
                r = term(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if not top or len(tokens) == 0:
                        return ({'Add': [e1, e2]}, tokens)
    if not top or len(tokens) == 0:
        return (r)
    return (None)

def factor(tmp, top = True):
    tokens = tmp[0:]
    r = leftFactor(tokens)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) > 0:
            if tokens[0] == '*':
                tokens = tokens[1:]
                r = factor(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if not top or len(tokens) == 0:
                        return ({'Multiply':[e1, e2]}, tokens)
    if not top or len(tokens) == 0:
        return (r)
    return (None)

def leftFactor(tokens):
    if tokens[0] == 'int' and tokens[1] == '(':
        tokens = tokens[2:]
        r = formula(tokens, False)
        if not r is None:
            (e1, tokens) = r
            if tokens[0] == ')':
                tokens = tokens[1:]
                return ({'Int':[e1]}, tokens)
    if tokens[0] == '(':
        tokens = tokens[1:]
        r = term(tokens, False)
        if not r is None:
            (e1, tokens) = r
            if tokens[0] == ')':
                tokens = tokens[1:]
                return ({'Parens':[e1]}, tokens)
    r = variable(tokens)
    if not r is None:
        (e1, tokens) = r
        return ({'Variable':[e1]}, tokens)
    r = number(tokens)
    if not r is None:
        (e1, tokens) = r
        return ({'Number':[e1]}, tokens)

def program(tmp, top = True):
    tokens = tmp[0:]
    if len(tokens) > 0:
        if tokens[0] == 'print':
            tokens = tokens[1:]
            r = expression(tokens)
            if not r is None:
                (e1, tokens) = r
                if tokens[0] == ';':
                    tokens = tokens[1:]
                    r = program(tokens, False)
                    if not r is None:
                        (e2, tokens) = r
                        if not top or len(tokens) == 0:
                            return ({'Print':[e1, e2]}, tokens)

    tokens = tmp[0:]
    if len(tokens) > 0:
        if tokens[0] == 'assign':
            tokens = tokens[1:]
            r = factor(tokens, False)
            if not r is None:
                (e1, tokens) = r
                if tokens[0] == '=':
                    tokens = tokens[1:]
                    r = expression(tokens)
                    if not r is None:
                        (e2, tokens) = r
                        if tokens[0] == ';':
                            tokens = tokens[1:]
                            r = program(tokens, False)
                            if not r is None:
                                (e3, tokens) = r
                                if not top or len(tokens) == 0:
                                    return ({'Assign':[e1, e2, e3]}, tokens)

    tokens = tmp[0:]
    if len(tokens) > 0:
        if tokens[0] == 'if':
            tokens = tokens[1:]
            r = expression(tokens)
            if not r is None:
                (e1, tokens) = r
                if tokens[0] == '{':
                    tokens = tokens[1:]
                    r = program(tokens, False)
                    if not r is None:
                        (e2, tokens) = r
                        if tokens[0] == '}':
                            tokens = tokens[1:]
                            r = program(tokens, False)
                            if not r is None:
                                (e3, tokens) = r
                                if not top or len(tokens) == 0:
                                    return ({'If':[e1, e2, e3]}, tokens)

    tokens = tmp[0:]
    if len(tokens) > 0:
        if tokens[0] == 'while':
            tokens = tokens[1:]
            r = expression(tokens)
            if not r is None:
                (e1, tokens) = r
                if tokens[0] == '{':
                    tokens = tokens[1:]
                    r = program(tokens, False)
                    if not r is None:
                        (e2, tokens) = r
                        if tokens[0] == '}':
                            tokens = tokens[1:]
                            r = program(tokens, False)
                            if not r is None:
                                (e3, tokens) = r
                                if not top or len(tokens) == 0:
                                    return ({'While':[e1, e2, e3]}, tokens)
    
    tokens = tmp[0:]
    if len(tokens) == 0 or tokens[0] == '}':
        return ('End', tokens)
    return (None)

def expression(tmp):
    tokens = tmp[0:]
    r = formula(tokens, False)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) == 0 or tokens[0] == ';' or tokens[0] == '{':
            return (e1, tokens)
    
    tokens = tmp[0:]
    r = term(tokens, False)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) == 0 or tokens[0] == ';' or tokens[0] == '{':
            return (e1, tokens)