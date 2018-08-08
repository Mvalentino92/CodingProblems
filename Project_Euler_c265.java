import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_c265
{
	//The list that will hold all solutions (including repeats. Hit it with unique).			
	public static ArrayList<Integer> solution = new ArrayList<>();

	//Generates the matrix to hold all combinations in lexographic order.
	public static int[][] binaryLex(int n)
	{
		int col = 0;
		int combos = (int)Math.pow(2,n);
		int stride = combos/2;
		int[][] retval = new int[combos][n];
		while(stride > 0)
		{
			for(int i = 0, j = 0; j < combos; i++, j += stride)
			{
				int digit = i % 2;
				for(int k = j; k < j + stride; k++) retval[k][col] = digit;
			}
			stride /= 2;
			col++;
		}
		return retval;
	}

	//Function that checks to see if all inbetween combos appear in the array. Returns true or false.
	public static boolean containsCandidatesOnly(int[][] candidates, int[][] inbetweeners)
	{
		int i,j,k;
		for(i = 0; i < inbetweeners.length; i++)
		{
			int[] currentB = inbetweeners[i];
			for(j = 0; j < candidates.length; j++)
			{
				int[] currentC = candidates[j];
				for(k = 0; k < currentC.length; k++)
				{
					if(currentB[k] != currentC[k]) break;
				}
				if(k == currentC.length) break;
			}
			if(j == candidates.length) return false;
		}
		return true;
	}

	//Checks to see if theres duplicates in the inbetweeners
	public static boolean uniqueInbetweeners(int[][] inbetweeners)
	{
		for(int i = 0; i < inbetweeners.length - 1; i++)
		{
			for(int j = i + 1; j < inbetweeners.length; j++)
			{
				int[] current = inbetweeners[i];
				int[] toCompare = inbetweeners[j];
				int count = 0;
				for(int k = 0; k < current.length; k++)
				{
					if(current[k] == toCompare[k]) count++;
				}
				if(count == current.length) return false;
			}
		}
		return true;
	}
	

	public static int[][] getNewCandidates(int n, int[][] candidates, int[][] inbetweeners,int indexOfSolution)
	{
		int minus = (n - 1);
		if(indexOfSolution == 0) minus = 1;
		int[][] newCandidates = new int[candidates.length - minus][n];
		int index = 0;
		int i,j,k;
		for(i = 0; i < candidates.length; i++)
		{
			int[] currentC = candidates[i];
			for(j = 0; j < inbetweeners.length; j++)
			{
				int[] currentB = inbetweeners[j];
				int count = 0;
				for(k = 0; k < currentB.length; k++)
				{
					if(currentB[k] == currentC[k]) count++;
				}
				if(count == currentB.length) break;
			}
			if(j == inbetweeners.length)
			{
				for(int ii = 0; ii < currentC.length; ii++)
				{
					newCandidates[index][ii] = currentC[ii];
				}
				index++;
			}
		}
		return newCandidates;
	}


	//Gets all the inbetweeners for the new sticker added to the solution.
	public static int[][] getInbetweeners(int n, int[] solutions, int indexOfSolution)
	{
		int[][] inbetweens = new int[n-1][n];
		int startIndex = indexOfSolution - (n - 1);
		for(int i = 0; i < n - 1; i++)
		{
			int start = startIndex + i;
			for(int j = start, k = 0; j < start + n; j++, k++)
			{	
				inbetweens[i][k] = solutions[j];
			}
		}
		return inbetweens;
	}

	public static int getNumber(int n, int[] binary)
	{
		//Rearrange the array with the zeros in front.
		int[] doubleBinary = new int[binary.length*2];
		for(int i = 0; i < binary.length; i++) doubleBinary[i] = binary[i];
		for(int i = binary.length, j = 0; j < binary.length; i++, j++) doubleBinary[i] = binary[j];
		int i;
		for(i = 0; i <= doubleBinary.length - n; i++)
		{
			int count = 1;
			if(doubleBinary[i] == 0)
			{
				for(int j = i + 1; j < i + n; j++)
				{
					if(doubleBinary[j] == 0) count++;
					else break;
				}
			}
			if(count == n) break;
		}

		int[] zeros = new int[binary.length];
		for(int ii = i, j = 0; j < binary.length; ii++, j++) zeros[j] = doubleBinary[ii]; 

		//Compute the binary.
		int toAdd = 1;
		int retval = 0;
		for(i = zeros.length - 1; i > -1; i--)
		{
			if(zeros[i] == 1) retval += toAdd;
			toAdd *= 2;
		}
		return retval;
	}
		
						        //solution must be 2^n + 1 length
	public static void generateSolutions(int n, int[][] candidates, int[] solutions, int indexOfSolution)
	{
		/*If candidates length is equal to (n - 2), then that means the last sticker was thrown on.
		 * The array is now a complete circle. The first and last index must match. If they dont RETURN.
		 * If they do, we still need to check the inbetweeners that come full circle. Make sure they are all
		 * in the candidates array (they will literally comprise everything in the candidates array.
		 * If they dont RETURN, if they do, THIS IS A SOLUTION.
		 * Add up all the digits binary style (exclusing the last one, because its a repeat.
		 * Add this number to the static array, and return.*/
		if(candidates.length == n - 2)
		{
			if(solutions[0] != solutions[solutions.length-1]) return;
			else
			{
				//Cut off the last repeat of solution matrix, and construct an array to store the last
				//"full circle" numbers to generate inbetweeners.
				int[] finalCheck = new int[(n-2) + (n-1)];
				int[] finalSolutions = new int[solutions.length - 1];
				for(int i = 0; i < finalSolutions.length; i++) finalSolutions[i] = solutions[i];

				//Populate this final check matrix
				int indexToStart = 0;
				for(int i = indexOfSolution - (n-1), j = 0; i < finalSolutions.length; i++, j++) 
				{
					indexToStart++;
					finalCheck[j] = finalSolutions[i];
				}
				for(int i = indexToStart, j = 0; i < finalCheck.length; i++, j++) finalCheck[i] = finalSolutions[j];

				//Prep and populate the final inbetweeners matrix, and poppulate.
				int[][] finalInbetweeners = new int[n-2][n];
				for(int i = 0; i < n - 2; i++)
				{
					for(int j = 0, k = i; j < n; j++, k++) finalInbetweeners[i][j] = finalCheck[k];
				}

				//Check if it's good! Return if either fail.
				if(!uniqueInbetweeners(finalInbetweeners)) return;
				if(!containsCandidatesOnly(candidates,finalInbetweeners)) return;

				//Finally, convert it to binary, and store it.
				int number = getNumber(n,finalSolutions);
				solution.add(number);
				return;

			}
		}
		
		//Iterate the candidates, and check for validity. return at the end!!!
		//After setting back solution (or not even necessary. Itll be overwritten anyway.
		//Prep the addition to index of solution.
		int toAdd = 0;
		if(indexOfSolution == 0) toAdd += n;
		else toAdd += (n - 1);
		for(int i = 0; i < candidates.length; i++)
		{
			if(indexOfSolution != 0)
			{
				if(candidates[i][0] != solutions[indexOfSolution-1]) continue;
			}
			//Prep col to start at appropiate spot. One over (unless it's the first iteration).
			int adjust = 0;
			if(indexOfSolution != 0) adjust++;

			//Remember to increment indexOfSolution if everything is valid.
			for(int j = indexOfSolution, col = 0 + adjust; col < n; j++, col++)
			{
				solutions[j] = candidates[i][col];
			}

			//Now create the matrix to hold the inbetweeners (and current placed potential).
			int[][] inbetweeners;
			if(indexOfSolution == 0)
			{
				inbetweeners = new int[1][n];
				for(int j = 0; j < candidates[0].length; j++) inbetweeners[0][j] = candidates[i][j];
			}
			else inbetweeners = getInbetweeners(n,solutions,indexOfSolution);
			
			//Iterate this matrix, and confirm that all inbetweeners are in fact in the original candidate matrix.
			//If false, continue.
			//Otherwise, prep for recursive call
			if(!uniqueInbetweeners(inbetweeners)) continue;
			if(!containsCandidatesOnly(candidates,inbetweeners)) continue;
			else
			{
				//Generate the new candidate matrix to pass.
				int[][] newCandidates = getNewCandidates(n,candidates,inbetweeners,indexOfSolution);

				//Do the recursive call.
				generateSolutions(n,newCandidates,solutions,indexOfSolution + toAdd);
				for(int iii = indexOfSolution; iii < solutions.length; iii++) solutions[iii] = 0;
				//Change solution back idk
			}

		}
		//If all solutions were exhausted, return.
		return;
	}

	public static void main(String[] args)
	{
		int n = 5;
		int combos = (int)Math.pow(2,n);
		int[][] candidates = binaryLex(n);
		int[] solutions = new int[combos+1];
		generateSolutions(n,candidates,solutions,0);
		Collections.sort(solution);
		long total = 0;
		for(int i = 0; i < solution.size(); i++)
		{
			int toAdd = solution.get(i);
			total += toAdd;
			int index = i + 1;
			while(index < solution.size())
			{
				if(solution.get(index) == toAdd) index++;
				else break;
			}
			i = index;
		}
		System.out.println(total);
	}
}
