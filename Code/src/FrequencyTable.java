import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class FrequencyTable {
	PriorityQueue<Element> table = new PriorityQueue<Element>(new Comparator<Element>() {
		public int compare(Element te1, Element te2) {
			if (te1.getFrequency() > te2.getFrequency())
				return 1;
			else if (te1.getFrequency() < te2.getFrequency())
				return -1;
			return 0;
		}
	});
	Element[] finalTable;

	FrequencyTable(String str) {
		ArrayList<String> letters = new ArrayList<String>(Arrays.asList(str.split("")));
		// Loop until all the frequency are stored in the frequency table
		while (!letters.isEmpty()) {
			String letter = letters.get(0);
			// Find frequency of letter
			int freq = 0;
			for (String _letter : letters) {
				if (_letter.equals(letter))
					freq++;
			}
			this.add(letter, freq);
			// Remove letter
			int size = letters.size();
			for (int index = size - 1; index >= 0; index--) {
				if (letters.get(index).equals(letter))
					letters.remove(index);
			}
		}
		this.finalTable = new Element[table.size()];
		this.table.toArray(finalTable);
	}

	FrequencyTable(int[][] residueMatrix) {
		// Make residue matrix into list
		ArrayList<Integer> residuePixelList = new ArrayList<Integer>();
		for (int x = 0; x < residueMatrix.length - 1; x++) {
			for (int y = 0; y < residueMatrix[0].length - 1; y++) {
				residuePixelList.add((int) residueMatrix[x][y]);
			}
		}
		// Loop until all the frequency are stored in the frequency table
		while (!residuePixelList.isEmpty()) {
			int residuePixel = residuePixelList.get(0);
			
			// Find frequency of letter
			int freq = 0;
			for (int pixel : residuePixelList) {
				if (residuePixel == pixel)
					freq++;
			}
			
			this.add(new Element(String.valueOf(residuePixel), freq));
			
			// Remove the pixels in list
			int size = residuePixelList.size();
			for (int index = size - 1; index >= 0; index--) {
				if (residuePixelList.get(index).equals(residuePixel))
					residuePixelList.remove(index);
			}
		}
		this.finalTable = new Element[table.size()];
		this.table.toArray(finalTable);
	}

	void add(Element te) {
		table.add(te);
	}

	void add(String unit, int frequency) {
		Element te = new Element(unit, frequency);
		table.add(te);
	}

	boolean isEmpty() {
		return table.isEmpty();
	}

	Element peek() {
		return table.peek();
	}

	Element pop() {
		return table.remove();
	}

	void reNew() {
		table = new PriorityQueue<Element>(Arrays.asList(finalTable));
	}
	
	public void print() {
		for(Element el : finalTable)
			System.out.println(el.toStringWithFrequency());
	}

	public int getTotalFrequency() {
		int total = 0;
		Iterator<Element> value = table.iterator();
		while (value.hasNext()) {
			total += value.next().getFrequency();
		}
		return total;
	}
}
