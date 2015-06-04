package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class SelectDayAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695703355651379691L;
	private int day;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().selectDay(day);
		return super.execute();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
