# Project_Euler-
Tackling it from the top. 

Here I'll test my problem solving skills by taking on Project Euler problem in various languages. 
I've messed around before, and solved random problems (Even before I made my account), but now I'll actually track my progress and save my files!

## Problems Solved:
**Total - 85**
<br>**Saved as code - 74**

## Notable Attempts:

**PE328_Attempt.java:** An attempt at Project Euler problem 328 (which is ranked at 95% difficulty). The problem has to do with what it describes as the Lowest Cost Search, of a sequence of numbers from 1..N. I spent a lot of time on this problem, and thought about it many different ways. I do have a working purely recursive solution, but it is only good for small N. The time complexity is about N^2/8, which is of course terrible. This algorithm did serve it's intended purpose though, which was to allow me to study the pattern and double check I understood the problem 100 percent correctly. After studying the solutions for small N, I put together another algorithm that was based off of how I believed the problems solutions behaved. 
It is hard for me to say what I was wrong about, as parts of the algorithm were based off of assumptions about things I couldnt test for large N, but observed for small n. Regardless of never arriving at the solution, I was happy with my results, and even if it was based off non-truths for all N, it was fantastic practice chasing down patterns, bounds and the behavior of solutions I was seeing for small N, and logically trying to confirm for all N.  

**PE484_Attempt.java:** An attempt at Project Euler problem 484 (which is ranked at 100% difficulty). The problems demands that you find the GCD of a number and it's arithmetic derivative. The algorithm I use bypasses the need to even calculate the arithmetic derivative of the numbers, thereby removing the need to calculate the GCD the conventional way as well. I do calculate the GCD by only using the original numbers prime factors. Since I need only need the prime factors of the numbers, it is more efficient to generate all the possible prime factor combinations that are possible below the threshold, then to iterate every number and calculate its prime factors. Doing it this way also allows me to automatically skip finding primes past half of the threshold as well.
