import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a33
{
	public static boolean isReduced(int n, int d)
	{
		for(int i = 2; i <= n; i++)
		{
			if(n % i == 0 && d % i == 0) return false;
		}
		return true;
	}

	public static int[] getReduced(int n, int d)
	{
		int[] arr = {n,d};
		for(int i = n; i > 1; i--)
		{
			if(n % i == 0 && d % i == 0)
			{
				arr[0] = n/i;
				arr[1] = d/i;
				break;
			}
		}
		return arr;
	}


	public static void main(String[] args)
	{
		int nFinal = 1;
		int dFinal = 1;
		for(int n = 1; n <= 9; n++)
		{
			for(int d = n + 1; d <= 9; d++)
			{
				if(!isReduced(n,d)) continue;
				int iter = 99/d;
				for(int i = 2; i <= iter; i++)
				{ 
					int nn = n*i;
					int dd = d*i;
					int nMod = nn % 10;
					int nDiv = nn / 10;
					int dMod = dd % 10;
					int dDiv = dd / 10;

					if(nMod == dMod && nMod != 0)
					{
						int[] rems = getReduced(nDiv,dDiv);
						if(rems[0] == n && rems[1] == d)
						{
							nFinal *= n;
							dFinal *= d;
						}
					}
					else if(nMod == dDiv && nMod != 0)
					{
						int[] rems = getReduced(nDiv,dMod);
						if(rems[0] == n && rems[1] == d)
						{
							nFinal *= n;
							dFinal *= d;
						}
					}
					else if(nDiv == dMod && dMod != 0)
					{
						int[] rems = getReduced(nMod,dDiv);
						if(rems[0] == n && rems[1] == d)
						{
							nFinal *= n;
							dFinal *= d;
						}
					}
					else if(nDiv == dDiv && nDiv != 0)
					{
						int[] rems = getReduced(nMod,dMod);
						if(rems[0] == n && rems[1] == d)
						{
							nFinal *= n;
							dFinal *= d;
						}
					}
				}
			}
		}
		int[] sol = getReduced(nFinal,dFinal);
		System.out.println("Solution is: "+sol[1]);
	}
}
