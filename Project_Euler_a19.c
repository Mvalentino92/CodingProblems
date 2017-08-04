#include <stdio.h>
#include <math.h>

int main()
{
	int i,j,k,Total = 0,Sunday = 6,Years = 1901;			//Declaring the first of the months for Leap and NonLeap
	int Leap[12] = {1,31,61,92,122,153,183,214,245,275,306,336};
	int NonLeap[12] = {1,31,60,91,121,152,182,213,244,274,305,335};
	
	for(i=Years-1;i<2000;i++) { 
		if(i % 4 == 0 && i % 400 == 0) {      //Checking if its a Leap Year
			for(j=Sunday;j<=Leap[11]+23;j+=7) {	//Iterating all the sundays and checking if theyre in the list
				for(k=0;k<=11;k++) {
					if(j == Leap[k]) {     //Important, +23!, otherwise j will exceed 365/366
						Total += 1;
					}
				}
			}
			Sunday = 7 - ((Leap[11]+30) - j);		//The first sunday is going to be 7 - whatever leftover from last sunday til the end of the year
		}
		else {
			for(j=Sunday;j<=NonLeap[11]+23;j+=7) {
				for(k=0;k<=11;k++) {				//Same, but just for NonLeap years
					if(j == NonLeap[k]) {
						Total += 1;
					}
				}
			}
			Sunday = 7 - ((NonLeap[11]+30) -j);
		}
	}
	printf("The total amount of days is %d\n",Total);
	return 0;
}
