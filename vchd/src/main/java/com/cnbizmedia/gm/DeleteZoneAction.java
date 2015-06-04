package com.cnbizmedia.gm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.struts2.ServletActionContext;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDao;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDto;
import com.cnbizmedia.gen.dto.MongoGen.ZoneDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteZoneAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	String zoneName;
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
		String pId = (String) session.getAttribute("projectId");

		ProjectDao dao = Daos.getProjectDao();
		ProjectDto dto = dao.get(pId);

		if (dto.getZones() == null) {
			ArrayList<ZoneDto> ls = Lists.newArrayList();
			dto.setZones(ls);
		}

		List<ZoneDto> zones = dto.getZones();

		Iterator<ZoneDto> it = zones.iterator();
		while (it.hasNext()) {
			ZoneDto next = it.next();
			String id = next.getId();
			if (id.equals(getId())) {
				it.remove();
			}
		}

		dao.save(dto);

		return SUCCESS;
	}

}