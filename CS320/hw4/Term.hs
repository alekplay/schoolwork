---------------------------------------------------------------------
--
-- CAS CS 320, Spring 2015
-- Assignment 4 (skeleton code)
-- Term.hs
--

data Term =
    Number Integer
  | Abs Term
  | Plus Term Term
  | Mult Term Term

evaluate :: Term -> Integer
evaluate(Number x) = x;
evaluate(Abs term) = abs(evaluate(term));
evaluate(Plus term1 term2) = evaluate(term1) + evaluate(term2);
evaluate(Mult term1 term2) = evaluate(term1) * evaluate(term2);

--eof