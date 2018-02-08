public class Project_Euler_a18
{
	public static int totalMax = 0;
	public static int count = 0;
	public static int index = 0;
	public static void getMax(int depth, int index, int max, int[][] triangle)
	{
		if(depth == triangle.length)
		{
			if(max > totalMax) totalMax = max;
			count++;
			return;
		}	

		getMax(depth + 1, index, max + triangle[depth][index], triangle);
		getMax(depth + 1, index + 1, max + triangle[depth][index], triangle);
	}

	public static int subTriangleSum(int[][] triangle, int row, int index)
	{
		int sum = 0;
		int forward = 1;
		for(int i = row; i < triangle.length; i++)
		{
			for(int j = index; j < index + forward; j++)
			{
				sum += triangle[i][j];
			}
			forward++;
		}
		return sum;
	}

	public static void main(String[] args)
	{
           int[][] triangle = {{75},
			     {95,64},
			    {17,47,82},
			  {18,35,87,10},
			 {20,4,82,47,65},
			{19,1,23,75,3,34},
		       {88,2,77,73,7,63,67},
		      {99,65,4,28,6,16,70,92},
		    {41,41,26,56,83,40,80,70,33},
	           {41,48,72,33,47,32,37,16,94,29},
	         {53,71,44,65,25,43,91,52,97,51,14},
	        {70,11,33,28,77,73,17,78,39,68,17,57},
	      {91,71,52,38,17,14,91,43,58,50,27,29,48},
	     {63,66,4,68,89,53,67,30,73,16,69,87,40,31},
	    {4,62,98,27,23,9,70,98,73,93,38,53,60,4,23}};

	   int[][] tri = {{56},
		   	{3,44},
		      {56,45,21},
		    {34,91,15,56},
		   {4,7,34,35,31},
		  {67,4,5,32,45,89},
		  {17,3,12,34,54,6,7}};

		int spaces = triangle.length*4 - 1;

		for(int i = 0; i < triangle.length; i++)
		{
			int count = 0;
			int evenTracker = 0;
			int limit = i;
			int fluff = (spaces - (limit*2 + 1))/2;
			for(int j = 0; j < fluff - i; j++) System.out.print(" ");
			for(int j = fluff; j < spaces - fluff; j++)
			{
				if(evenTracker % 2 == 0) System.out.printf("%02d",triangle[i][count++]);
				else 
				{
					for(int k = 0; k < 2; k++) System.out.print(" ");
				}
				evenTracker++;
			}
			System.out.println("\n");
		}
		System.out.println("************************************************************");

		getMax(0,0,0,triangle);
		int trueTotal = totalMax;

		totalMax = triangle[0][0];
		for(int i = 1; i < triangle.length; i++)
		{
			int left = 0;
			int right = 0;
			int row = i;
			int col = index;
			while(row < triangle.length)
			{
				left += subTriangleSum(triangle,row,col);
				right += subTriangleSum(triangle,row,col + 1);

				row += 2;
				col += 1;
			}
			System.out.println("Left is: "+left+"\tRight is: "+right);

			if(left > right) 
			{
				System.out.println("Choosing: "+triangle[i][index]+"\n");
				totalMax += triangle[i][index];
			}
			else 
			{
				System.out.println("Choosing: "+triangle[i][index+1]+"\n");
				totalMax += triangle[i][++index];
			}

		}
		System.out.println("The attempted max sum is "+totalMax);
		System.out.println("The true maximum path sum for the above triangle is "+trueTotal);
	}
}
