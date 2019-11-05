/**
 * Represents an IndexEntry, an entry for storing the lines than an individual word was found on.
 * 
 * @author Maxwell Dodson
 * 
 * @see IndexMaker
 * @see DocumentIndex
 * 
**/

import java.util.TreeSet;

public class IndexEntry {

	private String word;
	private TreeSet<Integer> indices;
	
	/**
	 * Instantiates an IndexEntry of a word. Instantiates a TreeSet<Integer> to store the indices of word.
	 * 
	 * @param word a String representing the word for this IndexEntry
	 * 
	**/
	public IndexEntry(String word) {
		this.word = word;
		indices = new TreeSet<Integer>();
	}
	
	/**
	 * Adds num to the TreeSet of indices belonging to this IndexEntry.
	 * 
	 * @param num an integer representing the line number this word was found on
	 * 
	**/
	public void add(int num) {
		indices.add(num); // TreeSet will determine whether to add num (if it is not already present)
	}
	
	/**
	 * Returns the word represented by this IndexEntry.
	 * 
	 * @return the word that this IndexEntry represents
	 * 
	**/
	public String getWord() {
		return word;
	}
	
	/**
	 * Returns this IndexEntry as a String. Consists of the word followed by line numbers.
	 * 
	 * @return String representing this IndexEntry
	 * 
	**/
	public String toString() {
		return word + " "+ indices.toString().replace("[", "").replace("]", "") + "\n"; // Return a String with the word and line numbers. Uses TreeSet's toString() but removes brackets
	}
}
