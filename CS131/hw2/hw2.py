""" 
	CS131 - Lapets
	Homework 2
	Aleksander Skjoelsvik - U54904431
	Contributors: 
"""

# Task 1 (only 'and', 'not'):

def oneA(x, y):
	return (not(not(x) and (y)))
	
def oneB(x, y):
	return (not(not(x) and (y)))
	
def oneC(x, y, z):
	return 	(not(not(not(x and y)) and not(z)))

def oneD(w, x, y, z):
	return (not(not(x) and not(y))) and (not(not(w) and not(z)))
	 
def oneE(w, x, y, z):
	return (not(not(not (w)) and (x) and (y and z)))
	
def oneF(v, w, x, y, z):
	return not(not(not(not(v)) and (w)) and (x) and (y) and not(z))
				

# Task 2:

U = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'}
X = {'a', 'b', 'c'}
Y = {'c', 'd', 'e', 'f'}
Z = {'f', 'g'}

twoA = Z.intersection(Y)
twoB = X.difference(Y)
twoC = (Y.difference(X)).difference(Z)
twoD = U.difference(Y)
twoE = (Y.difference(Z).union(U.difference((X.union(Y)).union(Z))))
twoF = U.difference((X.intersection(Y)).union(Z.intersection(Y)))
twoG = X.difference(U)

# Task 3:

def threeA(U, X, Y):
	return X.difference(U.difference(Y)) 
	
def threeB(U, X, Y):
	return U.difference(X.union(Y))
	
def threeC(U, X, Y, Z):
	return U.difference(Y.difference(U.difference(Z)))
	
def threeD(U, X, Y, Z):
	return (X.union(Z)).difference((X.union(Z)).difference(Y))
	
# Task 4:

def variables(f):
	return list(f.__code__.co_varnames)
    
def universe(n, values = tuple()):
	if len(values) == n:
		return {values}
	else:
		return universe(n, values + (True,)) | universe(n, values + (False,))

def models(f):
	M = set()
	for x in universe(len(variables(f))):
		if(f(*x)) == True:
			M.add(x)
	return M
	
def satisfies(S, f):
	for x in S:
		if f(*x) == False:
			return False
	return True
	
def equivalent(f, g):
	if models(f) == models(g):
		return True
	else:
		return False

def both(f, g):
	return models(f).intersection(models(g))
	
def either(f, g):
	return models(f).union(models(g))
	
def neither(f, g):
	return (universe(f).union(universe(g)).difference(either(f,g))
	
# Task 5:

def possible(A2, A4, B1, B3, C1, C5, D2, D3, E4, E5):
	return (A2 != D2) and (A4 != E4) and (B1 != C1) and (B3 != D3) and (C5 != E5) and (A2 != A4) and (B1 != B3) and (C1 != C5) and (D2 != D3) and (E4 != E5)
	
schedules = len(models(possible))