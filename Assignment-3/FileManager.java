/**
 * @author Niyousha Raeesinejad
 * 
 * Provides data fields and methods as a utility/helper class to class Main.
 */

import java.io.*;
import java.util.*;

public class FileManager {

	/**
	 * ArrayList to contain every word in input text file
	 */
	private ArrayList <String> words;
	
	/**
	 * Constructs empty arrayList to store words.
	 */
	public FileManager() {
		words = new ArrayList <String>();
	}
	
	/**
	 * Reads input text file and converts all characters to lower-case. 
	 * Then calls method addToArrayList to store each word in the ArrayList.
	 * @return ArrayList of words.
	 */
	public ArrayList<String> readFile() {
		while (true) {
			Scanner scan = new Scanner (System.in);
			System.out.println("Please enter the input filename or QUIT to terminate:");
			String input = scan.nextLine();
			
			if (input.equalsIgnoreCase("QUIT")) {
				System.out.println("Goodbye!");
				System.exit(0);
			}

			File f = new File(input);
			
			if (!f.exists()) 
				System.out.println("File does not exist. Please try again.");
			
			else if (!f.canRead()) 
				System.out.println("File is not readable. Please try again.");
				
			else {
				try{
					BufferedReader bf = new BufferedReader(new FileReader(f));
					String s = bf.readLine();
					
					while (s != null) {
						addToArrayList(s.replaceAll("[^0-9a-zA-Z ]", " ").toLowerCase().split("\\s+")); 
						s = bf.readLine();
					}
					bf.close();
				}catch (IOException e) {
					e.printStackTrace();
				}

				return words;
			}
		}
	}
	
	/**
	 * Copies words from array to ArrayList.
	 * @param arr is the array of words.
	 */
	private void addToArrayList(String [] arr){
		for (int i = 0; i < arr.length; i++) {
			words.add(arr[i]);
		}
	}
}
	
