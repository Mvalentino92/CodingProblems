/*145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 *
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.*/

public class Project_Euler_a34
{
	public static int factorial(int n)
	{
		int retval = 1;
		while(n > 1)
		{
			retval *= n--;
		}
		return retval;
	}

	public static void main(String[] args)
	{
		int iterationsWithoutSolution = 0;
		int sum = 0;
		int number = 3;
		while(iterationsWithoutSolution < 1e6)
		{
			int sumOfDigits = 0;
			int n = number;
			while(n > 0)
			{
			       sumOfDigits += factorial(n % 10);
			       n /= 10;	       
			}
			if(sumOfDigits == number)
			{
				sum += number;
				number++;
				iterationsWithoutSolution = 0;
			}
			else
			{
				number++;
				iterationsWithoutSolution++;
			}
		}
		System.out.println(sum);
	}
}
