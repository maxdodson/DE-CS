/**
 * Represents a modified JTextField that contains placeholder text
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/11/20
 * Currency Converter
 * 
 * @author Maxwell Dodson
 * 
 * @see CurrencyConverter
 * 
 */
import javax.swing.JTextField;
import java.awt.*;

public class MyTextField extends JTextField {
	
	private String placeholder;
   
	/**
	 * Instantiates a MyTextField with space for a specified number
	 * of columns
	 * 
	 * @param cols the number of character columns to fit in the field
	 */
	public MyTextField(int cols) {
		super(cols);
	}
   
	/**
	 * Instantiates a MyTextField with text inside
	 * 
	 * @param text any initial text entered in the field
	 */
	public MyTextField(String text) {
		super(text);
	}
   
	/**
	 * Instantiates a MyTextField with space for a specified number
	 * of columns and with text inside
	 * 
	 * @param text any initial text entered in the field
     * @param cols the number of character columns to fit in the field
	 */
	public MyTextField(String text, int cols) {
		super(text, cols);
	}
   
	/**
	 * Returns the placeholder text in this MyTextField
	 * 
	 * @return any placeholder text in the field
	 */
	public String getPlaceholder() {
		return placeholder;
	}
   
	/**
	 * Sets the placeholder text in this MyTextField
	 * 
	 * @param str a String to display as placeholder text
	 */
	public void setPlaceholder(String str) {
		placeholder = str;
	}
   
	/**
	 * Overrides paintComponent() of JTextField to draw placeholder text
	 * 
	 * @param g the Graphics object supplied automatically
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
   
		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
			return; // Only display the placeholder text if no text is entered
		}
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(getDisabledTextColor());
		g2D.setFont(new Font("Arial", Font.PLAIN, 14));
		
		// Draw a string in the text box to serve as a placeholder
		g2D.drawString(placeholder, getInsets().left+3, g.getFontMetrics().getMaxAscent()+13);
   }
}