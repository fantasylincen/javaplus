package com.cnbizmedia.gm;

import java.util.ArrayList;
import java.util.HashSet;
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

public class CreateZoneAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	String zoneName;
	String zoneId;

	public String getZoneId() {
		return zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PageContext pageContext = ServletActionContext.getPageContext();

		checkId();
		
		HttpSession session = request.getSession();
		String pId = (String) session.getAttribute("projectId");

		ProjectDao dao = Daos.getProjectDao();
		ProjectDto dto = dao.get(pId);

		if (dto.getZones() == null) {
			ArrayList<ZoneDto> ls = Lists.newArrayList();
			dto.setZones(ls);
		}

		List<ZoneDto> zones = dto.getZones();

		ZoneDto zone = new ZoneDto();
		zone.setId(getZoneId());
		zone.setName(zoneName);

		zones.add(zone);

		checkDup(zones);
		dao.save(dto);

		return SUCCESS;
	}

	private void checkId() {
		if(getZoneId() == null || !getZoneId().matches("[0-9]+")) {
			throw new RuntimeException("分区ID错误! 只能是数字");
		}
		if(getZoneName() == null || getZoneName().isEmpty()) {
			throw new RuntimeException("名字不能为空");
		}
	}

	private void checkDup(List<ZoneDto> zones) {
		HashSet<Object> set = Sets.newHashSet();
		for (ZoneDto dto : zones) {
			set.add(dto.getId());
		}
		if (set.size() != zones.size())
			throw new RuntimeException("分区ID重复");
	}
}