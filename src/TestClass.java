/**
 * Utilizes the Card, CardComparator, and Deck classes to test various methods and behaviors.
 * 
 * 
 * @author Maxwell Dodson
 * 
 * @see Deck
 * @see Card
 * @see CardComparator
 * 
**/
public class TestClass {
	/**
	 * Runs various Methods of the Card, Deck, and CardComparator classes.
	 * 
	 * @param args Unused
	 * 
	**/
	public static void main(String[] args) {
		Card card1 = new Card();
		System.out.println("card1: " + card1);
		Card card2 = new Card(2, "Ten");
		System.out.println("card2: " + card2);
		Card card3 = new Card("Clubs", "Five");
		System.out.println("card3: " + card3);
		Card card4 = new Card("Spades", "King");
		System.out.println("card4: " + card4);
		System.out.println("card4 Rank: " + card4.getRankInt());
		System.out.println("card4 Rank: " + card4.getRankStr());
		System.out.println("card4 Suit: " + card4.getSuitInt());
		System.out.println("card4 Suit: " + card4.getSuitStr());
		
		CardComparator comparator = new CardComparator();
		System.out.println("card1 compared to card2 (comparator): " + comparator.compare(card1, card2));
		System.out.println("card2 compared to card1 (comparator): " + comparator.compare(card2, card1));
		
		System.out.println("card4 compared to card3 (compareTo): " + card4.compareTo(card3));
		System.out.println("card3 compared to card4 (compareTo): " + card3.compareTo(card4));
		
		System.out.println("card4 equals card3: " + card4.equals(card3));
		System.out.println("card4 equals card4: " + card4.equals(card4));
		
		Deck deck1 = new Deck();
		System.out.println("deck1:\n" + deck1);
		Deck deck2 = new Deck(false);
		System.out.println("deck2 (unsorted):\n" + deck2);
		
		System.out.println("deck1 equals deck1: " + deck1.equals(deck1));
		System.out.println("deck1 equals deck2: " + deck1.equals(deck2));
        System.out.println("Picked Card: " + deck1.pick());
        System.out.println("deck1 (51 cards):\n" + deck1);
        deck1.shuffle();
        System.out.println("deck1 (51 cards, shuffled):\n" + deck1);
      
        System.out.println("Dealing 2 hands with 2 cards each.");
        Deck[] decks = deck1.deal(2, 2);
        System.out.println("Hand 1:\n" + decks[0]);
        System.out.println("Hand 2:\n" + decks[1]);
        System.out.println("Picked Card from Hand 1: " + decks[0].pick());
      
        System.out.println("Dealing 1 hand with 10 cards.");
        Deck[] hand = deck1.deal(1, 10);
        System.out.println("Hand:\n" + hand[0]);
        System.out.println("Shuffling Hand.");
        hand[0].shuffle();
        System.out.println("Hand (shuffled):\n" + hand[0]);
        hand[0].mergeSort();
        System.out.println("Performed MergeSort on Hand.");
        System.out.println("Hand (sorted):\n" + hand[0]);
      
        System.out.println("Shuffling Hand.");
        hand[0].shuffle();
        System.out.println("Hand (shuffled):\n" + hand[0]);
        hand[0].selectionSort();
        System.out.println("Performed SelectionSort on Hand.");
        System.out.println("Hand (sorted):\n" + hand[0]);
        
        System.out.println("Shuffling Hand.");
        hand[0].shuffle();
        System.out.println("Hand (shuffled):\n" + hand[0]);
        hand[0].shellSort();
        System.out.println("Performed ShellSort on Hand.");
        System.out.println("Hand (sorted):\n" + hand[0]);
		
	}
}