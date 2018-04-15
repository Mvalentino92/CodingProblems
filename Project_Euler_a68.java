public class Project_Euler_a68
{
	public static String comp = "2951051817673439";
	public static int[] numberCount = new int[9+9+10];
	public static int[] numbers = {1,2,3,4,5,6,7,8,9,10};
	public static void countSums(int index,int target)
	{
		for(int i = index + 1; i < numbers.length - 1; i++)
		{
			for(int j = i + 1; j < numbers.length; j++)
			{
				int sum = numbers[index] + numbers[i] + numbers[j];
				numberCount[sum]++;
			}
		}
	}

	public static boolean isUnique(int target, int[] nums)
	{
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == target) return false;
		}
		return true;
	}

	public static boolean isMagic(int target, int[] nums)
	{
		int a = 0;
		int b = 1;
		int c = 2;
		for(int i = 0; i < 5; i++)
		{
			if(nums[a] + nums[b] + nums[c] != target) return false;
			a += 2;
			b += 2;
			c += 2;
		}
		return true;
	}

	public static void printNumber(int[] magics)
	{
		int min =  magics[1];
		int minIndex = 1;
		for(int i = 3; i < magics.length; i += 2) 
		{
			if(magics[i] < min) 
			{
				min = magics[i];
				minIndex = i;
			}
		}

		String retval = "";
		int count = 0;
		int edges = minIndex;
		while(count < 5)
		{
			if(edges >= magics.length) edges = 1;
			retval += magics[edges];
			retval += magics[edges-1];
			retval += magics[edges+1];
			edges += 2;
			count++;
		}
		if(retval.length() == 16 /*&& retval.compareTo(comp) >= 0*/) System.out.println(retval);
	}


	public static void main(String[] args)
	{
		for(int i = 0; i < 10; i++) countSums(i,100);
		int max = numberCount[0];
		int magic = 0;
		for(int i = 1; i < numberCount.length; i++)
		{
			if(numberCount[i] > max) 
			{
				max = numberCount[i];
				magic = i;
			}
		}
		for(int i = 0; i < 10; i++) countSums(i,magic);
		int[] magics = new int[11];
		while(true)
		{
			magics = new int[11];
			int count = 0;
			while(count != 10)
			{
				int nextNum = (int)(Math.random()*10) + 1;
				if(isUnique(nextNum,magics))
				{
					if(count == 0) magics[magics.length - 1] = nextNum;
					magics[count++] = nextNum;
				}
			}
			if(isMagic(14,magics)) printNumber(magics);
		}
	}
}
