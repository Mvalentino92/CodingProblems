/*If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p <= 1000, is the number of solutions maximised?*/

public class euler
{
	public static void main(String[] args)
	{
		int[] elements = new int[1000];
		int c = 500;
		double b = 1;
		while(c > 200)
		{
			int a = 1;
			do
			{
				b = Math.sqrt((c*c)-(a*a));
				if((int)b == b)
				{
					elements[a + (int)b + c - 1] += 1;
				}
				a++;
			}
			while((a + b + c) < 1000);
			c--;
		}
		int best = 0;
		int index = 0;
		for(int i = 0; i < elements.length; i++)
		{
			if(elements[i] > best) 
			{

				best = elements[i];
				index = i;
			}
		}
		System.out.println(index + 1);
	}
}
