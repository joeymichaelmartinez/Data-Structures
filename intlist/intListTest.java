package intlist;

public class intListTest {
		//5 6 8 2 4
	public static void main (String[] args) {
			IntList inty = new IntList();
			inty.append(2);
			inty.append(4);
			inty.prepend(8);
			inty.prepend(6);
			inty.prepend(5);
			inty.insertAt(3, 3);
			for(int i=0; i < inty.length(); i++) {
			System.out.println(inty.getAt(i));
			}
		}
	
}
