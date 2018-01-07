  package int_linkedlist;
  
  public class IntLinkedList {
  
      public class Iterator {
          
          private Node current;
          
          Iterator () {
              // This might look funny, but check out the IntLinkedLists's
              // getIteratorAt method to see how it is intended to work
              current = head;
          }
          
          public boolean hasNext () {
              return current != null && current.next != null; 
          }
          
          public void next () {
              if (current == null) {return;}
              current = current.next;
          }
          
          public int getCurrentInt () {
              return current.data;
          }
          
      }
      
      **
      private class Node {
    	  
    	  int data;
    	  Node next;
    	  
    	  Node (int d) {
    		  data = d;
    		  next = null;
    	  }
    	  
  }