
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

		int ret = removeDupsBetter(nums);
		System.out.println("Result is: " + ret);
		
	}

	//my original attempt. It works, but is inefficient.
	//idea was to continuously swap the duplicate element to the end of the array, after which
	//	would leave only unique values at the beginning of array.
	@SuppressWarnings("unused")
	private static int removeDups(int[] _input)
	{
		//next position to move a duplicate to
		int swapPos = _input.length-1;
		
		//the counter for moving the duplicate
		int j = 0;
		
		for(int i=0; i <swapPos;i++)
		{
			if(_input[i] == _input[i+1])
			{
				j = i;
				while(j < swapPos)
				{
					swapPos(_input,j++);
//					System.out.print("After Swap: ");
//					printArray(_input);
				}
				
				swapPos--;
				
				//decrement current index just in case we moved another duplicate into current position.
				i--;
			}
		}
		
		//swapPos will hold the index of the last valid element, but we need to return the length of the valid elements
		return swapPos+1;
	}
	
	private static void swapPos(int[] _arr, int _i)
	{
		int temp = _arr[_i];
		_arr[_i] = _arr[_i+1];
		_arr[_i+1] = temp;
	}
	
	@SuppressWarnings("unused")
	private static void printArray(int[] _arr)
	{
		System.out.print("[");
		for(int i: _arr)
		{
			System.out.print(i + " ");
		}
		System.out.println("]");
	}
	
	//solution from leetcode
	//idea is to have two pointers - left pointer to point to next index to place a unique value,
	//	and a right pointer to search right to find next unique value.
	private static int removeDupsBetter(int[] _input)
	{
		//left pointer will point to next index to place next unique value.
		//this also means that lp's value is the number of unique values found.
		int lp = 1;
		
		//rp will keep looking right until it finds the next unique value.
		int rp = 1;
		
		while(rp<_input.length)
		{
			//compare to see if we have a new unique number.
			if(_input[rp] != _input[rp-1])
			{
				//we found a new unique number, put it in index pointed to by lp, then increment lp.
				_input[lp++] = _input[rp];
			}
			rp++;
		}
		
		//lp will contain the number of unique elements found, or lp-1 for the last index subarray of unique values.
		return lp;
	}
}
