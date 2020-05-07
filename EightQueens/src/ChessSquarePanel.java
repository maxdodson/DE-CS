/**
 * Represents a square on a graphical chess board.
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/7/20
 * Eight Queens
 * 
 * @author Maxwell Dodson
 * 
 * @see Queen
 * @see Solver
 * 
 */
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel {

   public static final int HEIGHT = 50;
   public static final int WIDTH = 50;
   Color background;
   boolean occupied;
   
   /**
	 * Instantiates a ChessSquarePanel with a background color and
     * occupancy status
	 * 
	 * @param bg the background color of this Chess square
     * @param occupied whether a Queen is placed on this square
	 */
   public ChessSquarePanel(Color bg, boolean occupied) {
      this.occupied = occupied;
      this.background = bg;
   }
   
   /**
	 * Sets whether this square is occupied by a Queen
	 * 
     * @param occupied whether a Queen is placed on this square
	 */
   public void setOccupied(boolean occupied) {
      this.occupied = occupied;
   }
   
   /**
	 * Paints this square with a background and Queen if occupied
	 * 
     * @param g the Graphics object supplied automatically
	 */
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.setBackground(background);
      if (occupied) { // Display a Queen in occupied squares
         g.drawString("Ⓠ", WIDTH/2, HEIGHT/2);
      }
   }
}