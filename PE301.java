import java.util.*; 
import java.math.*; 
import java.io.*; 

public class PE301
{

	public static int n = 128;
	public static int[] init = {1*n,2*n,3*n};
	public static int[][] table = lexo(init);

	//Function that will return a list of all possibilities and lexographic order.
	public static int[][] lexo(int[] arr)
	{
		int rows = arr[0]*arr[1]*arr[2] + 1;
		int[][] table = new int[rows][3];
		int index = 1;
		for(int x = 1; x <= arr[0]; x++)
		{
			for(int y = 1; y <= arr[1]; y++)
			{
				for(int z = 1; z <= arr[2]; z++)
				{
					table[index][0] = x;
					table[index][1] = y;
					table[index][2] = z;
				       	index += 1;
				}
			}
		}	
		return table;
	}

	//Function that grabs this combo of numbers place on the lexographic table
	public static int getIndex(int[] arr, int[] current)
	{
		int index = arr[1]*arr[2]*(current[0] - 1) + arr[2]*(current[1] - 1) + current[2];
		return index;
	}

	//Function that checks to see if there are duplicates and a non-zero term.
	public static boolean checkMate(int[] nums)
	{ if(nums[0] == nums[1] && nums[2] != 0) return true;
		if(nums[0] == nums[2] && nums[1] != 0) return true;
		if(nums[1] == nums[2] && nums[0] != 0) return true;
		return false;
	}

	//Function that checks see if there is a zero, and non duplicate terms.
	public static boolean checkMate2(int[] nums)
	{
		if(nums[0] == 0 && nums[1] != nums[2]) return true;
		if(nums[1] == 0 && nums[0] != nums[2]) return true;
		if(nums[2] == 0 && nums[0] != nums[1]) return true;
		return false;
	}

	//Function that deep clones an array
	public static int[] change(int[] nums,int index,int newNumber)
	{
		int length = nums.length;
		int[] newArray = new int[length];
		for(int i = 0; i < length; i++) newArray[i] = nums[i];
		newArray[index] = newNumber;
		return newArray;
	}

	//The main recursive function that will explore and possible pathways, until it is confirmed that you will win Nim.
	public static boolean win(int[] nums, int tracker)
	{
		boolean verdict = true;
		if(tracker % 2 == 1) verdict = false;
		for(int i = 0; i < nums.length; i++)
		{
			for(int j = nums[i] - 1; j > -1; j--)
			{
				int[] changedArray = change(nums,i,j);
				//MY MOVE ON CURRENT. I WANT THESE NUMBERS TO BE "UNCOUNTERABLE" LIKE 2 2 0
				if(tracker % 2 == 0)
				{
					if(checkMate(changedArray) || checkMate2(changedArray)) verdict = verdict && true;
					else 
					{
						int indexInTable = getIndex(init,changedArray);
						if(table[indexInTable][0] == 0) verdict = verdict && true;
						else if(table[indexInTable][0] == -1) verdict = verdict && false;
						else
						{
							boolean futureVerdict = win(changedArray, tracker + 1);
							verdict = verdict && futureVerdict;
							if(futureVerdict == true) table[indexInTable][0] = 0;
							else 
							{
								table[indexInTable][0] = -1;
								break;
							}
						}
					}
				}
				else
				{
					//OPPONENTS MOVE. I DONT WANT THESE TO BE "UNCOUNTERABLE" LIKE 2 4 1
					if(checkMate(changedArray) || checkMate2(changedArray)) verdict = verdict || false;
					else 
					{
						int indexInTable = getIndex(init,changedArray);
						if(table[indexInTable][0] == 0) verdict = verdict || false;
						else if(table[indexInTable][0] == -1) verdict = verdict || true;
						else
						{
							boolean futureVerdict = win(changedArray,tracker + 1);
							verdict = verdict || futureVerdict;
							if(futureVerdict == true) table[indexInTable][0] = -1;
							else table[indexInTable][0] = 0;
						}
					}
				}
			}
		}
		int indexToKill = getIndex(init,nums);
		if(tracker % 2 == 1)
		{
			if(verdict == true) table[indexToKill][0] = 0;
			else table[indexToKill][0] = -1;
		}
		else
		{
			if(verdict == true) table[indexToKill][0] = -1;
			else table[indexToKill][0] = 0;
		}
		return verdict;
	}

	//Main function
	public static void main(String[] args)
	{
		int sum = 0;
		for(int i = 1; i <= n; i++)
		{
			int[] arr = {1*i,2*i,3*i};
			boolean ver = win(arr,0);
			if(ver == true) 
			{
				sum += 1;
				System.out.println("N of "+i+": "+ver);
			}
		}
		System.out.println(sum);
		/*int[] arr = {5,10,15};
		int[][] table = lexo(arr);
		int[] nums = {1,2,3};
		int index = getIndex(arr,nums);
		for(int i = 0; i < table[index].length; i++)
		{
			System.out.print(table[index][i]+" ");
		}
		System.out.println();*/
	}
}
