/**
 * Represents a Queen in Chess.
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/7/20
 * Eight Queens
 * 
 * @author Maxwell Dodson
 * 
 * @see ChessSquarePanel
 * @see Solver
 * 
 */
public class Queen {
   private int row;
   private int col;
   
   /**
	 * Instantiates this Queen at a row and column
	 * 
	 * @param row the row of this Queen
     * @param col the column of this Queen
	 */
   public Queen(int row, int col) {
      this.row = row;
      this.col = col;
   }
   
   /**
	 * Returns the row this Queen is placed in
	 * 
	 * @return the row of this Queen
	 */
   public int getRow() {
      return row;
   }
   
   /**
	 * Returns the column this Queen is placed in
	 * 
	 * @return the column of this Queen
	 */
   public int getCol() {
      return col;
   }
}