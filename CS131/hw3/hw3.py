""" 
	CS131 - Lapets
	Homework 3
	Aleksander Skjoelsvik 
	Contributors: 
"""

# Task 1 (theorem, proof, or neither):

oneA = 'Proof'
oneB = 'Theorem'
oneC = 'Proof'
oneD = 'Neither'
oneE = 'Proof'
oneF = 'Proof'
oneG = 'Neither'

# Task 2:

def valid(axioms, proof):
	for a in range(0, len(proof)):
		check = 0
		for b in range(0, len(axioms)):
			if proof[a] == axioms[b]:
				check = check + 1
		for c in range(0, a):
			if proof[a] == proof[c]:
				check = check + 1
		if type(proof[a-1]) == list:
			if (proof[a-2] == proof[a-1][0] and proof[a] == proof[a-1][1]):
				check = check + 1
			elif (proof[a] == proof[a-1][0] and proof[a-2] == proof[a-1][1]):
				check = check + 1
		if check == 0:
			return False
	return True
	
# Task 3:

axiomsThree = [\
    ['user can log in as admin', 'user can change admin password'],\
    ['user can own files', 'user can delete files'],\
    ['user can log in as admin', 'user can set security policy'],\
    ['user can log in as admin', 'user can see files'],\
    ['user can set security policy', 'user can set file ownership'],\
    ['user knows admin password', 'user can log in as admin'],\
    ['user can set file ownership', 'user can own files'],\
    'user knows admin password'\
]

proofThree =[\
	'user knows admin password',\
	['user knows admin password', 'user can log in as admin'],\
	'user can log in as admin',\
	['user can log in as admin', 'user can set security policy'],\
	'user can set security policy',\
	['user can set security policy', 'user can set file ownership'],\
	'user can set file ownership',\
	['user can set file ownership', 'user can own files'],\
	'user can own files',\
	['user can own files', 'user can delete files'],\
	'user can delete files'\
]


















