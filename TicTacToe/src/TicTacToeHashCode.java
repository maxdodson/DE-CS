/**
 * Creates an array of hashed TicTacToe winners using a hash function that
 * results in no collisions and analyzes it.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @author Maxwell Dodson
 * 
 * @see TicTacToe
 * @see Board
 * @see TTC_HC
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TicTacToeHashCode extends Board {

	boolean[] winnersArr;
	public static final int SPACE_VALUE = 0;
	public static final int X_VALUE = 2;
	public static final int O_VALUE = 1;
	public static final String TEST_FILE = "tests.txt";
	public static final int DELAY_MS = 4000;

	/**
	 * Instantiates a new TicTacToeHashCode with a String title
	 * 
	 * @param s the title of the Graphics board
	 */
	TicTacToeHashCode(String s) {
		super(s);
		// Instantiate boolean array with capacity of 19,683; defaults to false
		winnersArr = new boolean[TicTacToe.POSSIBILITIES];
		File in = new File(TicTacToe.WINNERS); // Attempt to find winners file
		try {
			Scanner input = new Scanner(in);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				super.setBoardString(line);
				winnersArr[myHashCode()] = true;
			}
			input.close();
		} catch (IOException e) {}
	}

	/**
	 * Generates a hash code for this Board that results in no collisions
	 * 
	 * @return the integer hash code of the board
	 */
	@Override
	public int myHashCode() {
		int hashCode = 0;
		for (int row=0; row<TicTacToe.ROWS; row++) {
			for (int col=0; col<TicTacToe.COLS; col++) {
				int charHash = charHash(charAt(row, col), (row * 3) + col);
				hashCode += charHash;
			}
		}
		return hashCode;
	}
	
	/**
	 * Generates a hash code for a character at a position
	 * 
	 * @param chtr the character to hash
	 * @param pos the position of the character
	 * @return the integer hash code of the character
	 */
	public int charHash(char chtr, int pos) {
		chtr = Character.toLowerCase(chtr);
		if (chtr == ' ') {
			return SPACE_VALUE * (int)Math.pow(3, pos);
		}
		else if (chtr == 'x') {
			return X_VALUE * (int)Math.pow(3, pos);
		}
		else if (chtr == 'o') {
			return O_VALUE * (int)Math.pow(3, pos);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Determines whether a given TicTacToe String represents
	 * a winning board
	 * 
	 * @param s the TicTacToe String to test
	 * @return whether this TicTacToe String is a winner
	 */
	@Override
	public boolean isWin(String s) {
		// Calculate the hash code for this String
		int hashCode = 0;
		for (int row=0; row<TicTacToe.ROWS; row++) {
			for (int col=0; col<TicTacToe.COLS; col++) {
				int pos = (row * 3) + col;
				int charHash = charHash(charAt(s, row, col), pos);
				hashCode += charHash;
			}
		}
		// If the string's hash is in the winners array, it is a winner
		return winnersArr[hashCode];
    }
      
	/**
	 * Determines whether this Board represents a winning board
	 * 
	 * @return whether this TicTacToe String is a winner
	 */
	@Override
	public boolean isWin() {
		// If Board's hash is in the winners array, it is a winner
		return winnersArr[myHashCode()];
    }
      
	/**
	 * Instantiates a TicTacToeHashCode and TicTacToe Board with
	 * various test strings.
	 * 
	 * @param args any command line arguments to pass in
	 */
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		while (true) {
		    board.displayRandomString();
		    Thread.sleep(DELAY_MS); // Delay changing the board
		}/*
		File in = new File(TEST_FILE); // Attempt to find winners file
		try {
			Scanner input = new Scanner(in);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				
				
				
			}
			input.close();
		} catch (IOException e) {}*/
	}

}
