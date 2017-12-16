/*It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
 *
 * 9 = 7 + 2×12
 * 15 = 7 + 2×22
 * 21 = 3 + 2×32
 * 25 = 7 + 2×32
 * 27 = 19 + 2×22
 * 33 = 31 + 2×12
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 * */
public class Project_Euler_a46
{
	public static boolean isPrime(int number)
	{
		for(int i = 2; i <= Math.sqrt(number); i++)
		{
			if(number % i == 0)
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		boolean foundNumber = false;
		int number = 9;
		while(!foundNumber)
		{
			if(!isPrime(number))
			{
				int square = 1;
				boolean conjecture = false;
				while(2*square*square < number - 1)
				{
					int possiblePrime = number - 2*square*square;
					if(isPrime(possiblePrime))
					{
						conjecture = true;
						break;
					}
					else square++;
				}
				if(!conjecture) foundNumber = true;
				else number += 2;
			}
			else number += 2;
		}
		System.out.println("The first number to prove Goldbach\'s conjecture false is: "+number);
	}
}
