import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a82
{
	public static void main(String[] args) throws Exception
	{
		//Initialize matrix.
		int size = 80;
		int[][] matrix = new int[size][size];

		//Read in the file.
		File file = new File("p082_matrix.txt");
		Scanner input = new Scanner(file);
		
		//Scan the file and add the values to the matrix
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[i].length; j++) matrix[i][j] = input.nextInt();
		}

		//Construct and populate minimum matrix with base cases.
		int[][] mins = new int[size][size];
		for(int i = 0; i < matrix.length; i++) mins[i][0] = matrix[i][0];

		//Iterate the columns (moving to the right)
		for(int col = 1; col < matrix.length - 1; col++)
		{

			//Iterate the rows, and check for mins (look up and left once, then recur looking down and left, down and left)
			for(int row = 0; row < matrix.length; row++)
			{
				//Set the current min as either directly to left or above (if applicable)
				int min = mins[row][col-1];
				if(row - 1 > -1) min = mins[row-1][col] < min ? mins[row-1][col] : min;

				//Begin to iterate down the matrix, and stop one down iterations yield higher than current min.
				int under = 0;
				for(int down = row + 1; down < matrix.length; down++)
				{
					//If adding under is greater than min, stop.
					under += matrix[down][col];
					if(under > min) break;

					//Check if adding left is smaller than current min.
					int left = under + mins[down][col-1];
					min = left < min ? left : min;
				}
				//This is the current min for this iteration, add it to mins matrix.
				mins[row][col] = min + matrix[row][col];
			}
		}
		//Populate the final column of the mins matrix by adding the second to last column of mins, to the last column of original matrix.
		for(int i = 0; i < matrix.length; i++) mins[i][size-1] = mins[i][size-2] + matrix[i][size-1];

		//Find the min in the last column of the mins matrix.
		int solution = mins[0][size-1];
		for(int i = 1; i < matrix.length; i++)
		{
			if(mins[i][size-1] < solution) solution = mins[i][size-1];
		}
		System.out.println("The solution is: "+solution);
	}
}
