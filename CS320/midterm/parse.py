#####################################################################
#
# CAS CS 320, Spring 2015
# Midterm (skeleton code)
# parse.py
# Aleksander Skjoelsvik - U54904431
#
#  ****************************************************************
#  *************** Modify this file for Problem #1. ***************
#  ****************************************************************
#

import re

def number(tokens, top = True):
    if re.compile(r"(-(0|[1-9][0-9]*)|(0|[1-9][0-9]*))").match(tokens[0]):
        return ({"Number": [int(tokens[0])]}, tokens[1:])

def variable(tokens, top = True):
    if re.compile(r"[a-z][A-Za-z0-9]*").match(tokens[0]) and tokens[0] not in ['true', 'false']:
        return ({"Variable": [tokens[0]]}, tokens[1:])

def expression(tmp, top = True):
    tokens = tmp[0:]
    r = expressionLeft(tokens)
    if not r is None:
        (e1, tokens) = r
        if len(tokens) > 0:
            if tokens[0] == '+':
                tokens = tokens[1:]
                r = expression(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if not top or len(tokens) == 0:
                        return ({'Plus':[e1, e2]}, tokens)

    if not top or len(tokens) == 0:
        return(r)
    return (None)

def expressionLeft(tmp):
    tokens = tmp[0:]
    if tokens[0] == 'true':
        return ('True', tokens[1:])

    tokens = tmp[0:]
    if tokens[0] == 'false':
        return ('False', tokens[1:])

    tokens = tmp[0:]
    r = number(tokens)
    if not r is None:
        (e1, tokens) = r
        return (e1, tokens)

    tokens = tmp[0:]
    r = variable(tokens)
    if not r is None:
        (e1, tokens) = r
        return (e1, tokens)

    tokens = tmp[0:]
    if tokens[0] == '@':
        tokens = tokens[1:]
        r = variable(tokens)
        if not r is None:
            (e1, tokens) = r
            if tokens[0] == '[':
                tokens = tokens[1:]
                r = expression(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if tokens[0] == ']':
                        tokens = tokens[1:]
                        return ({'Array':[e1, e2]}, tokens)

def program(tmp, top = True):
    tokens = tmp[0:]
    if len(tokens) > 0:
        r = variable(tokens)
        if not r is None:
            (e1, tokens) = r
            if tokens[0] == '=' and tokens[1] == '[':
                tokens = tokens[2:]
                r = expression(tokens, False)
                if not r is None:
                    (e2, tokens) = r
                    if tokens[0] == ',':
                        tokens = tokens[1:]
                        r = expression(tokens, False)
                        if not r is None:
                            (e3, tokens) = r
                            if tokens[0] == ',':
                                tokens = tokens[1:]
                                r = expression(tokens, False)
                                if not r is None:
                                    (e4, tokens) = r
                                    if tokens[0] == ']' and tokens[1] == ';':
                                        tokens = tokens[2:]
                                        r = program(tokens, False)
                                        if not r is None:
                                            (e5, tokens) = r
                                            if not top or len(tokens) == 0:
                                                return ({'Assign':[e1, e2, e3, e4, e5]}, tokens)

    tokens = tmp[0:]
    if len(tokens) > 0:
        if tokens[0] == 'print':
            tokens = tokens[1:]
            r = expression(tokens, False)
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
        if tokens[0] == 'for':
            tokens = tokens[1:]
            r = variable(tokens)
            if not r is None:
                (e1, tokens) = r
                if tokens[0] == '{':
                    tokens = tokens[1:]
                    r = program(tokens, False)
                    if not r is None:
                        (e2, tokens) = r
                        if tokens[0] == '}':
                            tokens = tokens[1:]
                            r = program(tokens)
                            if not r is None:
                                (e3, tokens) = r
                                if not top or len(tokens) == 0:
                                    return ({'For':[e1, e2, e3]}, tokens)

    tokens = tmp[0:]
    if len(tokens) == 0 or tokens[0] == '}':
        return ('End', tokens)
    return (None)

def tokenizeAndParse(s):
    tokens = re.split(r"(\s+|=|print|@|\+|for|{|}|;|\[|\]|,)", s)
    tokens = [t for t in tokens if not t.isspace() and not t == ""]
    if not tokens is None:
        (e1, tokens) = program(tokens)
        return e1

#eof