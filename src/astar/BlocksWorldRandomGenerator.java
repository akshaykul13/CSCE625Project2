package astar;

import java.util.ArrayList;
import java.util.Collections;

public class BlocksWorldRandomGenerator {

	public static void main(String[] args) {
		int blocks = 10;
		int stacks = 6;
		
		int heuriticType = HeuristicConstants.CUSTOM_HEURISTIC;
		State initialState = new State(stacks, blocks, heuriticType);
		ArrayList<Character> blockOrder = new ArrayList<Character>();
		for (int i = 0; i < blocks; i++) {
			blockOrder.add((char)(65 + i));
		}
		Collections.shuffle(blockOrder);
		for (int i = 0; i < blocks; i++) {
			int stackNumber = (int)(Math.random() * stacks);						
			initialState.getBlocksArrangement()[stackNumber].add(blockOrder.get(i));			
		}
		initialState.printBlocks();
		initialState.calculateHeuristic();
		BlocksWorld.runHeuristicSearch(initialState);
	}
}
