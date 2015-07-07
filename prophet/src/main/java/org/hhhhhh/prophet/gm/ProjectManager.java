package org.hhhhhh.prophet.gm;

import javax.servlet.http.HttpSession;

import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gm.project.Project;


public class ProjectManager {
	
	public Zone getZone(HttpSession session) {
		String zoneId = (String) session.getAttribute("zoneId");
		return getProject(session).getZone(zoneId);
	}
	
	public String getProjectName(HttpSession session) {
		return getProject(session).getName();
	}

	public Zone getZone(String projectId, String zoneId) {
		Project p = getProject(projectId);
		if(p == null)
			return null;
		return p.getZone(zoneId);
	}

	public Project getProject(HttpSession session) {
		String projectId = (String) session.getAttribute("projectId");
		return getProject(projectId);
	}

	public Project getProject(String projectId) {
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto dto = dao.get(projectId);
		if(dto == null)
			return null;
		return new Project(dto);
	}
}
