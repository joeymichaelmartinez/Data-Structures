package intlist;

public class IntListAnswer {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;
    
    // Constructor
    IntListAnswer () {
        items = new int[START_SIZE];
        size  = 0;
    }
    
    public int size () {
        return size;
    }

    public int getAt(int index) {
        return items[index];
    }

    public void append(int toAdd) {
        size++;
        checkAndGrow();
        items[size] = toAdd;
    }
    
    public void prepend (int toAdd) {
        size++;
        checkAndGrow();
        shiftRight(0);
        items[0] = toAdd;
    }

    public void insertAt(int toAdd, int index) {
        size++;
        checkAndGrow();
        shiftRight(index);
        items[index] = toAdd;
    }

    public void removeAt(int index) {
        shiftLeft(index);
        size--;
    }
    
    public String toString () {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += items[i] + " ";
        }
        return result;
    }
    
    public void swapIntsAt (int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }
    
    /*
     * Expands the size of the list whenever it is at
     * capacity
     */
    private void checkAndGrow () {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }
        
        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];
        
        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        
        // Step 3: update IntList reference
        items = newItems;
    }
    
    /*
     * Shifts all elements to the right of the given
     * index one left
     */
    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }
    
    /*
     * Shifts all elements to the right of the given
     * index one right
     */
    private void shiftRight (int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i-1];
        }
    }
    
}