#include <stdio.h>
#include <math.h>
#include <malloc.h>

//Reverses a number so it can be concatenated correctly
int reverse(int n)
{
	int retval = 0;
	while(n > 0)
	{
		retval = retval*10 + (n % 10);
		n /= 10;
	}
	return retval;
}

//Concatenates number B to the end of number A
int concat(int A,int B)
{
	B = reverse(B);
	while(B > 0)
	{
		A = A*10 + (B % 10);
		B /= 10;
	}
	return A;
}

//Sieve to return an array containing numbers up to N, with all non-primes as -1.
int * sieve(int n)
{
	int * retval = malloc((n+1)*sizeof(int));
	for(int i = 0; i <=n; i++) retval[i] = i;

	for(int i = 2; i <= sqrt(n); i++)
	{
		if(retval[i] == -1) continue;
		for(int j = i+i; j <= n; j += i) retval[j] = -1;
	}
	return retval;
}

/* Finds the solution by working backwards from the starting number
 * and building a list of valid primes from the bottom, to ensure the lowest sum*/
int main()
{
	//Grab the array of primes.
	int n = 100000000;
	int * primes = malloc((n+1)*sizeof(int));
	primes = sieve(n);

	//Start at the first prime with 4 possible valid primes behind it.
	int primeStart = 13;
	while(1 == 1)
	{
		//Check to see if this starting point will grab the correct amount of valid primes.
		int * primeSet = malloc(5*sizeof(int));
		int count = 0;
		primeSet[count++] = primeStart;

		//Iterate the primes below the starting value.
		for(int i = 3; i < primeStart; i += 2)
		{
			if(primes[i] == -1) continue;
			int valid = 0;
			for(int j = 0; j < count; j++)
			{
				//Grab both concatenations
				int left = concat(i,primeSet[j]);
				int right = concat(primeSet[j],i);

				//Check to see if they are prime.
				if(primes[left] != -1 && primes[right] != -1) valid += 1;
			}
			//If all concatenations were primes, add this current prime to the set.
			if(valid == count) primeSet[count++] = i;
		}
		//If 5 primes are discovered for a set, print them and the total.
		if(count == 5) 
		{
			int sum = 0;
			printf("The set of primes is:");
			for(int i = 1; i < 5; i++) 
			{
				sum += primeSet[i];
				printf(" %d",primeSet[i]);
			}
			printf(" %d",primeSet[0]);
			sum += primeSet[0];
			printf(". And their sum is: %d\n",sum);
			break;
		}
		//Iterate until you find the next prime in the list to use as a starting point.
		primeStart += 2;
		while(primes[primeStart] == -1) primeStart += 2;
	}
}
