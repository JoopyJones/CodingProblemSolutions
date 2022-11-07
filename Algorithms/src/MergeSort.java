import java.util.Random;

public class MergeSort {

	public static void main(String[] args) {
		
		int[] nums = new int[10];
		Random rand = new Random();
		
		for(int i=0; i < nums.length;i++)
		{
			nums[i] = rand.nextInt(100-1);
		}
		
		printArray(nums);
		mergeSort(nums);
		printArray(nums);
	}

	//first let's recursively break the array in half until we have single element arrays.
	public static void mergeSort(int[] _arr) {
		if(_arr.length > 1) {
			
			//determine what half of the current array is.
			//using ceiling in the scenario we have odd length, we will have the left array be the larger one.
			// ex) 7 = [4] & [3] or 3 = [2] & [1].
			int half = (int)Math.ceil(_arr.length/2);
			
			int[] leftHalf = new int[half];
			int[] rightHalf = new int[_arr.length-half];
			
			//fill up the left and right sub arrays.
			//i will fill up left array, j will fill up right, i used as main array index.
			for(int i=0,j=0; i< _arr.length;i++) {
				if(i < leftHalf.length) {
					leftHalf[i] = _arr[i];
				}
				else
				{
					rightHalf[j] = _arr[i];
					j++;
				}
			}
			
			//now that we divided the array in half, let's divide the halves in half.
			//this call will return once we divide down to single element arrays.
			mergeSort(leftHalf);
			mergeSort(rightHalf);
			
			
			//now sort both halves and merge together.
			merge(_arr,leftHalf, rightHalf);
			
			//printArray(_arr);
		}
		
		
		
	}
	
	
	private static void merge(int[] _originalArray, int[] _arr1, int[] _arr2) {
		if(_arr1 != null && _arr2 != null)
		{
			//j will be the current index of the left array.
			//k will be the current index of the right array.
			//i is used for position to insert into the array that was divided into left and right sub array.
			for(int i=0, j=0, k=0; i<_originalArray.length;i++)
			{	
			
				if(_arr1.length-j > 0 && _arr2.length-k > 0) {
					
					//if left array value is smaller, add to array, then increment left array index.
					if(_arr1[j] < _arr2[k])
					{
						_originalArray[i] = _arr1[j];
						j++;
					}
					//add right array value to array, then increment right array index.
					else
					{
						_originalArray[i] = _arr2[k];
						k++;
					}
				}
				//right array is empty, add in left array's remaining elements.
				else if (_arr1.length-j > 0){
					_originalArray[i] = _arr1[j];
					j++;
				}
				//left array is empty, add in right array's remaining elements.
				else {
					_originalArray[i] = _arr2[k];
					k++;
				}
			}
			
		}
		
	}
	
	public static void printArray(int[] _arr) {
		for(int i=0; i < _arr.length; i++) {
			System.out.print(_arr[i]);
			if(i != _arr.length-1) {
				System.out.print(",");
			}
		}
		
		System.out.println();
	}

}
