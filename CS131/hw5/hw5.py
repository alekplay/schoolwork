""" 
	CS131 - Lapets
	Homework 5
	Aleksander Skjoelsvik
	Contributors: 
"""

from math import log

# Task 1:

def check(f, g):
	for i in range (1, 101):
		if(f(i) != g(i)):
			return False
	return True
	
# Task 2:

def twoA(x):
	return (3 + (4 * x))
	
def twoB(x):
	return ((3 * (x**2)) - (3 * x) + 4)

def twoC(x):
	return (1/3) * (4**(1+log(x,2))-1)
	
def twoD(x):
	return ((10 * x) - 10)
	
def twoE(x):
    return 5**(x-1)
	
def twoF(x):
	return (x**2) - 1
	
def twoG(x):
	return (3**(x-1)) + (3**(x-1)-1)
	
# Task 3:

def threeA(n):
	if n == 1:
		return 1
	elif n > 1:
		return threeA(n-1) + 13

def threeB(n):
	if n == 1:
		return 1
	elif n > 1:
		return threeB(n-1) + n
		
def threeC(n):
	if n == 1:
		return 1
	elif n > 1:
		return threeC(n-1) + (3 * n + 3)
		
def threeD(n):
	if n == 1:
		return 1
	elif n > 1:
		return 6 * threeD(n - 1) + 1
		
def threeE(n):
	if n == 1:
		return 1
	elif n > 1:
		return 26 * threeE(n - 1)
		
def threeF(n):
	if n == 1:
		return 1
	elif n > 1:
		return 3 * threeF(n - 1)
	
def threeG(n):
        if n == 1:
                return 1
        elif n > 1:
                return int(threeG(n / 3) + n)
		
# Task 4:

def fourA(x):
	return (13 * x) - 12	
	
def fourB(x):
	return int((x * (x + 1)) / 2)

def fourC(x):
	return int(3 * ((((x + 1) * x) / 2) - 1) + (3 * (x - 1)) + 1)

def fourD(x):
	return 6**(x-1) + ((6**(x-1) - 1)//5)

def fourE(x):
	return 26**(x - 1)
	
def fourF(x):
	return 3**(x - 1)
	
def fourG(x):
	return int((3**(log(x, 3) + 1)) // 2)
