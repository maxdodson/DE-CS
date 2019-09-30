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
	private HashMap<Integer, String> suits;
	
	public Card() {
		suits = new HashMap<Integer, String>()
		{{
		    put(0, "clubs");
		    put(1, "diamonds");
		    put(2, "hearts");
		    put(3, "spades");
		}};
		
		suitStr = "diamonds";
		rankInt = 10;
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
		if (this.suitInt == card.suitInt) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	public boolean equals() {
		
	}
	
	public String toString() {
		return "";
	}
	
	}

}

// I used StackOverflow and the Java API to learn how to use HashMap