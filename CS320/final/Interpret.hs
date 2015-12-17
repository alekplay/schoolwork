{- CS320 Final Project
 - Aleksander Skjoelsvik
 - Collaborators: Raphael Baysa, Vivek Kotecha
 -}

module Interpret where

import AbstractSyntax
import KeyValueStore
import TypeCheck

makeSet :: Value -> Value
makeSet Error      = Error
makeSet (Number n) = Set [n]
makeSet (Set ns)   = Set ns

type KeyValueStore = [([String], Value)]
type Algorithm = KeyValueStore -> Maybe KeyValueStore

-- Problem 3d
eval :: [(String, KeyValueStore)] -> Exp -> Algorithm
eval env (DATA          ) kvs = Just kvs
eval env (Variable     x) kvs = lookup x env
eval env (Max          e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (max)) (eval env e kvs))
eval env (Min          e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (min)) (eval env e kvs))
eval env (Sum          e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (+))   (eval env e kvs))
eval env (Product      e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (*))   (eval env e kvs))
eval env (Union        e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (\/))  (eval env e kvs))
eval env (Intersection e) kvs = (liftMaybe suffix) (liftMaybe(combine 1 (/\))  (eval env e kvs))
eval env (MakeSet      e) kvs = 
    let Just a = (eval env e kvs) 
    in Just [(x, makeSet(n)) | (x, n) <- a]

-- Problem 3d
exec :: [(String, KeyValueStore)] -> Stmt -> Algorithm
exec env (Assign x e s) kvs = 
    let Just a = (eval env e kvs)
    in let env' = [(x, a)] ++ env
    in (exec env' s kvs)
exec env (Return x    ) kvs = Just (head [v | (y, v) <- env, y == x])

-- Problem 3e
typeCheckInterpret :: Stmt -> Algorithm
typeCheckInterpret stmt = exec [] stmt

--eof 
