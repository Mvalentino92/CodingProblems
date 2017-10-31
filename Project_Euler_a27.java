public class Project_Euler_a27
{
	public static boolean isPrime(int n)
	{
		boolean divisible = false;
			if(n < 0) return false;
			for(int i = 2; i <= Math.sqrt(n); i++)
			{
				if(n % i == 0)
				{
					divisible = true;
					break;
				}
			}
			if(!divisible) return true;
			else return false;
	}
	public static void main(String[] args)
	{
		int finalTotal = 0;
		int finalAnswer = 0;
		int number = 2;
		while(number <= 1000)
		{
			boolean divisible = false;
			for(int i = 2; i <= Math.sqrt(number); i++)
			{
				if(number % i == 0)
				{
					divisible = true;
					break;
				}
			}
			if(divisible)
			{
				number++;
				continue;
			}
			else
			{
				for(int i = -999; i <= 999; i++)
				{
					int n = 0;
					int total = 0;
					int solution = n*n + i*n + number; 
					while(isPrime(solution))
					{
						total++;
						n++;
						solution = n*n + i*n + number;
					}
					if(total > finalTotal)
					{
						finalTotal = total;
						finalAnswer = i*number;
					}
				}
			}
			number++;
		}
		System.out.println(finalAnswer);
	}
}
