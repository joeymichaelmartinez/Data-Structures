  package forneymon;
  
  // [!] Burnymon is now a subclass of Forneymon
  public class Burnymon extends Forneymon implements MinForneymon {
      
      Burnymon (String n) {
          // [!] Forneymon has a constructor that
          // takes health and name, so call it here
          super(15, n);
      }
      
  }