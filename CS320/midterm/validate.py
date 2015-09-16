#####################################################################
#
# CAS CS 320, Spring 2015
# Midterm (skeleton code)
# validate.py
#
#  ****************************************************************
#  *************** Modify this file for Problem #4. ***************
#  ****************************************************************
#

exec(open('interpret.py').read())
exec(open('compile.py').read())
exec(open('analyze.py').read())

def expressions(n):
    if n <= 0:
        []
    elif n == 1:
        return ['True', 'False', {'Number':[2]}] # Add base case(s) for Problem #5.
    else:
        es = expressions(n-1)
        esN = []
        esN += [{'Array':[{'Variable':['a']}, e]} for e in es]
        
        return es + esN

def programs(n):
    if n <= 0:
        []
    elif n == 1:
        return ['End']
    else:
        ps = programs(n-1)
        es = expressions(n-1)
        psN = []
        psN += [{'Assign':[{'Variable':['a']}, e, e, e, p]} for p in ps for e in es]
        psN += [{'Print':[e, p]} for p in ps for e in es]
        psN += [{'For':[{'Variable':['x']}, p, p]} for p in ps]
                
        return ps + psN

# We always add a default assignment to the program in case
# there are variables in the parse tree returned from programs().

def defaultAssigns(p):
    return \
      {'Assign':[\
        {'Variable':['a']}, {'Number':[2]}, {'Number':[2]}, {'Number':[2]}, p\
      ]}

# Compute the formula that defines correct behavior for the
# compiler for all program parse trees of depth at most 4.
# Any outputs indicate that the behavior of the compiled
# program does not match the behavior of the interpreted
# program.

for p in [defaultAssigns(p) for p in programs(3)]:
    try:
        if not typeProgram({}, p) is None:
            if simulate(compileProgram({}, unrollLoops(p))[1]) != convert(execute({}, p)[1]):
                print('\nIncorrect behavior on: ' + str(p))
            else:
                print('\nCorrect output!')
    except:
        print('\nError on: ' + str(p))

#eof