import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		HashMap<String, Integer> map = new HashMap<>();
		String sentence = "";
		
		
		
		//keep reading in sentences from the keyboard until user enters stop
		while(!sentence.equalsIgnoreCase("stop"))
		{
			System.out.print("Please enter a sentence: ");
			sentence = input.nextLine();
			System.out.println();
			
			if(!sentence.equalsIgnoreCase("stop"))
			{
				char[] letters = sentence.toLowerCase().toCharArray();
				
				map = countLetters(letters);
				
				System.out.println(sentence + "\nLetter count: " + map.toString() + "\n");
			}
			else
			{
				input.close();
				System.out.println("Scanner has been closed. Goodbye");
			}
			
		}
	}
	
	//returns a hashmap with the unique letters and their frequency in the sentence
	private static HashMap<String, Integer> countLetters(char[] _sentence)
	{
		HashMap<String,Integer> ret = new HashMap<>();
		
		if(_sentence != null && _sentence.length > 0)
		{
			for(char i : _sentence)
			{
				//get the next character of the sentence
				String currentLetter = i+"".toLowerCase();
				
				//ignore spaces
				if(!currentLetter.equals(" "))
				{
					//if we have not seen this character before, add it to the hashmap
					if(!ret.containsKey(currentLetter))
					{
						ret.put(currentLetter, 1);
					}
					//if we have seen this character before, increment value
					else
					{
						ret.put(currentLetter, ret.get(currentLetter)+1);
					}
				}
			}
		}
		
		return ret;
	}
	
}
