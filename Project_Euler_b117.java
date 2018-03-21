import java.math.*;
public class Project_Euler_b117
{
	//Constant variables I'll need to avoid retyping.
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger ZERO = BigInteger.ZERO;

	//This method will now need all three tables.
	public static BigInteger getSum(BigInteger[] table1, BigInteger[] table2,
					BigInteger[] table3, int i, int size)
	{
		BigInteger iBig = BigInteger.valueOf(i);
		BigInteger sizeBig = BigInteger.valueOf(size);
		BigInteger sum = iBig.subtract(sizeBig).add(ONE);

		//One n variable for each table.
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;

		//One lookup variable for each table.
		int lookup1 = 0;
		int lookup2 = 0;
		int lookup3 = 0;

		//One while loop for each table, using eahc tables respective copies of variables.
		while((lookup1 = i - size - n1++) >= 2) sum = sum.add(table1[lookup1]);
		while((lookup2 = i - size - n2++) >= 3) sum = sum.add(table2[lookup2]);
		while((lookup3 = i - size - n3++) >= 4) sum = sum.add(table3[lookup3]);

		//Return the sum of them all. Order of generation wont effect the sum
		//They use eachothers previous values, not the current iterations.
		return sum;
	}

	public static void main(String[] args)
	{
		//The three tables that will each generate values for 2,3 and 4.
		BigInteger[] table1 = new BigInteger[51];
		BigInteger[] table2 = new BigInteger[51];
		BigInteger[] table3 = new BigInteger[51];

		//Fill the array with 0's, to avoid null pointer exception.
		for(int i = 0; i < table3.length; i++)
		{
			table1[i] = ZERO;
			table2[i] = ZERO;
			table3[i] = ZERO;
		}

		//Different base case for each table.
		table1[2] = ONE;
		table2[3] = ONE;
		table3[4] = ONE;

		//Begin tabulation, again order doesnt matter.
		for(int i = 3; i < table1.length; i++)
		{
			table1[i] = getSum(table1,table2,table3,i,2);
			table2[i] = getSum(table1,table2,table3,i,3);
			table3[i] = getSum(table1,table2,table3,i,4);
		}

		//Add one to the solution to account for the empty solution.
		BigInteger solution = table1[50].add(table2[50]).add(table3[50]);
		System.out.println("The solution is: "+solution.add(ONE));
	}
}
