/*By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, being the first member of this family, is the smallest prime with this property.

Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.*/

import java.util.*;;
public class Project_Euler_a51
{
	//I need this value to change everytime a boolean method call is successful.
	public static int targetNumber = 0;

	//Gets a collection of the possible combinations for that number
	public static void getSum(int[] numbers,ArrayList<Integer> addNumbers, int sum, int index)
	{
		if(index == numbers.length - 1)
		{
			if(sum != 0) addNumbers.add(sum);
			addNumbers.add(sum+10);
			return;
		}

		getSum(numbers,addNumbers,sum,index + 1);
		getSum(numbers,addNumbers,sum + numbers[index], index + 1);

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
	
	//Checks the current number as identical digits where needed for the supplied digit combo.	
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

		targetNumber = target;
		return true;
	}

	public static void main(String[] args)
	{
		//I suspect the answer is below 850000.
		int[] finalPrimes = sieve(850000);

		//Keeping track of digit combos I've already made.
		Hashtable<Integer,ArrayList<Integer>> table = new Hashtable<Integer,ArrayList<Integer>>();

		boolean foundIt = false;
		int smallestPrime = 0;
		for(int i = 50; i < finalPrimes.length; i++)
		{
			if(foundIt) break;
			int digitCount = numberOfDigits(finalPrimes[i]);
			if(table.get(digitCount) == null)
			{
				int[] newArray = makeMutiplesOfTenArray(digitCount);
				ArrayList<Integer> digitCombos = new ArrayList<>();
				getSum(newArray,digitCombos,0,0);
				table.put(digitCount,digitCombos);
			}

			ArrayList<Integer> combos = table.get(digitCount);
			for(int j = 0; j < combos.size(); j++)
			{
				if(isFit(finalPrimes[i],combos.get(j)))
				{
					int limit = 10 - (targetNumber + 1);
					int primeCount = 0;
					int potentialPrime = finalPrimes[i];
					for(int k = 0; k < limit; k++)
					{
						potentialPrime += combos.get(j);
						if(isPrime(potentialPrime)) primeCount++;
					}
					if(primeCount == 7)
					{
						foundIt = true;
						smallestPrime = finalPrimes[i];
						break;
					}
				}
				else continue;
			}
		}
		System.out.println("The answer is "+smallestPrime+".");
	}
}
