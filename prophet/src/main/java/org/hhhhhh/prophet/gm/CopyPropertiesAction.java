package org.hhhhhh.prophet.gm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;
import org.hhhhhh.prophet.Server;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ZoneDto;
import org.hhhhhh.prophet.gm.project.Project;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.opensymphony.xwork2.ActionSupport;

public class CopyPropertiesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String zoneId;


	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();
		HttpSession session = request.getSession();
		
		
		
		ProjectManager pm = Server.getProjectManager();
		
		Project project = pm.getProject(session);
		
		Zone src = project.getZone(zoneId);
		
		Zone dst = pm.getZone(session);
		
		
		ZoneDto dstDto = dst.getDto();
		ZoneDto srcDto = src.getDto();
		
		dstDto.setClientXmlFile(srcDto.getClientXmlFile());
		dstDto.setGameXmlFile(srcDto.getGameXmlFile());
		dstDto.setProperties(srcDto.getProperties());
		
		dst.save();
		
		return SUCCESS;
	}


	public String getZoneId() {
		return zoneId;
	}


	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}



}