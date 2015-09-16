---------------------------------------------------------------------
--
-- CAS CS 320, Spring 2015
-- Assignment 5 (skeleton code)
-- Database.hs
-- Aleksander Skjoelsvik U54904431
--

module Database where

type Column = String
data User = User String deriving (Eq, Show)
data Table = Table String deriving (Eq, Show)
data Command =
    Add User
  | Create Table
  | Allow (User, Table)
  | Insert (Table, [(Column, Integer)])
  deriving (Eq, Show)

example = [
    Add (User "Alice"),
    Add (User "Bob"),
    Create (Table "Revenue"),
    Insert (Table "Revenue", [("Day", 1), ("Amount", 2400)]),
    Insert (Table "Revenue", [("Day", 2), ("Amount", 1700)]),
    Insert (Table "Revenue", [("Day", 3), ("Amount", 3100)]),
    Allow (User "Alice", Table "Revenue")
  ]

lookup' :: Column -> [(Column, Integer)] -> Integer
lookup' c' ((c,i):cvs) = if c == c' then i else lookup' c' cvs

type Operator = Integer -> Integer -> Integer

--- | Problem 1a |
select :: [Command] -> User -> Table -> Column -> Maybe [Integer]
select commands user table column = if [(user, table)] == [(user', table') | (Allow(user', table')) <- commands]
  then Just [lookup' column row | (Insert (Table table, row)) <- commands] else Nothing

--- | Problem 1b |
aggregate :: [Command] -> User -> Table -> Column -> Operator -> Integer -> Maybe Integer
aggregate commands user table column operator integer = if [(user, table)] == [(user', table') | (Allow(user', table')) <- commands]
  then Just (foldr operator integer [lookup' column row | (Insert (Table table, row)) <- commands]) else Nothing

--- | Problem 1c |
validate :: [Command] -> Bool
validate commands = let
  rc = reverse commands
  top = head rc
  end = tail rc
  in
    helper top end

--- | Helper for problem 1c |
helper :: Command -> [Command] -> Bool
helper (Add (User u)) [] = True
helper (Create (Table t)) [] = True
helper (Add (User u)) x = True && (helper (head x) (tail x))
helper (Create (Table t)) x = True && (helper (head x) (tail x))
helper (Allow (User u, Table t)) x = (elem (Add (User u)) x) && (elem (Create (Table t)) x) && (helper (head x) (tail x))
helper (Insert (Table t, tup)) x = (elem (Create (Table t)) x) && (helper (head x) (tail x))

--eof