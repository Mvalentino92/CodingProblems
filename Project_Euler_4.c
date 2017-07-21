#include <stdio.h>
#include <math.h>
//Question: Find the largest palindrome made from the product of two 3-digit numbers.
int main()
{
    unsigned int Final = 0,Hold1,Hold2,Reverse;     
    unsigned short LeftBound = 100,RightBound = 999,i,j,Remainder;
    for(i=1;i<=RightBound;i++){                 //Im iterating though the bounds
        for(j=LeftBound;j<=RightBound;j++) {       //Doing updated double for loops so I dont multiply
            Reverse = 0;                           //the same numbers twice
            Hold1 = j*i;
            Hold2 = Hold1;                      //Having a variable hold my values
            while(Hold1 != 0){
                Remainder = Hold1 % 10;;        //Generating the reverse of the value
                Reverse = Reverse*10 + Remainder;
                Hold1 /= 10;
            }
            if(Hold2 == Reverse & Hold2 > Final){   //If its a palindrome and bigger than the last one, add it!
                Final = Hold2;
            }
        }
    }
    printf("Answer is %d\n",Final);
    return 0;
}
            
        
