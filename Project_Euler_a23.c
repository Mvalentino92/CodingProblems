#include <stdio.h>
#include <math.h>
/*Question: A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.*/ 

int main()
{	

	int i,j,k,Ubound,Number = 28123,Sum = 0,Quotient,Track = 0;	//First I have to find all the abundent Numbers
	int Minus,r,Check;
	int Abundent[10000];
	unsigned long int Total = 276;					//Total is going to start at 276. --> (23*(23 + 1))/2
	for(i=12;i<=Number;i++) {					//Starting at 12 because it is the first one
		Ubound = i;
		Sum = 1;
		for(j=2;j<Ubound;j++) {
			if(i % j == 0) {
				Quotient = i / j;	//Saving time by updating the upper bound, so I dont do another calculation
				Ubound = Quotient;	//to get the Quotient that I already know from the first calculation
				if(Quotient != j) {
					Sum += (Quotient + j);
				}
				else {				//Have to account for square roots! Dont want to add them twice!
					Sum += j;
				}
			}
		}
		if(Sum > i) {
			Abundent[Track] = i;		//Keeping track of how many values are in Abundent
			Track += 1;
		}
		
	}
	for(i=24;i<=Number;i++) {	//Starting at 24, and going until 28123
		int Max;
		Check = 0;		//Tracking variable called Check
		for(k=0;k<Track;k++) {
			if(Abundent[k] - i/2 >= 0) {		//Here, I'm calculating the max value of Abundent I should iterate up
				Max = k;			//to. If i is 24, I dont need to check anything above half of that
				break;				//for the first loop
			}
		}
		for(j=0;j<=Max;j++) {
			Minus =  i - Abundent[j];
			for(k=j;k<Track;k++) {
				if(Abundent[k] == Minus) {	//So I'm starting with the first Abundent number, and finding the 
					Check += 1;		//difference of that and i. If that number is in Abundent
					break;			//Then it is the sum of two abundent numbers
				}
			}
		}	
		if(Check == 0) {	//If check still equals 0, then it was not the sum of two abundent numbers
			Total += i;	//So add it
		}
	}
	printf("The sum of the numbers is %lu\n",Total);	//Not the most efficient, as I iterated through the a lot of checking
	return 0;					//But it solved the problem. Took abou 25 minutes computation time.
} 
			 

	


