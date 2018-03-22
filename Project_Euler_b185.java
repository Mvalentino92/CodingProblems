import java.util.*;

//A class I'll need to store data about the numbers.
class Spot
{
	public int number;
	public boolean taken = false;
	public Spot(int number)	{this.number = number;}
}

public class Project_Euler_b185
{
	//Static variables so I don't have to pass them to methods.
	public static Spot[][] table = new Spot[22][16];
	public static Scanner input = new Scanner(System.in);
	public static int[] numberCorrect = {0,1,1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3};

	/*Checks if the current spot has "trues" above it. Or numbers were assuming are
	 * true for the current iteration. From a top down approach, numbers are considered to be in
	 * the correct spot until proven otherwise*/
	public static int[] trueAbove(int i, int j)
	{
		for(i = i - 1; i > -1; i--)
		{
			if(table[i][j].taken == true)
			{
				int[] coordinates = {i,j};
				return coordinates;
			}
		}
		int[] falseCoordinates = {-1,-1};
		return falseCoordinates;
	}

	/*If the current column doesn't have any assumed trues above, this current spot can be true
	 * as long as it's number is unique. Because if no numbers above in this column are true
	 * then they all must be false and in the wrong spots.*/
	public static boolean isUnique(int i, int j)
	{
		for(int k = i - 1; k > -1; k--)
		{
			if(table[i][j].number == table[k][j].number) return false;
		}
		return true; 	
	}

	/* Once a row hits it's quota for correct/true positions, all the rest of the numbers
	 * must be in the wrong place then. So if any have true's above them, and the number in that
	 * column matches the number of the true, well that just can't be then right? That's saying
	 * a row that has 2 in the correct position, has 3 correct instead.*/
	public static boolean restFalse(int i, int j)
	{
		for(j = j + 1; j < table[0].length; j++)
		{
			int[] spot = trueAbove(i,j);
			if(spot[0] != -1)
			{
				if(table[i][j].number == table[spot[0]][spot[1]].number) return false;
			}
		}
		return true;
	}

	/*Generates an ArrayList with the numbers 0-9.
	 * For use with the final compiling of the solved sequence.*/
	public static ArrayList<Integer> generateList()
	{
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < 10; i++) list.add(i);
		return list;
	}

	public static void findSolution(int i, int j, int correct)
	{
		/*If we ever make it all the way through the matrix
		 * that means that all the number of correct sync up correctly for 
		 * each row, and nothing interferes with each other.
		 * But, it is possible that one of the column will be a number that wasn't guessed.
		 * If this is the case, putting any unique available number for that column,
		 * will not violate any of the rows numbers of correct. So as I'm printing
		 * the numbers associated with the true's for each column,
		 * if I need to fill in a nonviolating gap, I do. 
		 * It happens to be the number 9 in the 13th position*/
		if(i == table.length)
		{
			for(int col = 0; col < table[0].length; col++)
			{
				ArrayList<Integer> list = generateList();
				int count = 0;
				for(int row = 0; row < table.length; row++)
				{
					int num = table[row][col].number;
					if(table[row][col].taken == true)
					{
						System.out.print(num);
						break;
					}
					list.remove(Integer.valueOf(num));
					count++;
				}
				if(list.size() > 0 && count == 22) System.out.print(list.get(0));
			}
			System.out.println();
			System.exit(1);
		}

		/*Iterate all the spots of the matrix, and check for if a spot can be
		 * marked as correct. If it can't, keep moving til you hit the end of current row.
		 * Backtrack, when you either hit the end of a row without satisfying the number
		 * of correct.
		 * Backtrack, when during a backtrack you hit a spot that was marked true
		 * because spots above it were true.*/
		for(j = j; j < table[0].length; j++)
		{
			int[] above = trueAbove(i,j);
			if(above[0] != -1)
			{
				if(table[i][j].number == table[above[0]][above[1]].number)
				{
					if(correct == numberCorrect[i] - 1)
					{
						table[i][j].taken = true;
						if(restFalse(i,j)) findSolution(i + 1, 0, 0);
						table[i][j].taken = false;
						return;
					}
					else
					{
						table[i][j].taken = true;
						findSolution(i, j + 1, correct + 1);
						table[i][j].taken = false;
						return;
					}
				}
				
			}
			else
			{
				if(isUnique(i,j))
				{
					if(correct == numberCorrect[i] - 1)
					{
						table[i][j].taken = true;
						if(restFalse(i,j)) findSolution(i + 1, 0, 0);
					}
					else
					{
						table[i][j].taken = true;
						findSolution(i, j + 1, correct + 1);
					}
				}
			}
			table[i][j].taken = false;
		}
		return;
	}

	public static void main(String[] args)
	{
		//Getting the input for table.
		for(int i = 0; i < table.length; i++)
		{
			for(int j = 0; j < table[0].length; j++)
			{
				table[i][j] = new Spot(input.nextInt());
			}
		}

		/*Calling the backtracking method to find the solution.
		 * Starting at row 1, because I put the row with 0 correct on the top.*/
		findSolution(1,0,0);
	}
}
