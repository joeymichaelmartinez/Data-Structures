package linked_yarn;

import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;
    
    
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    LinkedYarn () {
        head=null;
        size=0;
        uniqueSize=0;
        modCount=0;
    }
    
    
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return size==0;
    }
    
    public int getSize () {
        return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public void insert (String toAdd) {
        Node dummy=findStringNode(toAdd);
        if(isEmpty()){
            head = new Node(toAdd,1);
            size++;
            uniqueSize++;
        }
        else if(dummy==null){
            dummy = new Node(toAdd,1);
            dummy.next=head;
            head.prev=dummy;
            head=dummy;
            size++;
            uniqueSize++;
        }
        else{
            dummy.count++;
            size++;
        }
        modCount++;
    }
    
    public int remove (String toRemove) {
        Node dummy=findStringNode(toRemove);
        if(!contains(toRemove)){
            return 0;
        }
        if(dummy.count>1){
            modCount++;
            size--;
            dummy.count--;
            return dummy.count;
        }
        else if(head==dummy){
            modCount++;
            uniqueSize--;
            size--;
            head=head.next;
            if(uniqueSize>=1){
                head.prev=null;
            }
            return 0;
        }
        else{
            modCount++;
            uniqueSize--;
            size--;
            dummy.prev.next=dummy.next;
            if(dummy.next!=null){
                dummy.prev.next=dummy.next;
            }
            return 0;
        }
    }
    
    public void removeAll (String toNuke) {
        if(contains(toNuke)){
            Node dummy=findStringNode(toNuke);
            if(head==dummy){
                modCount++;
                uniqueSize--;
                size-=dummy.count;
                head=head.next;
                if(uniqueSize>=1){
                    head.prev=null;
                }
            }
            else{
                modCount++;
                uniqueSize--;
                size-=dummy.count;
                dummy.prev.next=dummy.next;
                if(dummy.next!=null){
                    dummy.next.prev=dummy.prev;
                }
            }
        }
    }
    
    public int count (String toCount) {
        if(!contains(toCount)){
            return 0;
        }
        Node dummy = findStringNode(toCount);
        return dummy.count;
    }
    
    public boolean contains (String toCheck) {
        Node dummy = findStringNode(toCheck);
        return dummy!=null;
    }
    
    public String getMostCommon () {
        if(isEmpty()){
            return null;
        }

        Node dummy=head;
        Node dummy2=head.next;
        int i=0;
        int k=0;
        while (i < uniqueSize){
            k=i+1;
            dummy2=dummy.next;
            while(k < uniqueSize){
                if(dummy.next!=null && dummy.count<dummy2.count){
                    dummy=dummy2;
                }
                k++;
                if(dummy2!=null){
                    dummy2=dummy2.next;                    
                }
            }
            i++;
        }
        return dummy.text;
    }
    
    public LinkedYarn clone () {
        LinkedYarn ballClone = new LinkedYarn();
        addLinkedYarn(this, ballClone);
        return ballClone;
    }
    
    public void swap (LinkedYarn other) {
        Node dummyHead = head;
        int dummySize = size;
        int dummyUniqueSize = uniqueSize;
        this.head= other.head;
        other.head = dummyHead;
        this.size = other.size;
        this.uniqueSize = other.uniqueSize;
        other.size = dummySize;
        other.uniqueSize = dummyUniqueSize;
        this.modCount++;
        other.modCount++;
    }
    
    public LinkedYarn.Iterator getIterator () {
        if(isEmpty()){
            throw new IllegalStateException();
        }
        return new Iterator(this);
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn y3 = new LinkedYarn();
        addLinkedYarn(y1, y3);
        addLinkedYarn(y2, y3);
        return y3;
    }
    
    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn ball = y1.clone();
        Node dummy = y2.head;
        int i = 0;
        while(i<y2.uniqueSize){
            int k = dummy.count;
            while(k>0){
                ball.remove(dummy.text);
                k--;
            }
            dummy=dummy.next;
            i++;
        }
        return ball;
    }
    
    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        if(y1.size!=y2.size){
            return false;
        }
        LinkedYarn y3= tear(y1,y2);
        return y3.isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------
    
    private Node findStringNode(String toFind){
        Node dummy = head;
        int i=0;
        while(i<uniqueSize){
            if(toFind.equals(dummy.text)){
                return dummy;
            }
            dummy=dummy.next;
            i++;
        }
        return dummy=null;
    }
    
    private static void addLinkedYarn(LinkedYarn y1, LinkedYarn y2){
        Node dummy = y1.head;
        int i=0;
        while(i<y1.uniqueSize){
            int k = 0;
            while(k<dummy.count){
                y2.insert(dummy.text);
                k++;
            }
            dummy=dummy.next;
            i++;
        }
    }
    
    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;
        int currentCount;
        
        Iterator (LinkedYarn y) {
            owner = y;
            current=y.head;
            itModCount=modCount;
            currentCount=1;
        }
        
        public boolean hasNext () {
            return isValid() && current!=null && currentCount<current.count || current.next!=null;
        }
        
        public boolean hasPrev () {
            return isValid() && current!=null && currentCount>1 || current.prev!=null;
        }
        
        public boolean isValid () {
            return itModCount==modCount;
        }
        
        public String getString () {
            if(!isValid()){
                return null;
            }
            if(current==null){
                return null;
            }
            
            return current.text;
        }

        public void next () {
            if(!isValid()){
                throw new IllegalStateException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            
            while (currentCount<current.count){
                currentCount++;
                return;
            }
            current=current.next;
        }
        
        public void prev () {
            if(!isValid()){
                throw new IllegalStateException();
            }
            if(!hasPrev()){
                throw new NoSuchElementException();
            }
            while (currentCount>1){
                currentCount--;
                return;
            }
            current=current.prev;
        }
        
        public void replaceAll (String toReplaceWith) {
            if(!isValid()){
                throw new IllegalStateException();
            }
            current.text = toReplaceWith;
            itModCount++;
            modCount++;
        }
        
    }
    
    class Node {
        Node next, prev;
        String text;
        int count;
        
        Node (String t, int c) {
            text = t;
            count = c;
        }
    }
    
}