import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a15
{
	public static void main(String[] args)
	{
		long[] arr = new long[21];
		for(long i = 0; i < arr.length; i++) arr[(int)i] = i + 1;
		for(int count = 2; count < arr.length; count++)
		{
			for(long i = 1; i < arr.length; i++) arr[(int)i] = arr[(int)i] + arr[(int)i-1];
		}
		System.out.println(arr[arr.length-1]);
	}
}

