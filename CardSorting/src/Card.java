/**
 * Represents a Card, as in playing card. Provides various methods to compare, print, etc. a Card.
 * 
 * @author Maxwell Dodson
 * 
 * @see CardComparator
 * @see Deck
 * 
**/
import java.util.HashMap;
import java.lang.Comparable;
public class Card implements Comparable<Card> {

	private String suitStr;
	private int suitInt;
	private String rankStr;
	private int rankInt;
	public static HashMap<Integer, String> suits = new HashMap<Integer, String>()
	{{
	    put(0, "Clubs");
	    put(1, "Diamonds");
	    put(2, "Hearts");
	    put(3, "Spades");
	}};
	public static HashMap<Integer, String> ranks = new HashMap<Integer, String>()
	{{
	    put(1, "One");
	    put(2, "Two");
	    put(3, "Three");
	    put(4, "Four");
	    put(5, "Five");
	    put(6, "Six");
	    put(7, "Seven");
	    put(8, "Eight");
	    put(9, "Nine");
	    put(10, "Ten");
	    put(11, "Jack");
	    put(12, "Queen");
	    put(13, "King");
	}};
	
	/**
	 * Instantiates a default Card. A default card is a "Ten of Diamonds."
	 * 
	**/
	public Card() {
		suitStr = "Diamonds";
		rankInt = 10;
		suitInt = getKey(suits, suitStr);
		rankStr = ranks.get(rankInt);
	}
	
	/**
	 * Instantiates a Card when given a suit and rank.
	 * 
	 * @param suit suit as String
	 * @param rank rank as String
	 * 
	**/
	public Card(String suit, String rank) {
		super();
		suitStr = suit;
		suitInt = getKey(suits, suit);
		rankInt = getKey(ranks, rank);
		rankStr = rank;
	}
	
	/**
	 * Instantiates a Card when given a suit and rank.
	 * 
	 * @param suit suit as String
	 * @param rank rank as int
	 * 
	**/
	public Card(String suit, int rank) {
		super();
		suitStr = suit;
		suitInt = getKey(suits, suit);
		rankInt = rank;
		rankStr = ranks.get(rank);
	}
	
	/**
	 * Instantiates a Card when given a suit and rank.
	 * 
	 * @param suit suit as int
	 * @param rank rank as String
	 * 
	**/
	public Card(int suit, String rank) {
		super();
		suitStr = suits.get(suit);
		suitInt = suit;
		rankInt = getKey(ranks, rank);
		rankStr = rank;
	}
	
	/**
	 * Instantiates a Card when given a suit and rank.
	 * 
	 * @param suit suit as int
	 * @param rank rank as int
	 * 
	**/
	public Card(int suit, int rank) {
		super();
		suitStr = suits.get(suit);
		suitInt = suit;
		rankInt = rank;
		rankStr = ranks.get(rank);
	}
	
	/**
	 * Returns the key of an Entry in a HashMap when given a value.
	 * 
	 * @param map HashMap<Integer, String> to search in
	 * @param value a String to search for
	 * @return the integer key of the Entry in which value was found
	 * @return -1 if value was not found in map
	 * 
	**/
	private int getKey(HashMap<Integer, String> map, String value) {
		java.util.Set<java.util.Map.Entry<Integer, String>> entries = map.entrySet(); // Get all entries in map
		for (java.util.Map.Entry<Integer, String> entry : entries) { // Iterate through the entries
			if (entry.getValue().equals(value)) { // If entry's value is equal to passed value
				return entry.getKey(); // Return the entry's key
			}
		}
		return -1;
	}
	
	/**
	 * Returns the rank of the Card as an integer.
	 * 
	 * @return the integer rank of this Card
	 * 
	**/
	public int getRankInt() {
		return rankInt;
	}
	
	/**
	 * Returns the rank of the Card as a String.
	 * 
	 * @return the String rank of this Card
	 * 
	**/
	public String getRankStr() {
		return rankStr;
	}
	
	/**
	 * Returns the suit of the Card as a String.
	 * 
	 * @return the String suit of this Card
	 * 
	**/
	public String getSuitStr() {
		return suitStr;
	}
	
	/**
	 * Returns the suit of the Card as an integer.
	 * 
	 * @return the integer suit of this Card
	 * 
	**/
	public int getSuitInt() {
		return suitInt;
	}
	
	/**
	 * Returns the difference in rank of this Card and another Card.
	 * 
	 * @param Card another Card to compare
	 * @return the integer difference of this Card's rank and other Card's rank
	 * 
	**/
	public int compareTo(Card card) {
		return (this.rankInt - card.rankInt); // Return difference in ranks of cards
	}
	
	/**
	 * Returns whether this Card equals another Card by comparing suit and rank.
	 * 
	 * @param Card another Card to compare
	 * @return true if this Card's suit and rank are equal to the other Card's suit and rank, false otherwise
	 * 
	**/
	public boolean equals(Card other) {
		return ((this.suitInt == other.suitInt) && (this.rankInt == other.rankInt)); // Return true if suits and ranks equal, false otherwise
	}
	
	/**
	 * Returns this Card represented as a String.
	 * 
	 * @return the String representation of this Card: "[rank] of [suit]"
	 * 
	**/
	public String toString() {
		return rankStr + " of " + suitStr;
	}

}

// I used StackOverflow and the Java API to learn how to use HashMap