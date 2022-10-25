import java.util.Stack;

/*
 * The idea for this solution is to push all chars of a string to a stack, then pop them out-
 * 		the result is a reversed string.
 * 
 * 
 * Basic idea:
 * 		//push the letters to the stack
 * 		for(int i=0; i<letters.length; i++)
 *		{
 *			temp.push(""+letters[i]);
 *		}
 * 
 * 		//pop the letters off the stack
 * 		//LIFO naturally reverses the order
 * 		while(!temp.isEmpty())
 * 		{
 * 			System.out.print(temp.pop());
 * 		}
 */

public class ReverseString {

	public static void main(String[] args) {
		
		String str = "This is a string I want to reverse :D";

		reverseString(str);
		
		

	}
	
	private static void reverseString(String _str)
	{
		char[] letters;
		String reversedString;
		Stack<Character> temp = new Stack<>();
		
		letters = _str.toCharArray();
		
		//push each letter to the stack
		System.out.println("Original string: " + _str);
		for(int i=0; i<letters.length; i++)
		{
			temp.push(letters[i]);
		}
		
		//pop each letter from the stack
		//LIFO causes letters to come out in reverse order
		for(int i=0; i<letters.length; i++)
		{
			letters[i] = temp.pop();
		}
		
		reversedString = new String(letters);
		
		System.out.println("Reversed string: " + reversedString);
	}

}
