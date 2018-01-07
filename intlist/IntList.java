package intlist;

public class IntList {
	//fields
	int size;
	int[] items;
	
	private static final int START_SIZE = 8;
	
	//constructor
	IntList() {
	    items = new int[START_SIZE];
		size = 0;
	}
	
	//Methods
	public void append (int toAdd) {
		checkAndGrow();
		items[size] = toAdd;
		size++;
	}
	
	public int getAt (int index) {
		if (index >=size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return items[index];
	}
	
	public void removeAt (int index) {
		shiftLeft(index);
		size--;
	}
	
	
	/*
	 * shift all elements to the right of an index one left
	 */
	private void shiftLeft (int index) {
		for (int i = index; i < size - 1; i++) {
			items[i] = items[i + 1];
		}
	}
	
	/*
	 * Expands the size of the list whenever it is at
	 * capacity
	 */
	
	private void checkAndGrow () {
		//Not at capacity, so do nothing
		if (size < items.length) {
			return;
		}
		
		//Case: we're at capacity and need to grow
		int[] newItems = new int[items.length * 2];
		
		for (int i = 0; i < items.length; i++) {
			newItems[i] = items[i];
		}
		
		items = newItems;
		
	}
	
	private void shiftRight(int index) {
		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}
	}
	
	public void prepend(int toAdd) {
		checkAndGrow();
		shiftRight(0);
		items[0] = toAdd;
		size++;
	}
	
	public void insertAt(int toAdd, int index) {
		checkAndGrow();
		if(index == size) {
			append(toAdd);
		}
		
		else {
			shiftRight(index);
			items[index] = toAdd;
		}
		size++;
	}
	
	public int length() {
		return items.length;
	}
	
}
