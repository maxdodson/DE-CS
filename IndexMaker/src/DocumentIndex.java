/**
 * Represents a DocumentIndex, an index of each word in a file and the line number(s) each word was found on.
 * 
 * @author Maxwell Dodson
 * 
 * @see IndexMaker
 * @see IndexEntry
 * 
**/

import java.util.TreeMap;
import java.util.Map;

public class DocumentIndex {
	private TreeMap<String, IndexEntry> words;
	
	/**
	 * Instantiates a DocumentIndex. Also instantiates a TreeMap<String, IndexEntry> to store the words and IndexEntries.
	 * 
	**/
	public DocumentIndex() {
		words = new TreeMap<String, IndexEntry>();
		
	}
	
	/**
	 * Adds a word to this DocumentIndex along with the line number where word was found.
	 * 
	 * @param word the word to add to this DocumentIndex
	 * @param num the integer line number where word was found
	 * 
	**/
	public void addWord(String word, int num) {
		word = word.toUpperCase().replaceAll("[^a-zA-Z]", ""); // Convert word to uppercase and remove punctuation
		if (word.equals(" ") || word.equals("") || word.equals("\n")) { // Do not add empty "words"
			return;
		}
		IndexEntry entry = words.get(word); // Attempt to find the IndexEntry for this word
		if (entry == null) { // Add word to index with a new IndexEntry if not already there
			words.put(word, new IndexEntry(word));
			entry = words.get(word);
		}
		entry.add(num); // Add the new line number to word's IndexEntry
	}
	
	/**
	 * Separates a String into individual words and adds each to this DocumentIndex.
	 * 
	 * @param str the combined String containing multiple words
	 * @param num the line number where str was found
	 * 
	**/
	public void addAllWords(String str, int num) {
		String[] strs = str.split(" "); // Separate words
		for (String word : strs) {
			addWord(word, num); // Add each word and line number to DocumentIndex
		}
	}
	
	/**
	 * Returns this DocumentIndex as a String. Consists of a list of each IndexEntry in alphabetical order.
	 * 
	 * @return String representing this DocumentIndex
	 * 
	**/
	public String toString() {
		String result = "";
		for (Map.Entry<String, IndexEntry> entry : words.entrySet()) { // Iterate through each entry in words
			result += entry.getValue().toString(); // Concatenate each IndexEntry's toString() value
		}
		return result;
	}
	
}

// Used StackOverflow to learn more about RegEx and TreeMap entry sets