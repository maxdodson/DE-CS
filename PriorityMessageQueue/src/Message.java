/**
 * Represents a message with a specified priority level and arrival time
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/29/20
 * Priority Message Queue
 * 
 * @author Maxwell Dodson
 * 
 * @see MessagePriorityQueue
 * 
 */

public class Message {

	private int priority;
	private int arrival;
	
	/**
	 * Instantiates a Message with a given priority level and arrival time
	 * 
	 * @param priority the priority level assigned to this Message
	 * @param arrival the time when this Message arrived for processing
	 * 
	 */
	public Message(int priority, int arrival) {
		this.priority = priority;
		this.arrival = arrival;
	}
	
	/**
	 * Returns this Message's priority
	 * 
	 * @return the integer priority level
	 * 
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Returns this Message's arrival time
	 * 
	 * @return the integer arrival time
	 * 
	 */
	public int getArrivalTime() {
		return arrival;
	}
}
