/**
 * Provides data fields and methods to create a Java data-type, representing a 
 * linked list in a Java application.
 * 
 * @author Niyosusha Raeesinejad
 * @since March 2, 2020
 */

public class AnagramList {
	/**
	 * Provides data fields and methods to create a Java data-type, representing
	 * a node in a linked list.
	 *
	 */
	public class Node{
		/**
		 * Information contained in a node.
		 */
		private String anagram;
		
		/**
		 * Links to the next node in the list.
		 */
		private Node next;
	}
	
	/**
	 * head represents the first node in the list.
	 */
	private Node head;
	
	/**
	 * size of linked list (number of nodes in list).
	 */
	private int size;
	
	/**
	 * Creates empty linked list.
	 */
	public AnagramList(){
		this.head = null;
		this.size = 0;
	}
	
	/**
	 * Inserts a node with an anagram to the end of the list, and increments
	 * size by 1.
	 * @param anagram is the information contained in new node.
	 */
	public void addAnagram(String word) {
		Node newNode = new Node();
		newNode.anagram = word;
		
		if (this.head == null) {
			newNode.next = head;
			head = newNode;
		}else {
			Node currentNode = head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
			newNode.next = null;
		}
		
		this.size++;
	}
	
	/**
	 * Searches list for word supplied by the given parameter. 
	 * @param word is the String to search list for.
	 * @return true if word is found in list and false otherwise.
	 */
	public boolean searchAnagram(String word) {
		Node currentNode = head;
		if (currentNode.next == null) 
			return false;

		while (currentNode.next != null) {
			if (currentNode.anagram.equalsIgnoreCase(word)) 
				return true;
			
			currentNode = currentNode.next;
		}
		return false;
	}
	
	/**
	 * Prints anagrams in the list in order.
	 */
	public void print() {
		Node currentNode = head;
		for (int i = 0; i < size; i++, currentNode = currentNode.next) {
			System.out.print(currentNode.anagram + " ");
		}
	}
}
