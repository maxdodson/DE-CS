/**
 * Creates an array of hashed TicTacToe winners using my hash function and analyzes it.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @author Maxwell Dodson
 * 
 * @see TicTacToe
 * @see TicTacToeHashCode
 * @see Board
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class TTT_HC {
	
	private static final int SIZE = 1400;
	public static final int SPACE_VALUE = 331;
	public static final int X_VALUE = 719;
	public static final int O_VALUE = 1217;
	
	/**
	 * Generates a custom hash code for a given TicTacToe string
	 * 
	 * @param boardStr the String representation of the TicTacToe board
	 * @return the integer hash code of the board
	 */
	public static int tttHashCode(String boardStr) {
		int hashCode = 0;
		for (int row=0; row<TicTacToe.ROWS; row++) {
			for (int col=0; col<TicTacToe.COLS; col++) {
				int charHash = charHash(Board.charAt(boardStr, row, col), (row * 3) + col);
				hashCode += charHash;
			}
		}
		hashCode %= 9371;
		if (hashCode >= SIZE) { // Divide hash code down if it is greater than SIZE
			hashCode /= 7;
		}
		return hashCode;
	}
	
	/**
	 * Generates a hash code for a character at a position
	 * 
	 * @param chtr the character to hash
	 * @param pos the position of the character
	 * @return the integer hash code of the character
	 */
	public static int charHash(char chtr, int pos) {
		chtr = Character.toLowerCase(chtr);
		if (chtr == ' ') {
			return SPACE_VALUE * (int)Math.pow(3, pos);
		}
		else if (chtr == 'x') {
			return X_VALUE * (int)Math.pow(3, pos);
		}
		else if (chtr == 'o') {
			return O_VALUE * (int)Math.pow(3, pos);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Instantiates this HashMap and analyzes it
	 * 
	 * @param args any command line arguments to pass in
	 */
	public static void main(String[] args) {
		Object[] arr = new Object[SIZE];
		File in = new File(TicTacToe.WINNERS); // Attempt to find winners file
		try {
			Scanner input = new Scanner(in);
			while (input.hasNextLine()) { // Read in lines of input file
				String line = input.nextLine();
				int hashIndex = tttHashCode(line); // Hash the String
				// Add winners to the array at the index returned by my hash function
				add(arr, hashIndex, line);
			}
			input.close(); // Close input file
			listStats(arr);
		} catch (IOException e) {}
	}
	
	/**
	 * Adds a String to an array of Object at a specified index.
	 * Fills the index with a LinkedList if the index is already occupied.
	 * 
	 * @param arr the array to insert line into
	 * @param hashIndex the index to insert line at
	 * @param line the String to insert in the array at the specified index
	 */
	public static void add(Object[] arr, int hashIndex, String line) {
		if (arr[hashIndex] == null) { // No existing element at index
			arr[hashIndex] = line;
		}
		else if (arr[hashIndex] instanceof String) { // Existing element is String
			String temp = (String)arr[hashIndex];
			// Create LinkedList and add both elements
			LinkedList<String> newList = new LinkedList<String>();
			newList.add(temp);
			newList.add(line);
			// Store LinkedList in array
			arr[hashIndex] = newList;
		}
		else { // Existing element is LinkedList
			// Add new element to the list
			((LinkedList<String>)arr[hashIndex]).add(line);
		}
	}
	
	/**
	 * Prints various statistics about the hash array
	 * 
	 * @param arr the array to analyze
	 */
	public static void listStats(Object[] arr) {
		System.out.println("Size: " + SIZE);
		
		int elements = 0;
		int chains = 0;
		int maxChainLength = 0;
		int collisions = 0;
		for (int i=0; i<arr.length; i++) {
			if (arr[i] instanceof String) { // Element is single String
				elements++;
			}
			else if (arr[i] instanceof LinkedList) { // Element is LinkedList, therefore a chain
				chains++;
				// Use LinkedList size() to get number of elements in chain
				int size = ((LinkedList<String>)arr[i]).size();
				elements += size;
				collisions += size;
				if (size > maxChainLength) { // Calculate maximum chain length
					maxChainLength = size;
				}
			}
		}
		
		System.out.printf("Load Factor: %.2f\n",((double)(elements) / SIZE));
		System.out.println("Collisions: " + collisions);
		System.out.println("Chains: " + chains);
		System.out.println("Max Chain Length: " + maxChainLength);
		System.out.printf("Average Chain Length: %.3f\n", ((double)collisions / chains));
		
		// Count number of elements in each quarter of the array
		for (int qtr=1; qtr<=4; qtr++) {
			int elementsPerQtr = 0;
			for (int i=(SIZE * (qtr-1)/4); i<(SIZE * qtr/4); i++) {
				if (arr[i] instanceof String) { // Element is single String
					elementsPerQtr++;
				}
				else if (arr[i] instanceof LinkedList) { // Element is LinkedList, therefore a chain
					// Use LinkedList size() to get number of elements in chain
					int size = ((LinkedList<String>)arr[i]).size();
					elementsPerQtr += size;
				}
			}
			System.out.printf("Elements in quarter %d: %d\n", qtr, elementsPerQtr);
		}
		
		// Count number of collisions in each tenth of the array
		for (int tnth=1; tnth<=10; tnth++) {
			int collisionsPerTnth = 0;
			for (int i=(SIZE * (tnth-1)/10); i<(SIZE * tnth/10); i++) {
				if (arr[i] instanceof LinkedList) { // Element is LinkedList, therefore a chain
					// Use LinkedList size() to get number of elements in chain
					int size = ((LinkedList<String>)arr[i]).size();
					collisionsPerTnth += size;
				}
			}
			System.out.printf("Collisions in tenth %d: %d\n", tnth, collisionsPerTnth);
		}	
	}

}
