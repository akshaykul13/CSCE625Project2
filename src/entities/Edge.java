package entities;

public class Edge {
	
	private int id;
	
	private int v;
	
	private int w;

	public Edge(int id, int v, int w) {
		super();
		this.id = id;
		this.v = v;
		this.w = w;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}
}
