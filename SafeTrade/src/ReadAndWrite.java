/**
 * Provides useful methods that read and write to files
 * 
 * @author Maxwell.Dodson
 * 
 * Maxwell Dodson
 * DE CS II
 * 9/22/19
 * FileReading
 * 
 * --maks
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ReadAndWrite {
	
	private static PrintWriter output;
	private static Scanner kb;

	/**
	 * Passes 3-4 file paths to other methods that read and write to files
	 * 
	 * @param args 3-4 file paths
	 */
	public static void main(String[] args) {
		
		kb = new Scanner(System.in); // Prompt user for 3-4 filenames
		System.out.print("Enter filename: ");
		String fname1 = kb.next();
      
      
		System.out.print("Enter next filename: ");
		String fname2 = kb.next();

		System.out.print("Enter final filename(s): ");
		kb.nextLine();
		String fname3 = kb.nextLine();
		String[] fnames = fname3.split(" "); // Split line into String array in order to accept multiple filenames
		
		File out = new File("output.txt");
		try {
			output = new PrintWriter(out);
		} catch (FileNotFoundException e) {
		}
		
		testBracesBalanced(fname1); // Test if braces balanced
		testIdenticalFiles(fname1, fname2); // Test if files identical
		fillStory(fnames); // Fill story file with inputted words
		
		output.close(); // Close PrintWriter
	    kb.close(); // Close Scanner
		
	}
		
	/**
	 * Tests whether braces in a file are balanced
	 * 
	 * @param filename file path
	 */
	public static void testBracesBalanced(String filename) {
		
		File in = new File(filename);
		Scanner input1 = null; // Create Scanner to read in
      
		try {
			input1 = new Scanner(in);
         
	        int numOpenBraces = 0;
	   		int numCloseBraces = 0;
	   
	   		while (input1.hasNext()) { // While File in has more contents
	            String next = input1.next();
	   			if (next.contains("{")) { // Count open braces
	   				numOpenBraces++;
	   			}
	   			if (next.contains("}")) { // Count close braces
	   				numCloseBraces++;
	   			}
	   		}
	   
	   		if (numOpenBraces == numCloseBraces) { // Compare number of braces to determine if they are balanced
	   			output.println("Braces Balanced\n");
	   		}
	   		else {
	   			output.println("Braces Not Balanced\n");
	   		}

		} catch (FileNotFoundException e) { // File not found or unable to be opened
			output.println("Part 1: Unable to Open File");
		}
	}
	
	/**
	 * Tests whether two files are identical or not
	 * 
	 * @param filename1 file path
	 * @param filename2 file path
	 */
	public static void testIdenticalFiles(String filename1, String filename2) {

		File file1 = new File(filename1);
		File file2 = new File(filename2);
		Scanner input1 = null;
		Scanner input2 = null;

		try {
			input1 = new Scanner(file1); // Create Scanners to read file1 and file2
			input2 = new Scanner(file2);
         
			String file1Str = "";
			while (input1.hasNext()) {
				file1Str += input1.next(); // Append file1's contents to file1Str
			}
   
	   		String file2Str = "";
	   		while (input2.hasNext()) {
	   			file2Str += input2.next(); // Append file2's contents to file2Str
	   		}
	   
	   		if (file1Str.equals(file2Str)) { // Compare contents of file1 and file2 to determine if they are identical
	   			output.println("Files Identical\n");
	   		}
	   		else {
	   			output.println("Files Not Identical\n");
	   		}

		} catch (FileNotFoundException e) { // File not found or unable to be opened
			System.out.println("Part 2: Unable to Open File");
		}
	}
	
	/**
	 * Passes 3-4 file paths to other methods that read and write to files
	 * 
	 * @param fnames an array of filenames
	 */
	public static void fillStory(String[] fnames) {
		
		File file3 = new File(fnames[0]);
		Scanner input3 = null; // Create Scanner to read file3

		try {
			input3 = new Scanner(file3);
         
	   		ArrayList<String> tags = new ArrayList<String>(); // Create new ArrayList to hold words for the story
	        Scanner input4 = null;
         
         
	        if (fnames.length != 1) {
	        	File file4 = new File(fnames[1]);
   
	   			try {
	   				input4 = new Scanner(file4); // Create Scanner to read file4 if provided
	   			} catch (FileNotFoundException e) { // File not found or unable to be opened
	   				output.println("Part 3: Unable to Open File");
	   			}
	   			
	   			while (input4.hasNext()) { // While file4 has more words
	   				tags.add(input4.next()); // Add words to the tags ArrayList
	   			}
	        }
   		
	        String line = "";
	        Pattern pattern = Pattern.compile("<\\S+>"); // Use RegEx to match <> tags
	        Matcher matcher = null;
	        int index = 0;
	        while (input3.hasNextLine()) { // While file3 has another line
	   			line = input3.nextLine();
	            matcher = pattern.matcher(line); // Match any <> tags
	            while (matcher.find()) { // While matcher finds any <> tags in the line
	               if ((input4 != null) && (index < tags.size())) { // If file4 exists and there is a word in tags
	                  line = line.replace(line.substring(matcher.start(), matcher.end()), tags.get(index)); // Replace <> with the word
	                  matcher = pattern.matcher(line); // Match next <>
	                  index++;
	               }
	               else if (input4 == null) { // If file4 not provided
	                  System.out.println("Enter a word: "); // Prompt user for word
	   				  line = line.replace(line.substring(matcher.start(), matcher.end()), kb.next()); // Replace <> with inputted word
	                  matcher = pattern.matcher(line);
	               }
	            }
	            output.println(line); // Print new story to output file
	   		}
     
		} catch (FileNotFoundException e) { // File not found or unable to be opened
			output.println("Part 3: Unable to Open File");
		}

	}

}

/*
	Received help from StackOverflow to figure out how to use a Pattern and Matcher with RegEx
*/