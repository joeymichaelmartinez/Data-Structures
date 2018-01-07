package forneymon;

public class FlippingForneymonCard extends ForneymonCard {
	private boolean down;
	
	FlippingForneymonCard (String forneyName, String forneyType, boolean forneyDown) {
		super(forneyName, forneyType);
		down = forneyDown;
	}
	
	FlippingForneymonCard () {
		super();
		down = true;
	}
	
	public boolean flip() {
		return down=!down;
	}
	
	public boolean getDown(){return down;}
	
	public int match (FlippingForneymonCard other) {
		int returnValue = 0;
		if(down || other.down){
			returnValue = 2;
		}
		
		else if(this.getName().equals(other.getName()) && this.getType().equals(other.getType())) {
			returnValue = 1;
		}
		
		
		return returnValue;
	}
	
	public String toString() {
		if(down) {
			return "?: ?";
		}
		
		else{
			return super.toString();
		}
	}
}
