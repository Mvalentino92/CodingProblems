import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a67
{
	public static void main(String[] args) throws Exception
	{
		//Create the ragged array.
		int[][] tri = new int[100][];
		for(int i = 0; i < tri.length; i++) tri[i] = new int[i+1];

		//Read in the file.
		File file = new File("p067_triangle.txt");
		Scanner input = new Scanner(file);
		
		//Scan the file and add the values to the ragged array.
		for(int i = 0; i < tri.length; i++)
		{
			for(int j = 0; j < tri[i].length; j++) tri[i][j] = input.nextInt();
		}

		//Solve the problem.
		for(int i = tri.length - 1; i > 0; i--)
		{
			for(int j = 0; j < tri[i].length - 1; j++)
			{
				int left = tri[i][j];
				int right = tri[i][j+1];
				int larger = left > right ? left : right;
				tri[i-1][j] += larger;
			}
		}

		//Print solution
		System.out.println(tri[0][0]);
	}
}
