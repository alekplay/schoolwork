"""
    CS131 - Lapets
    Homework 7
    Aleksander Skjoelsvik
    Contributors:
"""

from math import factorial
from fractions import Fraction

class G():
    def __init__(self, g = {1: 1}): self.g = g
    def __getitem__(self, i): return self.g.get(i,0)
    def k(self): return list(self.g.keys())
    def __repr__(self): return self.__str__()
    def __str__(g): return " + ".join([str(g[i])+"*x**"+str(i) for i in g.g])
    def __add__(x, y): return G({i:x[i]+y[i] for i in x.k()+y.k()})
    def __radd__(x, y):
        r = {i:x[i] for i in x.g}
        r[0] = r.get(0,0) + y
        return G(r)
    def __mul__(x, y):
        g = {}
        for i in x.g:
            for j in y.g: g[i+j] = g.get(i+j,0) + x[i]*y[j]
        return G(g)
    def __rmul__(x, y): return G({i:y*x[i] for i in x.g})
    def __pow__(x, y, r = 1):
        if x.g == {1:1} and y <= 0: return G({y:1})
        while y >= 1: (r,y) = (r*x, y-1)
        return r

x = G()

# Task 1:

oneA = 'fox'
oneB = 'frog'
oneC = [{'is red','is green'},{'can eat','cannot eat'},{'can fly','cannot fly'}]
oneD = [\
    {frozenset({'is red', 'is green'}),frozenset({'can eat', 'cannot eat'})},\
    {frozenset({'is red', 'is green'}),frozenset({'can fly', 'cannot fly'})},\
    {frozenset({'can eat', 'cannot eat'}),frozenset({'can fly', 'cannot fly'})}\
    ]
oneE = Fraction(2,6)
oneF = Fraction(4,6) * Fraction(3,6)
oneG = Fraction(3,6) + Fraction(2,6)
oneH = Fraction(2,3)
oneI = (Fraction(4,6) * Fraction(1,3)) + (Fraction(2,6) * Fraction(2,3))

# Task 2:

twoA = 3
twoB = ((Fraction(1,3)**twoA)*2) + ((Fraction(1,3)**(twoA+1))*2)
twoC = 1 - twoB - (Fraction(1,3)**(twoA+2))

# Task 3:

def probHgtAtTime(h, t):
    g = x
    for i in range(1, t):
        g = (Fraction(1,3) * x) + (Fraction(2,3) * g * x)
    return g[h]
    

# Task 4:

def moreB(t):
    cellA = x
    cellB = x
    for i in range(1, t):
        cellA = (Fraction(1,3) * cellA) + (Fraction(2,3) * (cellA**2))
        cellB = (Fraction(2,3) * cellB) + (Fraction(1,3) * (cellB**3))

    probability = 0
    for i in range(1, (3**t + 1)):
        for j in range (1, (2**t + 1)):
            if i < j:
                probability = probability + (cellB[i] * cellA[i])
    return probability
    

# Task 5:

def probInRange(c, p, t, a, b):
    g = (((1 - p) * (x**(-c))) + (p * (x**c)))**t
    ans = sum(g[i] for i in range(a, (b + 1)))
    return ans

#def minAlloc(c, p, t):
    
