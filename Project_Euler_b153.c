#include <stdio.h>
#include <math.h>
#include <omp.h>

long gcd(long a, long b)
{
	long r;
	while(a != 0)
	{
		r = a;
		a = b % a;
		b = r;
	}
	return b;
}

unsigned long long gauss(long n)
{
	unsigned long long sum = 0;
	long long bound = n/2;
	long long divisor,quotient,BOOM,stop,next,isqrt,times,i,j,k;
        #pragma omp parallel for private(divisor,quotient,BOOM,stop,next,isqrt,times,i,j,k) reduction(+:sum)
	for(i = 1; i <= bound; i++)
	{
		//Add normal divisors
		sum += (n/i)*i;
		next = i + bound;
		sum += (n/next)*next;

		//Compute things you'll need just once.
		isqrt = i*i;

		//Get the factors one by one.
		for(divisor = 1; divisor <= sqrt(i); divisor++)
		{
			//Grab the two current divisor and quotient
			if(i % divisor != 0) continue;
			quotient = i/divisor;

			//For Divisor
			BOOM = divisor*n - isqrt;
			if(BOOM > 0)
			{
				stop = sqrt(BOOM);
				for(k = i; k <= stop; k += divisor)
				{
					if(gcd(i,k) != divisor) continue;
					times = (divisor*n)/(isqrt + k*k);
					sum += i*times*2;
					if(i != k) sum += k*times*2;
				}
			}

			//For Quotient (Make sure it's not the same as divisor.
			BOOM = quotient*n - isqrt;
			if(BOOM > 0 && quotient != divisor)
			{
				stop = sqrt(BOOM);
				for(k = i; k <= stop; k += quotient)
				{
					if(gcd(i,k) != quotient) continue;
					times = (quotient*n)/(isqrt + k*k);
					sum += i*times*2;
					if(i != k) sum += k*times*2;
				}
			}
		}
	}
	return sum;
}

long main()
{
	unsigned long long sol = gauss(100000000);
	printf("%llu\n",sol);
}
