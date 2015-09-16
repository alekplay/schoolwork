"""
    CS235 Homework 3
    Aleksander Skjoelsvik
"""

from math import floor
from fractions import gcd

#Task 1:

"""
    a:
        4x = 2 (mod 11)
        4x = 24 (mod 11)
        
        x = 6 + 11Z
    b:
        x = 3 (mod 7)
        x = 1 (mod 5)

        x = 31 + 35Z
    c:
        x = 2 (mod p)
        x = 4 (mod q)

        x = 2(q * q^(-1)) + 4(p * p^(-1)) (mod (p*q))
          = 2 * q * 3 + 4 * p * 5
          = 6q + 20p (mod pq)
    d:
        x = 4 (mod 5)
        x = 3 (mod 14)

        x = 59 + 70Z
        (range x E Z/70Z)
"""

#Task 2:

def invPrime(a, p):
    if a == 0:
        return None
    else:
        return pow(a, p-2, p)

def egcd(a, b):
    (x, s, y, t) = (0, 1, 1, 0)
    while b != 0:
        k = a // b
        (a, b) = (b, a % b)
        (x, s, y, t) = (s - k*x, x, t - k*y, y)
    return (s, t)

def inv(a, m):
    if gcd(a, m) == 1:
        (s, t) = egcd(a, m)
        return s % m
    else:
        return None

#Task 3:

def solveOne(c, a, m):
    if gcd(c, m) == 1:
        return a * inv(c, m) % m
    else:
        return None

def solveTwo(e1, e2):
    (c, a, m) = e1
    (d, b, n) = e2
    x1 = solveOne(c, a, m)
    x2 = solveOne(d, b, n)
    (u, v) = egcd(m, n)
    part1 = (m * u) * x2
    part2 = (n * v) * x1
    if ((x1 == None) or (x1 == None) or (gcd(n, m) != 1)):
        return None
    else:
        return (part1 + part2) % (m * n)

def solveAll(es):
    while len(es) > 1:
        (c, a, m) = es.pop()
        (d, b, n) = es.pop()
        result = solveTwo((c, a, m), (d, b, n))
        es.append((1, result, m*n))
    (c, a, m) = es.pop()
    return a

#Task 4:

""" A:
def sumOfPowers(nes, ps):
    answers = []
    for p in ps:
        answers += [(1, sum([pow(n, e, p) for (n, e) in nes]), p)]
    return solveAll(answers)
B: """ 

def sumOfPowers(nes, ps):
    answers = []
    for p in ps:
        answers += [(1, sum([pow(n, e % (p-1), p) for (n, e) in nes]), p)]
    return solveAll(answers)
