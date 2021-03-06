/**
 * Provides various methods for evaluating and converting TicTacToe
 * strings and boards.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @see Board
 * @see TicTacToeHashCode
 * 
 */
import java.util.Arrays;

public class TicTacToe {
   public final static int ROWS = 3;
   public final static int COLS = 3;
   public final static int POSSIBILITIES = (int) Math.pow(3,9);
   public final static int CHAR_POSSIBILITIES = 3; // x, o or space
   public static final String WINNERS = "winners.txt";
  
   /**
	 * Counts the amount of a certain character in a 2D array
	 * 
	 * @param b the 2D array of characters to analyze
	 * @param ch the character to test for
	 * @return the integer number of times the character appears
	 */
   private static int numChars(char[][] b, char ch) {
      int total = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++)
            if (ch == b[r][c]) 
               total++;
      return total;
   }
  
   /**
	 * Determines whether a TicTacToe board is valid
	 * 
	 * @param board the TicTacToe 2D character array to test
	 * @return true if the board is valid, false otherwise
	 */
   public static boolean valid(char[][] board) {
   
   // Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
   // Make sure there are at least one more x or one more o
      int numX = numChars(board, 'x');
      int numO = numChars(board, 'o');
      if (! (numX > 2 || numO > 2)) 
         return false;
      if ((numX == numO + 1) || (numO == numX + 1)) 
         return true;
      return false;
   }
  
   /**
	 * Converts a 2D TicTacToe character array to a String
	 * 
	 * @param b the 2D character array to convert
	 * @return the String representation of the array
	 */
   public static String boardToString(char[][] b) {
      String result = "";
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            result += b[r][c];
      //     result += "\n";
      }
      return result;
   }
   
   /**
	 * Converts a String to a 2D TicTacToe character array
	 * 
	 * @param board the String to convert
	 * @return the 2D character array representation of the string
	 */
   public static char[][] stringToBoard(String board) {
      char[][] b = new char[ROWS][COLS];
      int index = 0;
      for (int r = 0; r < ROWS; r++) {
         for (int c = 0; c < COLS; c++) 
            b[r][c] = whichLetter(board.charAt(index++));
      }
      return b;
   }

   /**
	 * Converts a numeric character to an x, o, or space
	 * 
	 * @param ch the numeric character
	 * @return the corresponding x, o, or space
	 */
   public static char whichLetter(char ch) {
      switch (ch) {
         case '1' : 
            return 'x';
         case '2' : 
            return 'o';
         case '0'  : 
            return ' ';
         default: 
            return ch;
      }
   }
     
   /**
	 * Converts a String to a 2D TicTacToe character array
	 * 
	 * @param s the String to convert
	 * @return the 2D character array representation of the string
	 */
   public static char[][] makeBoard(String s) {
      char[][] b = new char[ROWS][COLS];
      int ch = 0;
      for (int r = 0; r < ROWS; r++)
         for (int c = 0; c < COLS; c++){         
            b[r][c] = whichLetter(s.charAt(ch));
            ch++;
         }
      return b;
   }
   
   /**
	 * Adds 1 to the last character of the specified string
	 * 
	 * @param s the String to process
	 * @return the new String with 1 added to the last character
	 */
   private static String addOne(String s) {
   // s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
   // all the rest of the characters as necessary.
      boolean carry = false;
      char ch[] = s.toCharArray();
      ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
      for (int n = ch.length-1; n >=0; n--) {
         if (carry) ch[n] = (char)((int)(ch[n])+1);
         if (ch[n] == '3') {
            carry = true;
            ch[n] = '0';
         }
         else
            carry = false;      
      }
      return new String(ch);
   }
   
   /**
	 * Fills a String array with incrementing Strings
	 * 
	 * @return the array of Strings with incrementing String
	 */
   public static String[] fillValues() {
      String strBoard = "000000000";
      String[] values = new String[POSSIBILITIES];
      int index = 0;
      values[index++] = strBoard;
      while (!strBoard.equals("222222222") ) {
         strBoard = addOne(strBoard);
         values[index++] = strBoard;
      }
      return values;
   }
   
   /**
	 * Tests whether a TicTacToe board wins diagonally
	 * 
	 * @param board the TicTacToe board to test
	 * @return true if the board wins diagonally
	 */
   private static boolean diagonalWin(char[][] board) {
      int wins = 0;
      if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
         (board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) 
         wins++;
      

         if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
           (board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) 
            wins++;
         
      return wins == 1;
   }
   
   /**
	 * Tests whether a TicTacToe board wins horizontally
	 * 
	 * @param board the TicTacToe board to test
	 * @return true if the board wins horizontally
	 */
   private static boolean rowWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int r = 0; r < ROWS; r++) {
         win = true;
         ch = board[r][0];
         for (int c = 0; c < COLS; c++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ')
            wins++;
      } 
      return wins == 1;
   } 
   
   /**
	 * Tests whether a TicTacToe board wins vertically
	 * 
	 * @param board the TicTacToe board to test
	 * @return true if the board wins vertically
	 */
   private static boolean colWin(char[][] board) {
      char ch;
      int wins = 0;
      boolean win = true;
      for (int c = 0; c < COLS; c++) {
         win = true;
         ch = board[0][c];
         for (int r = 0; r < ROWS; r++) 
            if (ch != board[r][c]) {
               win = false;
               break;
            }
         if (win && ch != ' ') 
            wins++;
      } 
        return wins == 1;
   } 
   
   /**
	 * Tests whether a TicTacToe board wins
	 * 
	 * @param b the TicTacToe board to test
	 * @return true if the board wins
	 */
   public static boolean isWin(char[][]b) {
     return valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b));
     }

   /**
	 * Returns a string stating whether a TicTacToe board wins
	 * 
	 * @param b the TicTacToe board to test
	 * @return the String of whether the board is a winner or loser
	 */
   public static String isWinString(char[][]b) {
     boolean v = valid(b);
     boolean r = rowWin(b);
     boolean c = colWin(b);
     boolean d = diagonalWin(b);
     if (valid(b) && (rowWin(b) ^ colWin(b) ^ diagonalWin(b))) {
       if (r) return "Row";
       if (c) return "Col";
       if (d) return "Dia";
       return "winner";
     }
     else
       return "loser";
   }
     
   /**
	 * Tests whether a TicTacToe string wins
	 * 
	 * @param s the TicTacToe string to test
	 * @return true if the board wins
	 */
   public static boolean isWin(String s) {
      char[][] b = stringToBoard(s);
      return isWin(b);
   }

   /**
	 * Returns a string stating whether a TicTacToe string wins
	 * 
	 * @param s the TicTacToe string to test
	 * @return the String of whether the board is a winner or loser
	 */
   public static String isWinString(String s) {
      char[][] b = stringToBoard(s);
      return isWinString(b);
   }
     
   /**
	 * Tests whether a generic TicTacToe string wins
	 * 
	 * @param args any command line arguments to pass in
	 */
   public static void main(String[] args) {
      String s = "ooooxxxox";
      char[][] b = stringToBoard(s);
      System.out.println("Valid:   " + valid(b));
      System.out.println("Row Win: " + rowWin(b));
      System.out.println("Col Win: " + colWin(b));
      System.out.println("Dia Win: " + diagonalWin(b));
      System.out.println(isWin(b));
       
   }    
}
