public class Element implements el{
	String unit;
	int frequency;
	String bits;
	
	Element(String unit, int frequency){
			this.unit = unit;
			this.frequency = frequency;
	}
	
	Element(String unit, String bits){
		this.unit = unit;
		this.bits = bits;
	}
	
	String getUnit(){
		return unit;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	void setBits(String bits) {
		this.bits = bits; 
	}
	
	String getBits() {
		return bits;
	}
	
	public String toStringWithFrequency(){
		return unit+" "+frequency;
	}
	
	public String toStringWithBits() {
		return unit + " " + bits;
	}
	
	public String toString(){
		return unit; 
	}
	public String toString(el e) {
		return unit;
	}
	public el getEl1() {
		return null;
	};
	public el getEl2() {
		return null;
	};
}
