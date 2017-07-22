#include <stdio.h>
#include <math.h>
//Question: What is the 10 001st prime number?

int main()
{
    unsigned int Prime = 3;
    unsigned int Total = 2,i,Track;
    while(Total < 10001){           //Keep track of which prime I'm on.
        Track = 0;
        Prime += 1;
        for(i=2;i<Prime;i++){       //Check if the number is prime
            if(Prime % i == 0){
                Track += 1;
                break;
            }
        }
        if(Track == 0){
            Total+=1;               //If it is add 1 total
        }
    }
    printf("The 10001 prime is %d\n",Prime);
    return 0;
}
            
        
    
