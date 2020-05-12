/**
 * Provides a GUI to convert currencies. Implements a Weighted Graph to
 * store exchange rates retrieved from an API
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/11/20
 * Currency Converter
 * 
 * @author Maxwell Dodson
 * 
 * @see MyTextField
 * @see IO
 * @see WeightedGraph
 * 
 */
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.*;
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
	private static final String[] CURRENCIES = SYMBOLS_MAP.keySet().toArray(new String[SYMBOLS_MAP.keySet().size()]);
	private static final String API_ENDPOINT = "https://api.exchangeratesapi.io/latest?base=";
	private static WeightedGraph.Graph graph;
   
	/**
	 * Retrieves currency exchange rates by making HTTP requests to an
	 * API and stores them in a Weighted Graph 
	 * 
	 */
	public static void buildGraph() {
		graph = new WeightedGraph.Graph(CURRENCIES.length * CURRENCIES.length);
	    // Fetch the exchange rates using each currency as the base reference
	    for (String currency : CURRENCIES) {
	    	String url = API_ENDPOINT + SYMBOLS_MAP.get(currency);
		    String response = IO.get(url); // Perform the GET request
		    JSONObject json = IO.parseJSON(response);
		    JSONObject rates = (JSONObject)json.get("rates"); // Parse exchange rates
		   
		    // Store exchange rates for currencies as edges in the Graph
		    for (String nextCurrency : CURRENCIES) {
		    	String nextCurrencySymbol = SYMBOLS_MAP.get(nextCurrency);
		    	
		    	// Account for a minor inconsistency with the API
		    	// (this indeed caused me some pain for a time) 
		    	double exchangeRate;
		    	if (currency.equals("Euro") && nextCurrency.equals("Euro"))
		    		exchangeRate = 1.0;
		    	else
		    		exchangeRate = (double)rates.get(nextCurrencySymbol);
		    	
		    	graph.addEdge(currency, nextCurrency, exchangeRate);
		   	}
	    }
	}
   
	/**
	 * Instantiates and displays the various graphics components
	 * 
	 */
	public static void createGraphicalInterface() {
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
        			String startCurrency = (String)initialCurrencyMenu.getSelectedItem(); 
        			String endCurrency = (String)finalCurrencyMenu.getSelectedItem(); 
        			// Convert the currency and display the result
        			double converted = convertCurrency(startCurrency, endCurrency, value);
        			converted = Math.round(converted*100) / 100.0; // Round to 2 decimal places
        			convertedLabel.setText("" + converted);
        		}
        		catch (Exception e) {
        			System.out.println(e);
        		}
        	}
        });
	}
	
	/**
	 * Finds the currency exchange rate stored in the Graph and performs
	 * the mathematical conversion
	 * 
	 * @param initCurrency the initial currency
     * @param finalCurrency the currency to be converted to
     * @param amount the value of the currency to convert
     * 
	 */
	public static double convertCurrency(String initCurrency, String finalCurrency, double amount) {
		ArrayList<WeightedGraph.Edge> edges = graph.getEdges();
		// Find conversion factor in Graph
		for (int i=0; i<edges.size(); i++) {
			String origin = edges.get(i).getOrigin();
			String destination = edges.get(i).getDestination();
			if (origin.equals(initCurrency) && destination.equals(finalCurrency)) {
				// Convert the amount to the desired currency
				double factor = edges.get(i).getWeight();
				return amount * factor;
			}
		}
		return -1;
	}
   
	/**
	 * Builds the Graph containing exchange rates and launches the GUI
	 * 
	 * @param args any command line arguments
	 */
	public static void main(String[] args) {
		buildGraph();
		System.out.println("Edges created: " + graph.getEdges().size());
		createGraphicalInterface();
	}
}