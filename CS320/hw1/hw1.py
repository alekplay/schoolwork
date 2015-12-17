"""
	CS320 - Homework 1
	Aleksander Skjoelsvik
"""

# Task 1
import re
def tokenize(terms, s):
	sTerms = [re.escape(t) for t in terms]
	sTerms = "|".join(sTerms)
	sTerms = r"(\s+|" + sTerms + r"|,|\(|\)|\*|\+)"
	tokens = [t for t in re.split(sTerms, s)]
	return [t for t in tokens if not t.isspace() and not t==""]

def transformation(tokens):
	if tokens[0] == 'finish':
		return ('Finish', tokens[1:])

	if tokens[0] == 'projection' and tokens[1] == ';':
		(e1, tokens) = transformation(tokens[2:])
		return ({'Projection':[e1]}, tokens[1:])

	if tokens[0] == 'reflection' and tokens[1] == ';':
		(e1, tokens) = transformation(tokens[2:])
		return ({'Reflection':[e1]}, tokens[1:])

	if tokens[0] == 'left' and tokens[1] == 'rotation' and tokens[2] == ';':
		(e1, tokens) = transformation(tokens[3:])
		return ({'LeftRotation':[e1]}, tokens[1:])

	if tokens[0] == 'right' and tokens[1] == 'rotation' and tokens[2] == ';':
		(e1, tokens) = transformation(tokens[3:])
		return ({'RightRotation':[e1]}, tokens[1:])

# Task 2 & 3
import re
def number(tokens):
	if tokens[0] == '-':
		tokens = tokens[1:]
		tokens[0] = '-' + tokens[0]

	if re.match(r"^-?[0-9]+$", tokens[0]):
		return ({"Number": [int(tokens[0])]}, tokens[1:])

def variable(tokens):
	if re.match(r'[a-z]+', tokens[0]):
		return ({"Variable": [tokens[0]]}, tokens[1:])

def term(tmp, top = True):
	tokens = tmp[0:]
	if tokens[0] == '#':
		tokens = tokens[1:]
		r = number(tokens)
		if not r is None:
			(e1, tokens) = r
			if not top or len(tokens) == 0:
				return (e1, tokens)

	tokens = tmp[0:]
	if tokens[0] == '@':
		tokens = tokens[1:]
		r = variable(tokens)
		if not r is None:
			(e1, tokens) = r
			if not top or len(tokens) == 0:
				return (e1, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'abs' and tokens[1] == '(':
		tokens = tokens[2:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ')':
				tokens = tokens[1:]
				if not top or len(tokens) == 0:
					return ({'Abs':[e1]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'add' and tokens[1] == '(':
		tokens = tokens[2:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Add':[e1, e2]}, tokens)
			
	tokens = tmp[0:]
	if tokens[0] == 'subtract' and tokens[1] == '(':
		tokens = tokens[2:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Subtract':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == '(':
		tokens = tokens[1:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == '+':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Add':[e1, e2]}, tokens)
			elif tokens[0] == '-':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Subtract':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == '|':
		tokens = tokens[1:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == '|':
				tokens = tokens[1:]
				if not top or len(tokens) == 0:
					return ({'Abs':[e1]}, tokens)

		

def formula(tmp, top = True):
	tokens = tmp[0:]
	if tokens[0] == 'true':
		tokens = tokens[1:]
		if not top or len(tokens) == 0:
			return ('True', tokens)

	tokens = tmp[0:]
	if tokens[0] == 'false':
		tokens = tokens[1:]
		if not top or len(tokens) == 0:
			return ('False', tokens)

	tokens = tmp[0:]
	if tokens[0] == 'not' and tokens[1] == '(':
		tokens = tokens[2:]
		r = formula(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ')':
				tokens = tokens[1:]
				if not top or len(tokens) == 0:
					return ({'Not':[e1]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'and' and tokens[1] == '(':
		tokens = tokens[2:]
		r = formula(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = formula(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'And':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'or' and tokens[1] == '(':
		tokens = tokens[2:]
		r = formula(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = formula(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Or':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'compare' and tokens[1] == '(':
		tokens = tokens[2:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return({'Compare':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'less' and tokens[1] == 'than' and tokens[2] == '(':
		tokens = tokens[3:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'LessThan':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == 'greater' and tokens[1] == 'than' and tokens[2] == '(':
		tokens = tokens[3:]
		r = term(tokens, False)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ',':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = term(tokens, False)
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'GreaterThan':[e1, e2]}, tokens)

	tokens = tmp[0:]
	if tokens[0] == '(':
		tokens = tokens[1:]

		rFormula = formula(tokens, False)
		rTerm = term(tokens, False)
		r = None
		if not rFormula is None:
			r = rFormula
		elif not rTerm is None:
			r = rTerm

		if not r is None:
			(e1, tokens) = r
			if tokens[0] == '&&':
				tokens = tokens[1:]
				r = formula(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'And':[e1, e2]}, tokens)
			elif tokens[0] == '||':
				tokens = tokens[1:]
				r = formula(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Or':[e1, e2]}, tokens)
			elif tokens[0] == '=':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'Compare':[e1, e2]}, tokens)
			elif tokens[0] == '<':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return ({'LessThan':[e1, e2]}, tokens)
			elif tokens[0] == '>':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ')':
						tokens = tokens[1:]
						if not top or len(tokens) == 0:
							return({'GreaterThan':[e1, e2]}, tokens)

def program(tmp, top = True):
	tokens = tmp[0:]
	if tokens[0] == 'end' and tokens[1] == ';':
		tokens = tokens[2:]
		if not top or len(tokens) == 0:
			return ('End', tokens)

	tokens = tmp[0:]
	if tokens[0] == 'print':
		tokens = tokens[1:]
		
		rFormula = formula(tokens, False)
		rTerm = term(tokens, False)
		r = None
		if not rFormula is None:
			r = rFormula
		elif not rTerm is None:
			r = rTerm

		if not r is None:
			(e1, tokens) = r
			if tokens[0] == ';':
				tokens = tokens[1:]
				r = program(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if not top or len(tokens) == 0:
						return ({'Print':[e1, e2]}, tokens)
	
	tokens = tmp[0:]
	if tokens[0] == 'assign' and tokens[1] == '@':
		tokens = tokens[2:]
		r = variable(tokens)
		if not r is None:
			(e1, tokens) = r
			if tokens[0] == '=':
				tokens = tokens[1:]
				r = term(tokens, False)
				if not r is None:
					(e2, tokens) = r
					if tokens[0] == ';':
						tokens = tokens[1:]
						r = program(tokens, False)
						if not r is None:
							(e3, tokens) = r
							if not top or len(tokens) == 0:
								return({'Assign':[e1, e2, e3]}, tokens)

def complete(str):
	terminals = ["print", "assign", "end", "true", "false", "not", "and", "or", "compare", "less", "greater", "than", "add", "subtract", "abs", "@", "#", ";", "="]
	tokens = tokenize(terminals, str)
	if not tokens is None:
		return program(tokens)
