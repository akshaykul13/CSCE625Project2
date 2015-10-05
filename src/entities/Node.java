package entities;

public class Node {
	
	private int id;
	
	private int x;
	
	private int y;
	
	private int parent;
	
	private double distToGoal;
		
	public Node(int id, int x, int y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public double getDistToGoal() {
		return distToGoal;
	}

	public void setDistToGoal(double d) {
		this.distToGoal = d;
	}

	public void setDistToGoal(Node destination) {
		// TODO Auto-generated method stub
		int deltaX = destination.getX() - this.getX();
		int deltaY = destination.getY() - this.getY();
		this.setDistToGoal(Math.sqrt(deltaX*deltaX + deltaY*deltaY));
	}
	
}
