/**
 * Finds the maximum sum of a given set of numbers. It also processes
 * an umbrella test file with individual test files listed inside.
 * 
 * @author Maxwell Dodson
 * 
 * Maxwell Dodson
 * DE CS II
 * 1/23/19
 * Knapsack
 * 
 */

import java.util.*;
import java.io.*;

public class Knapsack {

	public static final String object = "watermelon";
	public static final String unit = "pound";
	public static final String nonePossible = "No possible ";
	public static final String outputFile = "knapsack.txt";
	
	/**
	 * Processes a file by running Knapsack with each listed test file and
	 * prints the results to an output file. 
	 * 
	 * @param fileName the path of the umbrella test file
	 */
	public static void processFile(String fileName) {
	
		File in = new File(fileName); // Attempt to find provided file
		try {
			Scanner input = new Scanner(in);
			
			File out = new File(outputFile); // Create output file
			try {
				PrintWriter output = new PrintWriter(out);
				
				ArrayList<File> files;
				while (input.hasNextLine()) { // Add each file listed inside to an ArrayList
					files.add(new File(input.nextLine()));
				}
				
				for (File f : files) { // Call knapsackSum() for each test file
					try {
						Scanner file = new Scanner(f);
						int limit = Integer.valueOf(file.next()); // limit is first number in file
						
						ArrayList<Integer> nums = new ArrayList<Integer>();
						while (file.hasNext()) { // Add remaining numbers (weights) in file to nums
							nums.add(Integer.valueOf(file.next()));
						}
						Integer[] weights = nums.toArray(new Integer[0]); // Convert nums to int Array
						
						ArrayList<Integer> bestWeights = new ArrayList<Integer>();
						knapsackSum(weights, weights.length, limit, bestWeights); 
						
						String weightsStr = listWeights(bestWeights);
						String weightsList = bestWeights.replaceAll("\\[", "").replaceAll("\\]",""); // Delete []
						
						output.print(f + "\t" + limit + "\t" + weightsList + "\n");
						output.print(weightsStr + "\n\n");
						
					} catch (IOException e) {
						continue; // Proceed to next file if invalid filename
					}
				}
			} catch (FileNotFoundException e) {
			}
		} catch (IOException e) { // If file is invalid, prompt user for a new one
			Scanner kb = new Scanner(System.in);
			
			System.out.print("Invalid file. Please enter another: ");
			String newFile = kb.next();
			
			processFile(newFile); // Try to processFile() again
		}

	}
	
	/**
	 * Formats weights into a String from the provided List.
	 * 
	 * @return the String list of weights
	 */
	public static String listWeights(List<Integer> weights) {
		if (weights.isEmpty()) {
			 return nonePossible + object + "s";
		}
		
		String str;
		for (Integer i : weights) {
			str += i + " " + unit + " " + object + "\n";
		}
		return str;
	}
	
	/**
	 * Tests Knapsack by passing in a file with various tests.
	 * 
	 * @param args command line arguments; may be a filename
	 */
	public static void main(String[] args) {
		
		int[] stuff = new int[] { 1, 2, 3, 4, 5};
		int[] stuff2 = new int[] { 8, 16, 20 };
		int[] stuff3 = new int[] { 8 };
		int[] stuff4 = new int[] { 8, 8 };
		int[] stuff5 = new int[] {1, 2, 3, 4, 5};
		
		if (args != null) {
			processFile(args[0]); // Process file provided in command line arguments
		}
		else { // Prompt user for filename if one is not provided
			Scanner kb = new Scanner(System.in);
			System.out.print("Enter a filename: ");
			processFile(kb.next());
		}
	}
	
	/**
	 * Recursively finds the maximum sum of a set of numbers, within a given limit.
	 * 
	 * @return the integer sum of the optimal weights for the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		
		if (limit <= 0 || n <= 0) { // Base Case: n has reached beginning of array or limit is 0
			return 0;
		}
		
		if (w[n-1] > limit) { // Skip w[n-1] if it is greater than limit
			return knapsackSum(w, n-1, limit);
		}
		
		int withLast = w[n-1] + knapsackSum(w, n-1, limit-w[n-1]); // Add w[n-1] and succeeding values
		int withoutLast = knapsackSum(w, n-1, limit); // Skip w[n-1] and only add succeeding values
		
		return Math.max(withLast, withoutLast); // Return the maximum sum of the weights
		
	}
	
	/**
	 * Recursively finds the maximum sum of a set of numbers, within a given limit.
	 * The optimal numbers are added to a list.
	 * 
	 * @return the integer sum of the optimal weights for the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		
		if (limit <= 0 || n <= 0) { // Base Case: n has reached beginning of array or limit is 0
			return 0;
		}
		
		if (w[n-1] > limit) { // Skip w[n-1] if it is greater than limit
			return knapsackSum(w, n-1, limit, list);
		}
		
		int withLast = w[n-1] + knapsackSum(w, n-1, limit-w[n-1], list); // Add w[n-1] and succeeding values
		int withoutLast = knapsackSum(w, n-1, limit); // Skip w[n-1] and only add succeeding values

		/*
		if (withLast >= withoutLast) {
			list.add(w[n-1]);
			return withLast;
		}
		else {
			return withoutLast;
		}*/
		list.add(w[n-1]); // Add the current best weight to the list
		return Math.max(withLast, withoutLast); // Return the maximum sum of the weights
	}

}

// I used StackOverflow to learn how to use ArrayList.toArray()
// Franky also helped me and Will understand the problem 
