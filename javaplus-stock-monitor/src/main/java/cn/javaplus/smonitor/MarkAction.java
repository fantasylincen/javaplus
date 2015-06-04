package cn.javaplus.smonitor;

import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class MarkAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8982428665127436684L;
	private String id;
	private int mark;

	@Override
	public String execute() throws Exception {

		SMonitor.getInstance().mark(id, mark);
		return super.execute();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
}
