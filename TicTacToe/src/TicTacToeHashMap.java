/**
 * Builds a HashMap of TicTacToe winners using String's hash function and analyzes it.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @author Maxwell Dodson
 * 
 * @see TicTacToeMyHashMap
 * 
 */
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeHashMap  {

	HashMap<String, Boolean> map;

	/**
	 * Instantiates a new TicTacToeHashMap that puts TicTacToe
	 * winners in a HashMap
	 *
	 */
	public TicTacToeHashMap() {
		 map = new HashMap<String, Boolean>();
		 File in = new File(TicTacToe.WINNERS); // Attempt to find winners file
			try {
				Scanner input = new Scanner(in);
				while (input.hasNextLine()) { // Read in lines of input file
					String line = input.nextLine();
					// Put winners in the map using String's hash function
					map.put(line, true);
				}
				input.close(); // Close input file
			} catch (IOException e) {}
	}

	/**
	 * Calculates the capacity of this HashMap using the Java Reflect API
	 * 
	 * @return the integer capacity of this HashMap
	 */
    private int capacity() throws NoSuchFieldException, IllegalAccessException {
    	// Access the HashMap table field
    	Field tableField = HashMap.class.getDeclaredField("table");
    	tableField.setAccessible(true);
    	// Cast table to an array of Objects and return the length
    	Object[] table = (Object[]) tableField.get(map);
    	return table == null ? 0 : table.length;   
    }

    /**
	 * Instantiates this HashMap and analyzes it
	 * 
	 * @param args any command line arguments to pass in
	 */
    public static void main(String[] args) throws java.io.FileNotFoundException,
                                              NoSuchFieldException, 
                                              IllegalAccessException {
    	TicTacToeHashMap m = new TicTacToeHashMap();
    	m.listStats();
   }
    /**
	 * Prints various statistics about this HashMap.
	 * Includes capacity, load factor, chain lengths, distribution, etc.
	 * 
	 */
    public void listStats() throws NoSuchFieldException, IllegalAccessException {
    	int capacity = capacity();
    	System.out.println("Capacity: " + capacity);
    	
    	int entries = 0;
    	int chains = 0;
    	// Access the HashMap table field
    	Field tableField = HashMap.class.getDeclaredField("table");
    	tableField.setAccessible(true);
    	// Cast table to an array of Objects
    	Object[] table = (Object[]) tableField.get(map);
    	int[] chainLengths = new int[capacity()];
    	for (int i=0; i<table.length; i++) { // Iterate through objects in the HashMap table
    		if (table[i] != null) {
    			int elements = 0;
    			Field next;
    			Object node = table[i];
    			// Recursively iterate through node's linked nodes
        		while (node != null) { // If node is null, the final node in the chain has been reached
        			entries++;
        			elements++;
        	    	next = node.getClass().getDeclaredField("next"); // Access this node's next field
        	    	next.setAccessible(true);
        	    	node = next.get(node); // Get the next node of this node
        		}
        		if (elements > 1) { // Count chains if they contain more than 1 element
        			chains++;
        			chainLengths[i] += elements;
        		}
    		}
    	}
    	System.out.println("Entries: " + entries);
    	System.out.printf("Load Factor: %.2f\n", (double)entries / capacity());
    	
    	// Count number of entries in each quarter of the array
    	for (int qtr=1; qtr<=4; qtr++) {
			int entriesPerQtr = 0;
			for (int i=(capacity * (qtr-1)/4); i<(capacity * qtr/4); i++) {
				// Count entries as the number of filled elements in the array
				if (chainLengths[i] > 0) 
					entriesPerQtr += chainLengths[i];
			}
			System.out.printf("Entries in quarter %d: %d\n", qtr, entriesPerQtr);
		}
    	// Count number of collisions in each tenth of the array
		for (int tnth=1; tnth<=10; tnth++) {
			int collisionsPerTnth = 0;
			for (int i=(capacity * (tnth-1)/10); i<(capacity * tnth/10); i++) {
				// Count collisions as elements in a chain with more than 1 element
				if (chainLengths[i] > 1)
					collisionsPerTnth += chainLengths[i];
			}
			System.out.printf("Collisions in tenth %d: %d\n", tnth, collisionsPerTnth);
		}	
    	
		// Find average and maximum chain length 
    	int totalChainLength = 0;
    	int maxChainLength = chainLengths[0];
    	for (int i=0; i<chainLengths.length; i++) {
    		if (chainLengths[i] > 1) {
    			// Add up the length of each chain if it contains more than 1 element
    			totalChainLength += chainLengths[i];
    		}
    		// Calculate the maximum chain length
    		if (chainLengths[i] > maxChainLength)
    			maxChainLength = chainLengths[i];
    	}
    	System.out.printf("Average Chain Length: %.3f\n", (double)totalChainLength / chains);
    	System.out.println("Max Chain Length: " + maxChainLength);
    }
}