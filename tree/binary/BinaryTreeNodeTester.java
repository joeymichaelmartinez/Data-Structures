package tree.binary;

public class BinaryTreeNodeTester {
   
    public static void main(String[] args){
        
        BinaryTreeNode root = new BinaryTreeNode("2");
        root.add("1", "L");
        root.add("3", "R");
        //System.out.println(root.getChild("L").getString());
        //System.out.println(root.getChild("R").getString());
        root.doubleTree();
        /*
        System.out.println(root.getChild("L").getString());
        System.out.println(root.getChild("L").getChild("L").getString());
        System.out.println(root.getChild("L").getChild("L").getChild("L").getString());
        System.out.println(root.getChild("R").getString());
        System.out.println(root.getChild("R").getChild("L").getString());
        
        BinaryTreeNode root2 = new BinaryTreeNode("2");
        root2.add("1", "L");
        root2.add("3", "R");
        root2.getChild("L").add("4", "L");
        root2.getChild("L").add("5", "R");
        //root2.getChild("R").add("6", "L");
        //root2.getChild("R").add("7", "R");
        root2.doubleTree();
        System.out.println(root2.getChild("L").getString());
        System.out.println(root2.getChild("L").getChild("L").getString());
        //System.out.println(root2.getChild("L").getChild("R").getString());
        System.out.println(root2.getChild("L").getChild("L").getChild("R").getString());
        System.out.println(root2.getChild("L").getChild("L").getChild("R").getChild("L").getString());
        System.out.println(root2.getChild("L").getChild("L").getChild("L").getString());
        System.out.println(root2.getChild("L").getChild("L").getChild("L").getChild("L").getString());
        System.out.println(root2.getChild("L").getChild("L").getChild("L").getChild("L").getChild("L").getString());
        System.out.println(root2.getChild("R").getString());
        System.out.println(root2.getChild("R").getChild("L").getString());
        //System.out.println(root2.getChild("R").getChild("L").getString());
        //System.out.println(root2.getChild("R").getChild("R").getString());
        */
        BinaryTreeNode root3 = new BinaryTreeNode("2");
        root3.add("1", "L");
        root3.add("3", "R");
        root3.getChild("L").add("4", "L");
        root3.getChild("L").add("5", "R");
        root3.getChild("R").add("6", "L");
        root3.getChild("R").add("7", "R");
        
        BinaryTreeNode root4 = new BinaryTreeNode("2");
        root4.add("1", "L");
        root4.add("3", "R");
        root4.getChild("L").add("4", "L");
        root4.getChild("L").add("5", "R");
        root4.getChild("R").add("6", "L");
        root4.getChild("R").add("7", "R");
        //root4.getChild("L").getChild("L").add("8", "L");
        System.out.println(root3.sameTree(root3,root4));
    }

}
