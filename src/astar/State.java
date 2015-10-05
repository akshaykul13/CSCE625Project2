package astar;

import java.util.ArrayList;

public class State {
	private int numberOfStacks;
	private int numberOfBlocks;
	private ArrayList<Character>[] blocksArrangement;
	private int heuristicScore = -1;
	private int heuristicType;
	private int depth; 
	private State previousState;
	
	public State(int numberOfStacks, int numberOfBlocks, int heuristicType) {
		super();
		this.numberOfStacks = numberOfStacks;
		this.numberOfBlocks = numberOfBlocks;
		blocksArrangement = new ArrayList[numberOfStacks];
		for (int i = 0; i < numberOfStacks; i++) {
			blocksArrangement[i] = new ArrayList<Character>();
		}
		this.heuristicType = heuristicType;
	}
	
	public State(State state) {
		this.numberOfStacks = state.getNumberOfStacks();
		this.numberOfBlocks = state.getNumberOfBlocks();
		blocksArrangement = new ArrayList[numberOfStacks];
		for (int i = 0; i < numberOfStacks; i++) {
			blocksArrangement[i] = new ArrayList<Character>();
			for (char block : state.getBlocksArrangement()[i]) {
				blocksArrangement[i].add(block);
			}
		}
		this.heuristicType = state.getHeuristicType();
		this.heuristicScore = state.getHeuristicScore();
	}
	
	public void moveBlock(int stack1, int stack2) {
		int lastIndex = this.blocksArrangement[stack1].size() - 1;
		char block = this.blocksArrangement[stack1].get(lastIndex);
		this.blocksArrangement[stack2].add(block);
		this.blocksArrangement[stack1].remove(lastIndex);
	}
	
	public void calculateHeuristic() {
		int score = 0;
		switch (this.heuristicType) {
			case HeuristicConstants.OUT_OF_PLACE:
				// Out of place in stack 0
				for (int i = 0; i < this.blocksArrangement[0].size(); i++) {
					char block = this.blocksArrangement[0].get(i);
					if (i != (int)(block) - 65) {
						score++;
					}
				}
				// All other blocks are out of place
				score = score + numberOfBlocks - this.blocksArrangement[0].size();
				break;
			case HeuristicConstants.MANHATTAN_DIST:				
				for (int i = 0; i < this.numberOfStacks; i++) {
					for (int j = 0; j < this.blocksArrangement[i].size(); j++) {
						char block = this.blocksArrangement[i].get(j);						
						score = score + Math.abs(i - ((int)block - 65)) + i;						
					}
				}
			case HeuristicConstants.CUSTOM_HEURISTIC:
				// Number of blocks to be popped out of first stack
				int blocksInOrder = 0;
				for (int i = 0; i < this.blocksArrangement[0].size(); i++) {
					char block = this.blocksArrangement[0].get(i);
					if (i != (int)(block) - 65) {
						break;
					}
					blocksInOrder++;
				}
				// Find required block
				char requiredBlock = (char)(blocksInOrder + 1 + 65);
				int numberOfBlocksToPop = 0;
				for (int i = 0; i < this.numberOfStacks; i++) {
					for (int j = 0; j < this.blocksArrangement[i].size(); j++) {
						char block = this.blocksArrangement[i].get(j);						
						if (block == requiredBlock) {
							if (i == 0) {
								numberOfBlocksToPop = 1;
							}
						} else {
							numberOfBlocksToPop = this.blocksArrangement[i].size() - j;
						}
					}
				}
				// Find number of blocks out of place
				int outOfPlace = (this.numberOfStacks - 1) * (this.numberOfBlocks - blocksInOrder);
				score = this.blocksArrangement[0].size() - blocksInOrder + numberOfBlocksToPop + outOfPlace;
			default:
				break;
		}
		this.heuristicScore = score + this.getDepth();
	}
	
	public void printBlocks() {
		for (int i = 0; i < numberOfStacks; i++) {
			System.out.print((i+1) + " | ");
			for (char block: this.blocksArrangement[i]) {
				System.out.print(block + " ");
			}
			System.out.println();
		}
	}

	public int getNumberOfStacks() {
		return numberOfStacks;
	}

	public void setNumberOfStacks(int numberOfStacks) {
		this.numberOfStacks = numberOfStacks;
	}

	public int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public void setNumberOfBlocks(int numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}

	public ArrayList<Character>[] getBlocksArrangement() {
		return blocksArrangement;
	}

	public void setBlocksArrangement(ArrayList<Character>[] blocksArrangement) {
		this.blocksArrangement = blocksArrangement;
	}

	public int getHeuristicScore() {
		return heuristicScore;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public State getPreviousState() {
		return previousState;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

	public int getHeuristicType() {
		return heuristicType;
	}

	public void setHeuristicType(int heuristicType) {
		this.heuristicType = heuristicType;
	}
	
}
