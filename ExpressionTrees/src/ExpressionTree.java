/**
 * Builds and evaluates ExpressionTrees and expressions
 * 
 * Maxwell Dodson
 * DE CS II
 * 2/14/20
 * Expression Trees
 * 
 * @author Maxwell Dodson
 * 
 * @see Expressions
 * @see TestClass
 * 
 */

import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {
	
	private static String ADD = "+";
	private static String MULTIPLY = "*";

	/**
	 * Instantiates a new Expression Tree
	 * 
	 * @param initValue the initial node value
	 */
	public ExpressionTree(Object initValue) {
		super(initValue);
	}
	
	/**
	 * Instantiates a new Expression Tree with children
	 * 
	 * @param initValue the initial node value
	 * @param initLeft the left node's value
	 * @param initRight the right node's value
	 */
	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}

	/**
	 * Generates an Expression Tree from an expression
	 * 
	 * @param exp a String array of the operators and operands
	 * @return a tree representing the expression
	 */
	public static ExpressionTree buildTree(String[] exp) {
		Stack<ExpressionTree> stk = new Stack<ExpressionTree>();
		for (int i=0; i<exp.length; i++) {
			if (exp[i].equals(ADD) || exp[i].equals(MULTIPLY)) {
				// Create new ExpressionTree for each operator
				ExpressionTree newTree = new ExpressionTree(exp[i]);
				newTree.setRight(stk.pop()); // Set left node to next on stack
				newTree.setLeft(stk.pop()); // Set right node to next on stack
				
				stk.push(newTree); // Push newTree to stack
			}
			else { // If exp[i] is a number
				// Create new ExpressionTree with value cast as Integer
				ExpressionTree newTree = new ExpressionTree(Integer.parseInt(exp[i]));
				stk.push(newTree); // Push newTree to stack
			}
		}
		return stk.pop(); // Return the final ExpressionTree
	}

	/**
	 * Evaluates this ExpressionTree by calling the recursive function
	 * 
	 * @return the integer result of the expression
	 */
	public int evalTree() {
		return evalTree(this);
	}
	
	/**
	 * Evaluates this ExpressionTree mathematically and returns the result
	 * 
	 * @param tree the ExpressionTree to evaluate
	 * @return the integer result of the expression
	 */
	private int evalTree(ExpressionTree tree) {
		if (tree == null) { // Base case
			return 0;
		}
		
		// Recursively evaluate each subtree
		if (tree.getValue().equals(ADD)) {
			return evalTree((ExpressionTree)tree.getLeft()) + evalTree((ExpressionTree)tree.getRight());
		}
		if (tree.getValue().equals(MULTIPLY)) {
			return evalTree((ExpressionTree)tree.getLeft()) * evalTree((ExpressionTree)tree.getRight());
		}
		// If node is not ADD or MULTIPLY, return the integer value
		return (Integer)tree.getValue();
	}

	/**
	 * Converts this ExpressionTree to prefix notation
	 * 
	 * @return the String representation of this tree in prefix notation
	 */
	public String toPrefixNotation() {
		String str = "";
		str += this.getValue() + " ";
		if (!(this.getLeft() == null))
			str += ((ExpressionTree)this.getLeft()).toPrefixNotation();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toPrefixNotation();
		
		return str;
	}

	/**
	 * Converts this ExpressionTree to infix notation
	 * 
	 * @return the String representation of this tree in infix notation
	 */
	public String toInfixNotation() {
		String str = "";
		if (!(this.getLeft() == null))
			str += "(" + ((ExpressionTree)this.getLeft()).toInfixNotation();
		str += this.getValue();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toInfixNotation() + ")";
		
		return str;
	}

	/**
	 * Converts this ExpressionTree to postfix notation
	 * 
	 * @return the String representation of this tree in postfix notation
	 */
	public String toPostfixNotation() {
		String str = "";
		if (!(this.getLeft() == null)) 
			str += ((ExpressionTree)this.getLeft()).toPostfixNotation();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toPostfixNotation();
		
		str += this.getValue() + " ";
		return str;
	}

	/**
	 * Evaluates a postfix expression
	 * 
	 * @param exp a String array of the operators and operands
	 * @return the integer result of the expression
	 */
	public int postfixEval(String[] exp) {
		// Create an ExpressionTree with the expression and evaluate it
		ExpressionTree tree = buildTree(exp);
		return tree.evalTree();
	}
}
