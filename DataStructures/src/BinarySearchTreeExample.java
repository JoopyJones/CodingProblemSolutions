import java.util.ArrayList;

public class BinarySearchTreeExample {
	
	//this is the root node.
	Node head;
	
	//temporary array used when rebalancing the tree
	ArrayList<Node> sortedArray;

	public static void main(String[] args) {
		
		BinarySearchTreeExample tree = new BinarySearchTreeExample();
		
		tree.addNode(new Node(10));
		tree.addNode(new Node(6));
//		tree.addNode(new Node(8));
//		tree.addNode(new Node(7));
//		tree.addNode(new Node(9));
		tree.addNode(new Node(2));
		tree.addNode(new Node(1));
		tree.addNode(new Node(5));
		tree.addNode(new Node(14));
		tree.addNode(new Node(11));
		tree.addNode(new Node(15));
		
		System.out.println(tree.isBalanced(tree.head));
		
		tree.preOrderTraversal(tree.head);
		System.out.println("---------------------------");
		tree.balanceTree();
		
		tree.preOrderTraversal(tree.head);
		
		System.out.println(tree.isBalanced(tree.head));
		
				
		
	}
	
	//this function adds a node to the tree by comparing the current node to the new node, and looking down the subtree to find an empty spot for it.
	//TODO - add blananceTree to this function once implemented.
	public void addNode(Node _newNode) {

		
		//Empty tree, add node as root
		if(this.head == null)
		{
			this.head = _newNode;
		}
		else
		{
			Node focusNode = this.head;
			Node parent;
			
			//while we still haven't found an empty spot to put new node. Still have nodes to compare against.
			while(true)
			{
				parent = focusNode;
				
				//new node's value needs to be be in current node's left subtree.
				if(_newNode.getData() < focusNode.getData())
				{
					focusNode = focusNode.getLeft();
					
					//current node has no left subtree, new node is placed here.
					if(focusNode == null)
					{
						parent.setLeft(_newNode);
						
						if(isBalanced(this.head) == false)
						{
							this.balanceTree();
						}
						
						return;
					}
				}
				//new node's value needs to be in the current node's right subtree.
				else
				{
					focusNode = focusNode.getRight();
					
					//current node has no right subtree, new node is placed here.
					if(focusNode == null)
					{
						parent.setRight(_newNode);
						
						if(isBalanced(this.head) == false)
						{
							this.balanceTree();
						}
						
						return;
					}
				}
			}
		}
	}

	//find and delete a node from the tree.
	//Steps: 1. find node, swap with successor/predecessor if child =2, then delete from tree.
	//TODO - add blananceTree to this function once implemented.
	public void deleteNode(int _x)
	{
		Node[] targetFamily = getNode(_x);
		
		if(targetFamily[2] != null)
		{
			//info for the node we want to delete
			Node targetNode = targetFamily[2];
			Node targetParent = targetFamily[1];
			Node targetLeft = targetNode.getLeft();
			Node targetRight = targetNode.getRight();
			
			
			//If the target node has two children, we need to find the successor or predecessor and swap places with the target
			if(targetLeft != null && targetRight != null)
			{
				//get the node with the next larger value
				Node temp = getSuccessor(targetNode.getData());
				
				if(temp == null)
				{
					//no successor, grab the node with the next smaller value
					temp = getPredecessor(targetNode.getData());
				}
				
				//info for the node that we are swapping with
				Node[] tempFamily = getNode(temp.getData());
				Node tempParent = tempFamily[1];
				Node tempLeft = temp.getLeft();
				Node tempRight = temp.getRight();
				
				//set the target node's children to the children of the swap node
				targetNode.setLeft(tempLeft);
				targetNode.setRight(tempRight);	
				
				//swap node is a left child
				if(temp == tempParent.getLeft())
				{
					//set the swap node's children to target's children
					temp.setLeft(targetLeft);
					temp.setRight(targetRight);
					
					//if the target is the swaps parent, set swap node as swap parent, so we move target to be swap's child
					if(targetNode == tempParent)
					{
						//TODO remove this setLeft, it's redundant
						temp.setLeft(targetNode);
						tempParent = temp;
					}
					
					//move target node to swap position
					tempParent.setLeft(targetNode);
				}
				//swap node is a right child
				else
				{
					//set the swap node's children to target's children
					temp.setLeft(targetLeft);
					temp.setRight(targetRight);
					
					//if the target is the swaps parent, set swap node as swap parent, so we move target to be swap's child
					if(targetNode == tempParent)
					{
						//TODO remove this setRight, it's redundant
						temp.setRight(targetNode);
						tempParent = temp;
					}
					
					//move target node to swap position
					tempParent.setRight(targetNode);
				}

				//if the target node is the root, then we just need to set head to the swap node
				//TODO what is this logic for exactly?
				if (targetFamily[2] != this.head) 
				{
					//put the swap node in target node's original position
					if (targetFamily[2] == targetParent.getLeft()) 
					{
						targetParent.setLeft(temp);
					} 
					else 
					{
						targetParent.setRight(temp);
					} 
				}
				else
				{
					this.head = temp;
				}
				
				//update target node's new position/children for deletion logic
				targetParent = tempParent;
				targetLeft = targetNode.getLeft();
				targetRight = targetNode.getRight();
			}
			
			
			//target has no children, just remove from parent
			if(targetLeft == null && targetRight == null)
			{
				if(targetNode == targetParent.getLeft())
				{
					targetParent.setLeft(null);
				}
				else
				{
					targetParent.setRight(null);
				}
			}
			//target only has a right subtree, set the target's new subtree in target's place
			else if(targetLeft == null)
			{
				if(targetNode == targetParent.getLeft())
				{
					targetParent.setLeft(targetRight);
					targetNode.setRight(null);
				}
				else
				{
					targetParent.setRight(targetRight);
					targetNode.setRight(null);
				}
			}
			//target only has a left subtree, set the target's new subtree in target's place
			else
			{
				if(targetNode == targetParent.getLeft())
				{
					targetParent.setLeft(targetLeft);
					targetNode.setLeft(null);
				}
				else
				{
					targetParent.setRight(targetLeft);
					targetNode.setLeft(null);
				}
			}
			
			
			if(isBalanced(this.head) == false)
			{
				this.balanceTree();
			}
			
		}
	}
	
	
	//TODO - there may be a better way to do this, but this will be the first attempt.
	//idea = copy BST to array using inorder traversal, then take the mid of the array, set as root, then do the same for the left and right sub arrays.
	private void balanceTree()
	{
		if(this.head != null)
		{
			int treeSize = this.getSize(this.head);
			
			this.sortedArray = new ArrayList<>();
			
			//adds the tree to the array in sorted order
			this.inOrderAddToArray(this.head);
			
			//get rid of current tree
			this.head = null;
			
			//build a new tree from the array values
			balanceTreeUtil(this.sortedArray, 0, treeSize-1);
			
			//clear temporary array
			this.sortedArray.clear();
		}
	}
	
