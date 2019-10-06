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
import Card;
import java.lang.Math;
import java.util.Arrays;
public class Deck implements Comparable<Deck> {
	private Card[] deck;
	private int top;
	private int deckSize = 52;
	
	public Deck() {
		deck = new Card[deckSize];
		int index = 0;
		
		java.util.Set<java.util.Map.Entry<Integer, String>> ranks = Card.ranks.entrySet(); // Get ranks map
		java.util.Set<java.util.Map.Entry<Integer, String>> suits = Card.suits.entrySet(); // Get suits map
		
		for (java.util.Map.Entry<Integer, String> suit : suits) {
			for (java.util.Map.Entry<Integer, String> rank : ranks) {
				deck[index] = new Card(suit.getKey(), rank.getValue());
				index++;
			}
		}
		
		top = deck.length;
	}
	
	public Deck(boolean sorted) {
		deck = new Card[deckSize];
		int index = 0;
		
		java.util.Set<java.util.Map.Entry<Integer, String>> ranks = Card.ranks.entrySet(); // Get ranks map
		java.util.Set<java.util.Map.Entry<Integer, String>> suits = Card.suits.entrySet(); // Get suits map
		
		for (java.util.Map.Entry<Integer, String> suit : suits) {
			for (java.util.Map.Entry<Integer, String> rank : ranks) {
				deck[index] = new Card(suit.getKey(), rank.getValue());
				index++;
			}
		}
		
		if (sorted == false) { // Shuffle cards if user wants unsorted deck
			shuffle();
		}
		
		top = deck.length;
	}
	
	public void shuffle() {
		for (int i=1; i<=5; i++) { // Perform shuffle process 5 times
			int random1 = (int)(Math.random() * (deck.length + 1)) // Generate two random ints from 0 to deck.length (inclusive)
			int random2 = (int)(Math.random() * (deck.length + 1))
			Card temp = deck[random1];
			deck[random1] = deck[random2]; // Switch cards at random1 and random2 indices
			deck[random2] = temp;
		}
	}
	
	public boolean equals(Deck other) {
		return Arrays.equals(deck, other) // Arrays equals method will compare the size of the decks and Cards in each one
	}
	
	public String toString() {
		if (deck.length == deckSize) { // If deck has 52 cards
			for (int i=0; i<deck.length; i++) { // Print each card in 4 columns by suit and in order of rank
				
			}
		}
		else { // If deck does not have 52 cards
			for (int i=0; i<deck.length; i++) { // Print each card in one column
				System.out.println(deck[i]);
			}
		}
	}
	
	public Card pick() {
		int random = (int)(Math.random() * (deck.length + 1)) // Generate random int from 0 to deck.length (inclusive)
		Card picked = deck[random];
		
		for (int i=random; i<deck.length-1; i++) { // Move all Cards past random index to the left by one
			deck[i] = deck[i+1];
		}
		
		return picked;
	}
	
	public void selectionSort() {
		for (int i=deck.length; i>1; i--) { // Find "greatest" Card and swap with Card from the right side
			int jMax = 0;
			for (int j=1; j<i; j++) {
				if (deck[j].compareTo(deck[jMax]) > 0) { // If deck[j] "greater than" deck[jMax], then jMax = j
					jMax = j;
				}
			}
			Card temp = deck[jMax]; // Swap cards at jMax and i-1
			deck[jMax] = deck[i-1];
			deck[i-1] = temp;
		}
	}
	
	public void mergeSort() {
		Card[] temp;
		
	}
	
	private void recursiveSort(Card[] arr, int from, int to) {
		
	}
	
	private void merge(Card[] arr, int from, int middle, int to) {
		
	}
	
}