module ProjectTests where

import AbstractSyntax
import Parse
import TypeCheck
import KeyValueStore
import Interpret
import Validate
import Compile

allTests = [
  show (failed parseTests),
  show (failed typeCheckTests),
  show (failed interpretTests)
  ]

-- To get the failures for an individual test, query that
-- test using "failed", e.g.:
-- 
-- *> failed parseTests

failed :: Eq a => [(a, a)] -> [(a, a)]
failed tests = [(x, y) | (x, y) <- tests, x /= y]

parseTests :: [(Maybe Stmt, Maybe Stmt)]
parseTests = [
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); return t;"), Just (Assign "t" (Sum DATA) (Return "t"))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); assign t = t; return t;"), Just (Assign "t" (Sum DATA) (Assign "t" (Variable "t") (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); assign t = set(DATA); return t;"), Just (Assign "t" (Sum DATA) (Assign "t" (MakeSet DATA) (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); assign t = DATA; return t;"), Just (Assign "t" (Sum DATA) (Assign "t" DATA (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = union(set(DATA)); assign b = DATA; return b;"), Just (Assign "b" (Union (MakeSet DATA)) (Assign "b" DATA (Return "b")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = union(set(DATA)); assign a = set(DATA); return b;"), Just (Assign "b" (Union (MakeSet DATA)) (Assign "a" (MakeSet DATA) (Return "b")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = union(set(DATA)); assign a = set(DATA); return a;"), Just (Assign "b" (Union (MakeSet DATA)) (Assign "a" (MakeSet DATA) (Return "a")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = union(set(DATA)); assign a = b; return b;"), Just (Assign "b" (Union (MakeSet DATA)) (Assign "a" (Variable "b") (Return "b")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); assign s = DATA; return t;"), Just (Assign "t" (Sum DATA) (Assign "s" DATA (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = sum(DATA); assign s = DATA; return s;"), Just (Assign "t" (Sum DATA) (Assign "s" DATA (Return "s")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Return "t"))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = t; return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Variable "t") (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); assign t = t; return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Assign "t" (Variable "t") (Return "t"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); assign t = set(DATA); return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Assign "t" (MakeSet DATA) (Return "t"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); assign t = DATA; return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Assign "t" DATA (Return "t"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); assign s = t; return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Assign "s" (Variable "t") (Return "t"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = sum(DATA); assign s = t; return s;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Sum DATA) (Assign "s" (Variable "t") (Return "s"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = min(min(DATA)); assign b = min(DATA); assign b = b; return b;"), Just (Assign "b" (Min (Min DATA)) (Assign "b" (Min DATA) (Assign "b" (Variable "b") (Return "b"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign b = min(min(DATA)); assign b = min(DATA); assign b = DATA; return b;"), Just (Assign "b" (Min (Min DATA)) (Assign "b" (Min DATA) (Assign "b" DATA (Return "b"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign y = sum(product(DATA)); assign y = product(DATA); assign x = DATA; return y;"), Just (Assign "y" (Sum (Product DATA)) (Assign "y" (Product DATA) (Assign "x" DATA (Return "y"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign y = sum(product(DATA)); assign y = product(DATA); assign x = DATA; return x;"), Just (Assign "y" (Sum (Product DATA)) (Assign "y" (Product DATA) (Assign "x" DATA (Return "x"))))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = set(DATA); return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (MakeSet DATA) (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = product(DATA); return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Product DATA) (Return "t")))),
  ((tokenizeParse :: String -> Maybe Stmt) ("assign t = set(sum(DATA)); assign t = product(DATA); assign t = t; return t;"), Just (Assign "t" (MakeSet (Sum DATA)) (Assign "t" (Product DATA) (Assign "t" (Variable "t") (Return "t")))))
  ]

typeCheckTests :: [(Maybe Type, Maybe Type)]
typeCheckTests = [
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Sum (Max (Product (Product (Max (Product DATA))))))), Just TyNumber),
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Product (Product (Product (Product (Max (Product DATA))))))), Just TyNumber),
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Sum (Product (Product (Product (Max (Product DATA))))))), Just TyNumber),
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Product (Min (Product (Sum (Max (Product DATA))))))), Just TyNumber),
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Sum (Min (Product (Sum (Max (Product DATA))))))), Just TyNumber),
  ((typeCheck [] :: Stmt -> Maybe Type) (Assign "b" (Sum DATA) (Assign "a" (MakeSet DATA) (Return "a"))), Just TyVoid),
  ((typeCheck [] :: Stmt -> Maybe Type) (Return "a"), Nothing),
  ((typeCheck [] :: Stmt -> Maybe Type) (Return "b"), Nothing),
  ((typeCheck [] :: Stmt -> Maybe Type) (Assign "b" DATA (Return "a")), Nothing),
  ((typeCheck [] :: Exp -> Maybe Type) (Product (Min (Product (Variable "q")))), Nothing),
  ((typeCheck [] :: Exp -> Maybe Type) (Sum (Min (Product (Variable "q")))), Nothing),
  ((typeCheck [] :: Stmt -> Maybe Type) (Assign "b" (Sum DATA) (Assign "a" (Variable "b") (Return "b"))), Just TyVoid),
  ((typeCheck [] :: Stmt -> Maybe Type) (Assign "a" (Variable "b") (Return "a")), Nothing)
  ]

