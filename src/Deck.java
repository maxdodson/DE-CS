/**
 * Represents a Deck of Cards. Provides various methods to manipulate the Deck, such as to sort, shuffle, etc.
 * 
 * @author Maxwell Dodson
 * 
 * @see Card
 * 
**/
import java.lang.Math;

public class Deck {
	private Card[] deck;
	private int top;
	private final int deckSize = 52;
	private final int numRanks = 13;
	private final int numSuits = 4;
	private Card[] temp;
	
	/**
	 * Instantiates a default Deck with 52 cards, in order.
	 * 
	**/
	public Deck() {
		deck = new Card[deckSize];
		int index = 0;
		
		java.util.Set<java.util.Map.Entry<Integer, String>> ranks = Card.ranks.entrySet(); // Get ranks map
		java.util.Set<java.util.Map.Entry<Integer, String>> suits = Card.suits.entrySet(); // Get suits map
		
		for (java.util.Map.Entry<Integer, String> suit : suits) { // For every suit and rank
			for (java.util.Map.Entry<Integer, String> rank : ranks) {
				deck[index] = new Card(suit.getKey(), rank.getValue()); // Create a Card and add it to deck
				index++;
			}
		}
		
		top = deck.length;
	}
	
	/**
	 * Instantiates a Deck with the option to be sorted or unsorted.
	 * 
	 * @param sorted a boolean saying whether the Deck should be sorted
	 * 
	**/
	public Deck(boolean sorted) {
		this(); // Call default constructor
		if (sorted == false) { // Shuffle cards if user wants unsorted deck
			shuffle();
		}
	}
   
	/**
	 * Instantiates a Deck with a specified size.
	 * 
	 * @param size the integer of the Deck's length
	 * 
	**/
	public Deck(int size) {
		deck = new Card[size];
	}
   
	/**
	 * Adds a provided Card to this Deck, if space is available.
	 * 
	 * @param c the Card to be added
	 * 
	**/
	public void add(Card c) {
		for (int i=0; i<deck.length; i++) { // Find an open spot in the deck
			if (deck[i] == null) {
				deck[i] = c; // Put card in open spot
				break;
			}
		}
	}
	
	/**
	 * Shuffles this Deck by randomly switching Cards.
	 * 
	**/
	public void shuffle() {
		for (int i=1; i<=5; i++) { // Perform shuffle process 5 times
			int random1 = (int)(Math.random() * (deck.length)); // Generate two random integers from 0 to deck.length-1
			int random2 = (int)(Math.random() * (deck.length));
			Card temp = deck[random1];
			deck[random1] = deck[random2]; // Switch cards at random1 and random2 indices
			deck[random2] = temp;
		}
	}
	
	/**
	 * Returns whether this Deck equals another Deck.
	 * 
	 * @param other another Deck to compare
	 * @return true if both Decks are the same size and have the same Cards in order, false otherwise
	 * @return 
	 * 
	**/
	public boolean equals(Deck other) {
      if (deck.length != other.getLength()) { // Return false if arrays not equal in length
         return false;
      }
      else { // If arrays equal in length, compare Cards in each array
         for (int i=0; i<deck.length; i++) {
            if (!(deck[i].equals(other.getCard(i)))) {
               return false; // Return false if any Cards at the same index are unequal
            }
         }
      }
      return true;
	}
	
	/**
	 * Returns an array containing a specified number of Decks, each with a specified number of Cards.
	 * 
	 * @param numHands the integer number of hands/Decks to return
	 * @param cardsPerHand the integer number of Cards to place in each hand/Deck
	 * @return an array of Decks with the specified number of Cards
	 * @return null when the number of cards requested is greater than this Deck's length
	 * 
	**/
	public Deck[] deal(int numHands, int cardsPerHand) {
		int totalNumCards = numHands * cardsPerHand;
		if (totalNumCards > deck.length) { // Return null if not enough cards in deck
			return null;
		}
		
	    Deck[] hands = new Deck[numHands]; // Create Deck[]
	    for (int i=0; i<hands.length; i++) {
	    	hands[i] = new Deck(cardsPerHand); // Instantiate each deck with size cardsPerHand
	    }
	    int hand = 0;
		int card = 0;
		int i = 0;
		while (i<totalNumCards) {
			while (card < cardsPerHand) { // Add the required number of cards to the hand
				hands[hand].add(deck[i]);
				card++;
				i++;
			}
			hand++; // Proceed to next hand
			card = 0; // Reset card index
		}
      
		Card[] newDeck = new Card[deck.length - totalNumCards]; // Create a new, smaller deck for remainging Cards
		int index = 0;
		for (int j=totalNumCards; j<deck.length; j++) { // Iterate through remaining Cards in deck
			newDeck[index] = deck[j]; // Add each Card to newDeck
			index++;
		}
      
		deck = newDeck; // Replace deck with newDeck
		return hands;
	}
	
