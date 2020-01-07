/**
 * Processes inputted disks by stacking them by size.
 * 
 * @author Maxwell Dodson
 * 
 * Maxwell Dodson
 * DE CS II
 * 12/13/19
 * Production Line
 * 
 * @see Disk
 * @see Tower
 * 
 */
import java.util.Queue;
import java.util.LinkedList;

public class ProductionLine {
	
	private Queue<Disk> input;
	private Queue<Tower> output;
	private Tower arm;

	/**
	 * Instantiates a new Production Line
	 * 
	 */
	public ProductionLine() {
		input = new LinkedList<Disk>();
		output = new LinkedList<Tower>();
		arm = new Tower(false);
	}
	
	/**
	 * Adds a new Disk to the robot's input queue
	 * 
	 * @param disk the Disk to add to the input queue
	 */
	public void addDisk(Disk d) {
		input.add(d); // Add Disk d to the input queue
	}
	
	/**
	 * Unloads the robot's arm
	 * 
	 */
	public void unloadRobot() {
		arm.invert(); // Invert the Tower of disks on the robot's arm
		output.add(arm); // Add the Tower to the output queue
		arm = new Tower(false);
	}
	
	/**
	 * Processes disks by comparing their sizes and then adds them to the robot's arm
	 * 
	 */
	public void process() {
		while (!input.isEmpty()) {
			if (arm.isEmpty()) {
				arm.addDisk(input.remove()); // Add input Disk to arm if arm is empty
			}
			else {
				if (input.peek().compareTo(arm.peek()) >= 0) { // Add input Disk to arm if the new Disk is larger
					arm.addDisk(input.remove());
				}
				else {
					unloadRobot(); // If arm is not empty or new Disk is smaller, unload the arm and try again
				}
			}
		}
		unloadRobot(); // Unload robot once input queue is empty
	}
	
	/**
	 * Removes and returns the next Tower in the robot's output queue
	 * 
	 * @return the next Tower in the output queue
	 */
	public Tower removeTower() {
		return output.poll(); // Remove the next Tower from output
	}

}
