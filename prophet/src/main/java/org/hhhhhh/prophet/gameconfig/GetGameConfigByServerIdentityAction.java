package org.hhhhhh.prophet.gameconfig;

import java.util.List;

import org.hhhhhh.prophet.JsonAction;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.KeyValueDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ZoneDto;


public class GetGameConfigByServerIdentityAction extends JsonAction {

	private static final long serialVersionUID = 1L;
	private String serverIdentity;
	private String projectId;

	private String serverConfigKey;

	public Object exec() {
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto p = dao.get(projectId);
		if (p == null)
			return null;
		List<ZoneDto> zs = p.getZones();
		if (zs == null)
			return null;
		for (ZoneDto dto : zs) {
			String v = getValue(dto, "serverIdentity");
			if (v == null)
				continue;

			if (v.equals(getServerIdentity())) {
				return ZoneUtil.buildZone(dto, projectId, getServerConfigKey());
			}
		}
		return null;
	}

	private String getValue(ZoneDto dto, String kk) {
		List<KeyValueDto> ps = dto.getProperties();
		for (KeyValueDto kv : ps) {
			if (kv.getKey().equals(kk))
				return kv.getValue();
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

	public String getServerIdentity() {
		return serverIdentity;
	}

	public void setServerIdentity(String serverIdentity) {
		this.serverIdentity = serverIdentity;
	}

}