
public class Die {
	
	private int faces;
	private int dots;

	public Die(int faces) {
		this.faces = faces;
		this.dots = 6;
	}

	/**
	 * @return the faces
	 */
	public int getFaces() {
		return faces;
	}

	/**
	 * @param faces the faces to set
	 */
	public void setFaces(int faces) {
		this.faces = faces;
	}

	/**
	 * @return the dots
	 */
	public int getDots() {
		return dots;
	}

	/**
	 * @param dots the dots to set
	 */
	public void setDots(int dots) {
		this.dots = dots;
	}
	
	public void roll() {
		dots = (int)(Math.random()*faces + 1);
	}
	
	public static void main(String[] args) {
		int oneFaces, twoFaces, numRolls;
		
		oneFaces = (args.length>0) ? Integer.parseInt(args[0]) : 6;
		twoFaces = (args.length>1) ? Integer.parseInt(args[1]) : 12;
		numRolls = (args.length>2) ? Integer.parseInt(args[2]) : 10;
		
		Die one = new Die(oneFaces);
		Die two = new Die(twoFaces);
		
		for (int roll=1; roll<=numRolls; roll++) {
			System.out.println(one + " and " + two);
			one.roll();
			two.roll();
		}
	}

	@Override
	public String toString() {
		return "Die [faces=" + faces + ", dots=" + dots + "]";
	}
	

}
