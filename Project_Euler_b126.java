import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_b126
{
	public static int[] numbers = new int[35000];
	public static int getInner(int l, int w, int k) {return 2*(l + w) + 4*k;}

	public static int getLayers(int l, int w, int h)
	{
		int inner = 2*(l+w);
		int layers = 2*(l*w) + inner*h;
		int k = 1;
		while(layers < numbers.length)
		{
			numbers[layers] += 1;
			if(h == 1) layers += inner;
			else if(h > 2) layers -= (inner*(h-2));
			inner = getInner(l,w,k++);
			layers += inner*h;
		}
		return k;
	}

	public static void main(String[] args)
	{
		for(int l = 1; l < 6500; l++)
		{
			for(int w = l; w < 6500; w++)
			{
				for(int h = w; h < 6500; h++)
				{
					if(getLayers(l,w,h) == 1) break;
				}
			}
		}

		int index = 0;
		for(int i = 0; i < numbers.length; i++)
		{
			if(numbers[i] == 1000)
			{
				index = i;
				break;
			}
		}
		System.out.println("The lowest n that satisfies is: C("+index+") = 1000");
	}
}


