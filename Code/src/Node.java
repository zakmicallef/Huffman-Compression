public class Node {
	Object storedObject;
	Node leftNode;
	Node rightNode;
	Node parentNode;
	boolean isChecked = false;
	String bitRef;
	String bits = "";
	
	Node(Object storedObject){
		this.storedObject = storedObject;
	}
	
	Node(String bitref, Object storedObject){
		this.storedObject = storedObject;
		this.bitRef = bitref;
	}
	
	String getBits() {
		return this.bits;
	}
	
	void setBits(String b) {
		this.bits = b;
	}
	
	void pushBits(String b) {
		this.bits = parentNode.getBits() + b;
		if(storedObject instanceof Element) ((Element) storedObject).setBits(this.getBits());
	}
	
	String getByte() {
		return this.bitRef;
	}
	
	void setParentNode(Node parentNode){
		this.parentNode = parentNode;
	}
	
	void setLeftNode(Node leftNode){
		leftNode.setParentNode(this);
		leftNode.setBits(this.bits);
		this.leftNode = leftNode;
	}
	
	void setRightNode(Node rightNode){
		rightNode.setParentNode(this);
		rightNode.setBits(this.bits);
		this.rightNode = rightNode;
	}
	
	void check() {
		this.isChecked = true;
	}
}
