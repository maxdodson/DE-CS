/**
 * 
 * 
 * @author Maxwell.Dodson
 * 
 * --maks
 */
import java.io.*;
import java.util.Scanner;

public class ReadAndWrite {

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Take in 3-4 command line arguments
		
		// Open first file user inputs
		// If error
		// 	Print "Part 1: Unable to Open File"
		
		// Count num of open braces and close braces
		// If openBraces == closeBraces
		// 	Write "Braced Balanced" in output file
		// Else
		// 	Write "Braces Not Balanced" in output file
		
		// Write "\n" to output file
		
		// Open second file user inputs
		// If error
		// 	Print "Part 2: Unable to Open File"
		
		// If second file indentical to first file 
		// 	Write "Files Identical" in output file
		// Else 
		// 	Write "Files Not Identical" in output file
		
		// Write "\n" to output file
		
		// Open third file user inputs
		// If error
		// 	Print "Part 3: Unable to Open File"
		
		// Count num of <...> tags
		
		// If fourth command line argument
		// 	Open file
		// 	Save num words in file in ArrayList
		// Else
		// 	Prompt user to provide num words
		// 	Save inputted words in ArrayList
		
		// Copy file to output file, replacing <> with words
		
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter filename: ");
		String fname1 = kb.next();

		System.out.print("Enter next filename: ");
		String fname2 = kb.next();

		System.out.print("Enter final filename(s): ");
		String fname3 = kb.nextLine();
		
		File in = new File(fname1);
		File out = new File("output.txt");
		PrintWriter output = new PrintWriter(out);
		Scanner input1 = null;

		try {
			input1 = new Scanner(in);
		} catch (FileNotFoundException e) {
			System.out.println("Part 1: Unable to Open File")
			System.exit(1);
		}

		int numOpenBraces = 0;
		int numCloseBraces = 0;

		while (input1.hasNext()) {
			if (input1.next().contains('{')) {
				numOpenBraces++;
			}
			if (input1.next().contains('}')) {
				numCloseBraces++;
			}
		}

		if (numOpenBraces == numCloseBraces) {
			output.println("Braces Balanced\n");
		}
		else {
			output.println("Braces Not Balanced\n");
		}

		File file2 = new File(fname2);
		Scanner input2 = null;

		try {
			input2 = new Scanner(file2);
		} catch (FileNotFoundException e) {
			System.out.println("Part 2: Unable to Open File")
			System.exit(1);
		}

		input1.useDelimiter("."); // Match all characters in file1
		String file1 = "";
		while (input1.hasNext()) {
			file1 += input1.next();
		}

		input2.useDelimiter("."); // Match all characters in file2
		String file2 = "";
		while (input2.hasNext()) {
			file2 += input2.next();
		}

		if (file1.equals(file2)) {
			output.println("Files Identical\n");
		}
		else {
			output.println("Files Not Identical\n");
		}

		// Split fname3 if two pathnames
		File file3 = new File(fname3);
		Scanner input3 = null;

		try {
			input3 = new Scanner(file3);
		} catch (FileNotFoundException e) {
			System.out.println("Part 3: Unable to Open File")
			System.exit(1);
		}

		input3.useDelimiter("<.+>") // Match all <...> tags

	}

}