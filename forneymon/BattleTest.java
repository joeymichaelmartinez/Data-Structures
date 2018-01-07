 package forneymon;
  
  public class BattleTest {
  
      public static void main(String[] args) {
          Burnymon emberlizard = new Burnymon("Dave");
          emberlizard.takeDamage(5, "dampy");
          System.out.println(emberlizard.getHealth());
      }
    
  }