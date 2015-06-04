package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteStockAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1341437674027138381L;
	private String id;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().deleteStock(id);
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
