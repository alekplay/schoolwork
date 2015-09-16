"""
    CS132 Homework 9
    Aleksander Skjoelsvik
"""

import numpy as np

# FROM HOMEWORK 2
def backsubstitution(B):
    A = B.copy()
    m, n = np.shape(B)
    for i in range(m-1, -1, -1):
        pivot = 0.0
        pivotLocation = -1.0
        for h in range(n):
            isNonZero = nonZero(A[i][h])
            if isNonZero and pivot == 0.0:
                pivot = A[i][h]       
                pivotLocation = h
            if not(pivot == 0.0):
                A[i][h] = A[i][h] / pivot
                
        for j in range(i - 1, -1, -1):
            rowReduce(A, i, j, pivotLocation)
    return A
    

def rowReduce(A, i, j, pivot):
    factor = A[j][pivot] / A[i][pivot]
    A[j] = A[j] - factor * A[i]
    
def nonZero(x):
    return np.abs(x) > 1.0e-10

def problem38():
    A = np.array([[5,-2,2,-4],\
                  [7,-4,2,-4],\
                  [4,-4,2,0],\
                  [3,-1,1,-3]])
    eigVals = np.linalg.eigvals(A)
    for eigVal in eigVals:
        print("Eigenvalue: " + str(eigVal))
        B = backsubstitution(np.subtract(A, (eigVal*np.eye(4,4))))
        print(B)