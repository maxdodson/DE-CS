/**
 * 
 * 
 * @author Maxwell.Dodson
 * #qwerty:P
 * MadMax
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		java.util.Scanner kb = new java.util.Scanner(System.in);
        
		System.out.println("Enter a number with a numerical value: ");
		int num1 = kb.nextInt();
		
		System.out.println("Enter another number that has a digit in it: ");
		int num2 = kb.nextInt();
		
		System.out.println("The summation of these digits is: " + (num1 + num2));

	}

}
