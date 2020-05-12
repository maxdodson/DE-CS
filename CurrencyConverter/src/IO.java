/**
 * Provides methods for making HTTP requests and parsing JSON
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IO {
	
	/**
	 * Makes an HTTP GET request to a specified API endpoint
	 * 
	 * @param endpoint the API endpoint URL
	 * @return the response from the API
	 */
	public static String get(String endpoint) {
		HttpURLConnection connection = null;
		String response = "";
	    try {
	    	// Establish connection
	    	URL url = new URL(endpoint);
	        connection = (HttpURLConnection)url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setUseCaches(false); // Make sure to fetch new rates each time
	    
	        // Process response
	        InputStream stream = connection.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
	        String line;
	        // Read response into String
	        while ((line = reader.readLine()) != null) {
	        	response += line + "\n";
	        }
	        
	        reader.close(); // Close BufferedReader
	    }
	    catch (Exception e) {
	    	System.out.println(e);
	    }
	    finally {
	    	// Close connection if it's open
	    	if (connection != null) {
	    		connection.disconnect();
	    	}
	    }
	    return response;
	}
	
	/**
	 * Parses a String of JSON into a JSONObject
	 * 
	 * @param str a String of JSON
	 * @return the parsed JSON as a JSONObject
	 */
	public static JSONObject parseJSON(String str) {
		JSONObject json = null;
		// Attempt to parse the String
	    JSONParser parser = new JSONParser();
	    try {
	    	Object obj  = parser.parse(str);
	        json = (JSONObject)obj; // Cast to JSONObject
	    }
	    catch (Exception e) {
	    	System.out.println("IO: " + e);
	    }
	    return json;
	}
	
}
