// Container is the encapsulation of element according to there frequency
public class Container implements el{
	// Array of object represents the brackets expressed in the documentation
	el[] element = new el[2];
	int frequency = -1;
	String lable;
	
	Container(el el1, el el2){
		this.element[0] = el1;
		this.element[1] = el2;
		this.frequency = el1.getFrequency() + el2.getFrequency();
		this.lable = "(" + el1.toString() + "." + el2.toString() + ")";
	}
	
	Container(Element element){
		this.element[0] = element;
		this.frequency = element.getFrequency();
	}
	
	public el getEl1(){
		return element[0];
	}
	
	public el getEl2(){
		return element[1];
	}

	 public int getFrequency() {
		return this.frequency;
	}
	
	//Container toString
	// fix to string
	public String toString(){
		return lable;
	}

	public String toString(el e) {
		// TODO Auto-generated method stub
		return null;
	}
}
