-- Finds the streak
streak :: Integer -> Integer -> Integer
streak n k
       | mod n k /= 0 = (k-1)
       | otherwise = streak (n+1) (k+1)

-- Solves the problem.
solve :: (Integer,Integer) -> Integer
solve (i,n)
      | i > 31 = 0
      | otherwise = total + (solve next)
      where next = head $ [(streak (n*x + 1) 1, n*x) | x <- [1..], streak (n*x + 1) 1 /= i]
            val = 4^i - 1 :: Integer
            total = d1 - d2
            d1 = getVar val n
            d2 = getVar val (snd next)
            getVar val' i'
                   | mod val' i' == 0 = div val' i'
                   | otherwise = succ $ quot val' i'

main = do
       print $ solve(1,1)


