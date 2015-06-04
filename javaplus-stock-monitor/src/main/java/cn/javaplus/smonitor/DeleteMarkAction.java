package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteMarkAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8192062595096453319L;
	private String id;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().deleteMark(id);
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
