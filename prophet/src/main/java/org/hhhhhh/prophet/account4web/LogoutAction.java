package org.hhhhhh.prophet.account4web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	private static final long serialVersionUID = -7706071822124421174L;
	private HttpSession session;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();
		session.setAttribute("userId", null);
		return SUCCESS;
	}
}
