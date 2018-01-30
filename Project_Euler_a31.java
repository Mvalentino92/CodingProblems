public class Project_Euler_a31
{
	public static int ways = 1;
	public static boolean getChange(int start, int[] change, int target)
	{
		if(start == change.length)
		{
			if(target == 0)
			{
				ways++;
				return true;
			}
			else return false;
		}

		if(!(target - change[start] < 0))
		{
			if(getChange(start,change,target - change[start])) target = 200;
		}

		if(getChange(start + 1, change, target)) target = 200;
		return false;
	}

	public static void main(String[] args)
	{
		int[] change = {1,2,5,10,20,50,100};
		getChange(0,change,200);
		System.out.println(ways);
	}
}
