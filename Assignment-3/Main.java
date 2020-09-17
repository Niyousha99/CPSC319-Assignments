/**
 * @author Niyousha Raeesinejad
 * 
 * Provides data fields and methods to represent an interactive console-based menu as the front end of this Java program.
 */
import java.util.*;

public class Main {
	/**
	 * Binary search tree object.
	 */
	private BST wordTree;
	
	/**
	 * Scanner object.
	 */
	private Scanner scan;
	
	/**
	 * Constructs an object of Main with the default values.
	 * Calls the constructor of FileManager to create a FileManager object.
	 * Creates an ArrayList of words and populated it by calling method readFile() from class FileManager.
	 * Then calls the constructor of BST to populate the binary search tree wordTree with the words in the ArrayList.
	 * Calls the constructor of Scanner to scan user input.
	 */
	public Main() {
		FileManager fm = new FileManager();
		ArrayList <String> words = fm.readFile();
		wordTree = new BST(words);
		scan = new Scanner(System.in);
	}
	
	/**
	 * Prints menu choices to the console.
	 */
	public void printMenuChoices() {
		System.out.println("\nPlease choose from one of the following options:");
		System.out.println("1. Display the total number of words in the file.");
		System.out.println("2. Display the number of unique words in the file.");
		System.out.println("3. Display the word which occurs the most often and the"
				+ " number of times that it occurs.");
		System.out.println("4. Display the maximum height of the tree.");
		System.out.println("5. Check if a word exists in the file.");
		System.out.println("6. Display the entire tree.");
		System.out.println("7. Quit.");
		System.out.println("\nPlease enter your selection:");
	}
	
	/**
	 * Calls method printMenuChoices() and other methods according to user's selection input.
	 * If user input is invalid, calls PrintMenuChoices() again.
	 */
	public void menu() {
		while (true) {
			printMenuChoices();
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1:
				wordTree.totalWords();
				break;
			case 2:
				wordTree.uniqueWords();
				break;
			case 3:
				wordTree.maxFreqWord();
				break;
			case 4:
				wordTree.maxHeight();
				break;
			case 5:
				System.out.println("Please enter the word you are looking for: ");
				String input = scan.nextLine();
				wordTree.search(input);
				break;
			case 6:
				promptTraverseMethod();
				break;
			case 7:
				System.out.println("\nGoodbye!");
				return;
			default:
				System.out.println("\nInvalid selection. Please try again.\n");
				break;
					
			}		
		}
	}

	/**
	 * Prompts user for traversal method or to go back to menu and calls the method or returns according to user input selection.
	 * If user input is invalid, prompts user to enter input selection again.
	 */
	private void promptTraverseMethod() {
		while (true) {
			System.out.println("\n\nPlease enter the BST traversal method"
					+ "(1 = IN-ORDER, 2 = PRE-ORDER, 3 = POST-ORDER)"
					+ "\nor enter 4 to return to menu:");
			int method = scan.nextInt();
			
			switch(method) {
			case 1:
				wordTree.traverseInOrder();
				break;
			case 2:
				wordTree.traversePreOrder();
				break;
			case 3:
				wordTree.traversePostOrder();
				break;
			case 4:
				return;
			default:
				System.out.println("\nInvalid selection. Please try again.\n");
			}
		}
	}

	public static void main (String [] args) {
		Main m = new Main();
		m.menu();
	}
}
