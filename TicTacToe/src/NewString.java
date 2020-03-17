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

    public NewString(String str) {
       s = str;
    }

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
   
    public void setString(String str) {
    	s = str;
    }
   
    public String getString() {
    	return s;
    }
    
    public String toString() {
    	return s;
    }
   
    @Override
    public boolean equals(Object other) {
    	if (other instanceof NewString) 
    		return s.equals(other);
    	else
    		return false;
    }
}