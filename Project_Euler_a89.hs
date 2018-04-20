import Data.List.Split
{-First converts a number to a list of "digit places".
 - For example 167 would return [100,60,7].
 - Then, it calls the romanNumeral function to turn each number in the list to its respective
 - Roman Numeral representation. And append it to the current string-}
romanNumeral x n
   | n <= x = (romanNumeral x (n*10)) ++ (numToRoman next)
   | otherwise = []
   where next = (mod x (n*10) - mod x n)

{-Converts the digit places to Roman Numeral string representation.
 - Would turn 10 into X, and 3 into III. -}
numToRoman x
   | x < 4 = replicate x 'I'
   | x == 4 = "IV"
   | x == 5 = "V"
   | x < 9 = "V" ++ replicate (x - 5) 'I'
   | x == 9 = "IX"
   | x == 10 = "X"
   | x < 40 = replicate (div x 10) 'X'
   | x == 40 = "XL"
   | x == 50 = "L"
   | x < 90 = "L" ++ replicate ((div x 10) - 5) 'X'
   | x == 90 = "XC"
   | x == 100 = "C"
   | x < 400 = replicate (div x 100) 'C'
   | x == 400 = "CD"
   | x == 500 = "D"
   | x < 900 = "D" ++ replicate ((div x 100) - 5) 'C'
   | x == 900 = "CM"
   | x == 1000 = "M"
   | x <= 4000 = replicate (div x 1000) 'M'
   | otherwise = ""

-- Calls the above two functions to ultimately return the Roman Numeral String.
roman x = romanNumeral x 1

{- Evaluates a list of numbers representing the values for each Roman Numeral character.
 - For example if the list was [2000,500,5,1] this would return 2551.
 - But if the list was [100,1000,5,2] it would return 907 because of the "100, 1000 in the beginning.
 - The rules of subtraction would apply here, because they represent "CM" (equivelent to the familiar "IX" in concept) -}
evalRoman [] = 0
evalRoman [x] = x
evalRoman (x:y:ps)
   | x < y = (evalRoman (y:ps)) + (-x)
   | otherwise = (evalRoman (y:ps)) + x

{- Takes a Roman Numeral String, and returns a list of numbers representing its characters.
 - The Roman Numeral "XXIV", would return [10,10,1,5]. -}
romanToNum :: String -> [Int]
romanToNum [] = []
romanToNum (x:xs) 
   | x == 'I' = 1:romanToNum xs 
   | x == 'V' = 5:romanToNum xs 
   | x == 'X' = 10:romanToNum xs 
   | x == 'L' = 50:romanToNum xs 
   | x == 'C' = 100:romanToNum xs 
   | x == 'D' = 500:romanToNum xs 
   | x == 'M' = 1000:romanToNum xs 
   | otherwise = []

{- Calls many of the above functions to return the most efficient way to write the number as a Roman Numeral string.
 - First it converts a Roman Numeral string to a list of numbers, "XIIIII" -> [10,1,1,1,1,1]
 - Then it evaluates that list of numbers and returns what value the Roman Numeral represented, [10,1,1,1,1,1] -> 15
 - Finally, passes that value to return its most efficient Roman Numeral string representation, 15 -> "XV" -}
romanEfficient ps = roman $ evalRoman $ romanToNum ps

{- Wraps all previous functions and returns how many characters were saved by rewriting the Roman Numeral efficiently.
 - EX: savedChar "XIIIII", will return: 4. Because "XV" has 2 characters, while "XIIIII" has 6. -}
savedChar ps = (length ps) - (length $ romanEfficient ps)

-- Here's main. My first time writing one. I've just been using the interactive terminal. 
main = do
   let file = "p089_roman.txt"
   contents <- readFile file
   let listOfRomans = splitOn "\n" contents
   let total = sum $ map savedChar listOfRomans
   putStr ("The answer is: ")
   print total
