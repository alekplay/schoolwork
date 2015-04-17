"""
    CS235 Homework #1
    Aleksander Skjoelsvik - U54904431    
"""

def quotient(X, R):
    return {frozenset({y for y in X if (x,y) in R}) for x in X}

# TASK 1A:

def relation(R, X):
  return subset(R, product(X, X))

def product(X, Y):
  return { (x,y) for x in X for y in Y }

def subset(X,Y):
  return forall(X, lambda x: x in Y)

def forall(X, P):
    S = {x for x in X if P(x)}
    return len(S) == len(X)

def isSymmetric(X, R):
    return relation(R, X) and forall(X, lambda a: forall(X, lambda b: ((a,b) in R) <= ((b,a) in R)))

# TASK 1B:

def isReflexive(X, R):
    return relation(R, X) and forall(X, lambda a: (a, a) in R)

def isTransitive(X, R):
    return relation(R, X) and forall(X, lambda a: forall(X, lambda b: forall(X, lambda c: ((a, b) in R and (b, c) in R) <= ((a, c) in R)))) 

def isEquivalence(X, R):
    return isSymmetric(X, R) and isReflexive(X, R) and isTransitive(X, R)

#TASK 2A:

X1 = {"a","b","c","d"}
R1 = {('a', 'a'),('b', 'b'),('a', 'b'),('b', 'a'),('c', 'c'),('d', 'd'),('c', 'd'), ('d', 'c')}

#TASK 2B:

X2 = {0, 1, 2, 3, 4, 5}
R2 = {(0,0), (3, 3), (0, 3), (3, 0), (1, 1), (4, 4), (1, 4), (4, 1), (2, 2), (5, 5), (2, 5), (5, 2)}

#TASK 2C:

def products(X):
    return {(x, y) for x in X for y in X}

X3 = set(range(-1000, 1001))
R3 = products(range(-1000, 0)) | {(0,0)} | products(range(1,1001))

#TASK 3A:

def exists(X, P):
  S = {x for x in X if P(x)}
  return len(S) > 0

def square(n) :
    return exists(set(range(0, n)), lambda k: k**2 == n)

#TASK 3B:

def prime(p):
    return  p > 1 and forall(set(range(2, p)), lambda n: p % n != 0)

def properPrimeFactors(n):
    return { x for x in range(2, n) if n % x == 0 and prime(x) }

#TASK 3C:

def disjoint(S):
    return {(x, y) for x in S for y in S if forall(properPrimeFactors(x), lambda a: a not in properPrimeFactors(y))}

#TASK 3D:

reflexive = {12, 24, 48, 96}
symmetric = None
transitive = {5,6,7,8,9}
