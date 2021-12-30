import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Huffman{

	static FrequencyTable frequencyTable = null;
	static BinaryTree binaryTree = null;
	
	public Huffman(String str) {
		frequencyTable = new FrequencyTable(str);
		binaryTree = new BinaryTree(frequencyTable);
	}
	
	void printCompressedResults() {
		binaryTree.printResults();
	}

	private void draw() {
		binaryTree.draw();
	}
	 
	void printFrequencyTable() {
		frequencyTable.print();		
	}

	//Example of running the class
	public static void main(String[] args) {
		//Handles letters into frequency table
		Huffman HM = new Huffman("BOB TO KILL YOU");
		
		//To Print frequencyTable
		HM.printFrequencyTable();
		
		//LINE BREAK
		System.out.println();
		
		//Printing the Array to display results
		HM.printCompressedResults();
		
		//Draw the tree
		HM.draw();
	}


}