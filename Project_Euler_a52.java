import java.util.*;
public class Project_Euler_a52
{
	//Sorts an array.
	public static void sort(int[] list)
	{
		for(int i = 0; i < list.length - 1; i++)
		{
			int min = list[i];
			int index = i;
			for(int j = i + 1; j < list.length; j++)
			{
				if(list[j] < min)
				{
					min = list[j];
					index = j;
				}
			}
			int temp = list[i];
			list[i] = min;
			list[index] = temp;
		}
	}

	//Returns the digits of a number as an array.
	public static int[] getDigits(int number,int numberOfDigits)
	{
		int[] digits = new int[numberOfDigits];
		int index = 0;
		while(number > 0)
		{
			digits[index++] = number % 10;
			number /= 10;
		}

		return digits;
	}

	//Compares the contents of two arrays and see's if they are equal.
	public static boolean sameDigits(int[] A, int[] B)
	{
		if(A.length != B.length) return false;
		for(int i = 0; i < A.length; i++)
		{
			if(A[i] != B[i]) return false;
		}
		return true;
	}

	public static void main(String[] args)
	{
		int min = 100;
		int digitCount = 3;
		int limit = 166;
		while(true)
		{
			if(min > limit)
			{
				int difference = limit - min;
				min = (min - limit) * 10;
				limit *= 10;
				limit += 6;
				digitCount += 1;
			}

			int[] originalDigits = getDigits(min,digitCount);
			sort(originalDigits);
			int count = 0;
			for(int i = 2; i < 7; i++)
			{
				int[] newDigits = getDigits(min*i,digitCount);
				sort(newDigits);
				if(sameDigits(originalDigits,newDigits)) count++;
				else break;
			}
			if(count == 5) break;
			min++;
		}
		System.out.println("The answer is "+min+".");
	}
}
