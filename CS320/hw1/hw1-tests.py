
import re

############################################################
# Load the file. Change the path if necessary.
exec(open('hw1.py').read())

def check(name, function, inputs_result_pairs):
    def str_(s):
        return '"'+str(s)+'"' if type(s) == str else str(s)
    if type(name) == tuple:
        prefix = name[0]
        suffix = name[1]
    if type(name) == str:
        prefix = name + '('
        suffix = ')'

    passed = 0
    for (inputs, result) in inputs_result_pairs:
        try:
            output = function(inputs[0], inputs[1]) if len(inputs) == 2  else function(inputs[0])

            # If the result includes the token list, take only the parse tree.
            output = output[0] if type(output) == tuple else output
        except:
            output = 'Error'

        if output != 'Error' and output == result:
            passed = passed + 1
        else:
            print("\n  Failed on:\n    "+prefix+', '.join([str_(i) for i in inputs])+suffix+"\n\n"+"  Should be:\n    "+str(result)+"\n\n"+"  Returned:\n    "+str(output)+"\n")
    print("Passed " + str(passed) + " of " + str(len(inputs_result_pairs)) + " tests.")
    print("")

############################################################
# The tests.

print("Problem #1, part (a)...")
try: tokenize
except: print("The tokenize() function is not defined.")
else:
    check('tokenize', tokenize, [\
        ([['token'], "token token   token token   "], ['token', 'token', 'token', 'token']),\
        ([['example','test'], "example test example example"], ['example', 'test', 'example', 'example']),\
        ([['a','b','c','d','e',','], "a,b,c,d,e,d,c,b,a"], ['a', ',', 'b', ',', 'c', ',', 'd', ',', 'e', ',', 'd', ',', 'c', ',', 'b', ',', 'a']),\
        ([['up','down','left','right','finish',';'], "left; right; finish;"], ['left', ';', 'right', ';', 'finish', ';']),\
        ([['+','*','(',')','f','x','y','z'], "f(x+y)*z"], ['f','(','x','+','y',')','*','z'])\
        ])

print("\n\nProblem #1, part (b)...")
try: (tokenize, transformation)
except: print("The tokenize() and/or transformation() functions are not defined.")
else:
    check(('''transformation(tokenize(["projection","reflection","left","right","rotation","finish",";"], ''', '''))'''), (lambda s: transformation(tokenize(["projection","reflection","left","right","rotation","finish",";"], s))), [\
        (["finish ;"], 'Finish'),\
        (["projection ; reflection ; projection ; finish ;"], {'Projection': [{'Reflection': [{'Projection': ['Finish']}]}]}),\
        (["finish;"], 'Finish'),\
        (["projection; reflection; projection; finish;"], {'Projection': [{'Reflection': [{'Projection': ['Finish']}]}]}),\
        (["reflection; projection; left rotation; right rotation; finish;"], {'Reflection': [{'Projection': [{'LeftRotation': [{'RightRotation': ['Finish']}]}]}]}),\
        (["right rotation; reflection; left rotation; right rotation; reflection; projection; left rotation; right rotation; reflection; left rotation; right rotation; reflection; projection; left rotation; finish;"], {'RightRotation': [{'Reflection': [{'LeftRotation': [{'RightRotation': [{'Reflection': [{'Projection': [{'LeftRotation': [{'RightRotation': [{'Reflection': [{'LeftRotation': [{'RightRotation': [{'Reflection': [{'Projection': [{'LeftRotation': ['Finish']}]}]}]}]}]}]}]}]}]}]}]}]}]}),\
        ])

print("\n\nProblem #2, part (c)...")
try: formula
except: print("The formula() function is not defined.")
else: check('formula', formula, [\
    (["false".split(' ')], 'False'),\
    (["not ( true )".split(' ')], {'Not': ['True']}),\
    (['and ( true , false )'.split(' ')], {'And': ['True', 'False']}),\
    (['and ( and ( true , false ) , and ( true , false ) )'.split(' ')], {'And': [{'And': ['True', 'False']}, {'And': ['True', 'False']}]}),\
    (['not ( and ( and ( true , not ( false ) ) , and ( true , false ) ) )'.split(' ')], {'Not': [{'And': [{'And': ['True', {'Not': ['False']}]}, {'And': ['True', 'False']}]}]}),\
    (['compare ( # 12 , # 34 )'.split(' ')], {'Compare': [{'Number': [12]}, {'Number': [34]}]}),\
    (['and ( and ( compare ( # 12 , # 34 ) , false ) , and ( less than ( @ foo , @ bar ) , false ) )'.split(' ')], {'And': [{'And': [{'Compare': [{'Number': [12]}, {'Number': [34]}]}, 'False']}, {'And': [{'LessThan': [{'Variable': ['foo']}, {'Variable': ['bar']}]}, 'False']}]})\
    ])

