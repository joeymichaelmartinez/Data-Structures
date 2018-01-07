package forneymon;

//[!] Note the abstract tag
abstract public class Forneymon{
	
    //fields
    private int health;
    private String name;
    
    Forneymon(int h, String n) {
        name = n;
        health = h;
    } 
    
    //Methods
    public int takeDamage (int dmg, String type) {
        health -= dmg;
        return health;
    }

    
    public String toString() {
        return name + " " + name;
    }
    
    //Getters
    public int getHealth() {return health;}
    public String getName() {return name;} 
}
