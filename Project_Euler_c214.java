import java.math.*;
public class Project_Euler_c214
{
	//Static variables I'll need. Arrays of primes, and totients of primes.
	public static int size = 40000000;
	public static int[] totes = new int[size + 1];
	public static final int[] primes = sieve(size);

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
			if(numbers[i] > 1)
			{
				primes[index++] = i;
				totes[i] = i-1; //Update the totient number for primes (prime - 1).
			}
		}
		return primes;
	}

	//Returns the number of totients using prime factors of the number.
	public static int totient(int number)
	{
		//Keep track of current index, update tote, and track the prime factors for uniqueness.
		int index = 0;
		double tote = number;
		int lastNumber = 0;

		//Keep grabbing prime factors and using them if they are unique.
		while(number != 1)
		{
			int remainder = number % primes[index];
			while(remainder != 0) remainder = number % primes[++index];
			number /= primes[index];
			if(primes[index] != lastNumber) 
			{
				tote *= (1 - 1.0/primes[index]);
				lastNumber = primes[index];
			}
		}
		return (int)tote;
	}

	public static void main(String[] args)
	{
		//Initialize starting variables and chain array (Dynamically generated).
		BigInteger sum = BigInteger.ZERO;
		int target = 25;
		int[] chains = new int[size+1];
		chains[1] = 1;

		//Iterate to bound. If it's a prime, just grab the value.
		for(int i = 2; i < chains.length; i++) 
		{
			if(totes[i] != 0) 
			{
				chains[i] = chains[i-1] + 1;
				if(chains[i] == target) sum = sum.add(BigInteger.valueOf(i));
			}
			else chains[i] = chains[totient(i)] + 1;
		}
		System.out.println(sum);
	}
}
