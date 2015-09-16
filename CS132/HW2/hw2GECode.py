"""
    CS132 Homework 2
    Aleksander Skjoelsvik - U54 90 4431
"""

import numpy as np

def swapRows(A, i, j):
    """
    interchange two rows of A
    operates on A in place
    """
    tmp = A[i].copy()
    A[i] = A[j]
    A[j] = tmp

def rowReduce(A, i, j, pivot):
    """
    reduce row j using row i with pivot pivot, in matrix A
    operates on A in place
    """
    factor = A[j][pivot] / A[i][pivot]
    A[j] = A[j] - factor * A[i]

def nonZero(x):
    """
    return True if x is significantly different from zero
    defined as abs(x)>1.0e-10
    the value 1.0e-10 is somewhat arbitrary
    """
    return np.abs(x) > 1.0e-10

# stage 1 (forward elimination)
def forwardElimination(B):
    """
    Return the row echelon form of B
    """
    A = B.copy()
    m, n = np.shape(A)
    for i in range(m-1):
        # Let j be the position of the leftmost nonzero value in row i or any row below it
        # if there is no such position, stop
        leftmostNonZeroRow = m
        leftmostNonZeroCol = n
        ## for each row below row i (including row i)
        for h in range(i,m):
            ## search, starting from the left, for the first nonzero
            for k in range(i,n):
                if (nonZero(A[h][k]) and (k < leftmostNonZeroCol)):
                    leftmostNonZeroRow = h
                    leftmostNonZeroCol = k
                    break
        # If the jth position in row i is zero, swap this row with a row below it
        # to make the jth position nonzero. This creates a pivot in position i,j.
        # Note that leftmostNonZeroCol is j
        if (leftmostNonZeroRow > i):
            swapRows(A, leftmostNonZeroRow, i)
        # Use row reduction operations to create zeros in all posititions below the pivot.
        for h in range(i+1,m):
            rowReduce(A, i, h, leftmostNonZeroCol)
            # deal with numerical inaccuracy
            # because b - (a * b/a) is not always exactly zero
            A[h][leftmostNonZeroCol] = 0
    return A

#################### 

# If any operation creates a row that is all zeros except the last element,
# the system is inconsistent; stop.
def inconsistentSystem(B):
    """
    B is assumed to be in echelon form; return True if it represents
    an inconsistent system, and False otherwise
    """
    # Get the shape of the matrix
    m, n = np.shape(B)
    # Go through each Row
    for i in range(m):
        # Go through each Column
        for h in range(n):
            # Get if the current item is zero or not
            isNonZero = nonZero(B[i][h])
            # If it isn't zero, and it isn't the last item in the list
            if (isNonZero and (h < n-1)):
                # Break out of this Row
                break
            # If it hasn't broke out yet, that means the current item is zero or it's the last item
            # If it is the last item in the row, and it's not zero
            if ((h == (n-1)) and isNonZero):
                # Return true as it is inconsistent, all previous entries are 0 and this, the last, is not
                return True
    # If it hasn't returned True yet, that means it is a consistent system
    return False

def backsubstitution(B):
    """
    return the reduced row echelon form matrix of B
    """
    # Get a copy of the matrix
    A = B.copy()
    # Get the rows and columns
    m, n = np.shape(B)
    # Go through each row, starting at the bottom
    for i in range(m-1, -1, -1):
        # Set the pivot to 0.0 and location of pivot to -1.0
        pivot = 0.0
        pivotLocation = -1.0
        # Go through each column
        for h in range(n):
            # Get if the current item is zero or not
            isNonZero = nonZero(A[i][h])
            # If it's not zero and the pivot is (not set yet)
            if isNonZero and pivot == 0.0:
                # Save the current item as pivot, and it's location as pivotLocation
                pivot = A[i][h]       
                pivotLocation = h
            # If the pivot is not 0.0 (it has been set)
            if not(pivot == 0.0):
                # Divide the current item by the pivot
                A[i][h] = A[i][h] / pivot
                
        # Go through each row above the current row (going up)
        for j in range(i - 1, -1, -1):
            # Reduce the the row by the current row and the pivot location
            rowReduce(A, i, j, pivotLocation)
    # Return the matrix
    return A

#####################