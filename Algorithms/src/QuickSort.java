import java.util.ArrayList;
import java.util.Random;

public class QuickSort {

	public static void main(String[] args) {
		
		ArrayList<Integer> nums = new ArrayList<>();
		Random rand = new Random();
		
		for(int i = 0; i < 12 ; i++) {
			nums.add(rand.nextInt(25-1));
		}
		
		System.out.println("Original Array: " + nums.toString());
		qSort(nums, 0, nums.size()-1);
		System.out.println("Sorted Array:   " + nums.toString());
	}
	
	public static void qSort(ArrayList<Integer> _arr, int _left, int _right) {
		

		//move left pointer until a number greater than pivot is found or left pointer = right pointer.
		//move right pointer until a number less than pivot is found or right pointer = left pointer.
		//swap values if not the same index.
		//repeat until left pointer and right pointer are same index
		//then swap pivot with current index
		
		if(_left >= _right)
		{
			return;
		}
		
		int pivot = _arr.get(_right);
		int lp = _left;
		int rp = _right;
		
		//while we still have elements to check
		while(lp < rp) 
		{
			//move the left pointer right to find a number larger than pivot, or until pointers are same index.
			while(_arr.get(lp) <= pivot && lp < rp) {
				lp++;
			}
			
			//move the right pointer left to find a number less than pivot, or until pointers are the same index.
			while(_arr.get(rp) >= pivot && rp > lp) {
				rp--;
			}
			
			
			//if left pointer found a number greater than pivot, and right pointer found a number less than pivot, swap.
			if(lp != rp)
			{
				swap(_arr, lp, rp);
			}
			
		}
		
		//when left pointer and right pointer are the same, it's pointing to the last number larger than pivot, so swap index with pivot.
		//after this swap, pivot will be in place, all values left of pivot are less, and all values right of pivot are greater.
		if(lp != _right)
		{
			swap(_arr, lp, _right);
		}

		//sort everything left of pivot.
		qSort(_arr,_left,lp-1);
		
		//sort everything right of pivot
		qSort(_arr,lp+1,_right);
		
	
		
	}
	
	public static void swap(ArrayList<Integer> _arr, int _left, int _right) 
	{
		int temp = _arr.get(_left);
		_arr.set(_left, _arr.get(_right));
		_arr.set(_right, temp);
	}
	

}
