import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_b115
{
	public static int getCombos(int[] table, int i)
	{
		int sum = 2;
		for(int j = i - 1; j > 49; j--)
		{
			sum += (i - j) + 1;
			int n = 1;
			while(i - (j + n) > 49)
			{
				sum += table[i - (j + n)] - 1;
				n++;
			}
		}
		return sum;
	}

	public static void main(String[] args)
	{
		int[] table = new int[1001];
		table[50] = 2;
		for(int i = 51; i < table.length; i++)
		{
			table[i] = getCombos(table,i);
			if(table[i] > 1e6)
			{
				System.out.println("The answer is: "+i);
				break;
			}
		}
	}
}
