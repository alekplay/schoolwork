""" 
	CS131 - Lapets
	Homework 1
	Aleksander Skjoelsvik
	Contributors: (Task 3b - jonrsharpe - stackoverflow), (Task 4a - Raphael Baysa)
"""

# Task 1:

A = (False <= (True or False))

B = ((True <= (True or False)) != False)

C = ((True != (not(True))) == (not(True)))

D = ((True == False) != (False == True))

E = ((True <= False) and ((not(False)) <= (not(True))))

# Task 2:

def a(x, y):
	return (x <= y)
	
def b(x, y, z):
	return (x == (y != z))
	
def c(x, y):
	return (((x <= y) and x) <= y)
	
def d(x, y):
	return ((x == y) <= ((not(x)) == (not(y))))

def e(x, y, w, v, z):
	return (((x <= y) or (w <= v)) <= ((x and w) <= (z and (y and v))))
	
# Task 3:

def prints(values):
	print("\t".join([str(value) for value in values]))
	
def variables(f):
	return list(f.__code__.co_varnames)
	
def f(x, y):
	return x and y
	
# Task 3a:
	
def truthtableXY(f):
	prints(['y', 'x', 'formula'])
	for x in [True, False]:
		for y in [True, False]:
			prints([x, y, f(x,y)])
			
# Task 3b:

def truthtable(f, values = None):
    if (values is None):
        prints(variables(f) + ["formula"])
        values = []
    if (len(values) == len(variables(f))):
        prints(values + [f(*values)])
    else:
        for b in [True, False]:
            truthtable(f, values + [b])
	
# Task 3c:

def rows(f):
	return 2 ** len(variables(f))
	
# Task 4:

def count(values):
	return len([v for v in values if v == True])
	
# Task 4a:

def solve(f, values = []):
	if(len(values) < len(variables(f))):
		true_results = solve(f, values+[True])
    	false_results = solve(f, values+[False])
    	
	if (len(values) == len(variables(f))):
		if(f(*values) == True):
			return values
		else: 
			return None
	
    if (count(true_reults) >= count(false_results)):
    	return true_results
    else:
    	return false_results
			
# Task 4b:

def transport(rhino, lion, zebra, hyena, snake):
	return ((snake <= hyena) and (lion <= hyena) and (rhino <= snake) and (lion <= snake) and (lion <= zebra) and (snake <= zebra))
	maximum = solve(transport())
	# Unsure whether I was gonna put this as a variable here, or a function. Did both
	
def maximum():
	return solve(transport())