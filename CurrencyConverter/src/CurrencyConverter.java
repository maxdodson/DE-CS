import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import org.json.simple.JSONObject;

public class CurrencyConverter {

   private static final HashMap<String, String> SYMBOLS_MAP = new HashMap<String, String>() {{
       put("U.S. Dollar", "USD");
       put("Canadian Dollar", "CAD");
       put("Euro", "EUR");
       put("British Pound", "GBP");
       put("Japanese Yen", "JPY");
       put("Chinese Yuan", "CNY");
   }};
   private static final String[] CURRENCIES = (String[]) SYMBOLS_MAP.keySet().toArray();
   private static final ArrayList<String> SYMBOLS = new ArrayList<String>(SYMBOLS_MAP.values());
   private static final String API_ENDPOINT = "https://api.exchangeratesapi.io/latest?base=";
   
   //public static void createGraphicalInterface() {
   //   
   //}
   
   public static void main(String[] args) {
      JFrame window = new JFrame("Currency Converter");
      window.getContentPane().setBackground(Color.WHITE);
      window.setSize(400,400);
      window.setLayout(null); 
      window.setResizable(false); // Make the window a fixed size
      
      JLabel label1 = new JLabel("Initial Currency");
      label1.setBounds(50,30,200,30);
      label1.setFont(new Font("Verdana", Font.PLAIN, 14));
      window.add(label1);
      
      MyTextField userField = new MyTextField(15);
      userField.setPlaceholder("Enter a value");
      userField.setBounds(50,60,140,40);
      userField.setFont(new Font("Arial", Font.BOLD, 16));
      window.add(userField);
      
      JComboBox initialCurrencyMenu = new JComboBox(CURRENCIES);
      initialCurrencyMenu.setBounds(200,39,150,45);
      window.add(initialCurrencyMenu);
     
      JLabel label2 = new JLabel("TO");
      label2.setBounds(265,65,15,30);
      label2.setFont(new Font("Arial", Font.PLAIN, 10));
      window.add(label2);
      
      JComboBox finalCurrencyMenu = new JComboBox(CURRENCIES);
      finalCurrencyMenu.setSelectedIndex(1);
      finalCurrencyMenu.setBounds(200,77,150,45);
      window.add(finalCurrencyMenu);
      
      JButton convertButton = new JButton("CONVERT"); 
      convertButton.setBounds(80,150,200,40);
      convertButton.setForeground(Color.WHITE);
      convertButton.setBackground(Color.decode("#4692c2"));
      convertButton.setOpaque(true);
      convertButton.setBorderPainted(false);
      convertButton.setFont(new Font("Arial", Font.BOLD, 16));
      window.add(convertButton);
      
      JButton switchButton = new JButton("↑↓"); 
      switchButton.setBounds(285,150,40,40);
      window.add(switchButton);
      
      JLabel label3 = new JLabel("Converted Currency");
      label3.setBounds(50,230,200,30);
      label3.setFont(new Font("Verdana", Font.PLAIN, 14));
      window.add(label3);
      
      JLabel convertedLabel = new JLabel();
      convertedLabel.setBounds(50,245,325,70);
      convertedLabel.setFont(new Font("Verdana", Font.BOLD, 24));
      window.add(convertedLabel);
                
      window.setLocationRelativeTo(null); // Center window
      window.setVisible(true);
      
      // Restrict user to entering decimal numbers only 
      userField.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent ke) {
            String value = userField.getText();
            if (value.contains(".") && ke.getKeyChar() == '.') // Allow 1 decimal point
               ke.consume();
            // Allow only numbers and a decimal point
            if (!((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyChar() == '.'))
               ke.consume();
         }
      });
      
      // Add functionality to the "Convert" button
      convertButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent event) {
            try {
               // Attempt to parse value inputed by user
               double value = Double.parseDouble(userField.getText());
               System.out.println(value);
               String selectedCurrency = (String)initialCurrencyMenu.getSelectedItem(); 
               System.out.println(selectedCurrency + " " + SYMBOLS_MAP.get(selectedCurrency));
               String url = API_ENDPOINT + SYMBOLS_MAP.get(selectedCurrency);
            }
            catch (Exception e) {
               System.out.println(e);
            }
         }
      });
      
   }
   
   public void buildGraphs() {
	   // Get JSON
	   WeightedGraph.Graph graph = new WeightedGraph.Graph(CURRENCIES.length * CURRENCIES.length);
	   for (String symbol : SYMBOLS) {
		   String url = API_ENDPOINT + symbol;
		   String response = IO.get(url);
		   JSONObject rates = (JSONObject)(IO.parseJSON(response)).get("rates");
		   
		   
	   }
   }
}