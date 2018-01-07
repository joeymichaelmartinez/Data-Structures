package sentinal;

import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    
    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    
    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash () {
        size=0;
        longest=0;
        buckets = new LinkedList[BUCKET_COUNT];
    }
    
    
    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------
    
    public int size () {
        return size;
    }
    
    public boolean isEmpty () {
        return size==0;
    }
    
    public void put (String s) {
        String trim = s.trim();
        String[] words = (trim.split(" "));
        if(words.length>longest){
            longest = words.length;
        }
        
        if(buckets[hash(s)]==null){
            buckets[hash(s)]= new LinkedList<String>();
            buckets[hash(s)].add(s);
            size++;
        }
        
        if(!buckets[hash(s)].contains(s)){
            buckets[hash(s)].add(s);
            size++;
        }           
    }
    
    public String get (String s) {
        if(buckets[hash(s)]==null){
            return null;
        }
        if(buckets[hash(s)].contains(s)){
            return s;
        }
        return null;
    }
    
    public int longestLength () {
        return longest;
    }
    
    
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    
    private int hash (String s) {
        int totalStringVal=0;
        for(int i=0; i<s.length(); i++){
            totalStringVal+=Character.getNumericValue(s.charAt(i));   
        }
        return totalStringVal%BUCKET_COUNT;
    }
    
}
