/**
 * @author Niyousha Raeesinejad
 * 
 * Provides data fields and methods to represent a Binary Search Tree in a Java program.
 */

import java.util.*;

public class BST{
	
	/**
	 * Inner class Node provides data fields and methods to represent a Node in a Binary Search Tree.
	 */
	public class Node implements Comparable <Node>{
		
		/**
		 * Left and right children of a Node.
		 */
		private Node left, right;
		
		/**
		 * Word stored in Node.
		 */
		private String word;
		
		/**
		 * Frequency of the word.
		 */
		private int freq;
		
		/**
		 * Constructs a Node object supplied with the default and given parameters.
		 * @param word is the word stored in Node.
		 * @param freq is the frequency of the word.
		 */
		public Node(String word, int freq) {
			this.word = word;
			this.freq = freq;
			right = left = null;
		}

		/**
		 * Compares 2 Nodes by calling method compareTo(String) to compare the words stored in each Node.
		 */
		@Override
		public int compareTo(Node other) {
			return this.word.compareTo(other.word);
		}
	}
	
	/**
	 * The root Node of the Binary Search Tree.
	 */
	private Node root;

	/**
	 * Constructs a Binary Search Tree object by calling method insert(String) with the supplied parameter.
	 * @param words is the ArrayList of words to store in the BST.
	 */
	public BST(ArrayList <String> words) {
		for (String word: words) {
			insert(word);
		}
	}
	
	/**
	 * Constructs a new Node and calls method insertNode(Node, Node) to be inserted in the BST.
	 * @param word is the word to be stored if not blank and not empty.
	 */
	private void insert(String word) {
		if (!word.isBlank() && !word.isEmpty()) {
			Node newNode = new Node(word, 1);
			root = insertNode(root, newNode);
		}
	}
	
	/**
	 * Inserts a new Node in the BST. 
	 * If BST is empty, the Node is assigned to the root of the tree.
	 * If BST already contains a Node containing the same word as the new Node, the frequency of that word is incremented by 1.
	 * Otherwise, calls insertNode(Node, Node) recursively to insert the new Node in the correct location in the BST.
	 * @param root is the root of the BST.
	 * @param newNode is the new Node to be inserted in the BST.
	 * @return the root of the BST (i.e. the tree itself).
	 */
	private Node insertNode(Node root, Node newNode) {
		if (root == null)
			root = newNode;
		else if (newNode.compareTo(root) == 0)
			root.freq++;
		else if (newNode.compareTo(root) < 0) 
			root.left = insertNode(root.left, newNode);
		else
			root.right = insertNode(root.right, newNode);
		
		return root;
	}
	
	/**
	 * Calls method numUniqueWords(Node) and prints the number of unique words in the BST.
	 */
	public void uniqueWords() {
		System.out.println("Number of unique words = " + numUniqueWords(root));
	}
	
	/**
	 * Traverses the BST using a Stack to find the number of unique words (words that occur only once) in the BST.
	 * 
	 * @param root is the root of the BST (i.e. the tree itself).
	 * @return the number of unique words.
	 */
	private int numUniqueWords(Node root) {
		int num = 0;
		
		if (root == null)
			return num;
		
		Stack <Node> Nodes = new Stack<Node>();
		Node currentNode = root;
		
		while (currentNode != null || Nodes.size() > 0) {
			
			while (currentNode != null) {
				Nodes.push(currentNode);
				currentNode = currentNode.left;
			}
			
			currentNode = Nodes.pop();
			if (currentNode.freq == 1)
				num ++;
			
			currentNode = currentNode.right;
		}
		
		return num;
	}
	
	/**
	 * Calls method numNodes(Node) and prints total number of words in the BST.
	 */
	public void totalWords() {
		System.out.println("Total number of words = " + numNodes(root));
	}
	
	/**
	 * Traverses the BTS using a Stack to find the total number of nodes (i.e. total number of words) in the BST.
	 * 
	 * @param root is the root of the BST (i.e. the tree itself).
	 * @return the total number of nodes (i.e. words) in the BST.
	 */
	private int numNodes(Node root) {
		int num = 0;
		
		if (root == null)
			return num;
		
		Stack <Node> Nodes = new Stack<Node>();
		Node currentNode = root;
		
		while (currentNode != null || Nodes.size() > 0) {
			
			while (currentNode != null) {
				Nodes.push(currentNode);
				currentNode = currentNode.left;
			}
			
			currentNode = Nodes.pop();
			num++;
			currentNode = currentNode.right;
		}
		
		return num;
	}

	/**
	 * Calls method inOrderStack() to populate an ArrayList of Nodes containing the words which occur the most often in the BST.
	 * Then prints the word and its frequency in each Node element of the list.
	 */
	public void maxFreqWord() {
		ArrayList <Node> list = inOrderStack();
		System.out.println("The word(s) which occur(s) most often and the number "
				+ "of times that it/they occur(s) =");
		for (Node i: list) {
			System.out.println(i.word + " = " + i.freq + " times");
		}
	}
	
