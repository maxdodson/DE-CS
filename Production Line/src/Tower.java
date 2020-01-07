/**
 * Represents a Tower - an abstract stack of Disks
 * 
 * @author Maxwell Dodson
 * 
 * @see ProductionLine
 * @see Disk
 * 
**/
import java.util.Stack;
import java.util.Iterator;

public class Tower extends Stack<Disk> {
	
	private Stack<Disk> disks;
	private boolean regular;

	/**
	 * Instantiates a new default Tower in regular order
	 * 
	 * @param radius the radius of the new Disk 
	 */
	public Tower() {
		disks = new Stack<Disk>();
		regular = true;
	}
	
	/**
	 * Instantiates a Tower in either regular or inverted order
	 * 
	 * @param regular true if regular order, false if inverted order
	 */
	public Tower(boolean regular) {
		disks = new Stack<Disk>();
		this.regular = regular;
	}
	
	/**
	 * Adds a new Disk to this Tower
	 * 
	 * @param Disk the new Disk to add to this Tower's Stack
	 */
	public void addDisk(Disk d) { // Add disk if it fits model
		if (regular) {
			if (disks.isEmpty() || d.compareTo(disks.peek()) <= 0) { // If regular, new Disk must be "smaller"
				disks.push(d);
			}
		}
		else {
			if (disks.isEmpty() || d.compareTo(disks.peek()) >= 0) { // If inverted, new Disk must be "larger"
				disks.push(d);
			}
		}
	}
	
	/**
	 * Inverts this Tower by reversing the order of each Disk
	 * 
	 */
	public void invert() {
		Stack<Disk> temp = new Stack<Disk>();
		while (!disks.isEmpty()) {
			temp.push(disks.pop()); // Store elements in reverse order in temporary Stack
		}
		disks = temp;
		regular = !regular; // Change whether Tower is regular or inverted
	}
	
	/**
	 * Returns the next Disk in this Tower's disks Stack
	 * 
	 * @return the next Disk in this Tower
	 */
	public Disk peek() {
		return disks.peek();
	}
	
	/**
	 * Returns and removes the next Disk in this Tower's disks Stack
	 * 
	 * @return the next Disk in this Tower
	 */
	public Disk pop() {
		return disks.pop();
	}
	
	/**
	 * Returns whether this Tower is empty
	 * 
	 * @return true if this Tower is empty, false otherwise
	 */
	public boolean isEmpty() {
		return disks.isEmpty();
	}
	
	/**
	 * Returns the String representation of this Tower
	 * 
	 * @return this Tower as a String. Includes regular or inverted order and the String representation of each Disk in this Tower.
	 */
	public String toString() {
		String reg; 
		if (regular)
			reg = "Regular";
		else
			reg = "Inverted";
		
		String result = "(Tower - " + reg + ")\n";
		for (int i=disks.size()-1; i>=0; i--) {
			result += disks.get(i).toString();
		}
		
		return result;
	}

}
