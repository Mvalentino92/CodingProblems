#include <stdio.h>
#include <math.h>

int main()
{
    unsigned long int Primes = 1000,Threshhold = 600851475143,Final;  //Setting my initial variables
    short Divisors,Track;
    while(Primes <  1e+4) {                                     //Im guessing the answer wont be that big,
        Primes += 1;                                            //So I'll start low in clips of thousands
        Track = 0;
        for(Divisors=2;Divisors<Primes;Divisors++){             //First checking if things are prime
            if(Primes % Divisors == 0){
                Track += 1;                                     //If they arent, add a value to my Tracker
                break;
            }
        }   
        if(Threshhold % Primes == 0 & Track == 0){              //Check if its divisible and Tracker must be 0!
            Final = Primes;
            printf("%lu is a prime factorization\n",Final);
        }
    }
    return 0;
}
                
    
