import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a81
{
	public static void main(String[] args) throws Exception
	{
		//Initialize matrix.
		int size = 80;
		int[][] matrix = new int[size][size];

		//Read in the file.
		File file = new File("p081_matrix.txt");
		Scanner input = new Scanner(file);
		
		//Scan the file and add the values to the matrix
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++) matrix[i][j] = input.nextInt();
		}

		//Construct and populate minimum matrix with base cases.
		int[][] mins = new int[size][size];
		mins[0][0] = matrix[0][0];
		for(int i = 1; i < matrix.length; i++) mins[0][i] = matrix[0][i] + mins[0][i-1];
		for(int j = 1; j < matrix.length; j++) mins[j][0] += matrix[j][0] + mins[j-1][0];

		//Iterate and find the minimum.
		for(int i = 1; i < matrix.length; i++)
		{
			for(int j = i; j < matrix.length; j++)
			{
				//Add m x n
				mins[i][j] = mins[i-1][j] < mins[i][j-1] ? mins[i-1][j] : mins[i][j-1];
				mins[i][j] += matrix[i][j];

				//Add n x m
				mins[j][i] = mins[j-1][i] < mins[j][i-1] ? mins[j-1][i] : mins[j][i-1];
				mins[j][i] += matrix[j][i];
			}
		}
		System.out.println(mins[size-1][size-1]);
	}
}