	private void balanceTreeUtil(ArrayList<Node> _x, int _start, int _end)
	{
		//TODO describe what cases this if is for. too tired to remember now. Sometimes start > end.
		if(_start <= _end)
		{
			//for sections with more than 1 element
			if(_end != _start)
			{
				//find the middle index of the current section.
				//mid is the index that we will act as a partition
				int mid = (int)Math.floor((_end+_start+1)/2);
				
				//mid is the next item we will add to tree
				this.addNode(_x.get(mid));
				
				//divide section left of mid
				balanceTreeUtil(_x, _start, mid-1);
				
				//divide section right of mid
				balanceTreeUtil(_x,mid+1, _end);
			}
			//single element section, no divide necessary. 
			else
			{
				this.addNode(_x.get(_start));
			}
		}
	}
		
	
	private void inOrderAddToArray(Node _x)
	{
		if(_x != null)
		{
			inOrderAddToArray( _x.getLeft());
			this.sortedArray.add(new Node(_x.getData()));
			inOrderAddToArray(_x.getRight());
		}
	}
	
	public void inOrderTraversal(Node focusNode) {
		if(focusNode != null)
		{
			inOrderTraversal(focusNode.getLeft());
			System.out.println("Current Node: " + focusNode.getData());
			inOrderTraversal(focusNode.getRight());
		}
	}
	
	public void preOrderTraversal(Node focusNode) {
		if(focusNode != null)
		{
			System.out.println("Current Node: " + focusNode.getData());
			preOrderTraversal(focusNode.getLeft());
			preOrderTraversal(focusNode.getRight());
		}
	}
	
	public void postOrderTraversal(Node focusNode) {
		if(focusNode != null)
		{
			postOrderTraversal(focusNode.getLeft());
			postOrderTraversal(focusNode.getRight());
			System.out.println("Current Node: " + focusNode.getData());
		}
	}

	//returns the grandparent, parent, and desired node if found.
	public Node[] getNode(int _x)
	{
		Node focusNode = this.head;
		Node parent = focusNode;
		Node gp = parent;
		
		//If desired node is found, this array will contain the grandparent, the parent, and the found node.
		//Grandparent and parent used for predecessor and successor functions.
		Node[] ret = new Node[3];
		
		while(focusNode != null && focusNode.getData() != _x)
		{
			gp = parent;
			parent = focusNode;
			
			//left subtree only contains values less than current node
			if(_x < focusNode.getData())
			{
				focusNode = focusNode.getLeft();
			}
			//right subtree holds values greater than or equal to current node
			else 
			{
				focusNode = focusNode.getRight();
			}
			
		}
		
		ret[0] = gp;
		ret[1] = parent;
		ret[2] = focusNode;
		return ret;
	}
	
