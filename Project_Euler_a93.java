import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a93
{
	public static int[] numbers = new int[2000];
	public static int[][] combos = makeCombos();

	public static int[][] makeCombos()
	{
		int[][] combos = new int[24][4];
		int count = 0;
		for(int i = 1; i < 5; i++)
		{
			for(int j = 1; j < 5; j++)
			{
				for(int k = 1; k < 5; k++)
				{
					for(int r = 1; r < 5; r++)
					{
						if(i == j || i == k || i == r
						|| j == k || j == r
						|| k == r) continue;
						combos[count][0] = i;
						combos[count][1] = j;
						combos[count][2] = k;
						combos[count][3] = r;
						count++;
					}
				}
			}
		}
		return combos;
	}

	public static void changeNumbers(int original, int change)
	{
		for(int i = 0; i < combos.length; i++)
		{
			for(int j = 0; j < combos[i].length; j++)
			{
				if(combos[i][j] == original) combos[i][j] = change;
			}
		}
	}

	public static void arith()
	{
		for(int i = 0; i < combos.length; i++)
		{
			int[] current = combos[i];
			for(int j = 0; j < 1000000; j++)
			{
				double sum = 0.0;
				for(int k = 0; k < current.length; k++)
				{
					double val = (double)current[k];
					int rand = (int)(Math.random()*4);
					if(rand == 0) sum += val;
					else if(rand == 1) sum -= val;
					else if(rand == 2) sum *= val;
					else sum /= val;
				}
				if(sum % 1 == 0 && sum > 0)
				{
					numbers[(int)sum] = 1;
				}
			}
		}
	}



	public static void main(String[] args)
	{
		//Just some trial and error at this point. I assumed the answer would contain 1 and 2, and the 3 and 4 would both change under 10.
		changeNumbers(3,5);
		changeNumbers(4,8);
		arith();
		int amount = 0;
		for(int i = 1; i < numbers.length; i++)
		{
			if(numbers[i] == 1) amount++;
			else break;
		}
		System.out.println(amount);
	}
}

