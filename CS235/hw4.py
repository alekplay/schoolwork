"""
    CS235 Homework 4 - Fall 2014
    Aleksander Skjoelsvik - U54904431
"""

from math import floor
from fractions import gcd
from random import randint

# Helper methods from previous homework

def invPrime(a, p):
    return pow(a, p - 2, p) if a % p != 0 else None

def solveOne(c,a,m):
    i = inv(c, m)
    return (i * a) % m if not i is None else None

def solveTwo(e1, e2):
    (c, a, m) = e1
    (d, b, n) = e2
    a = solveOne(c, a, m)
    b = solveOne(d, b, n)
    (s, t) = egcd(m, n)
    return (a * t * n + b * s * m) % (m * n)


def isPrime(p):
    if (p == 2): return True
    if (not(p&1)): return False
    return pow(2, p-1, p) == 1


# Task 1:

"""
    a.

    b.  x^2 = 1 (mod 33)
        
        x = +/- 1 (mod 33)
        
        x = 1 + 33Z
        x = 32 + 33Z
        
    c.

    d.

    e.
"""

# Task 2:

def factorsFromPhi(n, phi_n):
    x = n - phi_n + 1
    quad = floor(pow((pow(x, 2) - 4*n), 0.5))
    q = (x - quad) // 2
    p = (x + quad) // 2
    return (q, p)

def factorsFromRoots(n, x, y):
    q = abs(gcd(n, x - y))
    p = abs(gcd(n, x + y))
    return (q, p)

def phiFromPhiFour(n) :
    x = randPrime()
    y = randPrime()
    z = n * x * y
    return (phi_four(z) / ((x - 1) * (y - 1)))

def phi_four(n):
    return len({k for k in range(1,n+1) if gcd(k,n) == 1})

# Task 3:
              
def generate(k):
    p = 0
    q = 0
    
    while(not(isPrime(p))):
        p = randint(pow(10, k-1), pow(10, k))

    while(not(isPrime(q))):
        q = randint(pow(10, k-1), pow(10, k))

    n = p * q

    phi_n = (p - 1) * (q - 1)

    e = 1
    while((gcd(e, phi_n) != 1) or e < 2):
        e =  randint(2, phi_n-1)

    d = pow(e, -1) % phi_n
    
    return (n, e, d)

def encrypt(m, t):
    (n, e) = t
    c = pow(m, e) % n
    return c

def decrypt(c, t):
    (n, d) = t
    m = pow(c, d) % n
    return m

# Task 4:

def inv(a, m):
    (s,t) = egcd(a, m)
    return s % m if gcd(a,m) == 1 else None

def sqrtsPrime(a, p):
    if (p % 4 != 3):
        return None
    
    x = pow(a, (p + 1) // 4, p)

    if (pow(x, 2, p) != (a % p)):
        return None
    
    return (p - x, x)

def sqrtsPrimePower(a, p, k):
    if (k == 1):
        return sqrtsPrime(a, p)

    (x, y) = sqrtsPrimePower(a, p, k-1)
    c = (invPrime(x, p) * invPrime(2, p) * ((a - pow(x, 2)) // pow(p, k - 1))) % p
    ans = (x + c * pow(p, k-1)) % pow(p, k)
    return (ans, pow(p, k) - ans)

def sqrts(a, pks):
    res = set()
    n = 1
    for (p, k) in pks:
        if (n == 1):
            (x, y) = sqrtsPrimePower(a, p, k)
            res.add(x)
            res.add(y)
            n = n * pow(p, k)
        else:
            (x, y) = sqrtsPrimePower(a, p, k)
            res2 = set()
            for e in res:
                res2.add(solveTwo((1, x, pow(p, k)), (1, e, n)))
                res2.add(solveTwo((1, y, pow(p, k)), (1, e, n)))
            res = res2
            n = n * pow(p, k)
    return res
        

# Task 5:

""" NO IDEA
def roots(a, n):
    return 0
"""
