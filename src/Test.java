/**
 * 
 * 
 * @author Maxwell.Dodson
 * 
 * MadMax
 */
public class Test {

	/**
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

	}

}
