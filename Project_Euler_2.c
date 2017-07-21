#include <stdio.h>
#include <math.h>
//Question: By considering the terms in the Fibonacci sequence... 
//...whose values do not exceed four million, find the sum of the even-valued terms.
int main()
{
    unsigned int A = 1,B = 2,C,Total = 2; //Set up first values for variables
    while(A < 4e+6) {    
        C = B;              //Use C as value holder for A
        B = A + B;
        if(B % 2 == 0){         
            Total += B;
        }
        A = C;
    }
    printf("The Sum of even Fibonacci numbers under 4 mil is %d\n",Total);
    return 0;
}



