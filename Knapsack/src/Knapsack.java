/**
 * Finds the maximum sum of a given set of numbers. It also processes
 * an umbrella test file with individual test files listed inside.
 * 
 * Maxwell Dodson
 * DE CS II
 * 1/23/19
 * Knapsack
 * 
 * @author Maxwell Dodson
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
				
				ArrayList<File> files = new ArrayList<File>();
				while (input.hasNextLine()) { // Add each file listed inside to an ArrayList
					files.add(new File(input.nextLine()));
				}
				
				for (File f : files) { // Call knapsackSum() for each test file
					try {
						Scanner file = new Scanner(f);
						if (file.hasNext()) { // Process file if has contents
							int limit = Integer.valueOf(file.next()); // limit is first number in file
							
							ArrayList<Integer> nums = new ArrayList<Integer>();
							while (file.hasNext()) { // Add remaining weights in the file to nums
								nums.add(Integer.valueOf(file.next()));
							}
							int[] weights = new int[nums.size()];
							for (int i=0; i<weights.length; i++) { // Convert nums ArrayList to Array
								weights[i] = nums.get(i);
							}
							
							ArrayList<Integer> bestWeights = new ArrayList<Integer>();
							// Obtain best weights
							knapsackSum(weights, weights.length-1, limit, bestWeights);
							
							String weightsStr = listWeights(bestWeights);
							String weightsList = nums.toString();
							// Delete brackets in weightsList
							weightsList = weightsList.replaceAll("\\[", "").replaceAll("\\]", "");
							
							// Print output to file
							output.print(f + "\t" + limit + "\t" + weightsList + "\n\n");
							output.print(weightsStr + "\n\n");
							
						} else {
							continue; // Proceed to next file if this file is empty
						}
					} catch (IOException e) {
						continue; // Proceed to next file if filename is invalid
					}
				}
				output.close(); // Save and close PrintWriter
			} catch (FileNotFoundException e) {
			}
		} catch (IOException e) { // If file is invalid, prompt user for a new one
			Scanner kb = new Scanner(System.in);
			System.out.print("Invalid file. Please enter another: ");
			String newFile = kb.next();
			
			processFile(newFile); // Try processFile() again
		}
	}
	
	/**
	 * Formats weights into a String from the provided List.
	 * 
	 * @param weights a List of weights to format as a String
	 * @return the String list of weights
	 */
	public static String listWeights(List<Integer> weights) {
		if (weights.isEmpty()) {
			 return nonePossible + object + "s\n";
		}
		String str = "";
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
		if (args.length != 0) {
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
	 * @param w the array of weights to evaluate
	 * @param n the number of positive integers in weights
	 * @param limit the maximum possible value of the sum of the weights
	 * @return the integer sum of the optimal weights for the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit) {
		if (limit <= 0 || n < 0) { // Base Case: n has reached beginning of array or limit is 0
			return 0;
		}
		if (w[n] > limit) { // Skip w[n] if it is greater than limit
			return knapsackSum(w, n-1, limit);
		}
		
		int withLast = w[n] + knapsackSum(w, n-1, limit-w[n]); // Add w[n] and succeeding values
		int withoutLast = knapsackSum(w, n-1, limit); // Skip w[n] and only add succeeding values
		
		return Math.max(withLast, withoutLast); // Return the maximum sum of the weights
		
	}
	
	/**
	 * Recursively finds the maximum sum of a set of numbers, within a given limit.
	 * The optimal numbers are added to a list.
	 * 
	 * @param w the array of weights to evaluate
	 * @param n the number of positive integers in weights
	 * @param limit the maximum possible value of the sum of the weights
	 * @param list an empty List to contain the optimal weights
	 * @return the integer sum of the optimal weights for the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		if (limit <= 0 || n < 0) { // Base Case: n has reached beginning of array or limit is 0
			return 0;
		}
		if (w[n] > limit) { // Skip w[n] if it is greater than limit
			return knapsackSum(w, n-1, limit, list);
		}
		
		List<Integer> listWithLast = new ArrayList<Integer>();
		List<Integer> listWithoutLast = new ArrayList<Integer>();
		
		// Add w[n] and succeeding values
		int withLast = w[n] + knapsackSum(w, n-1, limit-w[n], listWithLast);
		listWithLast.add(w[n]);
		// Skip w[n] and only add succeeding values
		int withoutLast = knapsackSum(w, n-1, limit, listWithoutLast);

		if (withLast > withoutLast) { // Store optimal weights in list for whichever sum is greater
			list.addAll(listWithLast);
			return withLast;
		} else {
			list.addAll(listWithoutLast);
			return withoutLast;
		}
	}
}

// Franky helped me and Will understand the problem 
