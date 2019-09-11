/**
 * Represents a test class for becoming acquainted with Git, Eclipse, etc.
 * 
 * <p>
 * Runs a main program that reads in user values and prints various results.
 * </p>
 * 
 * @author Maxwell.Dodson
 * --maks
 * 
 */
public class Test {

	/**
	 * Prints various math operations computed with user-inputted values.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		java.util.Scanner kb = new java.util.Scanner(System.in);
        
		System.out.println("Enter a number: ");
		int num1 = kb.nextInt();
		
		System.out.println("Enter another number: ");
		int num2 = kb.nextInt();
		
		System.out.println("Sum: " + (num1 + num2));
		System.out.println("Difference: " + (num1 - num2));
		System.out.println("Product: " + (num1 * num2));
		System.out.println("Quotient: " + (num1 / num2));
		System.out.println("Modulus: " + (num1 % num2));
		
		kb.close();

	}

}
