package tree.heap;

import java.util.ArrayList;

public class BinaryHeapTester {
    public static void main(String[] args){
        BinaryHeap h1 = new BinaryHeap();
        h1.insert(50);
        h1.insert(25);
        h1.insert(20);
        h1.insert(8);
        h1.insert(7);
        h1.insert(5);
        h1.insert(30);
        h1.insert(108);
        ArrayList<Integer> h2 = h1.getSortedElements();
        for(int i = 0; i<h2.size();i++){
            System.out.println(h2.get(i));
        }
    }
}
