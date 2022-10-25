
public class Node {

	private int data;
	private Node left, right;
	
	public Node(int _data) {
		this.data = _data;
		this.left = null;
		this.right = null;
	}
	
	public int getData() {
		return this.data;
	}
	public void setData(int _data) {
		this.data = _data;
	}
	public Node getLeft() {
		return this.left;
		
	}
	public Node getRight() {
		return this.right;
	}
	public void setLeft(Node _left) {
		this.left = _left;
	}
	public void setRight(Node _right) {
		this.right = _right;
	}
	
	public Node copyNode() {
		return new Node(this.getData());
	}
	
}
