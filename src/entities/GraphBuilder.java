package entities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entities.Graph;


public class GraphBuilder {
	
	public static Graph buildGraph(String filename) throws FileNotFoundException {		
		File inputFile = new File(filename);
		Scanner in = new Scanner(inputFile);
		
		// read vertices
		in.next();
		int vertices = in.nextInt();
		Graph graph = new Graph(vertices);
		for (int i = 0; i < vertices; i++) {
			int id = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();			
			graph.addVertex(id, x, y);
		}
		// read edges
		in.next();
		int edges = in.nextInt();
		for (int i = 0; i < edges; i++) {
			int id = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();			
			graph.addEdge(id, v, w);
		}
		in.close();
		return graph;
	}
}
