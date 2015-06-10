package util;

public class CmdLog {

	private boolean isSuccess;
	private String heSay;
	private String time;

	public CmdLog(boolean isSuccess, String heSay, String time) {
		this.isSuccess = isSuccess;
		this.heSay = heSay;
		this.time = time;
	}

	public String getHeSay() {
		return heSay;
	}

	public boolean isSuccess() {
		return isSuccess;
	}
	
	public String toString() {
		return time + "&nbsp;&nbsp;&nbsp;" + heSay + "&nbsp;&nbsp;&nbsp;" + (isSuccess ? " √" : "×");
	}

}
