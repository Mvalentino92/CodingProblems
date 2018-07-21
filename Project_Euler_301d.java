import java.util.*; 
import java.math.*; 
import java.io.*; 

/* My algorithm uses a recursive and dynamic approach to build off solutions found from these two rules: (on your turn)
 * If there is a zero heap, and the remaining two are of different sizes -> You can move to ultimately win.
 * If two of the heaps are indentical, and the reminaing heap is non-zero -> You can move to ultimately win.
 * Using these rules, I build up from identifying heap set that will ultimately result in victory,
 * assuming you play with perfect stratedgy.
 * I explore all possible pathways with recursion, and use a dynanmic approach to avoid recalculation, 
 * and significantly boost computation time.
 * Since the "keys" are actually arrays of length 3 (The heap set), I used lexographic ordering to index
 * my current heap set, and check it's counterpart in the array. */
public class Project_Euler_301d
{

	//Checking up to 2^7, and initializing my table for dynamic approach.
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
		return index > -1 ? index : 0;
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
		//Verdict depends on whose turn it is. Tracker keeps track of whose turn it is.
		boolean verdict = true;
		if(tracker % 2 == 1) verdict = false;

		//Explore all possible moves from this heapset.
		for(int i = 0; i < nums.length; i++)
		{
			for(int j = nums[i] - 1; j > -1; j--)
			{
				int[] changedArray = change(nums,i,j);
				int indexInTable = getIndex(init,changedArray);

				//My move.
				if(tracker % 2 == 0)
				{
					if(table[indexInTable][0] == 0) verdict = verdict && true;
					else if(table[indexInTable][0] == -1) verdict = verdict && false;
					else 
					{
						if(!(checkMate(changedArray) || checkMate2(changedArray)))
						{
							boolean futureVerdict = win(changedArray, tracker + 1);
							verdict = verdict && futureVerdict;
							if(futureVerdict == true) table[indexInTable][0] = 0;
							else table[indexInTable][0] = -1;
						}
						else verdict = verdict && true;
					}
					if(verdict == false) break;
				}

				//Opponents move.
				else
				{
					if(table[indexInTable][0] == 0) verdict = verdict || false;
					else if(table[indexInTable][0] == -1) verdict = verdict || true;
					else
					{
						if(!(checkMate(changedArray) || checkMate2(changedArray))) 
						{
							boolean futureVerdict = win(changedArray,tracker + 1);
							verdict = verdict || futureVerdict;
							if(futureVerdict == true) table[indexInTable][0] = -1;
							else table[indexInTable][0] = 0;
						}
						else verdict = verdict || false;
					}
					if(verdict == true) break;
				}
			}
		}
		return verdict;
	}

	//Main function
	public static void main(String[] args)
	{
		//Keep track of the sum, and the current powers of 2 value, as well as the exponent.
		int sum = 0;
		int power2Tracker = 1;
		int exponent = 0;

		//Iterate all values and print sums at powers of 2.
		for(int i = 1; i <= n; i++)
		{
			int[] arr = {1*i,2*i,3*i};
			boolean ver = win(arr,0);
			if(ver == true) sum += 1;
			if(i == power2Tracker) 
			{
				System.out.println("2^"+exponent+": "+sum);
				power2Tracker *= 2;
				exponent += 1;
			}
		}
		//Looking at the pattern, the answers are the Fibonacci numbers!
	}
}
