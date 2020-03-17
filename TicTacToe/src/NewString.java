/**
 * Represents a String key for a HashMap with a custom hash function.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @author Maxwell Dodson
 * @author Mrs. Kelly :)
 * 
 * @see TicTacToeMyHashMap
 * @see TTT_HC
 * 
 */
public class NewString {

	private static final int SIZE = 1400;
	public static final int SPACE_VALUE = 331;
	public static final int X_VALUE = 719;
	public static final int O_VALUE = 1217;
    private String s;

    /**
	 * Instantiates a new NewString with a given String
	 *
	 * @param str the initial String
	 */
    public NewString(String str) {
       s = str;
    }

    /**
	 * Provides a hash function to use in my HashMap
	 * 
	 * @return the integer hash code of this NewString
	 */
    @Override 
    public int hashCode() {
	    int hashCode = 0;
		for (int row=0; row<TicTacToe.ROWS; row++) {
			for (int col=0; col<TicTacToe.COLS; col++) {
				int charHash = charHash(Board.charAt(s, row, col), (row * 3) + col);
				hashCode += charHash;
			}
		}
		hashCode %= 9371;
		if (hashCode >= SIZE) {
			hashCode /= 7;
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
    public static int charHash(char chtr, int pos) {
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
	 * Sets this NewString's string field to the specified String
	 * 
	 * @param str the String to set as s
	 */
    public void setString(String str) {
    	s = str;
    }
   
    /**
	 * Returns this NewString's string field
	 * 
	 * @return the String stored in s
	 */
    public String getString() {
    	return s;
    }
    
    /**
	 * Returns this NewString as a String
	 * 
	 * @return the String representation of this NewString
	 */
    public String toString() {
    	return s;
    }
   
    /**
	 * Tests whether this NewString is equal to another Object by
	 * comparing its string field.
	 * 
	 * @param other the other Object to compare
	 * @return true if this NewString's string field is equal to
	 * the other NewString's string field, false otherwise
	 */
    @Override
    public boolean equals(Object other) {
    	if (other instanceof NewString) 
    		return s.equals(other);
    	else
    		return false;
    }
}