package astar;

import java.util.ArrayList;
import java.util.Collections;

import entities.StateFrontier;

public class BlocksWorld {
	private static int goalStatesChecked = 0;
	private static int pushedStates = 0;
	
	public static void main(String[] args) {
		int heuriticType = HeuristicConstants.CUSTOM_HEURISTIC;
		State initialState = new State(3, 5, heuriticType);
		initialState.getBlocksArrangement()[0].add('B');
		initialState.getBlocksArrangement()[1].add('C');
		initialState.getBlocksArrangement()[1].add('E');
		initialState.getBlocksArrangement()[2].add('A');
		initialState.getBlocksArrangement()[2].add('D');
		initialState.calculateHeuristic();
		initialState.printBlocks();
		
		
		runHeuristicSearch(initialState);	
	}

	public static void runHeuristicSearch(State initialState) {
		StateFrontier frontier = new StateFrontier();
		ArrayList<State> visited = new ArrayList<State>();
		initialState.setDepth(0);
		initialState.setPreviousState(null);
		frontier.push(initialState);
		pushedStates++;
		visited.add(initialState);
		while(!frontier.isEmpty()) {
			State poppedState = frontier.pop();
			if (checkGoalState(poppedState)) {
				System.out.println("Found Goal");
				checkGoalState(poppedState);
				// poppedState.printBlocks();
				System.out.println("Goal States Checked = " + goalStatesChecked);
				System.out.println("Depth = " + (poppedState.getDepth()));
				System.out.println("Max Queue Size = " + frontier.getMaxSize());
				System.out.println("Pushed States = " + pushedStates);
				printMoveList(poppedState);
				return;
			}			
			for (int i = 0; i < initialState.getNumberOfStacks(); i++) { 
				for (int j = 0; j < initialState.getNumberOfStacks(); j++) { 
					if (i != j && poppedState.getBlocksArrangement()[i].size() != 0) {
						State newState = new State(poppedState);
						newState.setDepth(poppedState.getDepth() + 1);						
						newState.moveBlock(i, j);
						if (!isVisited(newState, visited)) {
							newState.calculateHeuristic();
							newState.setPreviousState(poppedState);
							// newState.printBlocks(); 
							// System.out.println("Score - " + newState.getHeuristicScore());
							frontier.push(newState);
							pushedStates++;
							visited.add(initialState);
						}						
					}
				}
			}
		}
	}

	private static void printMoveList(State poppedState) {
		ArrayList<State> movesList = new ArrayList<State>();
		while(poppedState != null) {
			movesList.add(poppedState);
			poppedState = poppedState.getPreviousState();
		}
		Collections.reverse(movesList);
		for (State state : movesList) {
			state.printBlocks();
			// System.out.println(state.getHeuristicScore());
			// System.out.println(state.getDepth());
		}
	}

	private static boolean isVisited(State newState, ArrayList<State> visited) {
		for (State state : visited) {
			if (areIdentical(state, newState)) {
				return true;
			}
		}
		return false;
	}

	private static boolean areIdentical(State state, State newState) {
		for (int i = 0; i < state.getNumberOfStacks(); i++) {
			if (state.getBlocksArrangement()[i].size() != newState.getBlocksArrangement()[i].size()) {
				return false;
			} else {
				for (int j = 0; j < state.getBlocksArrangement()[i].size(); j++) {
					if (state.getBlocksArrangement()[i].get(j) != newState.getBlocksArrangement()[i].get(j)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private static boolean checkGoalState(State poppedState) {
		goalStatesChecked++;
		if (poppedState.getBlocksArrangement()[0].size() != poppedState.getNumberOfBlocks()) {
			return false;
		} 
		for (int i = 0; i < poppedState.getBlocksArrangement()[0].size(); i++) {
			char block = poppedState.getBlocksArrangement()[0].get(i);
			if (i != (int)(block) - 65) {
				return false;
			}
		}
		return true;
	}
}
