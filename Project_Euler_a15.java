import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a15
{
	public static void main(String[] args)
	{
		long[] arr = new long[21];
		for(int i = 0; i < arr.length; i++) arr[i] = (long)(i + 1);
		for(int count = 2; count < arr.length; count++)
		{
			for(int i = 1; i < arr.length; i++) arr[i] = arr[i] + arr[i-1];
		}
		System.out.println(arr[arr.length-1]);
	}
}

