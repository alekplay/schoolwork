{- CS320 Final Project
 - Aleksander Skjoelsvik
 - Collaborators: Vivek Kotecha, Raphael Baysa
 -}

module Validate where

import AbstractSyntax
import Interpret
import Data.List(nub)

-- Functions for generating key-value stores for testing
-- purposes given a collection of integers and keys.
dat :: [[String]] -> [Integer] -> KeyValueStore
dat (ks:kss) ns = [(k:ks', n) | (ks', n) <- dat kss ns, k <- ks]
dat []       ns = [([], Number n) | n <- ns]

dats :: [[String]] -> [Integer] -> [KeyValueStore]
dats kss ns = [dat (rotate i kss) (rotate j ns) | i <- [0..length kss-1], j <- [0..length ns-1]]
  where rotate i xs = take (length xs) (drop i (cycle xs))

expList = [
    Max,
    Min,
    Sum,
    Product,
    Union,
    Intersection,
    MakeSet]

baseList = [
    DATA,
    Variable "x",
    Variable "y"]

class Exhaustive a where
  exhaustive :: Integer -> [a]

-- Problem 4a
instance Exhaustive Exp where
    exhaustive n = 
        if n == 0 then []
        else if n == 1 then baseList
        else let input = [(exp DATA) | exp <- expList] 
                 dataIns = head (exhaustive (n - 1))
                 varxIns = head (tail (exhaustive (n - 1)))
                 varyIns = last (exhaustive (n -1))
            in nub([exp dataIns | exp <- expList] ++ [exp varxIns | exp <- expList] ++ [exp varyIns | exp <- expList])

-- Problem 4a
instance Exhaustive Stmt where
    exhaustive n = 
        if n == 0 then []
        else if n == 1 then [Return v | v <- ["x", "y"]]
        else let varxIns = head (exhaustive (n - 1))
                 varyIns = last (exhaustive (n - 1))
            in nub([Assign "x" (exp (head (exhaustive (n - 1)))) varxIns       | exp <- expList]
                ++ [Assign "x" (exp (head (tail (exhaustive (n - 1))))) varxIns| exp <- expList]
                ++ [Assign "x" (exp (last (exhaustive (n - 1)))) varxIns       | exp <- expList]
                ++ [Assign "y" (exp (head (exhaustive (n - 1)))) varyIns       | exp <- expList]
                ++ [Assign "y" (exp (head (tail (exhaustive (n - 1))))) varyIns| exp <- expList]
                ++ [Assign "y" (exp (last (exhaustive (n - 1)))) varyIns       | exp <- expList])

-- Problem 4b
validate :: Integer -> (Stmt -> Algorithm) -> (Stmt -> Algorithm) -> [KeyValueStore] -> [(Stmt, KeyValueStore)]
validate d f g kvs = [(e, key) | e <- (exhaustive(d)), key <- kvs, (f (e) (key)) /= (g (e) (key))]

--eof
