{- CS320 Final Project
 - Aleksander Skjoelsvik
 - Collaborators: Vivek Kotecha, Raphael Baysa
 -}

module Compile where

import Data.List (union, (\\))
import AbstractSyntax
import TypeCheck
import Interpret
import KeyValueStore

data Instruction =
    Reliable (Value -> Value -> Value)
  | Unreliable (Value -> Value -> Value)
  | MakeSets
  | DropKeySuffixes

-- If a variable assignment statement will never affect the
-- final returned key-value store at the bottom of the abstract
-- syntax tree, remove that statement.
rmvDeadCode :: Stmt -> Stmt
rmvDeadCode (Assign x e s) = if not (x `elem` unbound (rmvDeadCode s)) then (rmvDeadCode s) else Assign x e (rmvDeadCode s)
rmvDeadCode (Return x    ) = Return x

unbound :: Stmt -> [String]
unbound (Assign x e s) = vars (Assign "" e (Return "")) `union` (unbound s \\ [x])
unbound (Return x    ) = [x]

compileExp :: Exp -> [Instruction]
compileExp (Max e          ) = compileExp e ++ [Reliable max, DropKeySuffixes]
compileExp (Min e          ) = compileExp e ++ [Reliable min, DropKeySuffixes]
compileExp (Sum e          ) = compileExp e ++ [Reliable (+), DropKeySuffixes]
compileExp (Product e      ) = compileExp e ++ [Reliable (*), DropKeySuffixes]
compileExp (Union e        ) = compileExp e ++ [Reliable (\/), DropKeySuffixes]
compileExp (Intersection e ) = compileExp e ++ [Reliable (/\), DropKeySuffixes]
compileExp (MakeSet e      ) = compileExp e ++ [MakeSets]
compileExp _                 = []

compileStmt :: Stmt -> [Instruction]
compileStmt (Assign _ e s) = compileExp e ++ compileStmt s
compileStmt (Return _    ) = []

-- Problem 5a
simulate :: [Instruction] -> KeyValueStore ->  KeyValueStore
simulate ((Reliable f)   :insts) kvs = simulate insts (combine 1 f kvs)
simulate ((Unreliable f) :insts) kvs = simulate insts (combine 2 f kvs)
simulate (DropKeySuffixes:insts) kvs = simulate insts (suffix kvs)
simulate (MakeSets       :insts) kvs = simulate insts ([(x, makeSet(n)) | (x, n) <- kvs])
simulate []                      kvs = kvs

-- Problem 5b
compile :: Stmt -> KeyValueStore -> Maybe KeyValueStore
compile s kvs = 
    if (typeCheck [] s) == Nothing then Nothing
    else Just (simulate(compileStmt(rmvDeadCode s)) kvs)

--eof
