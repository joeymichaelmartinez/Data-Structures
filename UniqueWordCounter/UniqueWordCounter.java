package UniqueWordCounter;
import java.util.Scanner;



public class UniqueWordCounter {
    
      public static void main (String[] args) {
          Scanner input = new Scanner(System.in); 
          System.out.println("Enter a sentence.");
          
          String[] words = input.nextLine().split(" ");
          int count = 0;
          for (int i = 0; i < words.length; i++) {
              boolean unique = true;
              for (int j = 0; j < words.length; j++) {
                  if (words[i].equals(words[j]) && i != j) {
                      unique = false;
                      break;
                  }
              }
              if (unique) {count++;}
          }
          System.out.println("There are " + count + " unique words in that sentence.");
      }
  }