package yarn;

public class Yarn implements YarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Entry[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Yarn () {
        items = new Entry[MAX_SIZE];
        size = 0;
        uniqueSize = 0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        if(size==0) {
            return true;
        }
        return false;
    }
    
    public int getSize () {
        return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public boolean insert (String toAdd) {
        if(uniqueSize==MAX_SIZE) {
            return false;
        }
        for(int i=0; i <= uniqueSize; i++) {
            if(i==uniqueSize) {
                items[i] = new Entry(toAdd, 1);
    			size++;
    			uniqueSize++;
    			return true;
    		}
    		
    		else if(toAdd.equals(items[i].text)) {
    			items[i].count++;
    			size++;
    			return true;
    		}
    	}
    	return false;
    }
    
    public int remove (String toRemove) {
    	for(int i=0; i<uniqueSize; i++) {
    		if(toRemove.equals(items[i].text) && items[i].count!=1) {
    			size--;
    			return items[i].count--;
    		}
    		else if(toRemove.equals(items[i].text)){
    			size--;
    			uniqueSize--;
    			items[i]=items[uniqueSize];
    			items[uniqueSize]=null;
    			return 0;
        	}
    	}
    	
    	return 0;
    }
    
    public void removeAll (String toNuke) {
    	for(int i=0; i<uniqueSize; i++) {
    		if(toNuke.equals(items[i].text)) {
    			size-=items[i].count;
    			uniqueSize--;
    			items[i]=items[uniqueSize];
    			items[uniqueSize]=null;
    		}
    	}
    }
    
    public int count (String toCount) {
    	for(int i=0; i<uniqueSize; i++) {
    		if(toCount.equals(items[i].text)) {
    			return items[i].count;
    		}
    	}
    		return 0;
    }
    
    public boolean contains (String toCheck) {
    	for(int i=0; i<uniqueSize; i++) {
    		if(toCheck.equals(items[i].text)) {
    			return true;
    		}
    	}
    	return false;
    		
    }
    
    public String getNth(int n) {
    	if(n<0) {
    		return "";
    	}
    	for(int i=0, k=0; i<uniqueSize; i++) {
    		k+= items[i].count;
    		
    		if(n<k){
    			return items[i].text;
    		}
    	}
    	return "";
    }
    
    public String getMostCommon () {
    	Entry mostCommon = items[0];
    	for(int i=1; i<uniqueSize; i++){
    		if(mostCommon.count < items[i].count) {
    			mostCommon = items[i];
    			if(i==uniqueSize-1){
    				return mostCommon.text;
    			}
    		}
    	}
    		
    	return mostCommon.text;
    }
    
    public Yarn clone () {
    	Yarn ball = new Yarn();
    	for(int i=0; i<size; i++){
    		ball.insert(getNth(i));
    	}
    	return ball;
    }
    
    public void swap (Yarn other) {
        Yarn ball = this.clone();
        this.items = other.items;
        other.items = ball.items;
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static Yarn knit (Yarn y1, Yarn y2) {
    	Yarn ball = new Yarn();
    	for(int i=0; i<y1.getSize(); i++){
    		ball.insert(y1.getNth(i));
    		ball.insert(y2.getNth(i));
    	}
    	return ball;
    	
    }
    
    public static Yarn tear (Yarn y1, Yarn y2) {
    	Yarn ball = new Yarn();
    	for(int i=0; i<y1.getSize(); i++){
    		ball.insert(y1.getNth(i));
    		ball.remove(y2.getNth(i));
    	}
    	return ball;
    }
    
    public static boolean sameYarn (Yarn y1, Yarn y2) {
    	if(y1.getSize() != y2.getSize()){
    		return false;
    	}
    	for(int i=0; i<y1.getUniqueSize(); i++){
    		for(int k=0; k<y1.getUniqueSize(); k++) {
    			if(y1.items[i].text.equals(y2.items[k].text) && y1.items[i].count==y2.items[k].count){
        			if(i==y1.getUniqueSize()-1){
        				return true;
        			}
        		}
        		
        		else if(k==y1.getUniqueSize()-1){
        			return false;
        		}	
    		}
    	}
    	return false;
    }
}
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    // Add your own here!
    
    


class Entry {
    String text;
    int count;
    
    Entry (String s, int c) {
        text = s;
        count = c;
    }
}