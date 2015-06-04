package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class SelectMinAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6820347114622358912L;
	private int min;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().selectMin(min);
		return super.execute();
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
}
