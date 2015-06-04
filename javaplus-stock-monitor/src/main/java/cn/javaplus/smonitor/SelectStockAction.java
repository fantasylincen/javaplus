package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class SelectStockAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4736828276026513835L;
	private String id;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().selectStock(id);
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
