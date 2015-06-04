package cn.mxz.mission;

public class Coordinate {
	/**
	 * 路径
	 */
	private int		path;
	
	/**
	 * 节点下标<br/>
	 * 注意，这个index不是MissionNodeTemplet的index，而是pathMap的List<MissionNode>的下标，有利于服务器快速定位节点
	 * 
	 */
	private int		node;

	public int getPath() {
		return path;
	}

	public int getNode() {
		return node;
	}

	public Coordinate setPath(int path) {
		this.path = path;
		return this;
	}

	public void setNode(int node) {
		this.node = node;
	}

	public Coordinate(int path, int node) {
		super();
		this.path = path;
		this.node = node;
	}
	
	public void copy( Coordinate copy ) {
		this.path = copy.path;
		this.node = copy.node;
	}
	
	public void next(){
		this.node++;
	}
		
}
