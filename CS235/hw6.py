"""
    CS235 Fall 2014 - Homework 6
    Aleksander Skjoelsvik - U54904431
"""

from math import floor
from fractions import gcd
from random import randint

# Task 1:

"""
    a. 8x = 16 (mod 32)
       
       gcd(8, 32) = 8
       
       x = 2 (mod 4)

    b. x = 8 (mod 12)
       x = 4 (mod 16)

       gcd(12, 16) = 4 -> 8 = 4 (mod 4) -> Solution exists
       
       4z = 8 (mod 12) -> z = 2 (mod 3) -> z = 2(4 * (4^(-1))) + 1(3 * (3^(-1))) = 2 * 4 * 1 + 1 * 3 * 3 = 17
       4z = 4 (mod 16) -> z = 1 (mod 4) ->
       
       x = g * z = 4 * 17 = 68 (mod ((12 * 16)/4))
       x = 20 (mod 48)

    c. x = 10 (mod 14)
       x = 17 (mod 21)
       
       gcd(14, 21) = 7 -> 10 = 17 (mod 7) -> Solution exists
       
       y + 3 = 10 (mod 14) -> y = 7 (mod 14) -> 7z = 7 (mod 14) -> z = 1 (mod 2)
       y + 3 = 17 (mod 21) -> y = 14 (mod 21)-> 7z = 14 (mod 21)-> z = 2 (mod 3)
       
       z = 1(3 * (3^(-1))) + 2(2 * (2^(-1))) = 3 + 8 = 11
       
       x = g * z + r = 7 * 11 + 3 = 80 (mod ((14 * 21)/7))
       x = 38 (mod 42)

    d. (x^2) = 4 (mod 35)
       3x = 15 (mod 21)
       
       ---
       
       (x^2) = 4 (mod 35) -> (x^2) = 4 (mod 7) -> x = 2 (mod 7)
                                                  x = 5 (mod 7)
                             (x^2) = 4 (mod 5) -> x = 3 (mod 5)
                                                  x = 2 (mod 5)
                                                  
       x = 2 (mod 7) -> x = 2(5 * 5^(-1)) + 3(7 * 7^(-1)) = 2*5*3 + 3*7*3 = 23 (mod 35)
       x = 3 (mod 5) ->
       
       x = 2 (mod 7) -> x = 2(5 * 5^(-1)) + 2(7 * 7^(-1)) = 2*5*3 + 2*7*3 = 2 (mod 35)
       x = 2 (mod 5) ->
       
       x = 5 (mod 7) -> x = 5(5 * 5^(-1)) + 3(7 * 7^(-1)) = 5*5*3 + 3*7*3 = 33 (mod 35)
       x = 3 (mod 5) ->
       
       x = 5 (mod 7) -> x = 5(5 * 5^(-1)) + 2(7 * 7^(-1)) = 5*5*3 + 2*7*3 = 12 (mod 35)
       x = 2 (mod 5) ->
       
       ---
       
       3x = 15 (mod 21) -> x = 5 (mod 7)
       
       ---
       
       x = 23 (mod 35) -> 23 != 5 (mod 7) -> No solution!
       x =  5 (mod  7) ->
       
       x = 2 (mod 35) -> 2 != 5 (mod 7) -> No solution!
       x = 5 (mod  7) ->
       
       x = 33 (mod 35) -> 33 = 5 (mod 7) -> Solution exists
       x =  5 (mod  7) ->
       
       x = 12 (mod 35) -> 12 == 5 (mod 7) -> Solution exists
       x =  5 (mod  7) ->
       
       ---
       
       y + 5 = 33 (mod 35) -> y = 28 (mod 35) -> 7z = 28 (mod 35) -> z = 4 (mod 5)
       y + 5 =  5 (mod  7) -> y =  0 (mod  5) -> 7z =  0 (mod  5) -> z = 0 (mod 1)
       
       z = 4(1 * 1^(-1)) + 0(5 * 5^(-1)) = 4*1*1 + 0 = 4
       
       x = g * z + r = 7 * 4 + 5 = 33 (mod (35*7)/7)
       x = 33 (mod 35)
       
       y + 5 = 12 (mod 35) -> y =  7 (mod 35) -> 7z =  7 (mod 35) -> z = 1 (mod 5)
       y + 5 =  5 (mod  7) -> y =  0 (mod  5) -> 7z =  0 (mod  5) -> z = 0 (mod 1)
       
       z = 1(1 * 1^(-1)) + 0(5 * 5^(-1)) = 1*1*1 + 0 = 1
       
       x = g * z + r = 7 * 1 + 5 = 12 (mod (35 * 7)/7)
       x = 12 (mod 35)
       
       ---
       
       x = 33 (mod 35)
       x = 12 (mod 35)
       
    e. 2x = 12 (mod 26)
    
       gcd(2, 26) = 2
       
       x = 6 (mod 13)
       x = 19 (mod 26)

    f. {0}
       {0, 4}
       {0, 2, 4, 6}
       {0, 1, 2, 3, 4, 5, 6, 7}
"""

