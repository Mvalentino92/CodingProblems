pent :: Integral a => a -> [a]
pent n = v : pent (n+1)
       where v = (3*n^2 - n) `div` 2

hits :: Integral a => a -> [a] -> Bool
hits x (y:ys)
          | y > x = False
          | x == y = True
          | otherwise = hits x ys

match :: Integral a => [a] -> [a] -> [a]
match xs (y:ys) = head [ if x > 2000000 then 1 else x' - x | (x,x') <- zip xs ys, (hits (x+x') ys) && (hits (x'-x) ys) || x > 2000000] : match xs ys

main = do
       {-let x = pent 1
       let y = filter (>1) (take 10000 $ match x x)
       let z = minimum y
       print z-}
       let x = pent 1
       let y = compare' 10 x
       print y

compare' :: Integral a => a -> [a] -> a
compare' p ys
        | null l = compare' (p+1) ys
        | otherwise = head l
        where getPent val = (3*val^2 - val) `div` 2
              n = getPent p 
              m = map getPent [(p-1),(p-2)..1] 
              l = [n - m' | m' <- m, hits (m'+n) ys, hits (n - m') ys] 
