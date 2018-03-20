import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a114
{
	//Some constants I'll need so I dont have to retype.
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger TWO = new BigInteger("2");

	/*I want to keep i, and j is ints, so I can use them in the loops
	 * and index with them easier. So I'm copying there values into BigInters each time.*/
	public static BigInteger getCombos(BigInteger[] table, int i)
	{
		BigInteger iBig = BigInteger.valueOf(i);
		BigInteger sum = TWO;
		for(int j = i - 1; j > 2; j--)
		{
			//Doing the BigInteger arithmetic in steps for readbility.
			BigInteger jBig = BigInteger.valueOf(j);
			BigInteger iMinusj = iBig.subtract(jBig);
			BigInteger plusOne = iMinusj.add(ONE);
			sum = sum.add(plusOne);
			int n = 1;
			while(i - (j + n) > 2)
			{
				BigInteger toAdd = table[i - (j + n)];
				sum = sum.add(toAdd);
				sum = sum.subtract(ONE);
				n++;
			}
		}
		return sum;
	}

	public static void main(String[] args)
	{
		BigInteger[] table = new BigInteger[51];
		table[3] = TWO;
		for(int i = 4; i < table.length; i++)
		{
			table[i] = getCombos(table,i);
		}

		System.out.println("The answer is: "+table[50]);
	}
}
