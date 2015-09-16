data IList = Nil
    | Cons Int IList
    deriving (Show, Eq)

itake :: IList -> Int -> IList
itake Nil n = Nil
itake (Cons x xs) n = if n == 0 then Nil
                    else Cons x (itake xs (n-1))

--itake Nil = let
--    temp n = Nil
--    in 
--        temp
--itake Cons x xs = let
--    temp n = if n == 0 then Nil
--        else Cons x (itake xs (n-1))
--    in 
--        temp

iremove :: IList -> Int -> IList
iremove Nil n = Nil
iremove (Cons x xs) n = 
    if n == 0 then (Cons x xs)
    else (iremove xs (n-1))

sum :: IList -> Int
sum xs = let
    loop Nil n = n
    loop (Cons x xs) n = loop xs (n+x)
    in
        loop xs 0


main :: IO()
main = do
    let 
        xs = Cons 1 (Cons 2 (Cons 3 Nil))
    putStrLn(show xs)