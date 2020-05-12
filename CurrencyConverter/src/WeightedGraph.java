/**
 * Implements a Weighted Graph. The Edge class connects each
 * node and the Graph class stores each Edge. 
 * 
 * Maxwell Dodson
 * DE CS II
 * 5/11/20
 * Currency Converter
 * 
 * @author Maxwell Dodson
 * 
 * @see CurrencyConverter
 * 
 */
import java.util.ArrayList;

public class WeightedGraph {
	
	static class Edge {
		private String origin;
		private String destination;
		private double weight;
		
		/**
		 * Instantiates a weighted Edge between two nodes
		 * 
		 * @param origin the starting node
		 * @param destination the ending node
		 * @param weight the weight value between the nodes
		 * 
		 */
		public Edge(String origin, String destination, double weight) {
			this.origin = origin;
			this.destination = destination;
			this.weight = weight;
		}
		
		/**
		 * Returns the origin of this Edge
		 * 
		 * @return the origin node
		 */
		public String getOrigin() {
			return origin;
		}
		
		/**
		 * Returns the destination of this Edge
		 * 
		 * @return the destination node
		 */
		public String getDestination() {
			return destination;
		}
		
		/**
		 * Returns the weight of this Edge
		 * 
		 * @return the integer weight
		 */
		public double getWeight() {
			return weight;
		}
	}
	
	static class Graph {
		private int numVertices;
		private ArrayList<Edge> edges;
		
		/**
		 * Instantiates a Graph with a specified number of vertices
		 * 
		 * @param vertices the number of vertices
		 * 
		 */
		public Graph(int vertices) {
			this.numVertices = vertices;
			edges = new ArrayList<Edge>(vertices-1);
		}
		
		/**
		 * Instantiates a new weighted Edge between two nodes and
		 * stores it in this Graph
		 * 
		 * @param origin the starting point/node
		 * @param destination the ending point/node
		 * @param weight the weight value between the nodes
		 * 
		 */
		public void addEdge(String org, String dest, double weight) {
			Edge e = new Edge(org, dest, weight);
			edges.add(e);
		}
		
		/**
		 * Returns the number of vertices in this Graph
		 * 
		 * @return the integer number of vertices
		 */
		public int getNumVertices() {
			return numVertices;
		}
		
		/**
		 * Returns an ArrayList containing the Edges in this Graph
		 * 
		 * @return the ArrayList of Edges
		 */
		public ArrayList<Edge> getEdges() {
			return edges;
		}
	}
}
