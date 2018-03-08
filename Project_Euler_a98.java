/*By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively, we form a square number: 1296 = 362. What is remarkable is that, by using the same digital substitutions, the anagram, RACE, also forms a square number: 9216 = 962. We shall call CARE (and RACE) a square anagram word pair and specify further that leading zeroes are not permitted, neither may a different letter have the same digital value as another letter.
 *
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common English words, find all the square anagram word pairs (a palindromic word is NOT considered to be an anagram of itself).
 *
 * What is the largest square number formed by any member of such a pair?
 *
 * NOTE: All anagrams formed must be contained in the given text file.*/

import java.io.*;
import java.util.*;

//Word class. Will store important data about each of the words.
class Word
{
	String word;
	int[] letterCount;
	int wordLength;

	public Word(String word, int[] letterCount)
	{
		this.word = word;
		this.letterCount = letterCount;
		int total = 0;
		for(int i = 0; i < letterCount.length; i++) wordLength += letterCount[i];
	}
}

public class Project_Euler_a98
{
	public static final double EPSILON = 1e-12;

	//Returns an array of how many of each letter occur in the word
	public static int[] getOccurences(String word)
	{
		int[] occurences = new int[26];
		for(int i = 0; i < word.length(); i++)
		{
			for(int c = 0, letter = 65; c < occurences.length; c++, letter++)
			{
				if((int)word.charAt(i) == letter) occurences[c]++;
			}
		}
		return occurences;
	}

	//Checks to see if two words are anagrams of eachother.
	public static boolean isAnagram(Word wordA, Word wordB)
	{
		if(wordA.wordLength != wordB.wordLength) return false;
		else
		{
			for(int i = 0; i < wordA.letterCount.length; i++)
			{
				if(wordA.letterCount[i] != wordB.letterCount[i]) return false;
			}
			return true;
		}
	}

	/*Confirms if a pair of anagrams works for the supplied perfect square
	 * Returns -1 if they are not square anagrams.
	 * Otherise return the perfect square generated.*/
	public static int squareAnagram(String[] anagram, int perfectSquare)
	{
		//Grabbing each word from the pair.
		String wordA = anagram[0];
		String wordB = anagram[1];

		//Figuring out how many digits the number is, before I create an array to store the digits.
		int x = 1;
		int count = 1;
		while((x *= 10) <= perfectSquare) count++;

		//If the word isn't the same length as the number, stop.
		if(count != wordA.length()) return -1;

		//Storing the digits in an array.
		int[] initialSquare = new int[count--];
		while(perfectSquare > 0)
		{
			initialSquare[count--] = perfectSquare % 10;
			perfectSquare /= 10;
		}

		//Checking to see if there are repeated numbers before continuing.
		for(int i = 0; i < initialSquare.length - 1; i++)
		{
			for(int j = i + 1; j < initialSquare.length; j++)
			{
				if(initialSquare[i] == initialSquare[j]) return -1;
			}
		}

		//Generating the key for the letter/number swap.
		int[] indexSwitch = new int[wordA.length()];
		for(int i = 0; i < wordA.length(); i++)
		{
			indexSwitch[i] = wordB.indexOf(wordA.charAt(i));
		}

		//Swapping the digits around using the key to create the new number.
		int[] swappedSquare = new int[initialSquare.length];
		for(int i = 0; i < indexSwitch.length; i++)
		{
			swappedSquare[i] = initialSquare[indexSwitch[i]];
		}

		//If the first number is a 0, stop.
		if(swappedSquare[0] == 0) return -1;

		//Creating the actual integer from the list of it's digits.
		int potentialSquare = 0;
		for(int i = 0; i < swappedSquare.length; i++)
		{
			potentialSquare *= 10;
			potentialSquare += swappedSquare[i];
		}

		//Checking to see if the new number is a perfect square.
		double squareRoot = Math.sqrt((double)potentialSquare);
		if(Math.abs((int)squareRoot - squareRoot) < EPSILON) return (int)squareRoot;
		else return -1;
	}



	public static void main(String[] args) throws Exception
	{
		/*Importing the .txt file containing all the words. Not too familiar with this.
		 * Going to do it a sort of round-about way, but it will suffice.*/
		File text = new File("p098_words.txt");
		Scanner input = new Scanner(text);
		String words = new String();
		while(input.hasNext())
		{
			words += input.next();
		}
		String[] wordList = words.split(",");

		String[] finalWordList = new String[wordList.length];
		for(int i = 0; i < wordList.length; i++)
		{
			String shavedWord = new String();
			for(int j = 0; j < wordList[i].length(); j++)
			{
				if(wordList[i].charAt(j) != '\"') shavedWord += wordList[i].charAt(j);

			}
			finalWordList[i] = shavedWord;
		}

		//Creating all the word objects with relevant information about the words.
		Word[] finalWords = new Word[finalWordList.length];
		for(int i = 0; i < finalWordList.length; i++)
		{
			finalWords[i] = new Word(finalWordList[i],getOccurences(finalWordList[i]));
		}
		
		//Finding all the anagrams and adding the pairs to an ArrayList
		ArrayList<String[]> anagrams = new ArrayList<>();
		for(int i = 0; i < finalWords.length - 1; i++)
		{
			for(int j = i + 1; j < finalWords.length; j++)
			{
				if(isAnagram(finalWords[i],finalWords[j]))
				{
					String[] newAnagram = {finalWords[i].word,finalWords[j].word};
					anagrams.add(newAnagram);
				}
			}
		}

		//Removing the ones with duplicate letters.
		//Initially, I'm going to work under the assumption it is not any of these.
		anagrams.remove(37);
		anagrams.remove(15);
		anagrams.remove(4);
		anagrams.remove(16);
		anagrams.remove(19);

		//Begin the loop (top down approach) to find the largest square.
		int max = 0;
		int square = 32000;
		boolean stop = false;
		while(!stop)
		{
			for(int i = 0; i < anagrams.size(); i++)
			{
				int verdict = squareAnagram(anagrams.get(i),square*square);
				if(verdict != -1)
				{
					if(verdict > square && verdict > max)
					{
						max = verdict;
						stop = true;
						System.out.print("The pair of "+
								"anagrams is: "+anagrams.get(i)[0]+
								" and "+anagrams.get(i)[1]+".");
						break;
					}
					else if(square > max)
					{
						max = square;
						stop = true;
						System.out.print("The pair of "+
								"anagrams is: "+anagrams.get(i)[0]+
								" and "+anagrams.get(i)[1]+".");
						break;
					}
					else continue;
				}
			}
			square--;
		}		
		System.out.println(" And the largest square between them is: "+(max*max));
	}
}
