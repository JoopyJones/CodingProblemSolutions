import java.util.HashMap;

/*
	Given an integer array nums and an integer k, return true if nums has a continuous subarray of 
	size at least two whose elements sum up to a multiple of k, or false otherwise.


	Input: nums = [23,2,6,4,7], k = 6
	Output: true
	Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.





*/
public class ContSubarraySum {

	public static void main(String[] args) {
		int[] nums = new int[] {1};
		int target = 20;
		
		System.out.println(subarraySumExistsBetter(nums,target));
		
	}
	
	//my first attempt. Works, but is too slow for large data sets
	@SuppressWarnings("unused")
	private static boolean subarraySumExists(int[] _input, int _target)
	{
		int sum;
		
		for(int i=0; i<_input.length-1; i++)
		{
			sum = _input[i];
//			System.out.println("i: " + i + " - sum: " + sum);
			
			for(int j=i+1; j<_input.length; j++)
			{
				sum+=_input[j];
//				System.out.println("	j: " + j + ", add " + _input[j] + " - sum: " + sum);
//				System.out.println("\tSum mod target: " + sum%_target);
				if(sum%_target==0)
				{
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	
	//solution from leetcode. fast for larger data sets.
	//idea: add the current element to a sum. Mod the sum by the target to get a remainder.
	//		push the remainder to the hashmap with it's index+1(accurate index doesn't matter, values just need to be consecutive).
	//		if it already exists from a lower index, we know we have a valid subset that contains a multiple of target.
	//			*if we encounter the same remainder, it means we can subtract the first value from our current subarray to get a multiple.
	//
	//		Example:	[23,2,6,4,7] target = 12
	//
	//					(23)%12 			= 11 (add to hashmap <11,1>)
	//					(23+2)%12 			=  1 (add to hashmap <1,2>)
	//					(23+2+6)%12			=  7 (add to hashmap <7,3>)
	//					(23+2+6+4)%12		= 11 (already seen at lower index!)
	//
	//					-- this means if we subtract 23 from our subarray, we have a valid subarray!
	//						(2+6+4)%12		=  0!
	private static boolean subarraySumExistsBetter(int[] _input, int _target)
	{
		int sum = 0;
		int size = _input.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		
		//this is inserted as our exit case. If we have a remainder of 0, exit function.
		map.put(0, 0);
		
		for(int i=0; i < size; i++)
		{
			sum+=_input[i];
			
			if(!map.containsKey(sum%_target))
			{
				map.put(sum%_target, i+1);
			}
			else if(map.get(sum%_target) < i)
			{
				return true;
			}
		}
		
		return false;
	}

}
