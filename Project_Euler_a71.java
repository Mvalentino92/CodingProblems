import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a71
{
	//Returns a list of the primes underneath the bound.
	public static int[] sieve(int bound)
	{
		//Initialize the array up to the bound.
		int[] numbers = new int[bound+ 1];
		for(int i = 0; i < numbers.length; i++) numbers[i] = i;

		//Apply the sieve and kill any non prime numbers by replacing them with a 0.
		int killCount = 2;
		for(int i = 2; i <= (int)Math.sqrt(bound); i++)
		{
			for(int j = i + i; j < numbers.length; j += i) 
			{
				if(numbers[j] != 0)
				{
					numbers[j] = 0;
					killCount++;
				}
			}
		}
		
		//Put the remaining primes into an array and return.
		int primes[] = new int[numbers.length - killCount];
		int index = 0;
		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] > 1) primes[index++] = i;
		}
		return primes;
	}

	//Returns true if numbers are coprime
	public static boolean areCoPrime(int small, int large)
	{
		for(int i = 2; i <= Math.sqrt(small); i++)
		{
			if(small % i == 0)
			{
				if(large % i == 0) return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		//Set target and generate list of primes.
		double target = 3/7.0;
		int bound = 1000000;
		int[] primes = sieve(bound);

		//Calculate the starting numerator and denominator. The highest reducable values of 3/7.
		int count = 0;
		int initDenom = 7;
		while(true)
		{
			if(initDenom*2 > bound) break;
			else
			{
				initDenom *= 2;
				count++;
			}
		}
		int initNumer = 3;
		for(int i = 0; i < count; i++) initNumer *= 2;

		/*Begin to iterate the primes larger than the starting denominator, 
		 * and increase the numerator until you hit a number larger than the target.
		 * If the current threshold value for that iterator is larger than the
		 * previous best result, update best. */
		double best = 10;
		int indexOfPrime = 0;
		int numer = initNumer;
		int bestNumer = initNumer;
		while(primes[indexOfPrime] < initDenom) indexOfPrime++;
		for(int i = indexOfPrime; i < primes.length; i++)
		{
			int denom = primes[i];
			while((double)numer/denom < target) numer++;
			double current = (double)--numer/denom;
			double diff = Math.abs(current - target);
			if(diff < best)
			{
				best = current;
				bestNumer = numer;
			}
		}

		//If the denominator being prime doens't work, try the numerator being prime.
		indexOfPrime = 0;
		while(primes[indexOfPrime] < initNumer) indexOfPrime++;
		double secondBest = 10;
		int secondBestNumer = primes[indexOfPrime];
		int denom = initDenom;
		for(int i = indexOfPrime; i < primes.length; i++)
		{
			numer = primes[i];
			while((double)numer/denom >= target) denom++;
			if(denom > bound) break;
			double current = (double)numer/denom;
			double diff = Math.abs(current - target);
			if(diff < secondBest)
			{
				secondBest = current;
				secondBestNumer = numer;
			}
		}
		double diff1 = Math.abs(best - target);
		double diff2 = Math.abs(secondBest - target);
		int finalNumer =  diff1 < diff2 ? bestNumer : secondBestNumer;
		int lowerBound = finalNumer;
		double finalbest = diff1 < diff2 ? diff1 : diff2;
		int start = bound;
		numer = bound/2;
		for(denom = start; denom > 0; denom--)
		{
			while((double)numer/denom >= target) numer--;
			if(numer <= lowerBound) break;
			double current = (double)numer/denom;
			double diff = Math.abs(current - target);
			boolean coPrime = areCoPrime(numer,denom);
			if(diff < finalbest && coPrime)
			{
				finalbest = diff;
				finalNumer = numer;
			}
		}
		System.out.println(finalNumer);
	}
}
