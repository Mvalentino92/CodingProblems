-- Returns list of prime factors
factor :: Integral a => a -> a -> [a]
factor n s 
       | n' == 1 = [s']
       | otherwise = s' : factor n' s'
       where s' = head $ dropWhile (\x -> mod n x /= 0) [s..]
             n' = div n s'

-- Combines the repeated prime factors -> [2,2,3] = [4,3]
combine :: Integral a => [a] -> [a]
combine [] = []
combine (x:xs) = product same : combine left
        where same = x : takeWhile (==x) xs
              left = dropWhile (==x) xs

-- Comp of above functions 
getFactor :: Integral a => a -> a -> [a] 
getFactor n = combine . factor n

-- Simply checks if the primes factors are 4 long
has4 :: Integral a => [a] -> Bool
has4 xs
     | length xs == 4 = True
     | otherwise = False

-- Infinite list of length 4 prime factors
genFac :: Integral a => [[a]]
genFac = [x' | x <- iterate (+1) 647, let x' = getFactor x 2, has4 x'] 

-- Finds the answer
satisfy :: Integral a => [[a]] -> a
satisfy (a:b:c:d:ds)
        | v2 && v1 = product a
        | otherwise = satisfy (b:c:d:ds)
        where list = concat (a:b:c:d:[])
              v1 = unique $ qSort list
              pa = product a
              v2 = (pa == product b - 1) && (pa == product c - 2) && (pa == product d -3)

-- Easier to find duplicates of sorted list
qSort :: Integral a => [a] -> [a]
qSort [] = []
qSort [x] = [x]
qSort (x:xs) = qSort smaller ++ [x] ++ qSort larger
             where smaller = [x' | x' <- xs, x' <= x]
                   larger = [x' | x' <- xs, x' > x]

-- See if list has duplicates or if it is unique
unique :: Integral a => [a] -> Bool
unique [] = True
unique [x] = True
unique (x:xs)
       | x == head xs = False
       | otherwise = unique xs      

main = do
       let x = genFac
       putStr "The solution is: "
       print $ satisfy x
