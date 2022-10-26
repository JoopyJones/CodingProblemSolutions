import java.util.Stack;

public class MatchingBraces {

	public static void main(String[] args) {
		String braces = ")(()()";
		
		System.out.println("Matching: " + isMatchingBetter(braces));
	}

	//my initial approach
	private static boolean isMatching(String _input)
	{
		//if string has odd length, something is unpaired.
		if(_input.length()%2==1)
		{
			return false;
		}
		
		Stack<String> stack = new Stack<>();
		int inLen = _input.length();
		boolean ret = true;
		
		//push all opening braces and pop when closed brace is encountered.
		for(int i=0; i<inLen; i++)
		{
			//if stack is empty, a closing brace came before an opening one.
			switch(_input.substring(i,i+1))
			{
			case "}":
				if(stack.isEmpty() || !stack.pop().equals("{"))
				{
					ret = false;
					i= inLen;
				}
				break;
				
			case "]":
				if(stack.isEmpty() || !stack.pop().equals("["))
				{
					ret = false;
					i= inLen;
				}
				break;
				
			case ")": 
				if(stack.isEmpty() || !stack.pop().equals("("))
				{
					ret = false;
					i= inLen;
				}
				break;
			
			//no closing brace encountered, so we have an opening brace
			default:
				stack.push(_input.substring(i,i+1));
			}
		}
		
		//if elements are left on the stack, then some braces were never closed.
		if(!stack.isEmpty())
		{
			ret = false;
		}
		
		return ret;
	}

	private static boolean isMatchingBetter(String _input)
	{
		if(_input.length()%2==1)
		{
			return false;
		}
		
		Stack<Character> stack = new Stack<>();
		
		for(char i : _input.toCharArray())
		{
			switch(i)
			{
			case '(':
				stack.push(')');
				break;
			case '{':
				stack.push('}');
				break;
			case '[':
				stack.push(']');
				break;
			default:
				if(stack.isEmpty() || !stack.pop().equals(i))
				{
					return false;
				}
			}
		}
		
		return stack.isEmpty();
	}
}
