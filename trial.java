public class trial
{
	public static int count = 0;

	//Gets a collection of the possible combinations for that number
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

	//Generates a list of all the primes up to a certain limit
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

	//Checks if a number is prime or not.
	public static boolean isPrime(int number)
	{
		if(number % 2 == 0) return false;
		for(int i = 3; i <= Math.sqrt(number); i += 2)
		{
			if(number % i == 0) return false;
		}

		return true;
	}

	//Returns the number of digits a number a contains.
	public static int numberOfDigits(int number)
	{
		int digitCount = 0;
		while(number > 0)
		{
			digitCount++;
			number /= 10;
		}

		return digitCount;
	}

	//Returns an array of multiples of ten for how long the supplied number is.
	public static int[] makeMutiplesOfTenArray(int numberOfDigits)
	{
		int[] multiplesOfTen = new int[numberOfDigits-1];
		int index = 0;
		for(int i = multiplesOfTen.length; i > 0; i--)
		{
			int nextMultiple = 1;
			for(int j = 0; j < i; j++) nextMultiple *= 10;
			multiplesOfTen[index++] = nextMultiple;
		}

		return multiplesOfTen;
	}
		
	public static boolean isFit(int number, int digits)
	{
		int target = -1;
		while(true)
		{
			if(digits % 10 != 0)
			{
				digits /= 10;
				if(number % 10 > 2) return false;
				target = number % 10;
				number /= 10;
				break;
			}
			else
			{
				digits /= 10;
				number /= 10;
			}
		}

		while(digits > 0)
		{
			if(digits % 10 != 0)
			{
				if(number % 10 != target) return false;
				digits /= 10;
				number /= 10;
			}
			else 
			{
				digits /= 10;
				number /= 10;
			}
		}

		return true;
	}

	public static void main(String[] args)
	{
		/*int[] arr = {10000000,1000000,100000,10000,1000,100,10};
		//getSum(arr,0,0);
		//System.out.println("There are "+count+" combinations");

		//int[] finalPrimes = sieve(1000000);
		//System.out.println(finalPrimes.length);
		//for(int i = 0; i < finalPrimes.length; i++) System.out.println(finalPrimes[i]);
		System.out.println(numberOfDigits(45654));
		int[] mult = makeMutiplesOfTenArray(numberOfDigits(56545));
		for(int i = 0; i < mult.length; i++) System.out.println(mult[i]);*/
		System.out.println(isFit(16223,10110));
	}
}