	/**
	 * Returns a Card chosen randomly from deck and collapses deck.
	 * 
	 * @return the Card that was randomly picked
	 * 
	**/
	public Card pick() {
		int random = (int)(Math.random() * (deck.length)); // Generate random int from 0 to deck.length-1
		Card picked = deck[random];

		Card[] newDeck = new Card[deck.length - 1]; // Create a new, smaller deck
		for (int i=0; i<deck.length-1; i++) {
			if (i < random) { // Move Cards before random into newDeck
				newDeck[i] = deck[i];
			}
			else {
				newDeck[i] = deck[i+1]; // Move Cards after random into newDeck, skipping the Card at random
			}
		}

		deck = newDeck; // Replace deck with newDeck
		return picked;
	}
	
	/**
	 * Sorts elements in an array by swapping largest element with next on the right.
	 * 
	 * May be used to sort when speed is not a necessity.
	 * 
	**/
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
	
	/**
	 * Recursively sorts elements in an array by splitting it and merging after sorting.
	 * 
	 * May be used when a sufficiently fast sorting algorithm is required.
	 * 
	**/
	public void mergeSort() {
		temp = new Card[deck.length];
		recursiveSort(deck, 0, (deck.length - 1));
	}
	
	/**
	 * Recursively sorts elements in an array.
	 * 
	 * @param arr a Card array of Cards to compare
	 * @param from the index from which to begin sorting
	 * @param to the index of the final element to sort
	 * @throws IndexOutOfBoundsException thrown when from or to are out of bounds of arr
	 * 
	**/
	private void recursiveSort(Card[] arr, int from, int to) {
		if (to - from < 2) { // If only one or two elements to sort
			if ((to > from) && (deck[to].compareTo(deck[from]) < 0)) { // Compare and swap them
				Card temp = deck[to];
				deck[to] = deck[from];
				deck[from] = temp;
			}
		}
		else { // If more than two elements to sort
			int middle = (from + to) / 2; // Estimate middle
			recursiveSort(arr, from, middle); // Sort Cards to left and right of middle
			recursiveSort(arr, middle + 1, to);
			merge(arr, from, middle, to); // Recombine both arrays
		}
	}
	
	/**
	 * Merges both halves of an array into a single array.
	 * 
	 * @param arr a Card array of Cards to compare
	 * @param from the index from which to begin merging
	 * @param middle the index of the middle element, used to merge the two halves
	 * @param to the index of the final element to merge
	 * @throws IndexOutOfBoundsException thrown when from, middle, or to are out of bounds of arr or temp
	 * 
	**/
	private void merge(Card[] arr, int from, int middle, int to) {
		int i = from, j = middle+1, k = from;
		while (i <= middle && j <= to) { // Iterate through both halves of arr
			if (arr[i].compareTo(arr[j]) < 0) { // Compare elements at i and j
				temp[k] = arr[i]; // Place "lesser" element in temp array
				i++;
			}
			else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}
		while (i <= middle) { // If j reached to
			temp[k] = arr[i]; // Add remaining elements to temp array
			i++;
			k++;
		}
		while (j <= to) { // If i reached middle
			temp[k] = arr[j]; // Add remaining elements to temp array
			j++;
			k++;
		}
		for (k = from; k <= to; k++) { // Copy elements in temp to arr
			arr[k] = temp[k];
		}
	}
   
	/**
	 * Returns the length of this deck.
	 * 
	 * @return the integer length of deck
	 * 
	**/
    public int getLength() {
    	return deck.length;
    }
   
    /**
	 * Returns the Card at a specified index.
	 * 
	 * @param index the integer index of the Card
	 * @return the Card at the specified index in deck
	 * @throws IndexOutOfBoundsException thrown when the index is out of bounds of deck
	 * 
	**/
    public Card getCard(int index) {
    	return deck[index];
    }
	
    /**
	 * Returns this Deck represented as a String.
	 * 
	 * If this Deck has more than 52 Cards, the Cards are listed in columns by suit
	 * If this Deck has less than 52 Cards, the Cards are simply listed in order of appearance
	 * 
	 * @return the String representation of this Deck
	 * 
	**/
	public String toString() {
		String str = "";
		if (deck.length >= deckSize) { // If deck has >=52 cards
			Card[][] cards = new Card[numSuits][numRanks]; // Store cards in 2D Card array
			for (int i=0; i<deck.length; i++) { 
	            int suitInt = deck[i].getSuitInt(); // Use suitInt to separate by suit
	            for (int j=0; j<cards[suitInt].length; j++) { // Find empty spot in cards
	                if (cards[suitInt][j] == null) {
		            	cards[suitInt][j] = deck[i]; // Add card to empty spot
		            	break;
			        }
	            }
			}
			
			for (int row=0; row<cards[numSuits-1].length; row++) { // Iterate through each row of cards
				for (int col=0; col<cards.length; col++) {
					str += String.format("%1$20s", cards[col][row].toString()); // Add all cards from the suit
				    str += " "; // Add tab after
				}
				str += "\n"; // Add new line after each row
			}
		}
		else { // If deck does not have 52 cards
			for (int i=0; i<deck.length; i++) { // Print each card in one column
				str += deck[i].toString() + "\n";
			}
		}
      return str;
	}
	
}