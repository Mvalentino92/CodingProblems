public class Project_Euler_a69
{
	public static int bound = 1000000;
	public static int[] primes = sieve(bound);
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
		double max = 0;
		int val = 0;
		for(int i = 2; i <= bound; i++)
		{
			System.out.println(i);
			double current = (double)i/totient(i);
			if(current > max) 
			{
				max = current;
				val = i;
			}
		}
		System.out.println("Max is: "+max+" and occurs at: "+val);
	}
}
