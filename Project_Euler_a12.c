#include <stdio.h>
#include <math.h>
int main()
{
	unsigned int Total= 0,i=5e+3,j,Triangle;	//Setting my initial guess for the bounds
	while(Total <= 500){
		Total = 0;
		i += 1;
		Triangle = pow(i,2) - (pow(i,2) - i)/2;		//Shortcut way to generate triangle numbers
		if(Triangle % 10 != 0 |  Triangle < 17907120){		//Previous answer for 400 (incremented from 200 and 300 prev)
			continue;		//I also think it has to end in 0
		}
		for(j=1;j<=Triangle;j++){
			if(Triangle % j == 0){		//Painful interation for seeing how many divisors
				Total += 1;
			}
		}
	}
	printf("The first triangle num with over 500 is %d\n",Triangle);
	return 0;
} 
 
