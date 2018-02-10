public class trial
{
	public static int count = 0;
	public static void getSum(int[] numbers, int sum, int index)
	{
		if(index == numbers.length - 1)
		{
			if(sum != 0)
			{
				count++;
				System.out.println(sum);
			}
			System.out.println(sum + 10);
			count++;
			return;
		}

		getSum(numbers,sum,index + 1);
		getSum(numbers,sum + numbers[index], index + 1);

		return;
	}

	public static int[] sieve(int limit)
	{
		int[] primes = new int[limit-1];
		for(int i = 0; i < primes.length; i++) primes[i] = i+2;
		int i = 0;
		int zeroCount = 0;
		while(primes[i]*primes[i] < primes.length)
		{
			for(int j = i + primes[i]; j < primes.length; j += primes[i])
			{
				if(primes[j] != 0) 
				{
					primes[j] = 0;
					zeroCount++;
				}
			}

			do
			{
				i++;
			} while(primes[i] == 0);
		}

		int newSize = primes.length - zeroCount;
		int[] finalPrimes = new int[newSize];
		int index = 0;
		for(int j = 0; j < primes.length; j++)
		{
			if(primes[j] != 0) finalPrimes[index++] = primes[j];
		}

		return finalPrimes;
	}
		

	public static void main(String[] args)
	{
		int[] arr = {10000000,1000000,100000,10000,1000,100,10};
		getSum(arr,0,0);
		System.out.println("There are "+count+" combinations");

		//int[] finalPrimes = sieve(1000000);
		//for(int i = 0; i < finalPrimes.length; i++) System.out.println(finalPrimes[i]);
	}
}
