
public class TestClass {

	public TestClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExpressionTree tree = new ExpressionTree("+");
		TreeNode t = tree.buildTree(new String[] {"3", "4", "+"});
		
		ExpressionTree.toString(t);
	}

}
