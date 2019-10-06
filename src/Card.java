/**
 * Represents a Card class
 * 
 * 
 * @author Maxwell.Dodson
 * 
**/
import java.util.HashMap;
public class Card implements Comparable<Card> {

	private String suitStr;
	private int suitInt;
	private String rankStr;
	private int rankInt;
	public static HashMap<Integer, String> suits;
	public static HashMap<Integer, String> ranks;
	
	public Card() {
		suits = new HashMap<Integer, String>()
		{{
		    put(0, "clubs");
		    put(1, "diamonds");
		    put(2, "hearts");
		    put(3, "spades");
		}};
		
		ranks = new HashMap<Integer, String>()
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
		
		suitStr = "diamonds";
		rankInt = 10;
	}
	
	public Card(String suit, String rank) {
		super();
		suitStr = suit;
		suitInt = getKey(suits, suit);
		rankInt = getKey(ranks, rank);
		rankStr = rank;
	}
	
	public Card(String suit, int rank) {
		super();
		suitStr = suit;
		suitInt = getKey(suits, suit);
		rankInt = rank;
		rankStr = ranks.get(rank);
	}
	
	public Card(int suit, String rank) {
		super();
		suitStr = suits.get(suit);
		suitInt = suit;
		rankInt = getKey(ranks, rank);
		rankStr = rank;
	}
	
	private int getKey(HashMap<Integer, String> map, String value) {
		java.util.Set<java.util.Map.Entry<Integer, String>> entries = map.entrySet(); // Get all entries in map
		for (java.util.Map.Entry<Integer, String> entry : entries) { // Iterate through the entries
			if (entry.getValue().equals(value)) { // If entry's value is equal to passed value
				return entry.getKey(); // Return the entry's key
			}
		}
		return -1;
	}
	
	public int getRankInt() {
		return rankInt;
	}
	
	public String getRankStr() {
		return rankStr;
	}
	
	public String getSuitStr() {
		return suitStr;
	}
	
	public int getSuitInt() {
		return suitInt;
	}

	public int compareTo(Card card) {
		return (this.rankInt - card.rankInt); // Return difference in ranks of cards
	}
	
	public boolean equals(Object obj) { // Override Object's equals method
		if (obj != null) {
			obj = (Card)obj; // Cast obj as Card
			return ((this.suitInt == obj.suitInt) && (this.rankInt == obj.rankInt)); // Return true if suits and ranks equal, false otherwise
		}
	}
	
	public String toString() {
		return rankStr + " of " + suitStr;
	}

}

// I used StackOverflow and the Java API to learn how to use HashMap