
public class Array {

	public static void main(String[] args) {
		Array x = new Array();
		
		x.remove();
	}
	
	public static void remove() {
		int arrSize = 30;
		int target = 8;
		boolean found = false;
		
		//Declare array
		int numArray[];
		
		//instantiate array
		numArray = new int[arrSize];
		
		//initialize each index of the array
		for(int i = 0; i < arrSize; i++)
		{
			numArray[i] =  i;
		}
	
		// remove target element and shift and indexes after target
//		for(int i = 0; i < numArray.length; i++) {
//			if(!found)
//			{
//				if(numArray[i] == target)
//				{
//					found = true;
//					numArray[i] = 0;
//				}
//			}else
//			{
//				numArray[i-1] = numArray[i];
//				numArray[i] = 0;
//			}
//		}
		
		//create new array from source array 
		int newArray[] = new int[numArray.length-1];
		
		//add values to new array, exclude target value (single target, guaranteed target)
//		for(int i = 0, newIndex = 0; i< numArray.length; i++, newIndex++) {
//			if(numArray[i] == target) {
//				newIndex--;
//			}
//			else
//			{
//				newArray[newIndex] = numArray[i];
//			}
//		}
		
//		for(int i = 0; i < newArray.length; i++)
//		{
//			System.out.println(i + " :" + newArray[i]);
//		}
		
		for(int i = 0; i < numArray.length; i++)
		{
			System.out.println(i + " :" + numArray[i]);
		}
	}

	
}
