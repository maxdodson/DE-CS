/**
 * Represents a TicTacToe board and provides Graphics components.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/15/20
 * Hash Functions
 * 
 * @see TicTacToe
 * @see TicTacToeHashCode
 * 
 */
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

abstract class Board extends JFrame implements ActionListener {

	private JButton buttons[][];
	private JLabel lblHashCode;
	private JLabel lblWinTitle;

	private String boardString = "";

	/**
	 * Instantiates a new TicTacToe board with a String title
	 * 
	 * @param s the title of the Graphics board
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * Changes the hash code label on the board
	 * 
	 * @param hashcode the hashcode of the current board
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * Changes the winner label on the board when given a String
	 * 
	 * @param result whether the board is a "winner" or "loser"
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}

	/**
	 * Changes the winner label on the board when given a boolean
	 * 
	 * @param result true if the board is a winner
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	/**
	 * Add the Graphics components to the JPanel
	 * 
	 * @param e the ActionEvent passed by the Graphics program 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	/**
	 * Creates a new TicTacToe board with random characters
	 * 
	 * @return the JPanel with the graphics components
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * Returns a different character than the one passed
	 * 
	 * @param ch the current character to cycle
	 * @return the new cycled character
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}

	/**
	 * Creates a new TicTacToe game using Java Graphics
	 * 
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}

	/**
	 * Generates a random x, o, or space
	 * 
	 * @return the random character
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}

	abstract int myHashCode();

	abstract boolean isWin(String s);

	abstract boolean isWin();

	/**
	 * Determines the character at a given row and column
	 * 
	 * @param row the row to retrieve from
	 * @param col the column to retrieve from
	 * @return the character at the specified row and column
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}
   
	/**
	 * Determines the character at a given row and column in a String
	 * 
	 * @param s the String to evaluate
	 * @param row the row to retrieve from
	 * @param col the column to retrieve from
	 * @return the character at the specified row and column
	 */
   public static char charAt(String s, int row, int col) {
     int pos = row * TicTacToe.COLS + col;
     if (s.length() >= pos)
       return s.charAt(pos);
     else
       return '*';   
   }

   /**
	 * Sets this Board to show a specified TicTacToe String
	 * 
	 * @param s the String to display in this Board
	 */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	/**
	 * Resets this Board's string to ""
	 * 
	 */
	public void resetBoardString() {
		boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * Sets this Board's String representation.
	 * 
	 * @param s the String to use as this Board's string
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}

	/**
	 * Returns this Board as a String
	 * 
	 * @return the String representation of this Board
	 */
	public String getBoardString() {
		return boardString;
	}

	/**
	 * Generates a random TicTacToe string to use in this Board
	 * 
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}