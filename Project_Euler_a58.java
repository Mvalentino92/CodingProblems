/*Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 is 62%.

If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?*/

public class Project_Euler_a58
{
	public static boolean isPrime(int number)
	{
		if(number % 2 == 0) return false;
		for(int i = 3; i <= Math.sqrt(number); i+= 2)
		{
			if(number % i == 0) return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		double total = 5;
		double primes = 3;

		int i = 2;
		int length = i*2 + 1;

		while(primes/total >= 0.10)
		{
			int numberKey = length*length;
			int tr = numberKey - 6*i;
			int tl = numberKey - 4*i;
			int bl = numberKey - 2*i;

			if(isPrime(tr)) primes++;
			if(isPrime(tl)) primes++;
			if(isPrime(bl)) primes++;

			total += 4;
			i++;
			length += 2;
		}
		System.out.println(length-2);
	}
}
