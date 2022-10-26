/*

	Given an integer array nums sorted in non-decreasing order, 
	remove the duplicates in-place such that each unique element appears only once. 
	The relative order of the elements should be kept the same.

	Since it is impossible to change the length of the array in some languages, 
	you must instead have the result be placed in the first part of the array nums. 
	More formally, if there are k elements after removing the duplicates, then the first k elements 
	of nums should hold the final result. It does not matter what you leave beyond the first k elements.

	input array must be sorted in no place, O(1) space complexity


	example:
			Input: nums = [0,0,1,1,1,2,2,3,3,4]
			Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]




*/
public class RemoveDuplicatesInPlace {

	public static void main(String[] args) {
		int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};

		int ret = removeDups(nums);
		
	}

	//TODO - figure out how to deal with more than 2 equal consecutive numbers.
	//have the right idea, but implementation is wrong. Also consider starting from end of array.
	private static int removeDups(int[] _input)
	{
		int swapPos = _input.length-1;
		int j = 0;
		
		for(int i=0; i <swapPos;i++)
		{
			if(_input[i] == _input[i+1])
			{
				j = i;
				while(j < swapPos)
				{
					swapPos(_input,j++);
					System.out.print("After Swap: ");
					printArray(_input);
				}
				
				swapPos--;
			}
		}
		
		return swapPos+1;
	}
	
	private static void swapPos(int[] _arr, int _i)
	{
		int temp = _arr[_i];
		_arr[_i] = _arr[_i+1];
		_arr[_i+1] = temp;
	}
	
	private static void printArray(int[] _arr)
	{
		System.out.print("[");
		for(int i: _arr)
		{
			System.out.print(i + " ");
		}
		System.out.println("]");
	}
}