interpretTests :: [(Maybe KeyValueStore, Maybe KeyValueStore)]
interpretTests = [
  (typeCheckInterpret (Assign "x" (Min (Max DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [5]),([],Set [5])]),
  (typeCheckInterpret (Assign "u" (Product (Sum DATA)) (Assign "v" (Product (Sum (Variable "u"))) (Return "v"))) testKVS, Just [([],Number 5832),([],Number 5832)]),
  (typeCheckInterpret (Assign "x" (Product (Max DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [5]),([],Set [5])]),
  (typeCheckInterpret (Assign "x" (Product (Max DATA)) (Assign "y" (Union (Union (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [5]),([],Set [5])]),
  (typeCheckInterpret (Assign "x" (Min (Product DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "x" (Min (Product DATA)) (Assign "y" (Union (Union (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "x" (Sum (Product DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "x" (Sum (Product DATA)) (Assign "y" (Union (Union (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "x" (Product (Product DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "x" (Product (Product DATA)) (Assign "y" (Union (Union (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [-30]),([],Set [-30])]),
  (typeCheckInterpret (Assign "u" (Product (Product DATA)) (Assign "v" (Product (Product (Variable "u"))) (Return "v"))) testKVS, Just [([],Number 729000000),([],Number 729000000)]),
  (typeCheckInterpret (Assign "u" (Sum (Product DATA)) (Assign "v" (Sum (Product (Variable "u"))) (Return "v"))) testKVS, Just [([],Number 2700),([],Number 2700)]),
  (typeCheckInterpret (Assign "x" (Sum (Sum DATA)) (Assign "y" (Sum (Sum (Variable "x"))) (Return "y"))) testKVS, Just [([],Number 54),([],Number 54)]),
  (typeCheckInterpret (Assign "x" (Product (Min DATA)) (Assign "y" (Min (Min (Variable "x"))) (Return "y"))) testKVS, Just [([],Number (-1)),([],Number (-1))]),
  (typeCheckInterpret (Assign "x" (Product (Max DATA)) (Assign "y" (Max (Max (Variable "x"))) (Return "y"))) testKVS, Just [([],Number 5),([],Number 5)]),
  (typeCheckInterpret (Assign "x" (Sum (Max DATA)) (Assign "y" (Intersection (Intersection (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [5]),([],Set [5])]),
  (typeCheckInterpret (Assign "x" (Sum (Max DATA)) (Assign "y" (Union (Union (MakeSet (Variable "x")))) (Return "y"))) testKVS, Just [([],Set [5]),([],Set [5])]),
  (typeCheckInterpret (Assign "x" (Product (Sum DATA)) (Assign "y" (Sum (Sum (Variable "x"))) (Return "y"))) testKVS, Just [([],Number 54),([],Number 54)])
  ]

testKVS = (dats [["T"], ["C", "D"], ["X", "Y", "Z"], ["a", "b"]] [-1,2,3,5]) !! 3

--eof