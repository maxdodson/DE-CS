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
		arm = new Tower();
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
		arm = new Tower();
	}
	
	/**
	 * Processes disks by comparing their sizes and then adds them to the robot's arm
	 * 
	 */
	public void process() {
		if (input.isEmpty()) { // Unload robot and end if input queue is empty
			unloadRobot();
			return;
		}
		
		if (arm.isEmpty() || input.peek().compareTo(arm.peek()) > 0) {
			arm.addDisk(input.remove()); // Add input disk to arm if arm is empty or the new disk is larger
			process();
		}
		else {
			unloadRobot(); // If arm is not empty or new disk is smaller, unload the arm and try again
			process();
		}
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
