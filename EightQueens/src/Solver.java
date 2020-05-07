/**
 * Represents an Eight Queens problem solver. Provides methods to build graphical
 * chess boards and solve the Eight Queens problem.
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/7/20
 * Eight Queens
 * 
 * @author Maxwell Dodson
 * 
 * @see ChessSquarePanel
 * @see Queen
 * 
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Solver {
   public static final int ROWS = 8;
   public static final int COLS = 8;
   public static final int BUFFER = 23;
   private static ChessSquarePanel[][] board;
   
   /**
	 * Displays the example Eight Queens solution I found
	 * 
	 */
   public static void mySolution() {
	   int[] rows = new int[] {5,0,4,1,7,2,6,3};
	   for (int i=0; i<rows.length; i++) {
		   board[rows[i]][i].setOccupied(true);
	   }
   }
   
   /**
	 * Recursively finds a solution to the Eight Queens problem
	 * 
	 * @param queens an ArrayList of Queens already placed on the board
     * @return true if a solution was found, false otherwise
	 */
   public static boolean addQueens(ArrayList<Queen> queens) {
      if (queens.size() == 8) { // Base Case: solution was found
         return true;
      }
      if (queens.size() == 0) { // Add the first queen if list is empty
         queens.add(new Queen(0,0));
         board[0][0].setOccupied(true);
         return addQueens(queens); // Try to add another queen
      }
      
      Queen last = queens.get(queens.size()-1);
      for (int r=last.getRow()+1; r<ROWS; r++) { // Start from most recent queen in list
         for (int c=0; c<COLS; c++) {
            if (isValid(queens, r, c)) { // If the position is valid, place a queen
               queens.add(new Queen(r,c));
               // ArrayList<Queen> temp = new ArrayList<Queen>();
               // Recurse to determine if this placement will result in a solution
               boolean success = addQueens(queens);
               if (success) {
                  board[r][c].setOccupied(true); // Change panel to occupied
               }
               // If this queen placement won't result in a solution, don't use it
               else {
                  queens.remove(queens.size()-1);
               }
            }
         }
      }
      return false; // Return false if no solution found
   }
   
   /**
	 * Recursively finds multiple solutions to the Eight Queens problem, storing
     * each in an ArrayList of solutions
	 * 
	 * @param queens an ArrayList of Queens already placed on the board
     * @param solutions an empty ArrayList for storing all found solutions
     * @return true if a solution was found, false otherwise
	 */
   public static boolean addQueens(ArrayList<Queen> queens, ArrayList<ArrayList<Queen>> solutions) {
      if (queens.size() == 8) { // If 8 queens, solution was found
         return true;
      }
      if (queens.size() == 0) { // Add the first queen if list is empty
         queens.add(new Queen(0,0));
         board[0][0].setOccupied(true);
         return addQueens(queens, solutions);
      }
      
      Queen last = queens.get(queens.size()-1);
      for (int r=last.getRow()+1; r<ROWS; r++) { // Start from most recent queen in list
         for (int c=0; c<COLS; c++) {
            if (isValid(queens, r, c)) { // If the position is valid, place a queen
               queens.add(new Queen(r,c));
               // Recurse to determine if this placement will result in a solution
               boolean success = addQueens(queens, solutions);
               if (success) {
                  board[r][c].setOccupied(true); // Change panel to occupied
                  solutions.add(queens);
               }
               // If this queen placement won't result in a solution, don't use it
               else {
                  queens.remove(queens.size()-1);
               }
            }
         }
      }
      return false; // Return false if no solution found
   }
   
   /**
	 * Creates a graphical chess board with a checkered background
	 * 
	 */
   public static void buildChessBoard() {
	  board = new ChessSquarePanel[ROWS][COLS];
      int width = ChessSquarePanel.WIDTH;
      int height = ChessSquarePanel.HEIGHT;
      
      JFrame window = new JFrame("Chess Board");
      window.setBounds(0, 0, width*COLS, height*COLS+BUFFER);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel canvas = new JPanel();
      canvas.setLayout(new GridLayout(ROWS,COLS));
      
      // Create grid
      boolean toggle = false;
      for (int r=0; r<ROWS; r++) {
         for (int c=0; c<COLS; c++) {
            ChessSquarePanel p;
            if (toggle) { // Checker background
               p = new ChessSquarePanel(Color.WHITE, false);
            }
            else {
               p = new ChessSquarePanel(Color.LIGHT_GRAY, false);
            }
            toggle = !toggle;
            board[r][c] = p;
            canvas.add(p);
         }
         toggle = !toggle; // Shift pattern for each row
      }
      window.add(canvas);
      window.setVisible(true);
   }
   
   /**
	 * Returns whether placing a Queen at a specified row and column is valid
	 * 
	 * @param queens an ArrayList of Queens already placed on the board 
     * @param row the row of the new Queen
     * @param col the column of the new Queen
     * @return true if the placement is valid, false otherwise
	 */
   public static boolean isValid(ArrayList<Queen> queens, int row, int col) {
      for (Queen q : queens) {
         // Invalid position if any queen exists on same row, column, or diagonal
         if (q.getRow() == row || q.getCol() == col || Math.abs(q.getRow()-row) == Math.abs(q.getCol()-col))
            return false;
      }
      return true;
   }
   
   /**
	 * Finds solutions to the Eight Queens problem on an empty board. Displays 
	 * one solution and prints how many were found
	 * 
	 * @param args any command line arguments
	 */
  public static void main(String[] args) {
     buildChessBoard(); // Prepare chess board for my solution
     mySolution();
     
     buildChessBoard(); // Prepare new chess board
     ArrayList<Queen> queens = new ArrayList<Queen>();
     ArrayList<ArrayList<Queen>> solutions = new ArrayList<ArrayList<Queen>>();
     
     addQueens(queens, solutions);
     // Display the solution
     for (Queen q : queens) {
        board[q.getRow()][q.getCol()].setOccupied(true); 
     }
     System.out.println("Solutions found: " + solutions.size());
  }
}