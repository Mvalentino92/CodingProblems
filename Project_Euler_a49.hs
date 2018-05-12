-- Converts number to list
int2list :: Integral a => a -> [a]
int2list 0 = []
int2list n = int2list n' ++ [d]
         where d = mod n 10
               n' = div n 10

-- Checks if two lists are permutations
permute :: Integral a => [a] -> [a] -> Bool
permute xs ys
        | xs' == ys' = True
        | otherwise = False
        where xs' = qSort xs
              ys' = qSort ys

-- Checks if two numbers are permutations
isPermute :: Integral a => a -> a -> Bool
isPermute x y = permute (int2list x) (int2list y)

-- Easier to find duplicates of sorted list
qSort :: Integral a => [a] -> [a]
qSort [] = []
qSort [x] = [x]
qSort (x:xs) = qSort smaller ++ [x] ++ qSort larger
             where smaller = [x' | x' <- xs, x' <= x]
                   larger =  [x' | x' <- xs, x' > x]

--Sieve
sieve :: Integral a => [a] -> [a]
sieve (p:xs) = p : sieve [x | x <- xs, x `mod` p /= 0]

-- get infinite primes
primes :: Integral a => [a]
primes = sieve [2..]

-- get primes between 1000..10000
getPrimes :: Integral a => [a]
getPrimes = filter (>999) (filter (<10000) (take 1500 primes))

-- Checks if the current filers are solution
checkSolution :: Integral a => [a] -> [a]
checkSolution [] = []
checkSolution [x] = []
checkSolution [x,y] = []
checkSolution (x:xs)
              | exists xnn xs = (x:xn:xnn:[])
              | otherwise = checkSolution (x:(tail xs))
              where xn = head xs
                    diff = xn - x
                    xnn = xn + diff
                    exists _ [] = False
                    exists x (y:ys)
                             | x == y = True
                             | otherwise = exists x ys
              
                    
-- Finds the solution starting from beginning of list
findSolution :: Integral a => [a] -> [a]
findSolution (p:ps)
             | null psol = findSolution ps
             | otherwise = psol
             where pmutes = filter (isPermute p) ps
                   psol = checkSolution pmutes
   
main = do
       let x = getPrimes
       print $ findSolution x
