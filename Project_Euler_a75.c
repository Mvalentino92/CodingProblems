#include <stdio.h>
#include <math.h>
#include <malloc.h>

long long quadratic(long long n, long long target)
{
	long long b = n;
	long long c = target/2;
	double retval= (-b + sqrt(b*b + 4*c))/2.0;
	return (long long)retval;
}

long long isPrimitive(long long a, long long b, long long c)
{
	if(a % 2 == 0 && b % 2 == 0 && c % 2 == 0) return -1;
	for(long long i = 3; i < a; i += 2)
	{
		if(a % i == 0 && b % i == 0 && c % i == 0) return -1;
	}
	return 0;
}

long long main()
{
	long long sol = 0;
	long long bound = 1500000;
	long long * list = malloc((bound+1)*sizeof(long long));
	for(long long i = 0; i < bound + 1; i++) list[i] = 0;

	for(long long i = 12; i <= bound; i += 2)
	{
		long long n = 1;
		long long m = quadratic(n,i);
		while(n < m)
		{
			//Everytime we find one, add and populate up to the bound only if its primitive
			if(2*m*m + 2*m*n == i) 
			{
				if(isPrimitive(m*m-n*n,2*m*n,m*m+n*n) == 0)
				{
					list[i] += 1;
					long long upperBound = bound/i;
					for(long long j = 2; j <= upperBound; j++) list[i*j] += 1;
				}
			}
			if(list[i] > 1) break;
			n++;
			m = quadratic(n,i);
		}
		if(list[i] == 1) sol++;
	}
	printf("%lli\n",sol);
}
