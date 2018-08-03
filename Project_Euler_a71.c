#include <stdio.h>
#include <malloc.h>

int factorial(int n)
{
	int retval = 1;
	for(int i = 2; i <= n; i++) retval *= i;
	return retval;
}

int nextLink(int n)
{
	int retval = 0;
	while(n > 0)
	{
		retval += factorial(n % 10);
		n /= 10;
	}
	return retval;
}

//Returns the index of the duplicate
int findDuplicate(int next, int * links, int bound)
{
	for(int i = 0; i < bound; i++)
	{
		if(links[i] == next) return i + 1;
	}
	return -1;
}


int main()
{
	int * table = malloc(10000000*sizeof(int));
	for(int i = 0; i < 10000000; i++) table[i] = 0;
	int sol = 0;
	for(int i = 1; i < 1000000; i++)
	{
		//If this number was already found stop
		if(table[i] > 0) continue;

		//Prepare array for checking if duplicates were found.
		int * links = malloc(61*sizeof(int));

		//Initialize the count of terms, and get next link.
		int count = 1;
		int next = nextLink(i);
		int duplicateIndex = findDuplicate(next,links,count);
		while(next != i && duplicateIndex < 0)
		{
			links[count-1] = next;
			count++;
			next = nextLink(next);
			duplicateIndex = findDuplicate(next,links,count);
		}
		table[i] = count;
		if(duplicateIndex > -1) table[next] = count - duplicateIndex;
		if(table[i] == 60 || table[next] == 60) sol++;
	}
	printf("The solution is: %d\n",sol);
}
