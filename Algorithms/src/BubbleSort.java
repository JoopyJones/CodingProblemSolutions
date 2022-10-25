import java.util.Random;


public class BubbleSort {

	public static void main(String[] args) {
		
		int[] nums = new int[5];
		Random rand = new Random();
		
		for(int i = 0; i < nums.length ; i++) {
			nums[i] = rand.nextInt(100);
		}
		
		printArray(nums);
		
		//TODO add in functionality so that on the first pass, if no swaps occur, then exit - the array is sorted
		//run this algorithm for the number of indices except the last one.
		// nums.length-1 because the 2nd to last element will check the last element. Without it, get an out of bounds error
		for(int i = 0; i<nums.length-1;i++) {
			//compare each index with the next index and swap if current index is larger
			for(int j=0; j<nums.length-1;j++) {
				int c = nums[j];
				int n = nums[j+1];
				
				if(c > n) {
					swap(nums, j, j+1);
				}
			}
		}
		
		printArray(nums);

	}
	
	public static void printArray(int[] arr)
	{
		for(int i=0; i<arr.length;i++) {
			if (!(i==arr.length-1)) {
				System.out.print(arr[i] + ",");
			}
			else
				System.out.print(arr[i]);
		}
		System.out.println();
	}

	public static void swap(int[] _arr, int _i, int _j) {
		int temp = _arr[_i];
		_arr[_i] = _arr[_j];
		_arr[_j] = temp;
	}
	
}
