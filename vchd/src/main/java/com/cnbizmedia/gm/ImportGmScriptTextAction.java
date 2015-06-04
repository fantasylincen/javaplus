package com.cnbizmedia.gm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;

import com.cnbizmedia.Server;
import com.cnbizmedia.gm.project.Project;
import com.opensymphony.xwork2.ActionSupport;

public class ImportGmScriptTextAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7455675855687354878L;
	private String text;

	@Override
	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession();

		String pId = (String) session.getAttribute("projectId");
		Util.Exception.checkNull(pId, "projectId is null");
		
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		
		ProjectManager pm = Server.getProjectManager();
		Project p = pm.getProject(session);
		
		p.importGmScript(getText().trim());
		p.save();
		
		return SUCCESS;
	}

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
}