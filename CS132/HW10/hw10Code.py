# -*- coding: utf-8 -*-
"""
Created on Mon Apr 13 20:06:09 2015

@author: Aleksander
"""

import numpy as np

P = np.array([[1./500, 1./500, 124./125, 1./500, 1./5],
              [83./250, 1./500, 1./500, 497./1000, 1./5],
              [83./250, 1./500, 1./500, 497./1000, 1./5],
              [83./250, 497./1000, 1./500, 1./500, 1./5],
              [1./500, 497./1000, 1./500, 1./500, 1./5]])
A = np.array([[1./6, 149./300, 299./1200, 1./600, 1./600, 1./6],
              [1./6, 1./600, 299./1200, 1./600, 1./600, 1./6],
              [1./6, 149./300, 1./600, 149./300, 1./600, 1./6],
              [1./6, 1./600, 299./1200, 1./600, 149./300, 1./6],
              [1./6, 1./600, 299./1200, 149./300, 1./600, 1./6],
              [1./6, 1./600, 1./600, 1./600, 149./300, 1./6]])
arrays = [P, A]              

for array in arrays:
    print 'arrray = \n{}'.format(array)
    
    eigenvalues, eigenvectors = np.linalg.eig(array)
    indices = np.argsort(eigenvalues)
    principal = indices[-1]
    steadyState = eigenvectors[:,principal]
    steadyState = np.divide(steadyState, np.sum(steadyState))
    reverseOrder = np.argsort(steadyState)
    order = 1+reverseOrder[::-1]

    print 'final order = {}'.format(order)
    print 'importance = {}\n'.format(steadyState[order-1])