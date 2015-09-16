import re

############################################################
# Load the files. Change the path if necessary.
exec(open('parse.py').read())
exec(open('interpret.py').read())

def check(name, function, inputs_result_pairs):
    def str_(s): return '"'+str(s)+'"' if type(s) == str else str(s)
    if type(name) == tuple:
        prefix = name[0]
        suffix = name[1]
    if type(name) == str:
        prefix = name + '('
        suffix = ')'
    
    passed = 0
    for (inputs, result) in inputs_result_pairs:
        try:
            if len(inputs) == 1:
                output = function(inputs[0])
            if len(inputs) == 2:
                output = function(inputs[0], inputs[1])
            if len(inputs) == 3:
                output = function(inputs[0], inputs[1], inputs[2])
        except:
            output = None

        if output == result: passed = passed + 1
        else: print("\n  Failed on:\n    "+prefix+', '.join([str_(i) for i in inputs])+suffix+"\n\n"+"  Should be:\n    "+str(result)+"\n\n"+"  Returned:\n    "+str(output)+"\n")
    print("Passed " + str(passed) + " of " + str(len(inputs_result_pairs)) + " tests.")
    print("")

############################################################
# The tests.

print("Problem #1, part (a), subst()...")
try: subst
except: print("The subst() function is not defined.\n")
else: check('subst', subst, [\
    ([{"x":{"Number":[5]}}, {"Variable":['x']}], {"Number":[5]}),\
    ([{"y":{"Number":[2]}}, {"Plus":[{"Variable":['y']}, {"Variable":['y']}]}], {"Plus":[{"Number":[2]}, {"Number":[2]}]}),\
    ([{"a":{"Number":[1]}, "b":{"Number":[2]}}, {"Mult":[{"Variable":['y']}, {"Variable":['y']}]}], {"Mult":[{"Variable":['y']}, {"Variable":['y']}]}),\
    ([{"a":{"Number":[1]}, "b":{"Number":[2]}}, {"Mult":[{"Plus":[{"Variable":['y']}, {"Variable":['y']}]}, {"Variable":['b']}]}], {"Mult":[{"Plus":[{"Variable":['y']}, {"Variable":['y']}]}, {"Number":[2]}]}),\
    ([{"z":{"Number":[2]}}, {"Plus":[{"Variable":['y']}, {"Variable":['z']}]}], {"Plus":[{"Variable":['y']}, {"Number":[2]}]})\
    ])

print("Problem #1, part (b), unify()...")
try: unify
except: print("The unify() function is not defined.\n")
else: check(('unifying... ', ' ...'), lambda s1,s2: unify(parser(grammar, 'expression')(s1), parser(grammar, 'expression')(s2)), [\
    (["x", "5"], {"x":{"Number":[5]}}),\
    (["x", "5 + 5"], {"x":{'Plus': [{'Number': [5]}, {'Number': [5]}]}}),\
    (["a + b", "5 + 5"], {"a":{'Number': [5]}, "b":{'Number': [5]}}),\
    (["c", "Test"], {"c":{'ConBase': ['Test']}}),\
    (["Node x y", "Node Leaf Leaf"], {"x":{'ConBase': ['Leaf']}, "y":{'ConBase': ['Leaf']}}),\
    (["Node (Node x y) Leaf", "Node (Node Leaf Leaf) Leaf"], {"x":{'ConBase': ['Leaf']}, "y":{'ConBase': ['Leaf']}}),\
    (["Node (Node a b) (Node x y)", "Node (Node (Node Leaf Leaf) Leaf) (Node Leaf Leaf)"], {"a":{'ConInd': ['Node', {'ConBase': ['Leaf']}, {'ConBase': ['Leaf']}]}, "b":{'ConBase': ['Leaf']}, "x":{'ConBase': ['Leaf']}, "y":{'ConBase': ['Leaf']}}),\
    (["5", "5"], {}),\
    (["Leaf", "Leaf"], {}),\
    (["Node (Node Leaf Leaf) Leaf", "Node (Node Leaf Leaf) Leaf"], {}),\
    (["And True False", "And True False"], {}),\
    (["Or x y", "Or True False"], {"x":{'ConBase': ['True']}, "y":{'ConBase': ['False']}})\
    ])

print("Problem #2, part (b), evaluate()...")
try: evaluate
except: print("The evaluate() function is not defined.\n")
else: check(('evaluating... ', ' ...'), (lambda d,e: evaluate(build({}, parser(grammar, 'declaration')(d)), {}, parser(grammar, 'expression')(e))), [\
    (["f(Leaf) = Leaf;", "f(Leaf)"], {'ConBase': ['Leaf']}),\
    (["f(x) = Test;", "f(Test)"], {'ConBase': ['Test']}),\
    (["f(Node t1 t2) = True; f(Leaf) = False;", "f(Leaf)"], {'ConBase': ['False']}),\
    (["f(Node t1 t2) = g(True); f(Leaf) = False; g(True) = False; g(False) = True;", "f(Leaf)"], {'ConBase': ['False']}),\
    (["f(Node t1 t2) = g(g(True)); f(Leaf) = g(False); g(True) = False; g(False) = True;", "f(Leaf)"], {'ConBase': ['True']}),\
    (["new(Node t1 t2) = NewNode new(t1) new(t2); new(Leaf) = NewLeaf;", "new(Leaf)"], {'ConBase': ['NewLeaf']}),\
    (["new(Node t1 t2) = NewNode new(t1) new(t2); new(Leaf) = NewLeaf;", "new(Node Leaf Leaf)"], {'ConInd':['NewNode', {'ConBase': ['NewLeaf']}, {'ConBase': ['NewLeaf']}]})\
    ])

#eof