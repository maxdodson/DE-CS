/**
 * Implements the Comparator interface to provide a compare function for two Cards.
 * 
 * 
 * @author Maxwell Dodson
 * 
 * @see Deck
 * @see Card
 * 
**/
import java.util.Comparator;
public class CardComparator implements Comparator<Card> {
	
	/**
	 * Returns the integer difference between the ranks of two Cards.
	 * 
	 * @param card1 the first Card to compare
	 * @param card2 the second Card to compare
	 * @return the integer difference between the ranks of card1 and card2
	 * 
	**/
	public int compare(Card card1, Card card2) {
		return card1.getRankInt() - card2.getRankInt();
	}
}