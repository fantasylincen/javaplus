package org.hhhhhh.prophet.gm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;
import org.hhhhhh.prophet.gm.project.GmScriptItem;
import org.hhhhhh.prophet.gm.project.GmScriptManager;
import org.hhhhhh.prophet.gm.project.Project;

import com.opensymphony.xwork2.ActionSupport;

public class GetGmScriptAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948616138750365894L;

	private String id;

	@Override
	public String execute() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		
		Project project = Server.getProjectManager().getProject(session);
		
		GmScriptManager manager = project.getGmScriptManager();
		
		String id2 = getId();
		
		GmScriptItem item = manager.getGmScript(id2);
		
		String commitPage = item.getCommitPage();
		
		commitPage = setCommitPageArgs(commitPage, session);
		
		out.println(commitPage);
		out.flush();
		out.close();

		return SUCCESS;
	}

	private String setCommitPageArgs(String commitPage, HttpSession session) {
		Zone zone = Server.getProjectManager().getZone(session);
		String serverUrl = zone.getServerUrl();
		commitPage = commitPage.replaceAll("GAME_SERVER_URL", serverUrl);
		return commitPage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}