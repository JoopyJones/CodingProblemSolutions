
public class BinarySearch {

	public static void main(String[] args) {
		
		int[] nums = new int[10];
		int targetIndex;
		int target = 32;
		
		
		for(int i=0; i<nums.length; i++)
		{
			nums[i] = (i+i) * 2;
		}
		
		printArray(nums);
		System.out.println("Target value is: " + target);
		
		targetIndex = binarySearch(nums, target, 0, nums.length-1);
		
		System.out.println("Target's index in array is: " + targetIndex);

	}
	
	//find the middle element of the array, compare it to the target, then find midpoint left or right of the middle element compare, repeat.
	//target value less than midpoint we will find the midpoint of the left section, else find the midpoint of right section.
	//repeat until we have found the target, or arrive at a single element that is not the target.
	//if target not found, return -1
	private static int binarySearch(int[] _arr, int _targetValue, int _start, int _end)
	{
		int ret = -1;
		
		//scenario where we have a valid range to check, or we have a single element.
		if(_start <= _end)
		{
			if(_arr != null && _arr.length > 0)
			{
				//get the middle index of the current section
				int mid = (int)Math.floor((_end+_start+1)/2);
				
				//target value is found, return it's index
				if(_targetValue == _arr[mid])
				{
					ret = mid;
				}
				//if start = end we already checked and did not find it here.
				else if(_start != _end) 
				{
					if(_targetValue > _arr[mid])
					{
						ret = binarySearch(_arr,_targetValue,mid+1,_end);
						
					}
					else
					{
						ret = binarySearch(_arr,_targetValue,_start,mid-1);
					}
				}
			}
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
