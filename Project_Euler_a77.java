import java.util.*; 
import java.math.*; 
import java.io.*; 

public class Project_Euler_a77
{
	public static void main(String[] args)
	{
		/******************Prime sieve**********************/
		//Initialize numbers, and the table to hold the value of prime paritions.
		int n = 100;
		int[] numbers = new int[n+1];
		int[] table = new int[n*2 + 1];
		for(int i = 0; i <= n; i++) numbers[i] = i;

		//Mark non-primes
		for(int i = 2; i <= Math.sqrt(n); i++)
		{
			if(numbers[i] == -1) continue;
			for(int j = i+i; j <= n; j += i) numbers[j] = -1;
		}
		numbers[0] = -1;
		numbers[1] = -1;
		/**************End prime sieve*******************/

		/*************Begin to create all possible stacks below the bound of n**********/
		int[] stacks = new int[n];;
		int stackIndex = 0;
		int prime = 2;
		int primeHold = 0;
		int value = 0;

		while(stackIndex > -1 && prime > -1)
		{
			while(value < n)
			{
				value += prime;
				table[value] += 1;
				stacks[stackIndex++] = prime;
			}
			if(value == n) table[value]++;
			if(stackIndex - 2 < 0) break;
			value -= stacks[--stackIndex];
			prime = -1;
			while(stackIndex > 0)
			{
				prime = -1;
				value -= stacks[--stackIndex];
				primeHold = numbers[stacks[stackIndex]];
				for(int i = primeHold + 1; i <= n; i++)
				{
					if(numbers[i] > -1) 
					{
						prime = numbers[i];
						break;
					}
				}
				if(prime > -1) break;
			}
		}
		for(int i = 0; i <= n; i++)
		{
			if(table[i] > 5000) 
			{
				System.out.println(i);
				break;
			}
		}
	}
}
