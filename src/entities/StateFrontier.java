package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

import astar.State;

public class StateFrontier {
	private static PriorityQueue<State> priorityQueue;
	private static int maxSize = 0;
	
	public StateFrontier() {
		HeuristicComparator hc = new HeuristicComparator();
		priorityQueue = new PriorityQueue<State>(200000, hc);
		maxSize = 0;
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

	public void push(State state) {
		priorityQueue.add(state);			
		
		if (priorityQueue.size() > maxSize) {
			maxSize = this.size();
		}
	}
	
	public State pop() {
		return priorityQueue.remove();					
	}
	
	public boolean isEmpty() {
		return priorityQueue.isEmpty();				
	}
	
	public int size() {
		return priorityQueue.size();					
	}
 
	public static int getMaxSize() {
		return maxSize;
	}
	
}
