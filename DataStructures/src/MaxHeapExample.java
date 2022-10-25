import java.util.Random;

public class MaxHeapExample {

	Node[] head;
	int nextSpot = 0;
	int maxSize;
	
	public static void main(String[] args) {
		
		MaxHeapExample heap = new MaxHeapExample();
		
		Node[] nums = new Node[10];
		int numsLength = nums.length;
		
		Random rand = new Random();
		
		
		for(int i=0; i< numsLength; i ++)
		{
			nums[i] = new Node(rand.nextInt(30+1));
		}
		
		System.out.println("Input: ");
		for(int i=0; i< numsLength; i++)
		{
			System.out.print("["+nums[i].getData()+"] ");
		}
		
		System.out.println("\n\nSorted: ");
		
		heap.heapSort(nums, numsLength);
		
		for(int i=0; i< numsLength; i++)
		{
			System.out.print("["+nums[i].getData()+"] ");
		}

		
	}
	
	private void heapSort(Node[] _arr, int _size) {
		
		//push the elements of the array to a heap.
		// O(n) time.
		for(int i=0; i < _size; i++)
		{
			//each push will add element to end of the array, then heapifyUp to maintain it as a max heap.
			this.push(_arr[i]);
		}
		
		
		for(int i=0; i< _size; i++)
		{
			//each pop will return the top element, swap the last element to the top, then heapifyDown to maintain the max heap.
			Node temp = this.pop();
			_arr[_size-1-i] = temp;
			
		}
	}
	
	@SuppressWarnings("unused")
	private void printHeap()
	{
		if(this.head != null)
		{
			for(Node x : this.head)
			{
				if(x != null)
				{
					System.out.print("[" +x.getData() + "] ");
				}
				else
				{
					System.out.print("[null] ");
				}
			}
			System.out.println("");
		}
		else
		{
			System.out.println("Heap is empty!");
		}
	}
	
	
	private void incrementIndex()
	{
		this.nextSpot++;
	}
	
	private void decrementIndex()
	{
		this.nextSpot--;
	}

	//removes the top element in the heap, swaps the last element to the top, then swaps with largest child until in correct spot.
	public Node pop()
	{
		Node ret = null;
		
		
		if(this.head != null && this.head[0] != null)
		{
			//if the heap only has 1 element.
			if(nextSpot == 1)
			{
				ret = this.head[0];
				head[0] = null;
				this.decrementIndex();
			}
			else
			{
				//set the top position to the last element in the heap.
				ret = this.head[0];
				head[0] = head[nextSpot-1];
				head[nextSpot-1] = null;
				
				this.decrementIndex();
				
				//continually swap new top with the largest of it's children to make it a max heap again.
				heapifyDown(0);

			}
			
			//shrink the heap so we are not wasting space.
			if(this.nextSpot < (int)Math.floor(this.maxSize/2))
			{
				resizeArray();
			}
		}
		
		//nothing in heap, remove heap.
		if(this.head[0] == null)
		{
			this.head = null;
		}
		
		return ret;
		
		
	}
	
	//adds an element in the next available position, then continually swaps with parent if value is larger than parents.
	public void push(Node _x)
	{
		//instantiate the heap.
		if(this.head == null)
		{
			resizeArray();
		}
		
		//if we have space in the heap, add element.
		if(this.nextSpot < this.maxSize)
		{
			head[nextSpot] = _x;
			this.incrementIndex();
			
			//swap new element with parent until it's in correct spot.
			this.heapifyUp(this.nextSpot-1);
		}
		//resize array - double the size
		else
		{
			resizeArray();
			
			head[nextSpot] = _x;
			this.incrementIndex();
			
			//swap new element with parent until it's in correct spot.
			this.heapifyUp(this.nextSpot-1);
		}
		
		
	}
	

	//this function will recursively swap a parent with it's largest value child until it's in the correct position.
	//used for removing items from a heap.
	//correct position is when parent >= value >= child values
	private void heapifyDown(int _index)
	{
		if(_index >= 0 && _index < this.nextSpot-1)
		{
			boolean hasLeft = this.hasLeftChild(_index);
			boolean hasRight = this.hasRightChild(_index);
			
			int left = (2*_index)+1;
			int right = (2*_index)+2;
			Node temp = this.head[_index];
			int tempValue = this.head[_index].getData();
			
			
			if(hasLeft || hasRight)
			{
				//if current index has two children
				if(hasLeft && hasRight)
				{
					//if the current index's value is less than either of it's children's.
					if(tempValue < Math.max(this.head[left].getData(), this.head[right].getData()))
					{
						//find greater child's value and swap with current index
						if(this.head[left].getData() > this.head[right].getData())
						{	
							this.head[_index] = this.head[left];
							this.head[left] = temp;
							
							//swap again from new position.
							heapifyDown(left);
						}
						else
						{
							this.head[_index] = this.head[right];
							this.head[right] = temp;
							
							//swap again from new position.
							heapifyDown(right);
						}
						
					}
				}
				//Heap will never have only a right child. Compare index to left child.
				else
				{
					//check if current index's values is less than left child, if so swap
					if(tempValue < this.head[left].getData())
					{
						this.head[_index] = this.head[left];
						this.head[left] = temp;

					}
				}
				
			}
			
		}
	}
	
	//this function will recursively swap a child and it's parent until it's in the correct position.
	//used when inserting items into a heap.
	//correct position is when parent >= value >= child values
	private void heapifyUp(int _index)
	{
		if(_index > 0)
		{
			int childIndex = _index;
			Node childElement = this.head[childIndex];
			
			int parentIndex = (int)Math.floor((childIndex-1)/2);
			Node parent = this.head[parentIndex];
			
			//if child is larger than parent, swap
			if(childElement.getData() > parent.getData())
			{
				this.head[parentIndex] = childElement;
				this.head[childIndex] = parent;
				this.heapifyUp(parentIndex);
			}
		}
	}
	

	
	private boolean hasLeftChild(int _index)
	{
		//check if index is out of bounds, or if element is null. Null element means empty space in heap.
		if(((2*_index)+1 >= this.maxSize ||_index >= this.maxSize) || this.head[(2*_index)+1] == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean hasRightChild(int _index)
	{
		//check if index is out of bounds, or if element is null. Null element means empty space in heap.
		if(((2*_index)+2 >= this.maxSize ||_index >= this.maxSize) || this.head[(2*_index)+2] == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private void resizeArray()
	{
		Node[] newArray;
		int newSize;
		
		//array has not been instantiated yet, create new array
		if(this.head == null)
		{
			newSize = 5;
			newArray = new Node[newSize];
			
		}
		//TODO shrink the array
		else if(this.nextSpot < this.maxSize)
		{
			//shrink the size by arrayLength/2 + (number of current elements)/2
			newSize = (int)(Math.floor(this.nextSpot/2) + Math.ceil(this.maxSize/2));
			newArray = new Node[newSize];
			
			for(int i = 0; i < this.nextSpot ; i++)
			{
				newArray[i] = this.head[i];
			}
		}
		else
		{
			//double the array size
			newSize = this.maxSize*2;
			newArray = new Node[newSize];
			
			for(int i = 0; i < this.nextSpot ; i++)
			{
				newArray[i] = this.head[i];
			}
			
		}
		
		this.maxSize = newSize;
		this.head = newArray;
	}
	
}
