public class Project_Euler_a41
{
	public static int getNextPrime(int currentPrime)
	{
		boolean foundPrime = false;
		currentPrime++;
		while(!foundPrime)
		{
			boolean isPrime = true;
			for(int i = 2; i <= Math.sqrt(currentPrime); i++)
			{
					if(currentPrime % i == 0)
					{
						isPrime = false;
						break;
					}
			}
			if(isPrime) foundPrime = true;
			else currentPrime++;
		}
		return currentPrime;
	}

	//Checks if the number is pandigital, and also pandigital from 1 to its lenth
	public static boolean alreadyExists(int number, int digit)
	{
		int count = 0;
		int triangleCount = 0;
		int digitSum = 0;
		while(number > 0)
		{
			if(number % 10 == digit && ++count == 2) return false;
			else 
			{
				triangleCount++;
				digitSum += number % 10;
				number /= 10;
			}
		}
		int triangleNumber = (triangleCount*(triangleCount + 1))/2;
		if(triangleNumber == digitSum) return true;
		else return false;
	}

	public static void main(String[] args)
	{
		int currentPrime = 2;
		boolean isPaldigital = true;
		int best = 0;
		while(currentPrime < 1e7)
		{
			int primeHolder = currentPrime;
			isPaldigital = true;
			while(primeHolder > 0)
			{
				if(alreadyExists(currentPrime,primeHolder % 10) &&
						primeHolder % 10 != 0) primeHolder /= 10;
				else
				{
					isPaldigital = false;
					break;
				}
			}
			if(isPaldigital) best = currentPrime;
			currentPrime = getNextPrime(currentPrime);
		}
		System.out.println(best);
	}
}

