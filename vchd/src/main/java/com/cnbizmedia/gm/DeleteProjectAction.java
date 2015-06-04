package com.cnbizmedia.gm;

import java.io.File;
import java.rmi.ServerException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import cn.javaplus.util.Util;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDao;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDto;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteProjectAction extends ActionSupport {
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

		String id = getId();
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto dto = dao.get(id);
		dao.delete(dto.getId());
		return SUCCESS;
	}
}