package forneymon;

public abstract class ForneymonCardTest {
	  
	  
	public static void main (String[] args) {
		FlippingForneymonCard burny1 = new FlippingForneymonCard("burny", "Burnymon", false);
		FlippingForneymonCard burny2 = new FlippingForneymonCard("burny", "Burnymon", false);
		FlippingForneymonCard missingNu = new FlippingForneymonCard();
		
		// "1"
		System.out.println(burny1.match(burny2));
		System.out.println(burny2.match(burny1));
		
		// "2"
		System.out.println(missingNu.match(burny1));
		System.out.println(missingNu.match(burny2));
		
		
		missingNu.flip();
		//"0"
		System.out.println(missingNu.match(burny1));
		System.out.println(missingNu.match(burny2));
		
		burny1.flip();
		// "2"
		System.out.println(burny1.match(burny2));
		System.out.println(burny2.match(burny1));
		
		burny2.flip();
		//"2"
		System.out.println(burny1.match(burny2));
		System.out.println(burny2.match(burny1));
		
		FlippingForneymonCard burny3 = new FlippingForneymonCard();
		System.out.println(burny3.getDown());
		burny3.flip();
		System.out.println(burny3.getDown());
		burny3.flip();
		System.out.println(burny3.getDown());
		
        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);
        // "Burnymon: burny"
        System.out.println(burny);
        burny.flip();
        // "?: ?"
        System.out.println(burny);
        
        // "Burnymon: MissingNu"
        System.out.println(missingNu);
        missingNu.flip();
        // "?: ?"
        System.out.println(missingNu);
		
	}
	      
	
	
}
	

