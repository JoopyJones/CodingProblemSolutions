
/*

	Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
	You may assume that each input would have exactly one solution, and you may not use the same element twice.
	You can return the answer in any order.


		The idea is to push each unique value from the array to a hashmap. Key = value, value = index in array.
		At the same time, check to see if the current value's complement is in the hashmap.
		- if it is, then return the complement's index(stored in value) as well as the current index.
		- if no solution return 0,0.

*/
import java.util.HashMap;

public class TwoSum {

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,6,4,1,6,7,8};
		int target = 9;

		int[] x = twoSum(nums, target);
		
		System.out.println("Solution: [" + x[0] + "," + x[1] +"]");
		
		
	}
	
	public static int[] twoSum(int[] _nums, int _target)
	{
		//key will be value, value will be index
		HashMap<Integer,Integer> map = new HashMap<>();
		int complement;
		
		int[] ret = new int[]{0,0};
		
		
		for(int i=0; i < _nums.length; i++)
		{
			complement = (_target-_nums[i]);
			
			//if we found the complement and it's not our current element, return
			if(map.containsKey(complement) && map.get(complement) != i) {
				ret = new int[] {map.get(complement),i};
				i = _nums.length;
			}
			else
			{
				//if we haven't seen this value before, add it to the array plus it's index
				if(!map.containsKey(_nums[i]))
				{
					map.put(_nums[i], i);
				}
			}
		}
		
		return ret;
	}

}
