/*
	example 1: 	Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
				Output: true

	example 2:  Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
				Output: true
*/

public class CompareStringArrays {

	public static void main(String[] args) {
		String[] word1 = new String[] {"gooba"};
		String[] word2 = new String[] {"go","ob","a"};
		
		System.out.println("Word1 = Word2? : " + arrayStringsAreEqualBetter(word1,word2));
		
	}
	
	//my initial solution
	private static boolean arrayStringsAreEqual(String[] word1, String[] word2)
	{
		boolean ret = true;
		
		int w1p =0;
		int w2p = 0;
		
		int char1 =0;
		int char2 = 0;
		
		
		while(ret)
		{
			//we still have strings to compare
			if(w1p < word1.length && w2p < word2.length)
			{
				System.out.println("Comparing " + word1[w1p].charAt(char1) + " - " + word2[w2p].charAt(char2));
				
				//if the current characters are not the same, we don't have same strings
				if(word1[w1p].charAt(char1) != word2[w2p].charAt(char2))
				{
					ret = false;
				}
				else
				{
					//if we've exhausted the current string, move to the next one
					if(char1 == word1[w1p].length()-1)
					{
						char1 = 0;
						w1p++;
					}
					else
					{
						char1++;
					}
					
					//if we've exhausted the current string, move to the next one
					if(char2 == word2[w2p].length()-1)
					{
						char2 = 0;
						w2p++;
					}
					else
					{
						char2++;
					}
				}
			}
			//we've exhausted all strings in each array
			else if(w1p == word1.length && w2p == word2.length)
			{
				break;
			}
			//we've fully searched 1 array and still have more in another - strings are not equal
			else
			{
				ret = false;
			}
		}
		
		return ret;
	}

	//leetcode solution
	private static boolean arrayStringsAreEqualBetter(String[] word1, String[] word2)
	{
		int w1p =0;
		int w2p = 0;
		
		int char1 =0;
		int char2 = 0;
		
		
		while(w1p < word1.length && w2p < word2.length)
		{
			//if the current characters are not the same, we don't have same strings
			//the increment happens after we search for current value of char
			if(word1[w1p].charAt(char1++) != word2[w2p].charAt(char2++))
			{
				return false;
			}
			else
			{
				//if we've exhausted the current string, move to the next one
				if(char1 == word1[w1p].length())
				{
					char1 = 0;
					w1p++;
				}
				
				//if we've exhausted the current string, move to the next one
				if(char2 == word2[w2p].length())
				{
					char2 = 0;
					w2p++;
				}
			}
		}
		
		return (w1p == word1.length && w2p == word2.length);
	}
	
	//solution from someone in leetcode discussion
	private static boolean arrayStringsAreEqualBetter2(String[] word1, String[] word2)
	{
		return String.join("", word1).equals(String.join("", word2));
	}
	
}
