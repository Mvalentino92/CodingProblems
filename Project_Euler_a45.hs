common :: Integral a => [a] -> [a] -> [a]
common _ [] = []
common ls (h:hs)
            | null ps = common ls hs
            | head ps /=  h = common ps hs
            | otherwise = head ps : common ps hs
            where ps = dropWhile (<h) ls


tri :: Integral a => a -> [a]
tri n = v : tri (n+1)
        where v = (n^2 + n) `div` 2

pen :: Integral a => a -> [a]
pen n = v : pen (n+1)
        where v = (3*n^2 - n) `div` 2
                 
hex :: Integral a => a -> [a]
hex n = v : hex (n+1)
        where v = (2*n^2 - n)


main = do
       let b = 100000
       let bb = head $ tri b
       let t = take b $ tri 1
       let p = takeWhile (<= bb) $ pen 1
       let h = takeWhile (<= bb) $ hex 1
       let c = common t p
       let cc = common h c
       let sol = head $ dropWhile (<=40755) cc
       putStr "The solution is: "
       print sol
