package forneymon;

public class ForneymonCard {
	private String name;
	private String type;
	
	ForneymonCard(String forneyName, String forneyType) {
		name = forneyName;
		type = forneyType;
		if(!type.equals("Burnymon") && !type.equals("Dampymon") && !type.equals("Leafymon")) {
			throw new IllegalArgumentException();
		}
	}
	
	ForneymonCard(String type){
		throw new IllegalArgumentException();
	}
	
	
	ForneymonCard(){
		name = "MissingNu";
		type = "Burnymon";
	}
	
	public String toString() {
		return type + ": " + name;
	}
	
	public String getName(){return name;}
	public String getType(){return type;}
}
