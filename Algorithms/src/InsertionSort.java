import java.util.Random;

public class InsertionSort {

	public static void main(String[] args) {
		
		int[] nums = new int[7];
		Random rand = new Random();
		
		for(int i = 0; i < 7 ; i++) {
			nums[i] = rand.nextInt(100);
		}

		printArray(nums);
		
		//start at index 1
		for(int i = 1; i <nums.length-1;i++) {
			int j = i;
			
			//compare and swap items to the left until a smaller or equal number is met
			//this will move smaller values to the left and larger values to the right at the same time
			//j>0 because j=0 is first index in array, nothing left of it
			while(j>0 && nums[j-1] > nums[j]) {
				swap(nums,j-1,j);
				j--;
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
