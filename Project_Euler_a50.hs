isPrime :: Integral a=> a -> Bool
isPrime 2 = True
isPrime n
        | mod n 2 == 0 = False
        | null d = True
        | otherwise = False
        where d = [False | x <- [3,5..(floor $ sqrt (fromIntegral n))], mod n x == 0]

--Sieve
sieve :: Integral a => [a] -> [a]
sieve (p:xs) = p : sieve [x | x <- xs, x `mod` p /= 0]

-- get infinite primes
primes :: Integral a => [a]
primes = sieve [2..]

-- get primes below the bound
getPrimes :: Integral a => [a]
getPrimes = take 546 primes

findPrimes :: Integral a => [a] -> [a]
findPrimes ps
           | isPrime shead = [shead]
           | isPrime stail = [stail]
           | otherwise = (findPrimes t) ++ (findPrimes h)
           where shead = sum h
                 stail = sum t
                 h = init ps
                 t = tail ps

main = do
       let x = findPrimes getPrimes 
       print $ head x
