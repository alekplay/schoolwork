{- CS320 Final Project
 - Aleksander Skjoelsvik
 - Collaborators: Raphael Baysa
 -}

module TypeCheck where

import AbstractSyntax
import Parse

class Typeable a where
  typeCheck :: [(String, Type)] -> a -> Maybe Type
  
-- Problem 2a
instance Typeable Exp where 
  typeCheck env (DATA) = 
    Just TyNumber
  typeCheck env (Variable x) = 
    if length [t | (s,t) <- env, s == x] > 0 then Just TyVoid else Nothing
  typeCheck env (Max e) = 
    if typeCheck env e == Just TyNumber then Just TyNumber else Nothing
  typeCheck env (Min e) = 
    if typeCheck env e == Just TyNumber then Just TyNumber else Nothing
  typeCheck env (Sum e) = 
    if typeCheck env e == Just TyNumber then Just TyNumber else Nothing
  typeCheck env (Product e) = 
    if typeCheck env e == Just TyNumber then Just TyNumber else Nothing
  typeCheck env (Union e) = 
    if typeCheck env e == Just TySet then Just TySet else Nothing
  typeCheck env (Intersection e) = 
    if typeCheck env e == Just TySet then Just TySet else Nothing
  typeCheck env (MakeSet e) = 
    if typeCheck env e == Just TyNumber then Just TySet else Nothing 

-- Problem 2b
instance Typeable Stmt where 
  typeCheck env (Assign x e s) = 
    let t = typeCheck env e 
    in if t == Nothing then Nothing else
      let env' = env ++ [(x, unwrap t)]
      in if typeCheck env' s == Just TyVoid then Just TyVoid else Nothing
  typeCheck env (Return x) = 
    if length [t | (s, t) <- env, s == x] > 0 then Just TyVoid else Nothing

-- Problem 2c
liftMaybe :: (a -> b) -> (Maybe a -> Maybe b)
liftMaybe f = let
    g (Just x) = Just (f x)
    g Nothing = Nothing
    in g

-- Problem 2c
joinMaybe :: Maybe (Maybe a) -> Maybe a
joinMaybe f = let
    Just g = f
    Nothing = Nothing
    in g

unwrap :: Maybe a -> a
unwrap (Just a) = a

-- Problem 2d
tokenizeParseTypeCheck :: String -> Maybe Type
tokenizeParseTypeCheck s = joinMaybe (liftMaybe (typeCheck [] :: Stmt -> Maybe Type) (tokenizeParse s))
  
-- eof
