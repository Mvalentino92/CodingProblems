import java.util.*;
class Spot
{
	public int number;
	public boolean taken = false;
	public Spot(int number)	{this.number = number;}
}

public class fate
{
	//Static variables so I don't have to pass them to methods.
	public static Spot[][] table = new Spot[22][16];
	public static Scanner input = new Scanner(System.in);
	public static int[] numberCorrect = {0,1,1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3};
	//public static int[] numberCorrect = {0,2,2,1,2,1};
	public static int max = 0;

	//Checks if the current spot has "trues" above it.
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

	public static boolean isUnique(int i, int j)
	{
		for(int k = i - 1; k > -1; k--)
		{
			if(table[i][j].number == table[k][j].number) return false;
		}
		return true; 	
	}

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

	public static void findSolution(int i, int j, int correct)
	{
		if(i > max) max = i;
		if(i == table.length)
		{
			for(int col = 0; col < table[0].length; col++)
			{
				int c = 0;
				for(int row = 0; row < table.length; row++)
				{
					c++;
					if(table[row][col].taken == true)
					{
						System.out.print(table[row][col].number);
						break;
					}
					if(c == 22) System.out.print(" ");
				}
			}
			/*for(int k = 0; k < table.length; k++)
			{
				for(int kk = 0; kk < table[0].length; kk++)
				{
					/*if(table[k][kk].taken == true) System.out.print(table[k][kk].number);
					//else System.out.print(" ");
				}
				System.out.println();
			}*/
			System.out.println();
			System.exit(1);
		}

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
		findSolution(1,0,0);
		System.out.println(max);
	}
}
