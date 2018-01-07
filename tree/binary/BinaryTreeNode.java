  package tree.binary;
  
  public class BinaryTreeNode {
      
      private String data;
      private BinaryTreeNode left, right;
      
      BinaryTreeNode (String s) {
          data = s;
          left = null;
          right = null;
      }
      
      public void add (String s, String child) {
          if (child.equals("L")) {
              left = new BinaryTreeNode(s);
          } else if (child.equals("R")) {
              right = new BinaryTreeNode(s);
          } else {
              throw new IllegalArgumentException();
          }
      }
      
      public BinaryTreeNode getChild (String child) {
          return (child.equals("L")) ? left : right;
      }
      
      public String getString () {
          return data;
      }
      
      public void doubleTree () {
          
          if(left!=null) {
              BinaryTreeNode dummy = left;
              add(data, "L");
              left.left = dummy;
              left.left.doubleTree();
          }
          else {
              add(data, "L");
              
          }
          if(right!=null){
              right.doubleTree();
          }
          
      
      }
      
      public boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
          if(n1==null && n2==null){
              return true;
          }
          
          if(n1==null || n2==null || n1.getString()!=n2.getString()){
              return false;
          }
          
          if(sameTree(n1.left, n2.left)){
              if(sameTree(n1.right, n2.right)){
                  return true;   
              }
          }
          return false;
          
      }
      
  }