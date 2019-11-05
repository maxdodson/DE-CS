/**
 * Represents a program that compiles an index of a given file. Performs all the file reading & writing, and passing to other classes.
 * 
 * @author Maxwell Dodson
 * 
 * @see DocumentIndex
 * @see IndexEntry
 * 
**/

import java.io.*;
import java.util.Scanner;

public class IndexMaker {

	private static String inputName, outputName;
	
	/**
	 * Performs the file reading & writing, and creates the DocumentIndex for a file.
	 * 
	 * @param args command line arguments (optional) specifying one or both files
	 * 
	**/
	public static void main(String[] args) {
		if (args != null) {
			parseFiles(args);  // Parse args if passed by user
		}
		else { // If no args passed, prompt user for two files
			Scanner kb = new Scanner(System.in);
			System.out.print("Enter two files: ");
			String[] filenames = kb.next().split(" "); // Split args into two filenames and parse them
			parseFiles(filenames);
		}
		
		DocumentIndex index = new DocumentIndex();
		
		// Access the files to be read and written
		File in = new File(inputName);
		File out = new File(outputName);
		try {
			PrintWriter output = new PrintWriter(out);
			Scanner inputFile = new Scanner(in);
			
			int line = 1;
			while (inputFile.hasNextLine()) { // Read inputFile line by line and add all words from each line to the DocumentIndex
				index.addAllWords(inputFile.nextLine(), line);
				line++;
			}
			
			output.print(index.toString()); // Save the index of the document to the output file
			output.close(); // Close PrintWriter
			
		} catch (FileNotFoundException e) {
		}
	}
	
	/**
	 * Parses an array of Strings to separate user-provided file names
	 * 
	 * @param filenames array of Strings
	 * 
	**/
	private static void parseFiles(String[] filenames) {
		inputName = filenames[0];
		if (filenames.length > 1) { // If user provided two files, input is first and output is second
			outputName = filenames[1];
		} else { // If user did not provide two files, use input file name to create output file
			String name = filenames[0];
			if (name.contains(".")) { // If the file has an extension, preserve it when "Index" is added to file name
				outputName = name.substring(0, name.lastIndexOf(".")) + "Index" + name.substring(name.lastIndexOf(".")); // Find last period (extension) and insert "Index" before it
			} else { // If the file does not have an extension, simply add "Index" to file name
				outputName = filenames[0] + "Index";
			}
		}
	}
}
