package cn.javaplus.monichaogu;

import com.opensymphony.xwork2.ActionSupport;

public class BackAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7415039884191639814L;
	private int day;
	@Override
	public String execute() throws Exception {
		
		ShiChang.back(getDay());
		
		return super.execute();
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

}
