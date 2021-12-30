import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ContainerTable {
	static FrequencyTable frequencyTable;
	
	static PriorityQueue<el> containers = new PriorityQueue<el>(new Comparator<el>() { 
		public int compare(el container1, el container2) 
		{ 
			if (container1.getFrequency() > container2.getFrequency()) 
				return 1; 
			else if (container1.getFrequency() < container2.getFrequency()) 
				return -1; 
			return 0; 
		}
	});
	
	ContainerTable(FrequencyTable ft) {
		frequencyTable = ft;
		
		Element el1 = frequencyTable.pop();
		Element el2 = frequencyTable.pop();

		// Always encapsulating symbols together
		containers.add(new Container(el1, el2));
		next();
	}
	// Recursive function to create the rest of the tree
	static void next() {
		if (frequencyTable.isEmpty()) {
			// Once last container has one object start unnerving encapsulate of containers
			if (containers.size() != 1) {
				// last two Containers to be concatenated
				el container1 = containers.poll();
				el container2 = containers.poll();
				containers.add(new Container(container1, container2));
				next();
			}
		} else {
			if (frequencyTable.peek().getFrequency() < containers.peek().getFrequency()) {
				// Pop two out of frequencyTable as smallest container getFrequency
				// is less the what's found in the frequencyTable
				Element el1 = frequencyTable.pop();
				if (frequencyTable.peek() != null) {
					el el2 = frequencyTable.pop();
					
					containers.add(new Container(el1, el2));
				} else {
					containers.add(el1);
				}
				
				// Always encapsulating symbols together
				next();
			} else {
				// container frequency is equal or larger thus a combination of frequencyTable
				// Element
				// and a containers Element need concatenation
				el el1 = frequencyTable.pop();
				el container = containers.poll();
				// Always encapsulating symbols together
				containers.add(new Container(el1, container));
				next();
			}
		}
	}
	
	void add(el e) {
		containers.add(e);
	}
	
	boolean isEmpty() {
		return containers.isEmpty();
	}
	
	el peek() {
		return containers.peek();
	}

	el poll() {
		return containers.poll();
	}
	
	int size() {
		return containers.size();
	}
	
	public void print() {
		Iterator<el> value = containers.iterator(); 
		while(value.hasNext()) {
			System.out.println(value.next().toString());
		}
	}
	
	public int getTotalFrequency() {
		int total = 0;
		Iterator<el> value = containers.iterator(); 
		while(value.hasNext()) {
			total += value.next().getFrequency();
		}
		return total;
	}
}
