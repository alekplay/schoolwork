"""
    CS132 - Homework 6 - Aleksander Skjoelsvik
"""
import numpy as np

def problem35():
    a = np.zeros((5, 4))
    b = np.ones((5, 3))
    c = np.identity(5)
    d = np.diagflat([[3,4], [2,5]])
    print("Problem 35a: \n" + str(a))
    print("Problem 35b: \n" + str(b))
    print("Problem 35c: \n" + str(c))
    print("Problem 35d: \n" + str(d))
    
def problem36():
    a = np.random.rand(5, 6)
    b = np.random.randint(-9, 9, (4,4))
    print("Problem 36 5x6 rand entries: \n" + str(a))
    print("Problem 35 4x4 rand entries between -9 and 9: \n" + str(b))
    
def problem37():
    isEqual = False
    for i in range(3):
        A = np.random.rand(4, 4)
        B = np.random.rand(4, 4)
        result = np.subtract(np.dot(A,B), np.dot(B,A))
        if not result.any():
            isEqual = True
            print("Problem 37: \n Matricies #" + str(i+1) + "are equal")
    if isEqual:
        print("Problem 37: \n At least one is equal")
    else:
        print("Problem 37: \n None are not equal")
        
def problem38():
    for i in range(3):
        A = np.random.randint(-10, 10, (5,5))
        B = np.random.randint(-10, 10, (5,5))
        I = np.identity(5)
        result1 = np.subtract(np.dot(np.add(A,I),np.subtract(A,I)),np.subtract(np.dot(A,A),I))
        result2 = np.subtract(np.dot(np.add(A,B),np.subtract(A,B)),np.subtract(np.dot(A,A),B))
        if not result1.any():
            print("Problem 38: \n Identity matrix #" + str(i+1) + " conforms to the formula")
        else:
            print("Problem 38: \n Identity matrix #" + str(i+1) + " does not conform to the formula")
        if not result2.any():
            print("Problem 38: \n #" + str(i+1) + " The two matricies make the zero matrix")
        else:
            print("Problem 38: \n #" + str(i+1) + " The two matricies does not make the zero matrix")

def problem41():
    aMatrix = np.array([[4.5,3.1],[1.6,1.1]])
    aNotRounded = np.array([[19.249,6.843]])
    aRounded = np.array([[19.25,6.84]])
    aResultRounded = np.linalg.solve(aMatrix, aRounded)
    aResultNotRounded = np.linalg.solve(aMatrix,aNotRounded)
    print("Problem 41a rounded: \n" + str(aResultRounded))
    print("Problem 41a not rounded: \n" + str(aResultNotRounded))
    
    percentageError = abs((aResultRounded[0]-aResultNotRounded[0])/aResultNotRounded[0])*100
    print("Problem 41b: \n Percentage error:" + str(percentageError))
    
    c = np.linalg.cond(aMatrix)
    print("Problem 41c: \n " + str(c))

def problem42():
    A = np.array([[4,0,-3,-7],[-6,9,9,9],[7,-5,10,19],[-1,2,4,-1]])
    aCond = np.linalg.cond(A)
    x = np.random.randint(-10,10,(4,1))
    b = np.dot(A,x)
    x1 = np.linalg.solve(A,b)
    
    print("Problem 42: \n Condition Number: " + str(aCond))
    print("Problem 42: Random vector \n" + str(x))
    print("Problem 42: Random vector computed \n" + str(x1))
    print("Problem 42: Difference \n" + str(np.subtract(x,x1)))
    print("Problem 42: \n It stores number of digits accuratly: " + str(np.finfo('float').precision))
    
def problem6():
    A = np.array([[4.5,3.0937],[1.6,1.1]])
    aCond = np.linalg.cond(A)
    x = np.random.randint(-10,10,(2,1))
    b = np.dot(A,x)
    x1 = np.linalg.solve(A,b)
    
    print("Problem 6: \n Condition Number: " + str(aCond))
    print("Problem 6: Random vector \n" + str(x))
    print("Problem 6: Random vector computed \n" + str(x1))
    print("Problem 6: Difference \n" + str(np.subtract(x,x1)))
    
def program():
    print("Computational part of Homework 6")
    print("\nRUNNING PROBLEM 35")
    problem35()
    print("\n\nRUNNING PROBLEM 36")
    problem36()
    print("\n\nRUNNING PROBLEM 37")
    problem37()
    print("\n\nRUNNING PROBLEM 38")
    problem38()
    print("\n\nRUNNING PROBLEM 41")
    problem41()
    print("\n\nRUNNING PROBLEM 42")
    problem42()
    print("\n\nRUNNING PROBLEM 6")
    problem6()
    
program()