	/**
	 * Traverses the BST using a Stack and finds the Node(s) containing the word(s) which occur(s) the most often in the BST.
	 * Then stores Node(s) in an ArrayList.
	 * @return ArrayList of Node(s).
	 */
	private ArrayList<Node> inOrderStack() {
		int max = 0;
		ArrayList <Node> list = new ArrayList<Node>();
		
		if (root == null)
			return null;
		
		Stack <Node> Nodes = new Stack<Node>();
		Node currentNode = root;
		
		while (currentNode != null || Nodes.size() > 0) {
			
			while (currentNode != null) {
				Nodes.push(currentNode);
				currentNode = currentNode.left;
			}
			
			currentNode = Nodes.pop();
			
			if (currentNode.freq > max) {
				max = currentNode.freq;
				Node maxNode = currentNode;
				list.clear();
				list.add(maxNode);
				
			}else if (currentNode.freq == max) {
				Node maxNode = currentNode;
				list.add(maxNode);
			}
			
			currentNode = currentNode.right;
		}
		return list;
	}
	
	/**
	 * Calls method maxDepth(Node) and prints the maximum height of the BST.
	 */
	public void maxHeight() {
		System.out.println("The maximum height of the tree = " + maxDepth(root));
	}
	
	/**
	 * Recursively traverses down the BST to find the maximum depth of the right and left side of the tree.
	 * Then calls method max(int, int) to find the maximum depth of the entire BST.
	 * @param root is the root of the BST (i.e. the tree itself).
	 * @return the maximum depth of the BST.
	 */
	private int maxDepth(Node root) {
		if (root == null)
			return 0;
		
		return(max(maxDepth(root.left), maxDepth(root.right)) + 1);
	}
	
	/**
	 * Finds the larger value of 2 integer numbers supplied by the given parameters.
	 * @param max1 is the first number.
	 * @param max2 is the second number.
	 * @return the maximum integer number.
	 */
	private int max(int max1, int max2) {
		if (max1 > max2)
			return max1;
		else
			return max2;
	}
	
	/**
	 * Constructs a new Node containing the word to be searched.
	 * Calls method searchNode(Node, Node) to search for the newNode in the BST.
	 * Then prints if the word was found and its frequency. 
	 * @param word is the word to search for in the BST.
	 */
	public void search(String word) {
		Node key = new Node(word, 1);
		int found = searchNode(root, key);
		
		if (found == 1)
			System.out.println("Found! It appears 1 time in the input text file.");
		else if (found > 1)
			System.out.println("Found! It appears " + found + " times in the input text file.");
		else
			System.out.println("Word not found!");
	}
	
	/**
	 * Searches the BST for the key Node supplied by the given parameter through recursion.
	 * 
	 * @param root is the root of the BST (i.e. the tree itself).
	 * @param key is the key Node to be searched for in the BST.
	 * @return the frequency of the word if found in the BST. Otherwise returns 0;
	 */
	private int searchNode(Node root, Node key) {
		int found;
		
		if (root == null)
			found = 0;
		else if (key.compareTo(root) == 0)
			found = root.freq;
		else if (key.compareTo(root) < 0)
			found = searchNode(root.left, key);
		else
			found = searchNode(root.right, key);
		
		return found;
	}
	
	/**
	 * Calls method printInOrder(Node).
	 */
	public void traverseInOrder() {
		printInOrder(root);
	}
	
	/**
	 * Traverses the BST using an in-order traversal method through recursion.
	 * Prints the word in each node in that order.
	 * 
	 * @param currentNode is the current location in the BST to keep track of. 
	 */
	private void printInOrder(Node currentNode) {
		if (currentNode != null) {
			printInOrder(currentNode.left);
			System.out.print(currentNode.word +" ");
			printInOrder(currentNode.right);
		}
	}
	
	/**
	 * Calls method printPreOrder(Node).
	 */
	public void traversePreOrder() {
		printPreOrder(root);
	}
	
	/**
	 * Traverses the BST using a pre-order traversal method through recursion.
	 * Prints the word in each node in that order.
	 * 
	 * @param currentNode is the current location in the BST to keep track of. 
	 */
	private void printPreOrder(Node currentNode) {
		if (currentNode == null)
			return;
		System.out.print(currentNode.word + " ");
		printPreOrder(currentNode.left);
		printPreOrder(currentNode.right);
	}
	
	/**
	 * Calls method printPostOrder(Node).
	 */
	public void traversePostOrder() {
		printPostOrder(root);
	}
	
	/**
	 * Traverses the BST using a post-order traversal method through recursion.
	 * Prints the word in each node in that order.
	 * 
	 * @param currentNode is the current location in the BST to keep track of. 
	 */
	private void printPostOrder(Node currentNode) {
		if (currentNode == null)
			return;
		printPostOrder(currentNode.left);
		printPostOrder(currentNode.right);
		System.out.print(currentNode.word + " ");
	}
}
