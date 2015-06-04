package cn.javaplus.smonitor;

import cn.javaplus.smonitor.init.ThreadFustBuy;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566453606318435030L;
	private String id;

	@Override
	public String execute() throws Exception {
		ThreadFustBuy.getInstance().test(id);
		return super.execute();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
