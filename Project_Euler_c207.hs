-- Creates the infinite list of non-perfects
np :: [Integer]
np  = [n*n - n | n <- [2..], not $ isPower n]

-- Checks if power of 2 (disqualified from non perfect list
isPower :: Integer -> Bool
isPower n 
        | odd n = False
        | n' == 1 = True
        | mod n' 2 /= 0 || n' < 1 = False
        | otherwise = isPower n'
        where n' = div n 2

-- Creates the infinite list of perfects
p :: [Integer]
p = [4^n - 2^n | n <- [1..]]

-- Finds the solution
findSol n p np
        | l == n = last $ take (12345*n - (n - 1)) np
        | otherwise = findSol (n+1) p np
        where b = last $ take (12345*n - n) np
              l = fromIntegral (length $ takeWhile (<b) p)

main = do
       print $ findSol 1 p np
