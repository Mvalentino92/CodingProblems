import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_b135
{
	//Finds the bound of the generators.
	public static int findBound(int target)
	{
		//Initial x,y,z
		int x = 3;
		int y = 2;
		int z = 1;

		//Caluclate initial value, set up while loop to check beneath target.
		int value = getValue(x,y,z);
		while(value < target)
		{
			//How x and y increase.
			x += 2;
			y += 1;

			//Recompute value.
			value = getValue(x,y,z);
		}

		//Return y - 1, (because the value above failed).
		return y - 1;
	}
			
	//Return an list of all the generator sequences. Returns them while value is below the bound. 
	public static ArrayList<int[]> getGenerators(int middleBound)
	{
		ArrayList<int[]> generators = new ArrayList<>();
		for(int i = 2; i <= middleBound; i++)
		{
			//Get initial starting values.
			int increment = i - 1;
			int x = i + increment;
			int y = i;
			int z = i - increment;

			//Create arrays.
			int[] first = {x,y,z};
			int[] second = {x+1,y+1,z+1};
			int[] third = {x+2,y+2,z+2};

			//Add the arrays.
			generators.add(first);
			generators.add(second);
			generators.add(third);
		}
		return generators;
	}

	//Calculate the value without using Math.pow.
	public static int getValue(int x, int y, int z)
	{
		return x*x - y*y - z*z;
	}

	public static void main(String[] args)
	{
		//Get the bound, the generators, and the number array.
		int bound = 1000000;
		int target = findBound(bound);
		ArrayList<int[]> gens = getGenerators(target);
		int[] numbers = new int[bound];

		for(int i = 0; i < gens.size(); i++)
		{
			int[] current = gens.get(i);
			int x = current[0];
			int y = current[1];
			int z = current[2];
			int value = getValue(x,y,z);
			while(value < bound && value > 0)
			{
				//Add a count to that number.
				numbers[value] += 1;

				//Increment the x,y,z.
				x += 5;
				y += 4;
				z += 3;

				//Change value.
				value = getValue(x,y,z);
			}
		}

		//Count the total number of unique solutions.
		int total = 0;
		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == 10) total++;
		}
		System.out.println(total);
	}
}
