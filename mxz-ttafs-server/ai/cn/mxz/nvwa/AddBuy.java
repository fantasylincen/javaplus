package cn.mxz.nvwa;

public class AddBuy {

	private String time;
	private Integer addCount;

	public AddBuy(String time, Integer addCount) {
		this.time = time;
		this.addCount = addCount;
	}

	public String getTime() {
		return time;
	}

	public int getCount() {
		return addCount;
	}

}
