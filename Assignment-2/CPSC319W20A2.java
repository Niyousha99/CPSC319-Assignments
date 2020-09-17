/**
 * Provides static methods to sort an arbitrary number of String inputs.
 * 
 * @author Niyousha Raeesinejad
 * @since February 29, 2020
 */

import java.util.Scanner;
import java.util.ArrayList;

public class CPSC319W20A2 {
	/**
	 * Reads and stores user input in an Array List. Then returns 1-D array copy
	 * of that array list.
	 * @return 1-D array.
	 */
	private static String[] readInput() {
		Scanner scanner = new Scanner(System.in);
		ArrayList <String> tempList = new ArrayList<String>();
		
		while (scanner.hasNextLine()) {
		     String line = scanner.nextLine().trim();
		     
		     if (line.equals("")) {
		          break;
		      }
		    tempList.add(line);
		}
		scanner.close();
		return convertToArray(tempList);
	}
	
	/**
	 * Converts an array list into a 1-D array.
	 * @param arrayList is an ArrayList of Strings.
	 * @return list is the converted 1-D array.
	 */
	private static String[] convertToArray(ArrayList <String> arrayList) {
		String [] list = new String[arrayList.size()];
		for (int i = 0; i < list.length; i++) {
			list[i] = arrayList.get(i);
		}
		return list;
	}
	
