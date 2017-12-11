/*The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include leading zeros.)*/

public class Project_Euler_a36
{
	public static boolean isPalindrome(String number)
	{
		String reversed = new String();
		for(int i = number.length() - 1; i >= 0; i--)
		{
			reversed += number.charAt(i);
		}
		if(number.equals(reversed)) return true;
		else return false;
	}

	public static void main(String[] args)
	{
		int sum = 0;
		for(int i = 1; i < 1e6; i++)
		{
			if(isPalindrome(Integer.toString(i)) 
					&& isPalindrome(Integer.toBinaryString(i))) sum += i;
		}
		System.out.println(sum);
	}
}
