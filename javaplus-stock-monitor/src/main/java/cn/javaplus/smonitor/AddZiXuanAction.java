package cn.javaplus.smonitor;

import java.util.ArrayList;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.smonitor.downloader.SMonitor;

import com.opensymphony.xwork2.ActionSupport;

public class AddZiXuanAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4895530478630873960L;
	private String addZiXuan;

	@Override
	public String execute() throws Exception {

		if(addZiXuan == null)
			return SUCCESS;
		
		String[] split = getAddZiXuan().split("\r");

		ArrayList<String> ls = Lists.newArrayList();
		for (String id : split) {
			id = id.trim();
			id = id.replaceAll("[A-Za-z]+", "");
			if(!id.isEmpty()) {
				ls.add(id);
			}
		}
		SMonitor.getInstance().appendZiXuan(ls);
		return super.execute();
	}

	public String getAddZiXuan() {
		return addZiXuan;
	}

	public void setAddZiXuan(String addZiXuan) {
		this.addZiXuan = addZiXuan;
	}
}
