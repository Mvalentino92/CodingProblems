public class Project_Euler_a73
{
	//Global variable count, left and right bound.
	public static int count = 0;
	public static double right = 1.0/2.0;
	public static double left = 1.0/3.0;

	//Calculates all reduced fractions of number, and adds to count as necessary.
	public static void countFractions(int number)
	{
		//Begin to iterate and find totients, and check if they are within the bounds.
		for(int i = 2; i < number; i++)
		{
			boolean totient = true;
			int bound = number/2 + 1;
			for(int j = 2; j <= i && j <= bound; j++)
			{
				if(i % j == 0)
				{
					if(number % j == 0)
					{
						totient = false;
						break;
					}
				}
			}
			if(totient)
			{
				double val = (double)i/number;
				if(val > left && val < right) count++;
			}
		}
	}

	public static void main(String[] args)
	{
		for(int i = 2; i <= 12000; i++) countFractions(i);
		System.out.println(count);
	}
}
