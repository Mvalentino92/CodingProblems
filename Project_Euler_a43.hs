main = do
       let x = pans 2 [[1]]
       print (sum $ satisfy x)
       return ()

gpan :: Integer -> Integer -> [[Integer]] -> [[Integer]]
gpan _ _ [] = []
gpan hardn n ps
        | n == 0 = []
        | otherwise = next ++ gpan hardn (n-1) ps
        where pnums = [1..(hardn -1)]
              pvals = [x | x <- [1..hardn], x /= n]
              pairs = zip pnums pvals
              next = getCombos ps pairs n 

getCombos :: [[Integer]] -> [(Integer,Integer)] -> Integer -> [[Integer]]
getCombos [] _ _ = []
getCombos (x:xs) ys n = (n:(combos x ys)) : getCombos xs ys n 

combos :: [Integer] -> [(Integer,Integer)] -> [Integer]
combos [] _ = []
combos _ [] = []
combos (x:xs) ys = val : combos xs ys
              where val = findVal x ys


findVal :: Integer -> [(Integer,Integer)] -> Integer
findVal x [y]
        | x == fst y = if snd y == 10 then 0 else snd y
        | otherwise = error "CANT FIND VALUE"
findVal x (y:ys)
        | x == fst y = if snd y == 10 then 0 else snd y
        | otherwise = findVal x ys

pans :: Integer -> [[Integer]] -> [[Integer]]
pans 11 _ = []
pans n ps = [x | x <- next ++ pans (n+1) next, length x == 10, x !! 0 /= 10]
          where next = gpan n n ps

list2num :: [Integer] -> Integer
list2num xs = foldl (\x y -> x*10 + y) 0 xs

satisfy :: [[Integer]] ->  [Integer]
satisfy [] = []
satisfy (all@(a:b:c:d:e:f:g:h:i:j):xs) = if list2num (b:c:d:[]) `mod` 2 == 0
                                && list2num (c:d:e:[]) `mod` 3 == 0
                                && list2num (d:e:f:[]) `mod` 5 == 0
                                && list2num (e:f:g:[]) `mod` 7 == 0
                                && list2num (f:g:h:[]) `mod` 11 == 0
                                && list2num (g:h:i:[]) `mod` 13 == 0
                                && list2num (h:i:j) `mod` 17 == 0
                                then list2num all : satisfy xs
                                else satisfy xs
