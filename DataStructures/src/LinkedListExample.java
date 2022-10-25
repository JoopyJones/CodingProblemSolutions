import java.util.LinkedList;


public class LinkedListExample {

	public static void main(String[] args) {
		
		LinkedList<Integer> lst = new LinkedList<>();

		for(int i = 0; i < 10; i++)
		{
			lst.add(i*2);
		}
		
	
//		for(int x : lst) {
//			System.out.println(x);
//		}
		
		
		System.out.println(lst.toString());
		System.out.println(lst.getLast());
	}

}
