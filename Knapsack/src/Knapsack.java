import java.util.*;
import java.io.*;

public class Knapsack {

	public static final String object = "watermelons";
	public static final String unit = "pound";
	public static final String nonePossible = "No possible watermelons";
	
	public static void processFile(String fileName) {
	
		File in = new File(fileName);
		Scanner input = new Scanner(in);
		
		File out = new File("knapsack.txt");
		try {
			PrintWriter output = new PrintWriter(out);
			
			ArrayList<File> files;
			while (input.hasNextLine()) {
				files.add(new File(input.nextLine()));
			}
			
			for (File f : files) {
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
				
			}
			
		} catch (FileNotFoundException e) {
		}
		
		
		
		
	}
	
	public static void listWeights(List<Integer> weights) {
		String str;
		if (weights.isEmpty()) {
			str += 
		}
	}
	
	
	public static void main(String[] args) {
		
		int[] stuff = new int[] { 1, 2, 3, 4, 5};
		int[] stuff2 = new int[] { 8, 16, 20 };
		int[] stuff3 = new int[] { 8 };
		int[] stuff4 = new int[] { 8, 8 };
		int[] stuff5 = new int[] {1, 2, 3, 4, 5};
		
		//int num = knapsackSum(stuff, stuff.length, 15);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		int num = knapsackSum(stuff5, stuff5.length, 6, list);

		System.out.println(list.toString());
		System.out.println(num);
		
	}
	
	public static int knapsackSum(int[] w, int n, int limit) {
		
		if (limit <= 0 || n <= 0) {
			return 0;
		}
		
		if (w[n-1] > limit) {
			return knapsackSum(w, n-1, limit);
		}
		
		int withLast = w[n-1] + knapsackSum(w, n-1, limit-w[n-1]);
		int withoutLast = knapsackSum(w, n-1, limit);
		
		return Math.max(withLast, withoutLast);
		
	}
	
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		
		if (limit <= 0 || n <= 0) {
			return 0;
		}
		
		if (w[n-1] > limit) { // Skip w[n-1] if it's greater than limit
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
		list.add(w[n-1]);
		return Math.max(withLast, withoutLast);

	
	}

}

// I used StackOverflow to learn how to use ArrayList.toArray()
// Franky also helped me and Will understand the problem 
