/**
 * Represents a Priority Queue of Messages. Provides methods to add and remove from this
 * Priority Queue.
 * 
 * Maxwell Dodson
 * DE CS II
 * 3/29/20
 * Priority Message Queue
 * 
 * @author Maxwell Dodson
 * 
 * @see Message
 * 
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class MessagePriorityQueue {

	private ArrayList<LinkedList<Message>> queues;
	private static int time;
	private static final int PRIORITIES = 5;
	private static final int PROCESSING_TIME = 4;
	
	/**
	 * Instantiates a MessagePriorityQueue by preparing an ArrayList
	 * of LinkedLists to store Messages
	 */
	public MessagePriorityQueue() {
		queues = new ArrayList<LinkedList<Message>>(PRIORITIES);
		for (int i=0; i<PRIORITIES; i++) { // Instantiate all LinkedLists within queues
			queues.add(new LinkedList<Message>());
		}
	}

	/**
	 * Adds a new Message to this MessagePriorityQueue
	 * 
	 * @param m the message to add to the queue
	 */
	public void add(Message m) {
		int priority = m.getPriority();
		queues.get(priority).add(m);
	}
	
	/**
	 * Removes the highest priority Message in this MessagePriorityQueue
	 * 
	 * @return the Message that was removed
	 */
	public Message remove() {
		for (LinkedList<Message> list : queues) { // Iterate through the queues
			// Remove a message only if it has waited at least 4 iterations
			if (list.peek() != null && (time - PROCESSING_TIME) >= list.peek().getArrivalTime()) {
				return list.remove();
			}
		}
		return null;
	}
	
	/**
	 * Runs a simulation of a stream of Messages and lists average arrival times for each priority.
	 * 
	 * @param args if 2 specified, will set number of iterations and min. number of messages in queue
	 */
	public static void main(String[] args) {
		int iterations = 1000;
		int minMessages = 50;
		// Allow changing of default iterations and minMessages through command line args
		if (args.length == 2) {
			iterations = Integer.parseInt(args[0]);
			minMessages = Integer.parseInt(args[1]);
		}
		
		// Create an array of ArrayLists to store the arrival times of Messages
		ArrayList<Integer>[] arrivals = new ArrayList[PRIORITIES];
		for (int arr=0; arr<arrivals.length; arr++) {
			arrivals[arr] = new ArrayList<Integer>();
		}
		
		// Instantiate MessagePriorityQueue and simulate a stream of Messages
		MessagePriorityQueue queue = new MessagePriorityQueue();
		MessagePriorityQueue.time = 0;
		for (int i=0; i<iterations; i++) {
			// Generate a random Message
			int rand = (int)(Math.random() * PRIORITIES);
			queue.add(new Message(rand, time));
			
			// Begin removing Messages once the minimum amount is reached
			if (time >= minMessages) {
				Message msg = queue.remove();
				if (msg != null) {
					arrivals[msg.getPriority()].add(time - msg.getArrivalTime());
				}
			}
			time++; // Increment the current time
		}
		
		// Print the average arrival times of Messages of each priority level
		System.out.println("Iterations: " + iterations);
		System.out.println("Minimum to prime: " + minMessages);
		for (int i=0; i<PRIORITIES; i++) {
			int sum = 0;
			for (int j : arrivals[i]) { // Sum all the arrival times
				sum += j;
			}
			double avg = (double)sum / arrivals[i].size();
			System.out.printf("Average arrival time (Priority %d): %.3f\n", i, avg);
		}
	}
}
