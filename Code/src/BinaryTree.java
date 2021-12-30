import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BinaryTree {

	static ArrayList<Element> results = new ArrayList<Element>();

	static ContainerTable containers;

	// The variables of the tree
	static Node root = null;
	static ArrayList<Node> nodes = new ArrayList<Node>();

	// Queue made for the BFS
	static Queue<Node> bfsQueue = new ArrayDeque<Node>();

	ArrayList<Element> dictionary;

	// Properties of tree image output
	final static int WIDTH = 1500;
	final static int HEIGHT = 800;
	final static int HEIGHT_SEPARATION = 100;
	final static int OVAL_SIZE = 25;
	static int widthDis = 300;

	BinaryTree(FrequencyTable frequencyTable) {
		containers = new ContainerTable(frequencyTable);
		this.dictionary = bfsTree();
	}


	// Construction of trees
	// Init of tree
	// Making root
	static void makeTree(el el) {
		root = new Node(el);
		if (el.getEl1() != null)
			setLeft(root, el.getEl1());
		if (el.getEl2() != null)
			setRight(root, el.getEl2());
		nodes.add(root);
	}

	// Recursively deciding what leaf to take
	// Set Left branch
	static void setLeft(Node p, el c) {
		Node e = new Node("0", c);
		p.setLeftNode(e);
		nodes.add(e);
		if (c instanceof Container) {
			if (c.getEl1() != null)
				setLeft(e, c.getEl1());
			if (c.getEl2() != null)
				setRight(e, c.getEl2());
		}
	}

	// Set Right branch
	static void setRight(Node p, el c) {
		Node e = new Node("1", c);
		p.setRightNode(e);
		nodes.add(e);
		if (c instanceof Container) {
			if (c.getEl1() != null)
				setLeft(e, c.getEl1());
			if (c.getEl2() != null)
				setRight(e, c.getEl2());
		}
	}

	// BFS to find out the bit value of symbols
	static ArrayList<Element> bfsTree() {
		
		makeTree(containers.peek());
		
		bfsQueue.add(root);
		ArrayList<Element> results = new ArrayList<Element>();
		while (!(bfsQueue.peek() == null)) {
			Node node = bsf(bfsQueue, bfsQueue.remove());
			if (!(node == null)) {
				if (node.storedObject instanceof Element) {
					results.add(new Element(node.storedObject.toString(), node.bits));
				}
			}
		}
		return results;
	}

	// Recursively doing the BSF search
	static Node bsf(Queue<Node> bfsQueue, Node leaf) {
		if (!(leaf.rightNode == null)) {
			if (!(leaf.rightNode.isChecked)) {
				bfsQueue.add(leaf.rightNode);
				leaf.rightNode.pushBits("0");
				leaf.rightNode.check();
			}
		}
		if (!(leaf.leftNode == null)) {
			if (!leaf.leftNode.isChecked) {
				bfsQueue.add(leaf.leftNode);
				leaf.leftNode.pushBits("1");
				leaf.leftNode.check();
			}
		}
		if (leaf.leftNode == null && leaf.leftNode == null) {
			return leaf;
		}
		return null;
	}

	
	
	void draw() {
		JFrame jFrame = new JFrame();
		jFrame.add(new JPanel() {

			private static final long serialVersionUID = 1L;

			// Overriding the paint component in order to draw the tree
			@Override
			public void paintComponent(Graphics g) {
				draw(g, root);
			}
		});
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setVisible(true);
	}

	void draw(Graphics g, Node node, int widthPos, int height, int widthDis) {
		// Draw each node
		g.drawOval(widthPos, height, OVAL_SIZE, OVAL_SIZE);
		if (node.storedObject != null)
			g.drawString(node.storedObject.toString(), widthPos, height);

		// Changing dimensions for next leafs
		height = height + HEIGHT_SEPARATION;
		int left = widthPos - widthDis;
		int right = widthPos + widthDis;
		widthDis = (int) (widthDis / 2);

		// Draw Left
		if (node.leftNode != null) {
			g.drawLine(widthPos, height - HEIGHT_SEPARATION, left, height);
			draw(g, node.leftNode, left, height, widthDis);
		}

		// Draw Right
		if (node.rightNode != null) {
			g.drawLine(widthPos, height - HEIGHT_SEPARATION, right, height);
			draw(g, node.rightNode, right, height, widthDis);
		}
	}

	// A receive function to to create the rest of the tree
	void draw(Graphics g, Node root) {
		int widthPos = WIDTH / 2 - 25;
		g.drawOval(widthPos, 10, OVAL_SIZE, OVAL_SIZE);
		g.drawString(root.storedObject.toString(), widthPos, 10);
		int height = HEIGHT_SEPARATION;
		int left = widthPos - widthDis;
		int right = widthPos + widthDis;
		widthDis = (int) (widthDis / 1.7);
		if (!(root.leftNode == null)) {
			g.drawLine(widthPos, 10, left, height);
			draw(g, root.leftNode, left, height, widthDis);
		}
		if (!(root.rightNode == null)) {
			g.drawLine(widthPos, 10, right, height);
			draw(g, root.rightNode, right, height, widthDis);
		}
	}

	void printResults() {
		for (int i = 0; i < dictionary.size(); i++) {
			System.out.println(dictionary.get(i).toStringWithBits());
		}
	}
}
