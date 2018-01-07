package tree.unbounded;

public class UnboundedTreeTest {

    public static void main(String[] args) {
        UnboundedTreeNode root = new UnboundedTreeNode("A");
        root.add("B");
        root.add("C");
        root.add("D");
        UnboundedTreeNode it = root.getChild(1);
        it.add("E");
        it.add("F");
    }
    
}
