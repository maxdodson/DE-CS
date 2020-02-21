import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {
	
	private static String ADD = "+";
	private static String MULTIPLY = "*";

	public ExpressionTree(Object initValue) {
		super(initValue);
	}
	
	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}

	public ExpressionTree buildTree(String[] exp) {
		Stack<ExpressionTree> stk = new Stack<ExpressionTree>();
		for (int i=0; i<exp.length; i++) {
			if (exp[i].equals(ADD) || exp[i].equals(MULTIPLY)) {
				// Create new ExpressionTree for each operator
				ExpressionTree newTree = new ExpressionTree(exp[i]);
				newTree.setLeft(stk.pop()); // Set left node to next on stack
				newTree.setRight(stk.pop()); // Set right node to next on stack
				
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

	public int evalTree() {
		return evalTree(this);
	}
	
	private int evalTree(ExpressionTree tree) {
		if (tree == null) { // Base case
			return 0;
		}
		if (tree.getValue().equals(ADD)) {
			return evalTree((ExpressionTree)tree.getLeft()) + evalTree((ExpressionTree)tree.getRight());
		}
		if (tree.getValue().equals(MULTIPLY)) {
			return evalTree((ExpressionTree)tree.getLeft()) * evalTree((ExpressionTree)tree.getRight());
		}
	}

	public String toPrefixNotation() {
		String str = "";
		str += this.getValue();
		if (!(this.getLeft() == null))
			str += ((ExpressionTree)this.getLeft()).toPrefixNotation();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toPrefixNotation();
		
		return str;
	}

	public String toInfixNotation() {
		String str = "";
		if (!(this.getLeft() == null))
			str += "(" + ((ExpressionTree)this.getLeft()).toInfixNotation();
		str += this.getValue();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toInfixNotation() + ")";
		
		return str;
	}

	public String toPostfixNotation() {
		String str = "";
		if (!(this.getLeft() == null)) 
			str += ((ExpressionTree)this.getLeft()).toPostfixNotation();
		if (!(this.getRight() == null))
			str += ((ExpressionTree)this.getRight()).toPostfixNotation();
		
		str += this.getValue();
		return str;
	}

	public int postfixEval(String[] exp) {
		ExpressionTree tree = this.buildTree(exp);
		return tree.evalTree();
	}
	
	public static void toString(TreeNode node) {
		if (node == null) {
		      return;
	    }
		
	    toString(node.getLeft());
	    System.out.print(node.getValue() + " ");
	    toString(node.getRight());
	}

}
