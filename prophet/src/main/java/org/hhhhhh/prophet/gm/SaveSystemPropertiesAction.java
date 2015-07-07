package org.hhhhhh.prophet.gm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;

import cn.javaplus.log.Log;

import com.opensymphony.xwork2.ActionSupport;

public class SaveSystemPropertiesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	List<String> key;
	List<String> dsc;
	List<String> value;
	private List<Boolean> isClientVisible;

	public List<String> getKey() {
		return key;
	}

	public void setKey(List<String> key) {
		this.key = key;
	}

	public List<String> getDsc() {
		return dsc;
	}

	public void setDsc(List<String> dsc) {
		this.dsc = dsc;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();

		HttpSession session = request.getSession();

//		Object id = session.getAttribute("gmUserId");
		
		Zone zone = Server.getProjectManager().getZone(session);
		for (int i = 0; i < getKey().size(); i++) {
			String key = getKey().get(i);
			if(key.trim().isEmpty())
				continue;
			String value = getValue().get(i);
			String dsc = getDsc().get(i);
			boolean isClientVisible = getIsClientVisible().get(i);
			zone.putProperty(key, value, dsc, isClientVisible);
		}
		zone.save();

		return SUCCESS;
	}

	public List<Boolean> getIsClientVisible() {
		return isClientVisible;
	}

	public void setIsClientVisible(List<Boolean> isClientVisible) {
		this.isClientVisible = isClientVisible;
	}
}