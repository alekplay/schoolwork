""" 
	CS131 - Lapets
	Homework 6
	Aleksander Skjoelsvik
	Contributors: Raph Baysa
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
        while y >= 1: (r,y) = (r*x, y-1)
        return r

x = G()
        
# Task 1:

oneA = factorial(7) // factorial(3)

oneB = factorial(8) // (factorial(2) * factorial(2) * factorial(2) * factorial(2))

oneC = factorial(10) // (factorial(6) * factorial (4))

oneD = factorial(15) // (factorial (10) * factorial(5))

oneE = (factorial(7) // (factorial(4) * factorial(3))) * (factorial(5) // (factorial(3) * factorial(2)))

oneF = (factorial(7) // (factorial(5) * factorial(2))) + (factorial(7) // (factorial(4) * factorial(3))) + (factorial(7) // (factorial(3) * factorial(4))) + (factorial(7) // (factorial(2) * factorial(5)))

oneG = ((factorial(9) // factorial(6) * factorial(3)) + (factorial(9) // factorial(5) * factorial(4)) + (factorial(9) // factorial(4) * factorial(5))) * ((factorial(4) // factorial(2) * factorial(2)) + (factorial(4) // factorial(1) * factorial(3)) + 1)

# Task 2:

twoA = ((1+x)**6)[4]

twoB = (((x**2) + (x**4) + (x**6) + (x**8) + (x**10))**4)[20]

twoC = (sum((((x**2) + (x**3) + (x**5) + (x**7))**i) for i in range(4, 11)))[20]

"""
twoC = ((10 * (x**2) + (x**3) + 4 * (x**5) + (x**7))**20)[20]
"""

twoD = (sum(x**i for i in range(0,50))**5)[158]

twoE = (sum(x**i for i in range(1, 101))**5)[406]

twoF = (((x) + (x**2) + (x**3))**3)[7]

# Task 3:

def sequences(cs):
    Set = set(cs)
    y = 1
    for ch in Set:
        y = y * factorial(cs.count(ch))
    return factorial(len(cs)) // y

# Task 4:

def solutions (k, n):
  return (factorial(n - 1) // ((factorial(n - k) * factorial(k - 1))))

"""
def solutions(k, n):
	return ((factorial(k + n - 1)) // (factorial((k + n - 1) - (k-1)) * factorial(k-1)))
"""

# Task 5:

def hgtToSizes(h):
	if h == 1:
    	return x
	else:
    	return ((hgtToSizes(h - 1)**1 + hgtToSizes(h - 1)**2 + hgtToSizes(h - 1)**3) * x)

"""
def hgtToSizes(h):
    g = x
    for i in range(1,h):
        g = g + g**2 + g**3
    return g
"""