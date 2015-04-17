"""
    CS235 Homework 2
    Aleksander Skjoelsvik - U54904431
"""

from fractions import gcd
from math import log

#TASK 1:

"""
    a:
        x = -6(mod 11)
        x = 5(mod 11)
        x = 5 + 11Z
    b:
        2x = -5(mod 13)
        2x = 8(mod 13)
        x = 4(mod 13)
        x = 4 + 13Z
    c:
        5x + 7 = 13(mod 29)
        5x = 6(mod 29)
        5x = 35(mod 29)
        x = 7(mod 29)
        x = 7 + 29Z
    d:
        48x = 4(mod 12)
        No solution
    e: 
        10x - 3 = 3(mod 5)
        10x = 6(mod 5)
        No solution
    f:
        4x + 1 = 8(mod 16)
        4x = 7(mod 16)
        No solution
    g:
        13x + 19 = 188(mod 401)
        13x = 169(mod 401)
        x = 13(mod 401)
        x = 13 + 401Z
    h:
        650472472230302x = 1(mod 8910581811374)
        No solution
    i:
        146467848x = 43698243047256(mod 7777777777777777777777777)
        x = 298347(mod 7777777777777777777777777)
        x = 298347 + 7777777777777777777777777Z
"""

#TASK 2:

def closest(t, ks):
    closestNum = ks[0]
    for x in ks:
        if abs(x - t) < abs(closestNum - t):
            closestNum = x
    return closestNum

#TASK 3:

def findCoprime(m):
    p = (m // 3) if (m // 3) > 3 else 3
    while (gcd((p - 1), m) != 1):
        p = p * gcd(p - 1, m)
    return p - 1

def randByIndex(m, i):
    a = findCoprime(m)
    return (a * i) % m

#TASK 4:

def probablePrime(m):
    for x in range(1, round(log(m, 3))):
        a = randByIndex(m, x)
        if m % a == 0 and a > 1: return False
        if gcd(a, m) != 1 and a > 1: return False
        if pow(a, m-1, m) != 1 and a > 1: return False
    return True

#TASK 5:

def makePrime(m):
    a = pow(10, m) - 1
    while not(probablePrime(a)):
        a = a - 1
    return a
