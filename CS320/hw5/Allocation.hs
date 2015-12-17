---------------------------------------------------------------------
--
-- CAS CS 320, Spring 2015
-- Assignment 5 (skeleton code)
-- Allocation.hs
-- Aleksander Skjoelsvik
--

module Allocation where

type Item = Integer
type Bin = Integer

data Alloc = Alloc Bin Bin deriving (Eq, Show)

data Graph a =
    Branch a (Graph a) (Graph a) 
  | Finish a
  deriving (Eq, Show)

type Strategy = Graph Alloc -> Graph Alloc

--- | Problem 2a |
graph :: Alloc -> [Item] -> Graph Alloc
graph (Alloc a b) (x : itemList) = Branch (Alloc a b) (graph (Alloc (a + x) b) (itemList)) (graph (Alloc a (b + x)) (itemList))
graph (Alloc a b) [] = (Finish (Alloc a b))

--- | Problem 2b |
contents :: Graph a -> a
contents (Branch a b c) = a
contents (Finish d) = d

--- | Problem 2c |
instance Ord Alloc where
    a <= b = if (helper a <= helper b) then True else False

--- | Helper for problem 2c |
helper :: Alloc -> Integer
helper (Alloc a b) = abs(a - b)

--- | Problem 2d |
instance Ord a => Ord (Graph a) where
    g <= g' = if (contents g <= contents g') then True else False

--- | Problem 2e |
final :: Graph a -> [a]
final (Branch a b c) = final b ++ final c
final (Finish d) = [d]

--- | Problem 2f |
depth :: Integer -> Graph a -> [a]
depth 0 (Branch a b c) = [a]
depth x (Branch a b c) = if x < 0 then [] else (depth (x - 1) b) ++ (depth (x - 1) c)
depth 0 (Finish d) = [d]

--- | Problem 3a |
greedy :: Strategy
greedy (Branch a b c) = if b <= c then b else c
greedy (Finish d) = Finish d

--- | Problem 3b |
patient :: Integer -> Strategy
patient 0 a = a
patient n (Branch a b c) = minimum([(patient (n - 1) b)] ++ [(patient (n - 1) c)])
patient n (Finish d) = Finish d

--- | Problem 3c |
optimal :: Strategy
optimal a = Finish(minimum(final (greedy a)))

--- | Problem 3d |
metaCompose :: Strategy -> Strategy -> Strategy
metaCompose s1 s2 g = (s2 (s1 g))

--- | Problem 3e |
metaRepeat :: Integer -> Strategy -> Strategy
metaRepeat i s g = if i > 0 then metaRepeat (i - 1) (s) (s(g)) else g

--- | Problem 3f |
metaGreedy :: Strategy -> Strategy -> Strategy
metaGreedy s1 s2 g = if (s1(g) < (s2(g))) then s1(g) else s2(g)

--- | Problem 3g |
impatient :: Integer -> Strategy
impatient n g = (metaRepeat n greedy) g
{-  It is superior because it uses less comparisons, and therefore is faster.
    However speed comes at a cost, and it doesn't necessarily get the
    best graph.
-}

--- | Problem 4 |
--fit :: Graph a -> [Strategy] -> Strategy

--eof
