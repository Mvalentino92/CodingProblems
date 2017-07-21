#include <stdio.h>
#include <math.h>
//Question: What is the smallest positive number that
//is evenly divisible by all of the numbers from 1 to 20?

int main()
{
    unsigned int Hold = 200e+6,i,Track,SmallestMutiple = 0;  
    while(SmallestMutiple == 0){     //While I dont arrive at an answer...
        Track = 0;
        for(i=1;i<=20;i++){         //Test all the numbers from 1 to 20
            if(Hold % i != 0){
                Track += 1;         //If its ever not divisible evenly, add one to my Tracker
                break;
            }
        }
        if(Track == 0){             //If the Tracker is still 0, then change value of SmallMultiple end the while loop
            SmallestMutiple = Hold;
        }
        else{
            Hold += 10;             //Otherwise go in increments of 10 (Has to be 10's because of 10 and 20)
        }
    }
    printf("The Smallest Multiple is %d\n",SmallestMutiple);
    return 0;
}
