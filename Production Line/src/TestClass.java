/**
 * Represents a test class for ProductionLine.
 * 
 * @author Maxwell Dodson
 * 
 * @see ProductionLine
 * @see Tower
 * @see Disk
 * 
**/
public class TestClass {
	
	/**
	 * Adds a new Disk to the robot's input queue
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		// Assertions
		Tower regular = new Tower(true);
		Tower inverted = new Tower(false);
		Disk d1 = new Disk(10);
		Disk d2 = new Disk(5);
		assert d1.compareTo(d2) == d1.getRadius() - d2.getRadius();
		
		regular.addDisk(d1);
		inverted.addDisk(d1);
		regular.addDisk(d2);
		inverted.addDisk(d2);
		
		assert regular.isEmpty() == false;
		assert inverted.isEmpty() == false;
		assert regular.peek() ==  d2;
		assert regular.pop() == d2;
		assert inverted.peek() != d2; // Ensure inverted Towers only add "larger" Disks
		assert inverted.pop() == d1;
		
		inverted.invert();
		inverted.addDisk(d2);
		assert inverted.peek() == d2; // Ensure invert() inverts inverted, allowing "smaller" Disks to be added
		
		
		// ProductionLine
		ProductionLine pLine = new ProductionLine();
		
		Disk[] disks = new Disk[15];
		for (int i=0; i<disks.length; i++) {
			disks[i] = new Disk((int)(Math.random() * 10) + 1); // Generate Disks of random radius (1-10)
			pLine.addDisk(disks[i]);
			System.out.println(disks[i]);
		}
		
		System.out.println("---------------------\n");
		
		pLine.process(); // Process all the Disks
		
		Tower out = pLine.removeTower();
		while (out != null) { // Remove each Tower and print its contents
			System.out.println(out); 
			out = pLine.removeTower();
		}
		
	}
}