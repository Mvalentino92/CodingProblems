import java.math.*;
public class Project_Euler_b116
{
	//Constants I'll need to avoid retyping.
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger ZERO = BigInteger.ZERO;

	//Method to return the combos for current index of the table.
	public static BigInteger getSum(BigInteger[] table, int i, int size)
	{
		//Convert necessary variables to BigInteger versions for arithmetic.
		BigInteger iBig = BigInteger.valueOf(i);
		BigInteger sizeBig = BigInteger.valueOf(size);
		BigInteger sum = iBig.subtract(sizeBig).add(ONE);
		
		//Variables for indexing the table and looking up values.
		int n = 0;
		int lookup = 0;
		while((lookup = i - size - n++) >= size) sum = sum.add(table[lookup]);
		return sum;
	}

	public static void main(String[] args)
	{
		//Three seperate tables.
		BigInteger[] table1 = new BigInteger[51];
		BigInteger[] table2 = new BigInteger[51];
		BigInteger[] table3 = new BigInteger[51];
		
		//All have a different base case index.
		table1[2] = ONE;
		table2[3] = ONE;
		table3[4] = ONE;

		//Starting tablulation to arrive at solution.
		for(int i = 3; i < table1.length; i++)
		{
			table1[i] = getSum(table1,i,2);
			table2[i] = getSum(table2,i,3);
			table3[i] = getSum(table3,i,4);
		}

		//Add up all three seperate solutions and print the result.
		BigInteger solution = table1[50].add(table2[50]).add(table3[50]);
		System.out.println(table2[6]);
		System.out.println(table3[6]);
		System.out.println("The solution is: "+solution);
	}
}
