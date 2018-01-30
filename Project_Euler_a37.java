import java.util.*;
public class Project_Euler_a37
{
	public static int nextPrime(int currentPrime)
	{
		int max = 10000000;
		while(currentPrime < max)
		{
			boolean isPrime = true;
			currentPrime++;
			if(currentPrime % 2 == 0) continue;
			for(int i = 3; i <= Math.sqrt(currentPrime); i+=2)
			{
				if(currentPrime % i == 0)
				{
					isPrime = false;
					break;
				}
			}
			if(isPrime) return currentPrime;
		}
		return 0;
	}

	public static boolean isPrime(int number)
	{
		if(number == 2) return true;
		if(number % 2 == 0 || number == 1) return false;
		for(int i = 3; i <= Math.sqrt(number); i+=2)
		{
			if(number % i == 0) return false;
		}
		return true;
	}

	public static int chopRight(int number)
	{
		int chopCount = 0;
		while(number >= 10)
		{
			number /= 10;
			if(isPrime(number)) chopCount++;
			else return -1;
		}
		return chopCount;
	}

	public static boolean chopLeft(int number, int chopCount)
	{
		int modCut = 10;
		for(int i = 1; i < chopCount; i++) modCut *= 10;

		while(modCut >= 10)
		{
			number = number % modCut;
			modCut /= 10;
			if(!(isPrime(number))) return false;
		}
		return true;
	}


			

	public static void main(String[] args)
	{
		ArrayList<Integer> nums = new ArrayList<>();
		int sum = 0;
		int result = 11;
		while(result != 0)
		{
			result = nextPrime(result);
			int chop = chopRight(result);
			if(chop != -1)
			{
				if(chopLeft(result,chop)) 
				{
					nums.add(result);
					sum += result;
				}
			}
		}
		System.out.println(sum);
		for(int i = 0; i < nums.size(); i++)
		{
			System.out.println(nums.get(i));
		}
	}
}
