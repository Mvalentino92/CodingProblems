public class Project_Euler_a32
{
	//Checks if the individual digits of of the 2 numbers are the product are unique and from 1 to 9.
	public static boolean unique(int a, int b)
	{
		int numbers[] = new int[9];
		int c = a*b;
		int cHold = c;
		int count = 0;
		while(a > 0)
		{
			numbers[count] = a % 10;
			if(numbers[count] == 0) return false;
			a /= 10;
			count++;
		}
		while(b > 0)
		{
			numbers[count] = b % 10;
			if(numbers[count] == 0) return false;
			b /= 10;
			count++;
		}
		boolean uniq = true;
		for(int i = 0; i < count; i++)
		{
			for(int j = 0; j < count; j++)
			{
				if(i == j) continue;
				else if(numbers[i] == numbers[j])
				{
					uniq = false;
					break;
				}
			}
			if(!uniq) break;
		}
		if(uniq)
		{
			while(c > 0 && count < 9)
			{
				numbers[count] = c % 10;
				if(numbers[count] == 0) return false;
				c /= 10;
				count++;
			}
			if(c > 0) return false;
			else
			{
				uniq = true;
				for(int i = 0; i < count; i++)
				{
					for(int j = 0; j < count; j++)
					{
						if(i == j) continue;
						else if(numbers[i] == numbers[j])
						{
							uniq = false;
							break;
						}
					}
					if(!uniq) break;
				}
				if(uniq && count == 9) return true;
				else return false;
			}
		}
		else return false;
	}

	//Testing the number ranges I believe could yield results.
	public static void main(String[] args)
	{
		int sum = 0;
		int[] redun = new int[500];
		int redoCount = 0;
		for(int i = 1; i < 10; i++)
		{
			for(int j = 4999; j > 999; j--)
			{
				if(unique(i,j))
				{
					boolean verd = true;
					redun[redoCount] = i*j;
					for(int k  = 0; k < redun.length; k++)
					{
						if(redoCount == k) continue;
						else
						{
							if(redun[redoCount] == redun[k])
							{
								verd = false;
								break;
							}
						}
					}
					if(verd)
					{
						sum += (i*j);
						System.out.printf("i is: %d, j is: %d, and product is %d\n",i,j,i*j);
					}
					redoCount++;
				}
			}
		}
		for(int i = 10; i < 100; i++)
		{
			for(int j = 999; j > 99; j--)
			{
				if(unique(i,j))
				{
					boolean verd = true;
					redun[redoCount] = i*j;
					for(int k  = 0; k < redun.length; k++)
					{
						if(k == redoCount) continue;
						else
						{
							if(redun[redoCount] == redun[k])
							{
								verd = false;
								break;
							}
						}
					}
					if(verd)
					{
						sum += (i*j);
						System.out.printf("i is: %d, j is: %d, and product is %d\n",i,j,i*j);
					}
					redoCount++;
				}
			}
		}
		System.out.println(sum);
	}
}