print("\n\nProblem #2, part (e)...")
try: complete
except: print("The complete() function is not defined.")
else: check('complete', complete, [\
    (["end ;"], 'End'),\
    (["print true ; end ;"], {'Print': ['True', 'End']}),\
    (["print true ; print # 123 ; end ;"], {'Print': ['True', {'Print': [{'Number': [123]}, 'End']}]}),\
    (["assign @ x = # 1 ; end ;"], {'Assign': [{'Variable': ['x']}, {'Number': [1]}, 'End']}),\
    (["end;"], 'End'),\
    (["print true; end;"], {'Print': ['True', 'End']}),\
    (["assign @x=#1;end;"], {'Assign': [{'Variable': ['x']}, {'Number': [1]}, 'End']}),\
    (["print true; print #123; end;"], {'Print': ['True', {'Print': [{'Number': [123]}, 'End']}]}),\
    (["print true; print #123; assign @x = #123; end;"], {'Print': ['True', {'Print': [{'Number': [123]}, {'Assign': [{'Variable': ['x']}, {'Number': [123]}, 'End']}]}]}),\
    (["print not(true); print add(abs(#4),#99); assign @x = #123; end;"], {'Print': [{'Not': ['True']}, {'Print': [{'Add': [{'Abs': [{'Number': [4]}]}, {'Number': [99]}]}, {'Assign': [{'Variable': ['x']}, {'Number': [123]}, 'End']}]}]}),\
    (["print not(true); assign @y = #10; print add(abs(#4),@y); assign @x = #123; print compare(@x,@y); end;"], {'Print': [{'Not': ['True']}, {'Assign': [{'Variable': ['y']}, {'Number': [10]}, {'Print': [{'Add': [{'Abs': [{'Number': [4]}]}, {'Variable': ['y']}]}, {'Assign': [{'Variable': ['x']}, {'Number': [123]}, {'Print': [{'Compare': [{'Variable': ['x']}, {'Variable': ['y']}]}, 'End']}]}]}]}]}),\
    (["assign @y = #100; assign @x = add(subtract(abs(@y),#20),#30); print and(less than(@x, @y), or(compare(@y, #200), false)); end;"], {'Assign': [{'Variable': ['y']}, {'Number': [100]}, {'Assign': [{'Variable': ['x']}, {'Add': [{'Subtract': [{'Abs': [{'Variable': ['y']}]}, {'Number': [20]}]}, {'Number': [30]}]}, {'Print': [{'And': [{'LessThan': [{'Variable': ['x']}, {'Variable': ['y']}]}, {'Or': [{'Compare': [{'Variable': ['y']}, {'Number': [200]}]}, 'False']}]}, 'End']}]}]}),\
    (["assign @a = #1; assign @b = abs(add(subtract(abs(@a),#2),#3)); print not(greater than(abs(add(subtract(abs(@a),#2),#3)),abs(add(subtract(abs(@a),#2),#3)))); print and(or(true,false), not(true)); end;"], {'Assign': [{'Variable': ['a']}, {'Number': [1]}, {'Assign': [{'Variable': ['b']}, {'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}, {'Print': [{'Not': [{'GreaterThan': [{'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}, {'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}]}]}, {'Print': [{'And': [{'Or': ['True', 'False']}, {'Not': ['True']}]}, 'End']}]}]}]}),\
    ])

print("\n\nProblem #3...")
try: complete
except: print("The complete() function is not defined.")
else: check('complete', complete, [\
    (["print ( # 2 + @ x ) ; end ;"], {'Print': [{'Add': [{'Number': [2]}, {'Variable': ['x']}]}, 'End']}),\
    (["print (#2 - @x) ; end ;"], {'Print': [{'Subtract': [{'Number': [2]}, {'Variable': ['x']}]}, 'End']}),\
    (["print (#2 = @x) ; end ;"], {'Print': [{'Compare': [{'Number': [2]}, {'Variable': ['x']}]}, 'End']}),\
    (["print (#2 < @x) ; end ;"], {'Print': [{'LessThan': [{'Number': [2]}, {'Variable': ['x']}]}, 'End']}),\
    (["print (#2 > @x) ; end ;"], {'Print': [{'GreaterThan': [{'Number': [2]}, {'Variable': ['x']}]}, 'End']}),\
    (["print (true && false) ; end ;"], {'Print': [{'And': ['True', 'False']}, 'End']}),\
    (["print (true || not(false)) ; end ;"], {'Print': [{'Or': ['True', {'Not': ['False']}]}, 'End']}),\
    (["print not(true); assign @y = #10; print (abs(#4) + @y); assign @x = #123; print (@x = @y); end;"], {'Print': [{'Not': ['True']}, {'Assign': [{'Variable': ['y']}, {'Number': [10]}, {'Print': [{'Add': [{'Abs': [{'Number': [4]}]}, {'Variable': ['y']}]}, {'Assign': [{'Variable': ['x']}, {'Number': [123]}, {'Print': [{'Compare': [{'Variable': ['x']}, {'Variable': ['y']}]}, 'End']}]}]}]}]}),\
    (["assign @y = #100; assign @x = add(subtract(abs(@y),#20),#30); print and((@x < @y), or((@y = #200), false)); end;"], {'Assign': [{'Variable': ['y']}, {'Number': [100]}, {'Assign': [{'Variable': ['x']}, {'Add': [{'Subtract': [{'Abs': [{'Variable': ['y']}]}, {'Number': [20]}]}, {'Number': [30]}]}, {'Print': [{'And': [{'LessThan': [{'Variable': ['x']}, {'Variable': ['y']}]}, {'Or': [{'Compare': [{'Variable': ['y']}, {'Number': [200]}]}, 'False']}]}, 'End']}]}]}),\
    (["assign @a = #1; assign @b = abs(((abs(@a)-#2)+#3)); print not((abs(((abs(@a) - #2) + #3))>abs(add(subtract(abs(@a),#2),#3)))); print and(or(true,false), not(true)); end;"], {'Assign': [{'Variable': ['a']}, {'Number': [1]}, {'Assign': [{'Variable': ['b']}, {'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}, {'Print': [{'Not': [{'GreaterThan': [{'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}, {'Abs': [{'Add': [{'Subtract': [{'Abs': [{'Variable': ['a']}]}, {'Number': [2]}]}, {'Number': [3]}]}]}]}]}, {'Print': [{'And': [{'Or': ['True', 'False']}, {'Not': ['True']}]}, 'End']}]}]}]}),\
    ])

#eof