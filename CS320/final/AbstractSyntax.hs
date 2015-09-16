{- CS320 Final Project
 - Aleksander Skjoelsvik - U54904431
 - Collaborators: Raphael Baysa
 -}

{-# OPTIONS_GHC -fno-warn-missing-methods #-}

module AbstractSyntax where

import Data.List (union, intersect, nub)

data Exp =
    DATA
  | Variable String
  | Max Exp
  | Min Exp
  | Sum Exp
  | Product Exp
  | Union Exp
  | Intersection Exp
  | MakeSet Exp
  deriving (Eq, Show)

data Stmt =
    Assign String Exp Stmt
  | Return String
  deriving (Eq, Show)

data Type =
    TyNumber
  | TySet
  | TyVoid
  deriving (Eq, Show)

data Value =
    Set [Integer]
  | Number Integer
  | Error
  deriving (Eq, Show)

-- Problem 1b
instance Num Value where
  (Number x) + (Number y) = Number (x + y)
  _          + _          = Error
  (Number x) * (Number y) = Number (x * y)
  _          * _          = Error

-- Problem 1b
instance Ord Value where
  (Number x) <  (Number y) = (x <  y)
  (_       ) <  (Number y) = True
  (_       ) <  (_       ) = False
  (Number x) <= (Number y) = (x <= y)
  (_       ) <= (Number y) = False
  (_       ) <= (_       ) = False

-- Problem 1c
(\/) :: Value -> Value -> Value
(\/) (Set a) (Set b) = Set (union a b)
(\/) (_    ) (_    ) = Error

-- Problem 1c
(/\) :: Value -> Value -> Value
(/\) (Set a) (Set b) = Set (intersect a b)
(/\) (_    ) (_    ) = Error

-- Type class Foldable for a fold function on data types.
--
--  * The first argument is a constant that will replace all
--    leaf nodes that contain no variable.
--  * The second argument is a function that will be applied to
--    (i.e., and will replace) any variables.
--  * The third argument is the aggregator for combining
--    results of recursive folds.
--  * The fourth argument is the data value that will be folded.

class Foldable a where
  fold :: b -> (String -> b) -> ([b] -> b) -> a -> b

-- Problem 1d
instance Foldable Exp where
  fold b v f (DATA          ) = b
  fold b v f (Variable     s) = v s
  fold b v f (Max          e) = f[fold b v f e]
  fold b v f (Min          e) = f[fold b v f e]
  fold b v f (Sum          e) = f[fold b v f e]
  fold b v f (Product      e) = f[fold b v f e]
  fold b v f (Union        e) = f[fold b v f e]
  fold b v f (Intersection e) = f[fold b v f e]
  fold b v f (MakeSet      e) = f[fold b v f e]

-- Problem 1d
instance Foldable Stmt where
  fold b v f (Assign s e e2) = f[fold b v f e, fold b v f e2]
  fold b v f (Return s     ) = f [v s]

-- Problem 1e
vars :: Stmt -> [String]
vars s = nub (fold [] varsChange concat s)

-- Problem 1e
operations :: Stmt -> Integer
operations op = fold 1 (\x -> 0) (\k -> sum k+1) op

varsChange :: String -> [String]
varsChange x = [x]

-- eof