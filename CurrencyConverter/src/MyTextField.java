import javax.swing.JTextField;
import java.awt.*;

public class MyTextField extends JTextField {

   private String placeholder;
   
   public MyTextField(int cols) {
       super(cols);
   }
   
   public MyTextField(String text) {
       super(text);
   }
   
   public MyTextField(String text, int cols) {
       super(text, cols);
   }
   
   public String getPlaceholder() {
       return placeholder;
   }
   
   public void setPlaceholder(String str) {
       placeholder = str;
   }
   
   @Override
   protected void paintComponent(final Graphics pG) {
       super.paintComponent(pG);
   
       if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
           return;
       }
       final Graphics2D g = (Graphics2D) pG;
       g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g.setColor(getDisabledTextColor());
       g.setFont(new Font("Arial", Font.PLAIN, 14)); 
       g.drawString(placeholder, getInsets().left+3, pG.getFontMetrics().getMaxAscent()+13);
   }
}