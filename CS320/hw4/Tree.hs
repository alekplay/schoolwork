---------------------------------------------------------------------
--
-- CAS CS 320, Spring 2015
-- Assignment 4 (skeleton code)
-- Tree.hs
--

data Tree =
    Leaf
  | Twig
  | Branch Tree Tree Tree
  deriving (Eq, Show);

twigs :: Tree -> Integer
twigs(Twig) = 1;
twigs(Leaf) = 0;
twigs(Branch t1 t2 t3) = twigs(t1) + twigs(t2) + twigs(t3);

branches :: Tree -> Integer
branches(Twig) = 0;
branches(Leaf) = 0;
branches(Branch t1 t2 t3) = branches(t1) + branches(t2) + branches(t3) + 1

height :: Tree -> Integer
height(Twig) = 1
height(Leaf) = 0
height(Branch t1 t2 t3) = maximum[height(t1), height(t2), height(t3)] + 1

perfect :: Tree -> Bool
perfect(Twig) = False
perfect(Leaf) = True
perfect(Branch t1 t2 t3) = (t1 == t3) && perfect(t1) && perfect(t2) && perfect(t3)

degenerate :: Tree -> Bool

infinite :: Tree
infinite = Branch infinite infinite infinite

--eof