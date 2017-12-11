/*The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?*/

public class Project_Euler_a35
{
	public static boolean isPrime(int number)
	{
		boolean isDivisible = false;
		boolean primeVerdict = true;
		while(primeVerdict)
		{
			for(int i = 2; i <= Math.sqrt(number); i++)
			{
				if(number % i == 0)
				{
					primeVerdict = false;
					break;
				}
			}
			if(primeVerdict) break;
		}
		return primeVerdict;
	}

	public static int[] convertToArray(int number)
	{
		//Initally setting the array length as 6, because I'm only testing to under 1 million
		int[] maxArray = new int[6];
		int index = 5;
		//Traversing backwards in the array so I do not reverse the integer
		while(number > 0)
		{
			maxArray[index--] = number % 10;
			number /= 10;
		}
		//Setting up the new array of proper length that will hold the integer
		int[] retvalArray = new int[(6 - ++index)];
		for(int i = 0; i < retvalArray.length; i++)
		{
			retvalArray[i] = maxArray[i + index];
		}
		return retvalArray;
	}

	public static int convertToInt(int[] intArray)
	{
		int retval = 0;
		int digitPlace = 1;
		//Setting up a multiplier for the largest digits place
		for(int i = 1; i < intArray.length; i++)
		{
			digitPlace *= 10;
		}
		//Appending the digit for that place, and decrease the digit place by 1 (division by 10)
		for(int i = 0; i < intArray.length; i++)
		{
			retval += intArray[i]*digitPlace;
			digitPlace /= 10;
		}
		return retval;
	}

	//Rotates the elements in the array one forward, and returns it as a new array as well.
	public static int[] rotate(int[] intArray)
	{
		int hold = 0;
		int switchNum = intArray[0];
		for(int i = 1; i < intArray.length; i++)
		{
			hold = intArray[i];
			intArray[i] = switchNum;
			switchNum = hold;
		}
		intArray[0] = switchNum;
		return intArray;
	}
		
	public static void main(String[] args)
	{
		int circularPrimes = 0;
		for(int i = 2; i < 1e6; i++)
		{
			int number = i;
			if(isPrime(number))
			{
				number = convertToInt(rotate(convertToArray(number)));
				int possibleRotations = convertToArray(i).length - 1;
				int primeChecks = 1;
				//If its prime, rotate to all of its possible permutations and then stop.
				//If one of the permutations is not prime, stop.
				while(isPrime(number) && primeChecks <= possibleRotations)
				{
					primeChecks++;
					number = convertToInt(rotate(convertToArray(number)));
				}
				if(primeChecks == possibleRotations + 1) circularPrimes++;
			}
		}
		System.out.println("There are "+circularPrimes+" circular primes below 1 million.");
	}
}
