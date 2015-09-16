"""
    CS132 Homework 4
    Aleksander Skjoelsvik
"""

import numpy as np

def innerProduct(a,b):
    finalSum = 0                    # This will hold the final sum
    for i in range(len(a)):         # Go through each entry in the vector (assuming they're of equal length)
        finalSum += a[i] * b[i]     # Multiply the two entries with eachother, and add to the final sum
    return finalSum                 # Return the final sum

def AxIP(A,x):
    B = np.zeros(len(A))                    # Create an empty array of same size
    for i in range(len(A)):                 # Go through each row
        B[i] = innerProduct(A[i, :], x)     # Get the inner product of the current row and vector, and add to new array
    return B                                # Return new array

def AxVS(A,x):
    B = np.zeros(len(A))            # Create an empty array of same size
    for i in range(len(x)):         # Go  through each column
        B += x[i]  * A[:, i]        # Multiply the column by a scalar, and add to the final array
    return B                        # Return new array