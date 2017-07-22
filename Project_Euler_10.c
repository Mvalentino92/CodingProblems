#include <stdio.h>
#include <math.h>
//Question: Find the sum of all the primes below two million.
int main()
{
    unsigned int Prime = 3,i;   
    unsigned long int Total = 5;    //Setting the total to 5 because im starting past 3
    unsigned short Track;
    while(Prime < 2e+6){
        Track = 0;
        Prime += 2;                 //Iterate only for odd numbers
        for(i=3;i<Prime;i+=2){
            if(Prime % i == 0){
                Track += 1;         //Track it
                break;
            }
        }
        if(Track == 0 & Prime < 2e+6){      //If its prime, add it
            Total += Prime;
            //printf("Prime is %d\n",Prime);
        }
    }
    printf("The total is %lu\n",Total);
    return 0;
}
