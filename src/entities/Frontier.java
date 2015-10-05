package entities;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import astar.State;
import navigators.Constants;

public class Frontier {
	private static int algorithm;
	private static Stack<Node> stack;
	private static Queue<Node> queue;
	private static PriorityQueue<Node> priorityQueue;
	private static PriorityQueue<State> statePriorityQueue;
	private static int maxSize = 0;
	
	public Frontier(int navigatorType) {
		algorithm = navigatorType;
		switch (algorithm) {
			case Constants.BFS:
				queue = new LinkedList<Node>();			
				break;
			case Constants.DFS:
				stack = new Stack<Node>();			
				break;
			case Constants.GreedyBFS:
				NodeDistanceComparator ndc = new NodeDistanceComparator();
				priorityQueue = new PriorityQueue<Node>(200, ndc);			
				break;
			case Constants.ASTAR:
				HeuristicComparator hc = new HeuristicComparator();
				statePriorityQueue = new PriorityQueue<State>(10000, hc);			
				break;
			default:
				break;
		}
	}
	
	public class HeuristicComparator implements Comparator<State> {		
		@Override
		public int compare(State s1, State s2) {
			if (s1.getHeuristicScore() < s2.getHeuristicScore()) {
				return -1;
			}
			if (s1.getHeuristicScore() > s2.getHeuristicScore()) {
				return 1;
			}
			return 0;
		}
	}
	
	public class NodeDistanceComparator implements Comparator<Node> {
		@Override
		public int compare(Node n1, Node n2) {
			if (n1.getDistToGoal() < n2.getDistToGoal()) {
				return -1;
			}
			if (n1.getDistToGoal() > n2.getDistToGoal()) {
				return 1;
			}
			return 0;
		}
	}

	public void push(Node vertex) {
		switch (algorithm) {
			case Constants.BFS:
				queue.add(vertex);	
				break;
			case Constants.DFS:
				stack.add(vertex);
				break;
			case Constants.GreedyBFS:
				priorityQueue.add(vertex);			
				break;
			default:
				break;
		}
		if (this.size() > maxSize) {
			maxSize = this.size();
		}
	}
	
	public Node pop() {
		Node vertex = null;
		switch (algorithm) {
			case Constants.BFS:
				vertex = queue.remove();			
				break;
			case Constants.DFS:
				vertex = stack.pop();
				break;
			case Constants.GreedyBFS:
				vertex = priorityQueue.remove();			
				break;
			default:
				break;
		}
		return vertex;
	}
	
	public boolean isEmpty() {
		boolean isEmpty = true;
		switch (algorithm) {
			case Constants.BFS:
				isEmpty = queue.isEmpty();			
				break;
			case Constants.DFS:
				isEmpty = stack.isEmpty();
				break;
			case Constants.GreedyBFS:
				isEmpty = priorityQueue.isEmpty();			
				break;
			default:
				break;
		}
		return isEmpty;
	}
	
	public int size() {
		int size = 0;
		switch (algorithm) {
			case Constants.BFS:
				size = queue.size();
				break;
			case Constants.DFS:
				size = stack.size();
				break;
			case Constants.GreedyBFS:
				size = priorityQueue.size();			
				break;
			default:
				break;
		}
		return size;
	}

	public static int getMaxSize() {
		return maxSize;
	}
	
}
