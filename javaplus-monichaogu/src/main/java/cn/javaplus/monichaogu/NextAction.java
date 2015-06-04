package cn.javaplus.monichaogu;

import com.opensymphony.xwork2.ActionSupport;

public class NextAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3472474840041551771L;
	private int day;
	@Override
	public String execute() throws Exception {
		
		ShiChang.next(getDay());
		
		return super.execute();
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

}
