#include <stdio.h>
#include <math.h>
//Question: Find the difference between the sum of the squares of the first
//one hundred natural numbers and the square of the sum.

int main()
{
    unsigned int SumSqaure,SquareSum,Diff;
    unsigned short i,j;
    for(i=1;i<=100;i++){        //Did two seperate for loops for clarity
        SumSqaure += pow(i,2);
    }
    for(j=1;j<=100;j++){        
        SquareSum += j;
    }
    SquareSum = pow(SquareSum,2);
    Diff = fabs(SumSqaure - SquareSum);
    printf("The difference is %d\n",Diff);
    return 0;
}
    
        
