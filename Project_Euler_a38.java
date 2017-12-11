public class Project_Euler_a38
{
	//Checking to see if the pandigital is unique and 9 digits long
	public static boolean isUnique(int number)
	{
		int[] numbers = new int[9];
		int index = 0;
		while(number > 0 && index < 9)
		{
			numbers[index++] = number % 10;
			number /= 10;
		}

		for(int i = 0; i < numbers.length; i++)
		{
			for(int j = 0; j < numbers.length; j++)
			{
				if(i == j) continue;
				else if(numbers[i] == numbers[j] || numbers[j] == 0) return false;
			}
		}
		if(numbers.length == 9) return true;
		else return false;
	}

	public static int returnPandigital(int number, int n)
	{
		int[] pandigital = new int[9];
		int index = 8;
		for(int i = n; i > 0; i--)
		{
			int numberCopy = number*i;
			while(numberCopy > 0 && index >= 0)
			{
				pandigital[index--] = numberCopy % 10;
				numberCopy /= 10;
			}
		}
		//Converting into an integer
		int panNumber = 0;
		for(int i = 0; i < pandigital.length; i++)
		{
			panNumber = panNumber*10 + pandigital[i];
		}

		return panNumber;
	}
	
	//These are the bounds i came up with that give possible solutions
	public static void main(String[] args)
	{
		int best = 0;
		for(int i = 5; i < 1e4; i++)
		{
			int pandigitalSolution = 0;
			boolean pandigitalCheck = false;
			if(i > 4 && i < 10) 
			{
				pandigitalSolution = returnPandigital(i,5);
				if(isUnique(pandigitalSolution)) pandigitalCheck = true;
			}
			else if(i > 25 && i < 33)
			{
				pandigitalSolution = returnPandigital(i,4);
				if(isUnique(pandigitalSolution)) pandigitalCheck = true;
			}
			else if(i > 99 && i < 334)
			{
				pandigitalSolution = returnPandigital(i,3);
				if(isUnique(pandigitalSolution)) pandigitalCheck = true;
			}
			else if(i > 4999 && i < 10000)
			{
				pandigitalSolution = returnPandigital(i,2);
				if(isUnique(pandigitalSolution)) pandigitalCheck = true;
			}
			if(pandigitalCheck && pandigitalSolution > best) best = pandigitalSolution;
		}
		System.out.println(best);
	}
}
