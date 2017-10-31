public class Project_Euler_a30
{
	public static void main(String[] args)
	{
		int totalSum = 0;
		for(int i = 10; i < 1e6; i++)
		{
			int n = i;
			int total = 0;
			while(n > 0)
			{
				total += Math.pow(n%10,5);
				n /= 10;
			}
			if(total == i) totalSum += total;
		}
		System.out.println(totalSum);
	}
}
