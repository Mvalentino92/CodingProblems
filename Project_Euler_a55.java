public class Project_Euler_a55
{
	//Checking if the numbers are palindromic in under 50 iterations.
	public static boolean palindrone(long x)
	{
		int count = 0;
		while(count < 50)
		{
			long multiplier = 1;
			int digits = 0;
			long digitList[] = new long[50];
			long xHold = x;
			while(xHold > 0)
			{
				digitList[digits] = xHold % 10;
				xHold /= 10;
				digits++;
			}
			for(int i = 1; i < digits; i++)
			{
				multiplier *= 10;
			}
			for(int i = 0; i < digits; i++)
			{
				x += digitList[i]*multiplier;
				multiplier /= 10;
			}
			long xCheck = x;
			multiplier = 1;
			digits = 0;
			digitList = new long[50];
			long y = 0;
			while(xCheck > 0)
			{
				digitList[digits] = xCheck % 10;
				xCheck /= 10;
				digits++;
			}
			for(int i = 1; i < digits;i++)
			{
				multiplier *= 10;
			}
			for(int i = 0; i < digits; i++)
			{
				y += digitList[i]*multiplier;
				multiplier /= 10;
			}
			if(x == y) return false;
			else count++;
		}
		return true;
	}
	//Checking if numbers under 10000 are Lychrel Numbers
	public static void main(String[] args)
	{
		int count = 0;
		for(int i = 10; i < 10000; i++)
		{
			if(palindrone(i)) count++;
		}
		System.out.println(count);
	}
}

