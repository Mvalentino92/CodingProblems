import Data.List
--The Ranks
data Rank = Two | Three | Four | Five | Six | Seven |
            Eight | Nine | Ten | Jack | Queen | King | Ace deriving (Show,Eq,Ord)

--Denominations, are all equal with respect or Ordering
data Denomination = Hearts | Diamonds | Spades | Clubs deriving (Show,Eq)
instance Ord Denomination where
    compare _ _ = EQ

--The possible Hands
data Hand = HighCard [Card] | OnePair [Card] | TwoPairs [Card] | 
            ThreeKind [Card] | Straight [Card] | Flush [Card] |
            FullHouse [Card] | FourKind [Card] | StraightdFlush [Card] |
            RoyalFlush [Card] deriving (Show,Eq,Ord)

--Card type
type Card = (Rank,Denomination)

--Checks if same denom
allSame :: (Eq a) => [a] -> Bool
allSame (c:cs) = and [if c == c' then True else False | c' <- cs]

--Zip everything up with the length of its occurances
pair :: [Card] -> [(Int,[Card])]
pair [] = []
pair (x:ys) = (1 + xsl,(x:xs)) : pair ys'
          where x' = fst x 
                xs = takeWhile (\y -> x' == (fst y)) ys
                ys' = dropWhile (\y -> x' == (fst y)) ys
                xsl = length xs

--Return the [Card] sorted with priority being number of occurances, then value
prime :: [Card] -> [Card]
prime = concat . (map snd) . reverse . sort . pair . reverse . sort

--Ranks and all possible strides for checking straights
ranks = [Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Jack,Queen,King,Ace]
strides = filter ((==5) . length) $ map (take 5) $ tails ranks

--Checks for all the hands-------------------------------------------------------------------------------
isOnePair :: [Card] -> Bool
isOnePair cs = (allSame $ map fst $ take 2 cs) && (not $ allSame $ map fst $ (cs !! 2 : cs !! 3 : []))

isTwoPair :: [Card] -> Bool
isTwoPair cs = (allSame $ map fst $ take 2 cs) && (allSame $ map fst $ (cs !! 2 : cs !! 3 : []))

isThreeKind :: [Card] -> Bool
isThreeKind cs = (allSame $ map fst $ take 3 cs) && (not $ allSame $ map fst $ (cs !! 3 : cs !! 4 : []))

isFullHouse :: [Card] -> Bool
isFullHouse cs = (allSame $ map fst $ take 3 cs) && (allSame $ map fst $ (cs !! 3 : cs !! 4 : []))

isFourKind :: [Card] -> Bool
isFourKind cs = (allSame $ map fst $ take 4 cs)

isStraight :: [Card] -> Bool
isStraight cs = straight && (not sameDenom) && (not royal)
           where sameDenom = allSame $ map snd cs'
                 royal = (map fst cs') == (last strides)
                 straight = elem (map fst cs') strides
                 cs' = sort cs

isStraightFlush :: [Card] -> Bool
isStraightFlush cs = straight && sameDenom && (not royal)
           where sameDenom = allSame $ map snd cs'
                 royal = (map fst cs') == (last strides)
                 straight = elem (map fst cs') strides
                 cs' = sort cs

isRoyalFlush :: [Card] -> Bool
isRoyalFlush cs = royal && sameDenom
           where sameDenom = allSame $ map snd cs'
                 royal = (map fst cs') == (last strides)
                 cs' = sort cs

isFlush :: [Card] -> Bool
isFlush cs = (not straight) && sameDenom
           where sameDenom = allSame $ map snd cs'
                 straight = elem (map fst cs') strides
                 cs' = sort cs
----------------------------------------------------------------------------------------------------------
getCard :: String -> Card
getCard s
        | s == "2H" = (Two,Hearts)
        | s == "3H" = (Three,Hearts)
        | s == "4H" = (Four,Hearts)
        | s == "5H" = (Five,Hearts)
        | s == "6H" = (Six,Hearts)
        | s == "7H" = (Seven,Hearts)
        | s == "8H" = (Eight,Hearts)
        | s == "9H" = (Nine,Hearts)
        | s == "TH" = (Ten,Hearts)
        | s == "JH" = (Jack,Hearts)
        | s == "QH" = (Queen,Hearts)
        | s == "KH" = (King,Hearts)
        | s == "AH" = (Ace,Hearts)
        | s == "2D" = (Two,Diamonds)
        | s == "3D" = (Three,Diamonds)
        | s == "4D" = (Four,Diamonds)
        | s == "5D" = (Five,Diamonds)
        | s == "6D" = (Six,Diamonds)
        | s == "7D" = (Seven,Diamonds)
        | s == "8D" = (Eight,Diamonds)
        | s == "9D" = (Nine,Diamonds)
        | s == "TD" = (Ten,Diamonds)
        | s == "JD" = (Jack,Diamonds)
        | s == "QD" = (Queen,Diamonds)
        | s == "KD" = (King,Diamonds)
        | s == "AD" = (Ace,Diamonds)
        | s == "2C" = (Two,Clubs)
        | s == "3C" = (Three,Clubs)
        | s == "4C" = (Four,Clubs)
        | s == "5C" = (Five,Clubs)
        | s == "6C" = (Six,Clubs)
        | s == "7C" = (Seven,Clubs)
        | s == "8C" = (Eight,Clubs)
        | s == "9C" = (Nine,Clubs)
        | s == "TC" = (Ten,Clubs)
        | s == "JC" = (Jack,Clubs)
        | s == "QC" = (Queen,Clubs)
        | s == "KC" = (King,Clubs)
        | s == "AC" = (Ace,Clubs)
        | s == "2S" = (Two,Spades)
        | s == "3S" = (Three,Spades)
        | s == "4S" = (Four,Spades)
        | s == "5S" = (Five,Spades)
        | s == "6S" = (Six,Spades)
        | s == "7S" = (Seven,Spades)
        | s == "8S" = (Eight,Spades)
        | s == "9S" = (Nine,Spades)
        | s == "TS" = (Ten,Spades)
        | s == "JS" = (Jack,Spades)
        | s == "QS" = (Queen,Spades)
        | s == "KS" = (King,Spades)
        | otherwise = (Ace,Spades)
---------------------------------------------------------------------------------------------------------
--Turns a string of two card representations into [Cards]
convertToHand :: [([String],[String])] -> [([Card],[Card])]
convertToHand ss = map (\(x,y) -> (map getCard x,map getCard y)) ss 

--Counts the number of times player 1 wins
wins :: [([Card],[Card])] -> Int
wins ps = sum [1 | (p1,p2) <- ps, (decideHand p1) > (decideHand p2)]

--Takes a list of cards, and assigns it to a hand
decideHand :: [Card] -> Hand
decideHand cs
           | isRoyalFlush cs' = RoyalFlush cs'
           | isStraightFlush cs' = StraightdFlush cs'
           | isFlush cs' = Flush cs'
           | isStraight cs' = Straight cs'
           | isFourKind cs' = FourKind cs'
           | isFullHouse cs' = FullHouse cs'
           | isThreeKind cs' = ThreeKind cs'
           | isTwoPair cs' = TwoPairs cs'
           | isOnePair cs' = OnePair cs'
           | otherwise = HighCard cs'
           where cs' = prime cs

--Solves
main = do
     contents <- readFile "p054_poker.txt"
     let l = map words $ lines contents
         x = zip (map (take 5) l) (map (drop 5) l)
     print $ wins $ convertToHand x
