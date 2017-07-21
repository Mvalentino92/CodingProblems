#include <stdio.h>
#include <math.h>
//Question: Sum of mutiples of 3 or 5 under 1000
int main()
{
    int Total,i;                 
    for(i=1;i<1000;i++) {
        if(i % 3 == 0 | i % 5 == 0) {       //Check if the remainder for division by 3 or 5 is zero
            Total += i;                     //If it is, throw it on in!
        }
    }
    printf("The sum of mutiples of 3 and 5 under 1000 is %d\n",Total);
    return 0;
}
