/*
 	Given an integer x, return true if x is palindrome integer.
	An integer is a palindrome when it reads the same backward as forward.
 	
	Idea: 	% the input number by 10 to get the last digit.
			take the current reversed number, multiply by 10 to move to the next 10s position, then add the last digit.
			repeat until the input is smaller than the reversed number.
				- this will always happen when the input is in half.
				- even input will have even halves, odd input will put middle digit in reversed number.
			now check if modified input is equal to reversed number if even, or  equal to (reversed number % 10) if input is odd.
				- if input was a palindrone, it will equal one of the two. 

*/
public class IntPalindrone {

	public static void main(String[] args) {

		int x = 12521;
		
		System.out.println(isPalindroneBetter(x));

	}

	//my first attempt.
	private static boolean isPalindrone(int _input)
	{
		boolean ret = true;
		int mid;
		
		String number = "" + _input;
		char[] arr = number.toCharArray();
		mid = arr.length/2;

		
		for(int i=0; i < mid; i++)
		{
			if(arr[i] != arr[arr.length-1-i])
			{
				ret = false;
				break;
			}
		}
		
		return ret;
	}
	
	//solution from leetcode
	private static boolean isPalindroneBetter(int _input)
	{
		//disregard negative numbers - also, since numbers do not have leading zeros,
		//		any numbers ending in zero are not palindrones. Only 0 itself works.
		if(_input <0 || (_input%10 ==0 && _input != 0))
		{
			return false;
		}
		
		//initialize to zero because the first digit goes into the ones spot.
		int reversedNumber = 0;
		
		//_input%10 will always return the last digit in the number.
		//reversedNumber*10 will always put the last number in the next power of 10s spot. (ones, tens, hundreds, thousands..).
		// _input/=10 will account for the last digit being removed + once _input > reservedNumber, we will be at middle.
		// ex) 8008 = x=8008 r=0, x=800 r=8, x=80 r=80 - stop!
		// ex) 80508 = x=80508 r=0, x=8050 r=8, x=805 r=80, x=80 r=805 - stop!
		while(_input > reversedNumber)
		{
			reversedNumber = (reversedNumber*10)+(_input%10);
			_input /= 10;
		}
		
		//for palindrones, if input was even then reversed half will be the same.
		//		if input was odd, then we divide by 10 to remove the middle digit of the input number.
		//for input = 80508, at this point x=80 and r=805. 
		return(_input == reversedNumber || _input == reversedNumber/10);
	}
}
