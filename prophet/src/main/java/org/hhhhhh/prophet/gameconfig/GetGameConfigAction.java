package org.hhhhhh.prophet.gameconfig;

import java.util.List;

import org.hhhhhh.prophet.JsonAction;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ZoneDto;


public class GetGameConfigAction extends JsonAction {

	private static final long serialVersionUID = 1L;
	String zoneId;
	private String projectId;
	
	private String serverConfigKey;

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public Object exec() {
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto p = dao.get(projectId);
		if (p == null)
			return null;
		List<ZoneDto> zs = p.getZones();
		if (zs == null)
			return null;
		for (ZoneDto dto : zs) {
			if (dto.getId().equals(getZoneId())) {
				return ZoneUtil.buildZone(dto, projectId, getServerConfigKey());
			}
		}
		return null;
	}

	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getServerConfigKey() {
		return serverConfigKey;
	}

	public void setServerConfigKey(String serverConfigKey) {
		this.serverConfigKey = serverConfigKey;
	}

}