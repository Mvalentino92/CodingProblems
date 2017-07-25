#include <stdio.h>
#include <math.h>
/*Question: Find sum of Amicable numbers under 10000.
Amicable numbers are defined as so.
If the sum of divisors for A = B, and the sum of the divisors for B = A (without A == B), then they are amicable.*/
int main()
{
	unsigned short Values[5000], Amicable,i,j,k,Hold,Total,Index= 0,Go;
	for(i=1;i<10000;i++){				//Iterating through, and setting everything back to zero
		Amicable = 0;
		Hold = 0;
		Go = 0;
		for(j=0;j<1000;j++){
			if(i==Values[j]){		//If I already found the upper bound amicable of the pair, skip it!
				Go += 1;
				break;
			}
		}	
		if(Go == 1){				//I just used Go in order to do this
			continue;
		}
		for(k=1;k<i;k++){			//Find B
			if(i % k == 0){
				Amicable += k;
			}
		}
		for(k=1;k<Amicable;k++){
			if(Amicable % k == 0){		//Check if the sum of divisors of B == A.
				Hold += k;
			}
		}
		if(i == Hold & i != Amicable){
			printf("I = %d, Hold = %d, Amicable = %d\n",i,Hold,Amicable);	//If  it does, and A !=B, add em!
			Values[Index] = Amicable;			//Also store B in the list, and update index value
			Index += 1;
			Total += (i + Amicable);
		}
	}
	printf("The total is %d\n",Total);
	return 0;
}
		
