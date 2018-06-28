#include <math.h>
#include <stdio.h>

int main()
{
	int count = 0;
	int n,N,p;
	unsigned long a,b,addy,multy,x,z,val;

	//Iterating n
	for(n = 1; n < 10; n++)
	{
		N = pow(10,n);
		//Parallize the code
		#pragma omp parallel for reduction(+:count) private(p,a,b,addy,multy,x,z,val)
		for(p = 1; p < (N+N+1); p++)
		{
			//Figuring out lower bound for a.
			a = ceil(1.0/((double)p/N) + 0.000000001);
			b = a;

			addy = N*(a + b);
			multy = a * b * p;

			//Valid solutions possible until multy is greate than addy.
			while(addy >= multy)
			{
				/*Only 1 unknown variable. Solve for it.
				 * If it's a valid solution, count it.*/
				x = a*N;
				z = a*p;
				val = z - N;
				if(val > 0) //Avoid division by 0
				{
					if(x % val == 0) count += 1;
				}
					
				//Increment and update all relevant variables.
				a += 1;
				b += 1;
				addy = N*(a + b);
				multy = a * b * p;
			}
		}
	}
	printf("The sum is: %d\n",count);
	return 0;
}