# Task 2:

"""
    a. The alarm rang at midnight exactly 27 hours ago. It is 3am.
    
       I got this answer by drawing a clock, and tracing back 11h from 3,
       reaching 4. Tracing back another 16 hours gave me 12, but this could
       be both midnight and noon. Tracing back another 16 hours did not give
       me midnight, but I have now gone back more than two days. Since the
       alarm only rang once at 12 during the past two days, I know that it
       must have been midnight. Tracing forward until present time gives
       me that it's 3am currently.
       
       EDIT:
       You wanted an explanation with an equation, so here it is:
       x = 11 mod 16    (Currently 11 hours "into" the 16 cycle)
       x =  3 mod 12    (Currently 3 o'clock on a 12 hour clock)
       ....             (solve)
       x = 27 (mod 48)  (27 hours out of a possible 48)
       27 mod 24 = 3    (Mod by 24 (24 hour clock), gives us 3, which is 3am)
       
    b. Based on the data given, we can logically figure out that the best
       way of finding the number of problems is to find the number of machines
       used in each case that gives the same amount of problems. We can set up
       a set of equations like so:

       12y + 2 = x
       15z + 8 = x

       With some trial, error, and basic logic, we figure out that with
       y = 3 and z = 2 we get x = 38 in both cases. This means that there are
       38 problems.
       
       If he uses two machines, both of them would be capable of solving 19 problems 
       bc 19 + 19 = 38
       
       EDIT:
       Came up with something more "scientifical":
       x = 2 mod 12     (2 on one machine, 12 on the rest)
       x = 8 mod 15     (8 on one machine, 15 on the rest)
       ...              (solve)
       x = 38 mod 60    (At most 38 problems (when at most 60))

    c. When modding, you "chop off" the last part of the password. She mods 2^8, which leaves the last 8 bits of the string unknown. You cannot do anything useful with the information in mod 2^4, as you're only left with less of the same information as in mod 2^8.
        Effectively, she will then have to try the remaining 2^8 possible values for the password (8 * 2 = 16, which is the length of the password)
"""

# Task 3:

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

def solveOne(a, b, n):
    g = gcd(a, n)
    if (b % g == 0):
        a = a // g
        b = b // g
        n = n // g
        aInv = inv(a, n)
        x = (aInv * b) % n
        return x
    return None

def solveTwo(e1, e2):
    (a1, b1, n1) = e1
    (a2, b2, n2) = e2
    x1 = solveOne(a1, b1, n1)
    x2 = solveOne(a2, b2, n2)
    if (x1 != None and x2 != None):
        n1 = n1 // gcd(n1, a1)
        n2 = n2 // gcd(n2, a2)
        
        g = gcd(n1, n2)
        if (x1 % g == x2 % g):
            r = x1 % g
            y1 = x1 - r
            y2 = x2 - r
            N1 = n1 // g
            N2 = n2 // g
            z1 = (y1 // g) % N1
            z2 = (y2 // g) % N2
            N1Inv = inv(N1, N2)
            N2Inv = inv(N2, N1)
            z = (z1 * (N2 * N2Inv)) + (z2 * (N1 * N1Inv))
            x = (g * z + r) % ((n1 * n2) // g)
            return x
        
    return None

def solveAll(es):
    while len(es) > 1:
        (a1, b1, n1) = es.pop()
        (a2, b2, n2) = es.pop()
        result = solveTwo((a1, b1, n1), (a2, b2, n2))
        es.append((1, result, ((n1 * n2) // gcd(n1, n2))))
    (a, b, n) = es.pop()
    return b

# Task 4:

from random import randint
def plus256unreliable(x, y):
    r = randint(0,7) - 4
    return (min(255, max(0, ((x + y)%256) + r)))

def plus16(x, y):
    return (plus256unreliable(plus256unreliable(x * 16, y * 16), 8) // 16)

def plus256(x, y):
    consts = [3, 5, 7, 16]
    result = [(1, plus16(x % i, y % i), i) for i in consts]
    return solveAll(result) % 256
