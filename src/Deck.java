/*
Fields:
	- Card[] deck
	- int top // index of last card in deck
	
	Constructors:
	- Deck()
		create deck
		sort cards by suit and rank
	- Deck(boolean sorted)
		create deck
		sort cards if sorted == true, shuffle if sorted == false
			// can do: new Deck() and then shuffle()
		
	Methods:
	- String toString()
		if deck.length == 52
			print cards in 4 columns by suit
		if deck.length != 52
			print cards in 1 column
	- void shuffle()
		use Math.random twice to select random cards and switch them
		repeat multiple times
	- boolean equals(Deck other)
		iterate through this deck
		compare this deck's cards to other's cards
	- Card[] deal(int hands, int cardsPerHand)
		remove (hands*cardsPerHand) number of cards from deck
		return hands number of decks with cardsPerHand cards in each
		if deck != hands*cards
			return null
	- Card pick()
		removes and returns random card
		// can use Math.random
	- selectionSort()
		perform a selection sort on this deck
	- mergeSort()
		perform a merge sort on this deck
		
		*/
		
public class Deck implements Comparable<Deck> {
	private Card[] deck;
	private int top;
	
	public Deck() {
		deck = new Card[52];
		int index = 0;
		
		java.util.Set<java.util.Map.Entry<Integer, String>> ranks = Card.ranks.entrySet(); // Get ranks map
		java.util.Set<java.util.Map.Entry<Integer, String>> suits = Card.suits.entrySet(); // Get suits map
		
		for (java.util.Map.Entry<Integer, String> suit : suits) {
			for (java.util.Map.Entry<Integer, String> rank : ranks) {
				deck[index] = new Card(suit.getKey(), rank.getValue());
				index++;
			}
		}
	}
	
	public Deck(boolean sorted) {
		deck = new Card[52];
		int index = 0;
		
		java.util.Set<java.util.Map.Entry<Integer, String>> ranks = Card.ranks.entrySet(); // Get ranks map
		java.util.Set<java.util.Map.Entry<Integer, String>> suits = Card.suits.entrySet(); // Get suits map
		
		for (java.util.Map.Entry<Integer, String> suit : suits) {
			for (java.util.Map.Entry<Integer, String> rank : ranks) {
				deck[index] = new Card(suit.getKey(), rank.getValue());
				index++;
			}
		}
		
		if (sorted == false) {
			shuffle();
		}
	}
	
	public int compareTo() {
		return -1;
	}
	}
}