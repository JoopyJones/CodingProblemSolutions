import java.util.Random;

public class LinearSearch {

	public static void main(String[] args) {
		
		int range = 50;
		int[] nums = new int[20];
		Random rand = new Random();
		
		int target = rand.nextInt(range-1);
		
		
		for(int i=0; i<nums.length; i++)
		{
			nums[i] = rand.nextInt(range-1);
		}
		
		printArray(nums);
		System.out.println("Target: " + target);
		
		int searchResult = linearSearch(nums, target);
		
		
		System.out.println("Index of target in array: " + searchResult);
	}
	
	//sequentially look through the array until we find the target, or we hit the end of the array
	//if target not found, return -1
	private static int linearSearch(int[] _arr, int _targetValue)
	{
		int ret = -1;
		
		//current position in the array
		int index = 0;
		
		for(int i: _arr)
		{
			if(i == _targetValue)
			{
				ret = index;
				break;
			}
			
			index++;
		}
		
		return ret;
	}
	
	private static void printArray(int[] _arr)
	{
		System.out.print("Array: ");
		
		if(_arr != null && _arr.length > 0)
		{
			for(int i : _arr)
			{
				System.out.print("[" + i + "] ");
			}
		}
		else
		{
			System.out.print("null");
		}
		
		System.out.println();
		
	}

}
