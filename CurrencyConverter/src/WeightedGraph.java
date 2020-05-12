import java.util.ArrayList;
public class WeightedGraph {
	
	static class Edge {
		private String origin;
		private String destination;
		private double weight;
		
		public Edge(String origin, String destination, double weight) {
			this.origin = origin;
			this.destination = destination;
			this.weight = weight;
		}
		
		public String getOrigin() {
			return origin;
		}
		
		public String getDestination() {
			return destination;
		}
		
		public double getWeight() {
			return weight;
		}
	}
	
	static class Graph {
		private int numVertices;
		private ArrayList<Edge> edges;
		
		public Graph(int vertices) {
			this.numVertices = vertices;
			edges = new ArrayList<Edge>(vertices-1);
		}
		
		public void addEdge(String org, String dest, double weight) {
			Edge e = new Edge(org, dest, weight);
			edges.add(e);
		}
		
		public int getNumVertices() {
			return numVertices;
		}
		
		public ArrayList<Edge> getEdges() {
			return edges;
		}
	}
}
