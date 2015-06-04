package cn.mxz.bag;


public class AddPropEvent {

	private int	id;
	private int	count;
	private String	uname;

	public AddPropEvent(int id, int count, String uname) {
		this.id = id;
		this.count = count;
		this.uname = uname;
	}

	public int getId() {
		return id;
	}

	public String getUname() {
		return uname;
	}

	public int getCount() {
		return count;
	}

}
