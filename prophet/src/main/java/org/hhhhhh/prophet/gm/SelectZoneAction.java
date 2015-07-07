package org.hhhhhh.prophet.gm;

import java.io.File;
import java.rmi.ServerException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;

import cn.javaplus.util.Util;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.opensymphony.xwork2.ActionSupport;

public class SelectZoneAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	String id;
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();
		
		HttpSession session = request.getSession();
		
		String id = getId();
		
		if(id == null)
			throw new ServerException("id不能为空");
		
		session.setAttribute("zoneId", id);
		return SUCCESS;
	}
}