import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IO {
	public static String get(String endpoint) {
		HttpURLConnection connection = null;
		String response = "";
	    try {
	    	// Establish connection
	    	URL url = new URL(endpoint);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setUseCaches(false);
	        connection.setDoOutput(true);
	    
	        // Process response
	        InputStream is = connection.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
