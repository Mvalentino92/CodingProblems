import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a79
{
	//Static variables that hold information about numbers appearing and their occurances and order in passcode.
	public static boolean[][] mat = new boolean[10][10];
	public static int[] numbers = new int[10];
	public static boolean[] usedNumbers = new boolean[10];

	//Marks the numbers that the current number precedes in matrix.
	public static void markNumbers(int number, ArrayList<int[]> logs)
	{
		for(int i = 0; i < logs.size(); i++)
		{
			int[] list = logs.get(i);
			for(int j = 0; j < list.length; j++)
			{
				usedNumbers[list[j]] = true;
				if(list[j] == number)
				{
					for(int k = j + 1; k < list.length; k++) mat[number][list[k]] = true;
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		File file = new File("p079_keylog.txt");
		Scanner input = new Scanner(file);

		ArrayList<int[]> logs = new ArrayList<>();
		while(input.hasNextInt())
		{
			int current = input.nextInt();
			int[] list = new int[3];
			int index = 2;
			while(current > 0)
			{
				list[index--] = current % 10;
				current /= 10;
			}
			logs.add(list);
		}
		
		//Create the matrix of true values for numbers.
		for(int i = 0; i < numbers.length; i++) markNumbers(i,logs);

		//Initialize numbers.
		int count = 0;
		for(int i = 0; i < usedNumbers.length; i++)
		{
			if(usedNumbers[i] == true)
			{
				numbers[i]++;
				count++;
			}
		}

		//Go through and add the actual amount for each.
		for(int i = 0; i < mat.length; i++)
		{
			int sum = 0;
			for(int j = 0; j < mat[0].length; j++)
			{
				if(mat[i][j] == true) sum++;
			}
			numbers[i] += sum;
		}
		
		//Go through and print each number in ascending order.
		System.out.print("The passcode is: ");
		for(int i = 0; i < count; i++)
		{
			int max = 0;
			int maxIndex = 0;
			for(int j = 0; j < numbers.length; j++)
			{
				if(numbers[j] > max) 
				{
					max = numbers[j];
					maxIndex = j;
				}
			}
			System.out.print(maxIndex);
			numbers[maxIndex] = 0;
		}
		System.out.println();
	}
}