	/**
	 * Converts 1-D array to an ArrayList.
	 * @param list is a 1-D array of Strings.
	 * @return arrayList is the converted ArrayList.
	 */
	private static ArrayList <String> convertToArrayList(String[] list){
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < list.length; i++) {
			arrayList.add(list[i]);
		}
		return arrayList;
	}
	
	/**
	 * Computes the median of the Strings stores in the 3 indices of the array
	 * supplied by the given parameters.
	 * @param list is the 1-D array of Strings.
	 * @param a represents the first index of list.
	 * @param b represents the middle index of list.
	 * @param c represents the last index of list.
	 * @return median is the median String value computed.
	 */
	private static int median(String [] list, int a, int b, int c) {
		int median = 0;
		if ((list[a].compareTo(list[b]) < 0 && list[b].compareTo(list[c]) < 0) || 
				(list[c].compareTo(list[b]) < 0 && list[b].compareTo(list[a]) < 0))
			median = b;
		else if ((list[b].compareTo(list[a]) < 0 && list[a].compareTo(list[c]) < 0) ||
				(list[c].compareTo(list[a]) < 0 && list[a].compareTo(list[b]) < 0))
			median = a;
		else
			median = c;
		
		if (median == b) {
			swapStrings(list, median, c);
			median = c;
		}
		return median;
	}
	
	/**
	 * Swaps 2 values in a 1-D array of Strings supplied by the given parameters.
	 * @param list is the 1-D array of Strings.
	 * @param a is the index of first String to be swapped.
	 * @param b is the index of the second String to be swapped with the first.
	 */
	private static void swapStrings(String[] list, int a, int b) {
		String tmp = list[a];
		list[a] = list[b];
		list[b] = tmp;
	}
	
	/**
	 * Calls method median to compute the pivot. Then, divides array of Strings 
	 * into 2 sub-arrays by using method swapStrings, with one sub-array
	 * containing all values less than the pivot value and the other sub-array
	 * containing all values greater than the pivot.
	 * @param list is the 1-D array of Strings.
	 * @param tooBig is the first index of list.
	 * @param tooSmall is the last index of list.
	 * @return topBig is the first index of a sub-array.
	 */
	private static int divideList(String[] list, int tooBig, int tooSmall) {

		int length = tooSmall - tooBig + 1;
		int pivot = median(list, 0, length/2, length-1);

		while (tooSmall > tooBig) {
			while (list[tooBig].compareTo(list[pivot]) < 0 && tooBig != length-1) {
				++tooBig;
			}
			while (list[tooSmall].compareTo(list[pivot]) > 0 && tooSmall != 0) {
				--tooSmall;
			}
			if (tooBig < tooSmall) {
				swapStrings(list, tooBig, tooSmall);
			}
		}
		swapStrings(list, tooBig, pivot);
		
		return tooBig;	
	}
	
	/**
	 * Sorts an array containing 3 or less String elements lexicographically.
	 * @param list is the 1-D array of Strings.
	 * @param tooBig is the first index of list.
	 * @param tooSmall is the last index of list.
	 */
	private static void trivialSort(String[] list, int tooBig, int tooSmall) {
		if (tooSmall - tooBig <= 0) // if list contains 1 element
			return;
		else if (tooSmall - tooBig == 1) { // if list contains 2 elements
			if (list[tooBig].compareTo(list[tooSmall]) > 0)
				swapStrings(list, tooBig, tooSmall);
			return;
		}
		else { // if list contains 3 elements
			if (list[tooBig].compareTo(list[tooSmall-1]) > 0)
				swapStrings(list, tooBig, tooSmall-1);
			if (list[tooBig].compareTo(list[tooSmall]) > 0)
				swapStrings (list, tooBig, tooSmall);
			if (list[tooSmall-1].compareTo(list[tooSmall]) > 0)
				swapStrings(list, tooSmall-1, tooBig);
		}
	}
	
	/**
	 * Sorts an array of Strings in lexicographical order by calling method 
	 * trivialSort if the array contains 3 or less elements, otherwise using
	 * method divideList and recursion.
	 * @param list is the 1-D array of Strings.
	 * @param tooBig is the first index of list.
	 * @param tooSmall is the last index of list.
	 * @return list is the sorted 1-D array.
	 */
	private static String[] sortListA(String[] list, int tooBig, int tooSmall) {
		if (tooSmall - tooBig <= 2) 
			trivialSort(list, tooBig, tooSmall);
		else {
			int pivotIndex = divideList(list, tooBig, tooSmall);
			sortListA(list, tooBig, pivotIndex-1); 
			sortListA(list, pivotIndex+1, tooSmall);
		}
		
		return list;
	}
	
	/**
	 * Swaps 2 values in an array of chars. 
	 * @param arr is the 1-D array of chars.
	 * @param a is the index of the first char to be swapped.
	 * @param b is the index of the second char to be swapped with the first.
	 */
	private static void swapChar(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	/**
	 * Sorts the characters in a String in alphabetical order by using method
	 * swapChar.
	 * @param word is the String to be sorted.
	 * @return the sorted String.
	 */
	private static String sortWord(String word) {
		char [] arr = word.toCharArray();
		int i, j, max;
		
		for (i = arr.length-1; i > 0; i--) {
			max = 0;
			for (j = 0; j <= i; j++) {
				if (arr[j] > arr[max]) {
					max = j;
				}
			}
			swapChar(arr, i, max);
		}
		
		String sortedWord = new String(arr);
		return sortedWord;
	}
	
	/**
	 * Checks if 2 words are anagrams of each other by using method sortWord.
	 * @param word1 is the first String to be sorted and compared.
	 * @param word2 is the second String to be sorted and compared.
	 * @return true if word1 and word2 are anagrams of each other. 
	 *         Otherwise returns false.
	 */
	private static boolean isAnagram(String word1, String word2) {
		if (sortWord(word1).compareTo(sortWord(word2)) == 0) 
			return true;
		else
			return false;
	}
	
	/**
	 * Sorts an array of Strings by storing all anagrams in a singly linked list
	 * in alphabetical order and subsequently storing each linked list at each
	 * entry of an ArrayList also in alphabetical order, by using methods of class
	 * AnagramList and isAnagram.
	 * Then, converts the sorted Array List into a 1-D array to be returned.
	 * @param listA is the 1-D array of Strings.
	 * @return listB is the sorted 1-D array.
	 */
	private static AnagramList[] sortListB (String[] listA) {
		ArrayList <String> arrayListA = convertToArrayList(listA);
		ArrayList <AnagramList> arrayListB = new ArrayList <AnagramList>();
		
		for (int i = 0; i < arrayListA.size(); i++) {
			AnagramList anagrams = new AnagramList();
			anagrams.addAnagram(arrayListA.get(i));

			for (int j = i+1; j < arrayListA.size(); j++) {
				if (isAnagram(arrayListA.get(i), arrayListA.get(j)) && !anagrams.searchAnagram(arrayListA.get(j))) {
					anagrams.addAnagram(arrayListA.get(j));
					arrayListA.remove(j);
					j--;
				}	
			}
			
			arrayListB.add(anagrams);
		}

		AnagramList[] listB = new AnagramList[arrayListB.size()];
		for (int i = 0; i < arrayListB.size(); i++) {
			listB[i] = arrayListB.get(i);
		}
		
		return listB;
	}

	public static void main (String [] args) {
		String [] listA = readInput();
		listA = sortListA(listA, 0, listA.length-1);
		AnagramList[] listB = sortListB(listA);
		
		for (int i = 0; i < listB.length; i++) {
			listB[i].print();
			System.out.println();
		}
	}
}
