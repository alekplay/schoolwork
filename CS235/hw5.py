"""
    CS235 Homework 5 - Fall 2014
    Aleksander Skjoelsvik - U54904431
"""

from math import floor
from fractions import gcd
from random import randint
from urllib.request import urlopen

# Task 1

"""
    a. [2, 3, 4, 0, 1] o [4, 0, 1, 2, 3]
       2 E Z/5Z          4 E Z/5Z
       2 + 4 = 6 = 1 E Z/5Z
       [1, 2, 3, 4, 0]

    b. [23, 24, ..., 99, 0, 1, ..., 22] o [57, 58, ..., 99, 0, 1, ..., 56]
       23 E Z/100Z                         57 E Z/100Z
       23 + 57 = 80
       [80, 81, ..., 99, 0, 1, ..., 79]

    c. p o p o p o p
       (p o p) o (p o p)
       [0, 1, ..., 6] o [0, 1, ..., 6]
       [0, 1, ..., 6]

    d. p o q o p^(-1) o q^(-1)
       (p o p^(-1)) o (q o q^(-1))
       [0, 1, ..., 12]

    e. [0, 1] - 1
       [1, 0] - 3

       [0, 1] o [0, 1] = [0, 1]  1 * 1 = 1
       [0, 1] o [1, 0] = [1, 0]  1 * 3 = 3
       [1, 0] o [0, 1] = [1, 0]  3 * 1 = 3
       [1, 0] o [1, 0] = [0, 1]  3 * 3 = 1

    f. (closure({2, + 15Z}, ⋅), ⋅) = (1, 2, 4, 8)
       (Z/4Z, +) = (0, 1, 2, 3)

       Each element (n) from (Z/4Z, +) corresponds to an element (m) in the closure by 2^n = m
       2^0 = 1, 2^1 = 2, 2^2 = 4, 2^3 = 8
       Each element (m) from the closure corresponds to an element (n) in (Z/4z, +) by log_2(m) = n
       log_2(1) = 0, log_2(2) = 1, log_2(4) = 2, log_2(8) = 3
       
    g. The set (S_5, o) has 5! = 120 permutations, but the other set doesn't have that many.
       Because they aren't the same size, we can't have a bijection, and therefore not a isomophism 
"""

# Task 2

def permute(p, l):
    return [l[p[i]] for i in range(0, len(p))]

def C(k, m):
    return [(i + k) % m for i in range(0, m)]

def M(a, m):
    return [(i * a) % m for i in range(0, m)]

def sort(l):
    size = len(l)
    sortedList = sorted(l)
    
    for i in range(0, size):
        if permute(C(i, size), l) == sortedList:
            return C(i, size)
        
    for i in range(0, size):
        if permute(M(i, size), l) == sortedList:
            return M(i, size)
        
    return None

# Task 3

def unreliableUntrustedProduct(xs, n):
    url = 'http://cs-people.bu.edu/lapets/235/reliable.php'
    return int(urlopen(url+"?n="+str(n)+"&data="+",".join([str(x) for x in xs])).read().decode())

def privateProduct(xs, p, q):
    n = p * q
    phi_n = (p - 1) * (q - 1)
    e = phi_n - 1
    d = pow(e, phi_n - 1)

    c = [pow(xs[i], e, n) for i in range(0, len(xs))]
    r = unreliableUntrustedProduct(c, n)
    m = pow(r, d, n)
    return pow(m, 1, p)

def validPrivateProduct(xs, p, q):
    check =  True
    answer = 0
    while(check):
        r = randint(0, q)
        pInv = pow(p, q-2)
        qInv = pow(q, p-2)
        xsNew = [pow(((xs[i] * (q*qInv)) + (r * (p*pInv))), 1, p * q) for i in range(0, len(xs))]

        n = p * q
        phi_n = (p-1) * (q-1)
        e = phi_n - 1
        d = pow(e, phi_n - 1)

        c = [pow(xsNew[i], e, n) for i in range(0, len(xsNew))]
        r = unreliableUntrustedProduct(c, n)
        m = pow(r, d, n)

        mNew = m * (q * qInv) + m * (p * pInv)
        decryptedVal = pow(m, 1, p)
        if (pow(r, len(xs), q) == decryptedVal):
            answer = decryptedVal
            check = False
    return answer

# Task 4

def isomorphism(A, B):
    funcA = A[1]
    funcB = B[1]
    
    list = []
    for i in range(0, len(A[0])):
        list.append((A[0][i], B[0][i]))

    list2 = []
    for i in range(0, len(list)):
        for j in range(0, len(list)):
            a = list[i][0]
            b = list[i][1]
            aPrime = list[j][0]
            bPrime = list[j][1]
            list2.append((funcA(a, aPrime), funcB(b, bPrime)))

    for i in range(0, len(list2)):
        if not(list2[i] in list):
            return False
    return True
