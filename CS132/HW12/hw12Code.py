"""
    CS132 Homework 12
    Aleksander Skjoelsvik
"""

import numpy as np

def problem35():
    A = np.array([[-6,-3, 6, 1],
                  [-1, 2, 1,-6],
                  [ 3, 6, 3,-2],
                  [ 6,-3, 6,-1],
                  [ 2,-1, 2, 3],
                  [-3, 6, 3, 2],
                  [-2,-1, 2,-3],
                  [ 1, 2, 1, 6]])
    transpose = np.transpose(A)
    result = np.dot(A, transpose)
    print "Problem 35:"
    print "The columns are orthogonal if the off diagonal elements are equal"
    print result
    
def problem36():
    A = np.array([[-6,-3, 6, 1],
                  [-1, 2, 1,-6],
                  [ 3, 6, 3,-2],
                  [ 6,-3, 6,-1],
                  [ 2,-1, 2, 3],
                  [-3, 6, 3, 2],
                  [-2,-1, 2,-3],
                  [ 1, 2, 1, 6]])
    U = np.divide(A, np.linalg.norm(A))
    transpose = np.transpose(U)
    UTU = np.dot(transpose, U)
    UUT = np.dot(U, transpose)
    print "Problem 36a:"
    print "UTU:\n" + str(UTU)
    print "UUT:\n" + str(UUT)
    
    y = np.random.randint(-10, 10, size=(8, 4))
    p = np.dot(U, transpose)
    p = np.dot(p, y)
    z = np.subtract(y, p)
    print "\nProblem 36b:"
    print "p:\n" + str(p)
    print "z:\n" + str(z)
    
    """q = np.dot(transpose, z)
    print "\nProblem 36c:"
    print "z is orthogonal to each column of U if this number is 0: " + str(q)"""

def problem25():
    A = np.array([[-6,-3, 6, 1],
                  [-1, 2, 1,-6],
                  [ 3, 6, 3,-2],
                  [ 6,-3, 6,-1],
                  [ 2,-1, 2, 3],
                  [-3, 6, 3, 2],
                  [-2,-1, 2,-3],
                  [ 1, 2, 1, 6]])
    U = np.divide(A, np.linalg.norm(A))
    transpose = np.transpose(U)
    UUT = np.dot(U, transpose)
    y = np.array([1, 1, 1, 1, 1, 1, 1, 1])
    yHat = np.dot(UUT, y)
    print "Problem 25:"
    print "The closest point is:\n" + str(yHat)