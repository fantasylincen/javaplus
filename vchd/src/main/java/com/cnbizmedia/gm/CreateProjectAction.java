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

public class CreateProjectAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	String projectName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();
		
		String pname = getProjectName();
		if(pname == null)
			throw new ServerException("项目名字不能为空");
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto dto = dao.createDTO();
		dto.setId(Util.ID.createId());
		dto.setName(pname);
		dao.save(dto);
		return SUCCESS;
	}
}