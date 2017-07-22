#include <stdio.h>
#include <math.h>
//Question: There exists exactly one Pythagorean triplet for which a + b + c = 1000.
//Find the product abc.
int main()
{
    unsigned short LeftBound = 100,RightBound = 400,i,j;
    unsigned int A,B,a,b;
    double C,c;
    for(i=LeftBound;i<=RightBound;i++){             //Making initial guesses for left and right bounds
        for(j=LeftBound+1;j<=RightBound;j++){
            A = pow(i,2);
            B = pow(j,2);
            C = sqrt(A + B);
            if(i + j + C == 1000){      //Checking if it equals 1000
                a = i;
                b = j;
                c = C;
                break;
            }
        }   
    }   LeftBound += 1;
    printf("The numbers are %d, %d, and %lf,and the product is %lf\n",a,b,c,(a*b*c));
    return 0;
}
