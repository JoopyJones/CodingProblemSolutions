import java.util.ArrayList;
import java.util.Random;

public class SelectionSort {

	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<>();
		Random rand = new Random();
		
		for(int i = 0; i < 7 ; i++) {
			nums.add(rand.nextInt(100));
		}
		
		System.out.println("Original Array: " + nums.toString());
		
		//from index i, look for the smallest number in remaining indices, then swap i with smallest
		// nums.size()-1 so we don't check the last index - causes out of bounds error
		for(int i = 0; i < nums.size()-1; i++) {
			
			//get element at current index to compare against
			int temp = nums.get(i);
			int pos = i;
			
			//starting at the next index, compare all remaining elements against current index
			for(int j = i+1; j < nums.size(); j++) {
				 if(nums.get(j) < temp) {
					 temp = nums.get(j);
					 pos = j;
				 }
			}
			
			
			if(pos != i)
			{
				//set swap position to value of current index
				nums.set(pos, nums.get(i));
				
				//set current index to swap value
				nums.set(i, temp);
			}
			
		}
		
		System.out.println("Sorted Array:   " +nums.toString());
	}
	
}