	//this function will get the node with the next largest value.
	//rightmost nodes in the tree have no successor.
	public Node getSuccessor(int _x)
	{

		Node[] famTree = this.getNode(_x);
		
		Node gp = famTree[0];
		Node parent = famTree[1];
		Node focusNode = famTree[2];

		
		if(focusNode != null)
		{
			//x has a right subtree, return the leftmost node of the right subtree.
			if(focusNode.getRight() != null)
			{
				return this.getLeftMost(focusNode.getRight());
			}
			//x has no right subtree and x is a right child, return the grandparent if x is not the rightmost node in the tree.
			else if(focusNode.getRight() == null && parent.getRight() == focusNode)
			{
				//if the node is the rightmost node, there is no successor
				if(focusNode != this.getRightMost(this.head))
				{
					return null;
				}
				
				return gp;
				
			}
			//x has no right subtree and x is a left child, return the parent.
			else if(focusNode.getRight() == null && parent.getLeft() == focusNode)
			{
				return parent;
			}
			else
			{
				return focusNode;
			}
		}
		else
		{
			return null;
		}
	}
	
	//this function gets the node with the next smallest value.
	//leftmost nodes in the tree have no predecessor.
	public Node getPredecessor(int _x)
	{

		Node[] famTree = this.getNode(_x);
		
		Node gp = famTree[0];
		Node parent = famTree[1];
		Node focusNode = famTree[2];

		
		if(focusNode != null)
		{
			//x has a left subtree, return the rightmost node of left subtree.
			if(focusNode.getLeft() != null)
			{
				return this.getRightMost(focusNode.getLeft());
			}
			//x has no left subtree and x is a left child, then return the grandparent if it's not the leftmost node.
			else if(focusNode.getLeft() == null && parent.getLeft() == focusNode)
			{
				//if the node is the leftmost node, then there is no predecessor
				if(focusNode != this.getLeftMost(this.head))
				{
					return null;
				}
				
				return gp;
			}
			//x has no left subtree and x is a right child, return the parent.
			else if(focusNode.getLeft() == null && parent.getRight() == focusNode)
			{
				return parent;
			}
			else
			{
				return focusNode;
			}
		}
		else
		{
			return null;
		}
	}
	
	public Node getLeftMost(Node _right)
	{
		Node focusNode = _right;
		
		while(focusNode.getLeft() != null)
		{
			focusNode = focusNode.getLeft();
		}
		
		return focusNode;
	}
	
	public Node getRightMost(Node _left)

	{
		Node focusNode = _left;
		
		while(focusNode.getRight() != null)
		{
			focusNode = focusNode.getRight();
		}
		
		return focusNode;
	}
	
	//get the maximum number of edges starting from the current node.
	//recursively return the length of the left and right subtree at each node, then take the max height.
	public int getHeight(Node _focusNode)
	{
		if(_focusNode != null)
		{
			int height = 0;
			Node left = _focusNode.getLeft();
			Node right = _focusNode.getRight();
			
			//no children, no edge.
			if(left== null && right == null)
			{
				return 0;
			}
			//add 1 to account for edge between current node and right child, then get right child's height.
			else if(left == null)
			{
				height++;
				height+= getHeight(right);
				
			}
			//add 1 to account for edge between current node and left child, then get left child's height.
			else if(right == null)
			{
				height++;
				height+= getHeight(left);
			}
			//both children. add 1 to account for edge between the children, then take the max height between the two subtrees.
			else
			{
				height++;
				height+= Math.max(getHeight(left), getHeight(right));
			}
			
			return height;
		}
		//tree with only 1 element has zero height.
		else
		{
			return 0;
		}
	}
	
	//This function recursively checks that every subtree is balanced.
	//If left subtree vs right subtree height is greater that 1, entire tree is not balanced.
	public boolean isBalanced(Node _focusNode)
	{
		boolean balanced = true;
		
		
		if(_focusNode != null && balanced)
		{
			//First check that the left substree is balanced.
			balanced = isBalanced(_focusNode.getLeft());
			
			if(balanced)
			{
				//If left subtree is balanced, now check the right substree.
				balanced = isBalanced(_focusNode.getRight());
				
				if(balanced)
				{
					int leftHeight = 0;
					int rightHeight = 0;
					
					//get the height of the left subtree.
					//add 1 to account for the edge between current node and left child, since we're getting height starting at the child.
					if(_focusNode.getLeft() != null)
					{
						leftHeight = getHeight(_focusNode.getLeft());
						leftHeight++;
					}
				
					//get the height of the right subtree.
					//add 1 to account for the edge between current node and left child, since we're getting height starting at the child.
					if(_focusNode.getRight() != null)
					{
						rightHeight = getHeight(_focusNode.getRight());
						rightHeight++;
					}
					
					//any difference between the heights > 1 it is unbalanced tree. return false.
					if(Math.abs(leftHeight - rightHeight) > 1)
					{
						return false;
					}
				}
			}
			
		}
		
		return balanced;
	}
	
	//TODO print the tree in a nice format
	public void printTree()
	{
		String buffer = "          ";
		Node focusNode = this.head;
		
		while(focusNode.getLeft() != null || focusNode.getRight() != null)
		{
			
		}
		
	}
	
	private int getSize(Node _x)
	{
		int size =0;
		
		if(_x != null)
		{
			size = 1;
			
			if(_x.getLeft() != null)
			{
				size+= getSize(_x.getLeft());
			}
			
			if(_x.getRight() != null)
			{
				size+= getSize(_x.getRight());
			}
		}
		
		return size;
	}
}
