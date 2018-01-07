package tree.unbounded;

import java.util.ArrayList;

public class UnboundedTreeNode {

    private String data;
    private ArrayList<UnboundedTreeNode> children; 
    
    UnboundedTreeNode(String d) {
        data = d;
        children = new ArrayList<UnboundedTreeNode>();
    }
    
    public String getString (){
        return data;
    }
    
    public void add (String s){
        children.add(new UnboundedTreeNode(s));
    }
    
    public UnboundedTreeNode getChild (int index) {
        return children.get(index);
    }

}
