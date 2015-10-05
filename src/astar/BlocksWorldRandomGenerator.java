package astar;

import java.util.ArrayList;
import java.util.Collections;

public class BlocksWorldRandomGenerator {

	public static void main(String[] args) {
		int blocks = Integer.parseInt(args[0]);
		int stacks = Integer.parseInt(args[1]);
		
		// int heuriticType = HeuristicConstants.CUSTOM_HEURISTIC;
		State initialState1 = new State(stacks, blocks, HeuristicConstants.OUT_OF_PLACE);
		State initialState2 = new State(stacks, blocks, HeuristicConstants.CUSTOM_HEURISTIC);
		ArrayList<Character> blockOrder = new ArrayList<Character>();
		for (int i = 0; i < blocks; i++) {
			blockOrder.add((char)(65 + i));
		}
		Collections.shuffle(blockOrder);
		for (int i = 0; i < blocks; i++) {
			int stackNumber = (int)(Math.random() * stacks);						
			initialState1.getBlocksArrangement()[stackNumber].add(blockOrder.get(i));			
			initialState2.getBlocksArrangement()[stackNumber].add(blockOrder.get(i));
		}
		
		System.out.println();
		System.out.println("Out of place Heuristic");
		initialState1.printBlocks();
		initialState1.calculateHeuristic();
		BlocksWorld.runHeuristicSearch(initialState1);
		
		System.out.println();
		System.out.println("Custom Heuristic");
		initialState2.printBlocks();
		initialState2.calculateHeuristic();
		BlocksWorld.runHeuristicSearch(initialState2);
	}
}
