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
		
		String fname1 = "";
		String fname2 = "";
		String[] fnames = new String[2];
		kb = new Scanner(System.in);
		
		if (args.length == 4) {
			fname1 = args[0];
			fname2 = args[1];
			fnames[0] = args[2];
			fnames[1] = args[3];
		}
		else if (args.length == 3) {
			fname1 = args[0];
			fname2 = args[1];
			fnames[0] = args[2];
		}
		else if (args.length == 2) {
			fname1 = args[0];
			fname2 = args[1];
			System.out.print("Enter a story file: ");
			fnames[0] = kb.next();
		}
		else if (args.length == 1) {
			fname1 = args[0];
			System.out.print("Enter a file to compare: ");
			fname2 = kb.next();
			System.out.print("Enter a story file: ");
			fnames[0] = kb.next();
		}
		else if (args.length == 0) {
			System.out.print("Enter a file to test braces: ");
			fname1 = kb.next();
			System.out.print("Enter a file to compare: ");
			fname2 = kb.next();
			System.out.print("Enter a story file: ");
			fnames[0] = kb.next();
		}
		
		File out = new File("output.txt");
		try {
			output = new PrintWriter(out);
		} catch (FileNotFoundException e) {
		}
		
		testBracesBalanced(fname1);
		testIdenticalFiles(fname1, fname2);
		fillStory(fnames);
		
		output.close();
	    kb.close();
		
	}
		
	/**
	 * Tests whether braces in a file are balanced
	 * 
	 * @param filename file path
	 */
	public static void testBracesBalanced(String filename) {
		
		File in = new File(filename);
		Scanner input1 = null;
      
		try {
			input1 = new Scanner(in);
         
	        int numOpenBraces = 0;
	   		int numCloseBraces = 0;
	   
	   		while (input1.hasNext()) {
	            String next = input1.next();
	   			if (next.indexOf('{') != -1) {
	   				numOpenBraces++;
	   			}
	   			if (next.indexOf('}') != -1) {
	   				numCloseBraces++;
	   				if (numCloseBraces > numOpenBraces) { // If } before {, braces will never be balanced
	   					output.println("Braces Not Balanced\n");
	   					return;
	   				}
	   			}
	   		}

	   		if (numOpenBraces == numCloseBraces) {
	   			output.println("Braces Balanced\n");
	   		}
	   		else {
	   			output.println("Braces Not Balanced\n");
	   		}
		} catch (FileNotFoundException e) {
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
			input1 = new Scanner(file1);
			input2 = new Scanner(file2);
         
			String file1Str = "";
			while (input1.hasNext()) {
				file1Str += input1.next();
			}
   
	   		String file2Str = "";
	   		while (input2.hasNext()) {
	   			file2Str += input2.next();
	   		}
	   
	   		if (file1Str.equals(file2Str)) {
	   			output.println("Files Identical\n");
	   		}
	   		else {
	   			output.println("Files Not Identical\n");
	   		}

		} catch (FileNotFoundException e) {
			output.println("Part 2: Unable to Open File");
		}
	}
	
	/**
	 * Modifies a story in a text file, replacing <> tags with words
	 * 
	 * @param fnames an array of filenames
	 */
	public static void fillStory(String[] fnames) {
		
		File file3 = new File(fnames[0]);
		Scanner input3 = null;
		
		Scanner kboard = new Scanner(System.in); //
		String result = ""; //

		try {
			input3 = new Scanner(file3);
         
	   		ArrayList<String> tags = new ArrayList<String>();
	        Scanner input4 = null;
         
         
	        if (fnames.length != 1) {
	        	File file4 = new File(fnames[1]);
   
	   			try {
	   				input4 = new Scanner(file4);
	   			} catch (FileNotFoundException e) {
	   				output.println("Part 3: Unable to Open File");
	   			}
	   			
	   			while (input4.hasNext()) {
	   				tags.add(input4.next());
	   			}
	        }
   		
	        String story = "";
	        Pattern pattern = Pattern.compile("<(.+?)>", Pattern.DOTALL);
	        Matcher matcher = null;
	        int index = 0;
	        
	        while (input3.hasNextLine()) {
	   			story += input3.nextLine() + "\n";
	        }
            matcher = pattern.matcher(story);
            String line = "";
            while (matcher.find()) {
               if ((input4 != null) && (index < tags.size())) {
            	   line = story.substring(matcher.start(), matcher.end());
            	   if (line.contains("\n")) {
                 	  story = matcher.replaceFirst(tags.get(index) + "\n");
                   }
                   else {
                 	  story = matcher.replaceFirst(tags.get(index));
                   }
            	   matcher = pattern.matcher(story);
            	   index++;
               }
               else if (input4 == null) {
                  System.out.println("Enter a " + story.substring(matcher.start(), matcher.end()) + ": ");
                  line = story.substring(matcher.start(), matcher.end());
                  if (line.contains("\n")) {
                	  story = matcher.replaceFirst(kboard.next() + "\n");
                  }
                  else {
                	  story = matcher.replaceFirst(kboard.next());
                  }
                  matcher = pattern.matcher(story);
               }
            }
            output.println(story);
		} catch (FileNotFoundException e) {
			output.println("Part 3: Unable to Open File");
		}

	}

}

/*
	Received help from StackOverflow to figure out how to use a Pattern and Matcher with RegEx
*/