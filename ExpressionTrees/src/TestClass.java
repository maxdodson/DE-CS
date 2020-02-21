/**
 * Tests the ExpressionTree class
 * 
 * Maxwell Dodson
 * DE CS II
 * 2/14/20
 * Expression Trees
 * 
 * @author Maxwell Dodson
 * 
 * @see ExpressionTree
 * @see Expressions
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {
	
	private static final String filename = "postFixExpressions.txt";
	private static final String myFilename = "Max_Expressions.txt";

	public static void main(String[] args) {
		if (args.length != 0) {
			processFile(args[0]); // Process file provided in command line arguments
		}
		else { // Use default filename if one is not provided
			processFile(filename);
		}
	}
	
	/**
	 * Processes a file by generating ExpressionTrees for expressions
	 * and evaluating them in various ways.
	 * 
	 * @param fileName the path of the file
	 */
	public static void processFile(String fileName) {
		File in = new File(fileName); // Attempt to find provided file
		try {
			Scanner input = new Scanner(in);
			File out = new File(myFilename); // Create output file
			try {
				PrintWriter output = new PrintWriter(out);
				
				ArrayList<String> expressionsList = new ArrayList<String>();
				while (input.hasNextLine()) { // Add each expression in the file to an ArrayList
					String line = input.nextLine().trim();
					if (!line.equals("")) // Only add non-empty lines
						expressionsList.add(line);
				}
				input.close();
				// Convert expressions ArrayList to Array
				String[] expressions = new String[expressionsList.size()];
				for (int i=0; i<expressions.length; i++) {
					expressions[i] = expressionsList.get(i);
				}
				// Process expressions
				for (String str : expressions) {
					String[] arr = str.split("\\s+"); // Split string on spaces
					ExpressionTree tree = ExpressionTree.buildTree(arr); // Create Expression Tree
					output.println("Eval: " + tree.evalTree()); // evalTree()
					output.println("Prefix: " + tree.toPrefixNotation()); // Prefix
					output.println("Infix: " + tree.toInfixNotation()); // Infix
					output.println("Postfix: " + tree.toPostfixNotation()); // Postfix
					output.println("Postfix Eval: " + tree.postfixEval(arr)); // Postfix Eval
					output.print("\n\n"); // Two blank lines
				}
				output.close(); // Save and close PrintWriter
			} catch (FileNotFoundException e) {
			}
		} catch (IOException e) { // If file is invalid, prompt user for another one
			Scanner kb = new Scanner(System.in);
			System.out.print("Please enter a filename: ");
			String newFile = kb.next();			
			
			processFile(newFile); // Try processFile() again
			kb.close();
		}
	}
}
// I used StackOverflow to learn about split() and trim()
