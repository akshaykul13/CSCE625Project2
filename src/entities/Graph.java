package entities;
import java.util.ArrayList;


public class Graph {
	private final int V;
	private int E;	
	private ArrayList<Edge>[] adj;
	private Node vertices[];
	
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		vertices = new Node[V];
		adj = (ArrayList<Edge>[])new ArrayList[V];
		for(int v = 0; v < V; v++){
			adj[v] = new ArrayList<Edge>();
		}
	}
	
	public void addVertex(int id, int x, int y){
		Node vertex = new Node(id, x, y);
		vertices[id] = vertex;
	}
	
	public void addEdge(int id, int v, int w){
		Edge edge1 = new Edge(id, v, w);
		adj[v].add(edge1);
		Edge edge2 = new Edge(id, w, v);
		adj[w].add(edge2);
		E = E + 2;
	}
	
	public Node[] getVertices() {
		return vertices;
	}
	
	public ArrayList<Edge>[] getAdj() {
		return adj;
	}

	public void setAdj(ArrayList<Edge>[] adj) {
		this.adj = adj;
	}

	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void setE(int E) {
		this.E = E;
	}		
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
}
