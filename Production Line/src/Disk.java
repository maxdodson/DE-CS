/**
 * Represents a Disk - an abstract, comparable disk with a radius
 * 
 * @author Maxwell Dodson
 * 
 * @see ProductionLine
 * @see Tower
 * 
**/
public class Disk implements Comparable<Disk> {

	private int radius;
	
	/**
	 * Instantiates a new Disk of a certain radius
	 * 
	 * @param the radius of the new Disk 
	 */
	public Disk(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Compares this Disk and another Disk by radius
	 * 
	 * @param other the other Disk to compare to this Disk
	 * @return the integer difference between this Disk's radius and other Disk's radius
	 */
	public int compareTo(Disk other) {
		return this.radius - other.getRadius();
	}
	
	/**
	 * Returns the radius of this Disk
	 * 
	 * @return this Disk's radius
	 */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Returns the String representation of this Disk
	 * 
	 * @return this Disk as a String, including radius value
	 */
	public String toString() {
		return "(Disk)\n  " + radius + "\n";
	}

}
