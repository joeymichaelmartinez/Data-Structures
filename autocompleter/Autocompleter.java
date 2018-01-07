package autocompleter;

import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    
    public boolean isEmpty () {
        return root==null;
    }
    
    public void addTerm (String toAdd) {
        addTerm(normalizeTerm(toAdd), root, 0);
        
    }
    
    public boolean hasTerm (String query) {
        return hasTerm(normalizeTerm(query), root, 0);
    }
    
    public String getSuggestedTerm (String query) {
        return getSuggestedTerm (normalizeTerm(query), root, 0);
    }
    
    public ArrayList<String> getSortedTerms () {
        ArrayList<String> sortedTerms = new ArrayList<String>();
        return getSortedTerms(sortedTerms, "", root);
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    public void addTerm(String toAdd, TTNode current, int index){
        if(isEmpty()){
            if(toAdd.length()==1){
                current=new TTNode(toAdd.charAt(0), true);
                root=current;
                return;
            }
            else{
                current=new TTNode(toAdd.charAt(0), false);
                root=current;
            }
        }
        if (compareChars(toAdd.charAt(index), current.letter)==0){
            if(index>=toAdd.length()-1){
                current.wordEnd=true;
                return;
            }
            index++;
            if(current.mid==null){
                if(index>=toAdd.length()-1){
                    current.mid= new TTNode(toAdd.charAt(index), true);
                    return;
                }
                else{
                    current.mid= new TTNode(toAdd.charAt(index), false);
                    addTerm(toAdd, current.mid,index);
                }
            }
            addTerm(toAdd, current.mid, index);
        }
        
        else if(compareChars(toAdd.charAt(index), current.letter)<0){
            if(current.left==null){
                if(index>=toAdd.length()-1){
                    current.left=new TTNode(toAdd.charAt(index), true);
                    return;
                }
                current.left=new TTNode(toAdd.charAt(index), false);
            }
            addTerm(toAdd, current.left, index);
        }
        
        else if(compareChars(toAdd.charAt(index), current.letter)>0){
            if(current.right==null){
                if(index>=toAdd.length()-1){
                    current.right=new TTNode(toAdd.charAt(index), true);
                    return;
                }
                current.right=new TTNode(toAdd.charAt(index), false);
            }
            addTerm(toAdd, current.right, index);
        }
    }
    
    private boolean hasTerm(String query, TTNode current, int index){
        if(current==null || index==query.length()){
            return false;
        }
        if(compareChars(query.charAt(index), current.letter)==0){
            if(index==query.length()-1 && current.wordEnd){
                return true;
            }
            index++;
            return hasTerm(query, current.mid, index);
        }
        
        if(compareChars(query.charAt(index), current.letter)<0){
            return hasTerm(query, current.left, index);
        }
        
        if(compareChars(query.charAt(index), current.letter)>0){
            return hasTerm(query, current.right, index);
        }
        return false;
    }
    
    private String getSuggestedTerm (String query, TTNode current, int index) {
        if(current==null){return null;}
        if(query.charAt(index)==current.letter && index==query.length()-1){
            if(current.wordEnd){
                return query;
            }
            return finishString(current, query);
        }
        if(query.charAt(index)==current.letter){
            index++;
            return getSuggestedTerm(query, current.mid, index);
        }
        if(compareChars(query.charAt(index), current.letter)<0){
            return getSuggestedTerm(query, current.left, index);
        }
        if(compareChars(query.charAt(index), current.letter)>0){
            return getSuggestedTerm(query, current.right, index);
        }
        return null;
    }
    
    private ArrayList<String> getSortedTerms (ArrayList<String> sortedTerms, String toInsert, TTNode current){    
       
        if(current==null){
            return sortedTerms;
        }
        
        if(current.wordEnd){
            sortedTerms.add(toInsert + current.letter);
        }
        
        getSortedTerms (sortedTerms, toInsert, current.left);
        
        getSortedTerms (sortedTerms, toInsert + current.letter, current.mid);
        
        getSortedTerms (sortedTerms, toInsert, current.right);
        
        return sortedTerms; 
    }

    
    private String finishString(TTNode current, String toFinish){
        if(current.mid==null || current.wordEnd){
            return toFinish;
        }
        toFinish= toFinish + current.mid.letter;
        return finishString(current.mid, toFinish);
    }
    
    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }
    
    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }
    
    // [!] Add your own helper methods here!
    
    
    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------
    
    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {
        
        boolean wordEnd;
        char letter;
        TTNode left, mid, right;
        
        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }
        
    }
    
}
