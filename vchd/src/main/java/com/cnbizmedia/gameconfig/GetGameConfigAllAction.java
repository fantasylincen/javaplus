package com.cnbizmedia.gameconfig;

import java.util.List;

import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnbizmedia.JsonAction;
import com.cnbizmedia.config.GameProperties;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.KeyValueDto;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDao;
import com.cnbizmedia.gen.dto.MongoGen.ProjectDto;
import com.cnbizmedia.gen.dto.MongoGen.ZoneDto;

public class GetGameConfigAllAction extends JsonAction {

	private static final long serialVersionUID = 1L;
	private String projectId;

	private String serverConfigKey;

	public Object exec() {
		ProjectDao dao = Daos.getProjectDao();
		ProjectDto p = dao.get(projectId);
		if (p == null)
			return null;

		return buildResult(p);
	}

	private Object buildResult(ProjectDto p) {
		JSONObject obj = new JSONObject();
		obj.put("projectId", p.getId());
		obj.put("name", Util.Chinese.getPinYinHump(p.getName()));
		List<ZoneDto> zones = p.getZones();
		if (zones != null) {
			JSONArray jr = new JSONArray();
			for (ZoneDto dto : zones) {
				jr.add(buildResult(dto, p.getId()));
			}
			obj.put("zones", jr);
		}
		return obj;
	}

	private Object buildResult(ZoneDto dto, String pid) {
		JSONObject obj = new JSONObject();

		ZoneUtil.putHead(obj, dto, pid);
		for (KeyValueDto k : dto.getProperties()) {
			if (isShowKV(k)) {
				obj.put(k.getKey(), k.getValue());
			}
		}
		return obj;
	}

	private boolean isShowKV(KeyValueDto k) {
		boolean visible = k.getIsClientVisible();
		if (visible)
			return true;
		boolean isServerKeyRight = GameProperties.getString("serverConfigKey")
				.equals(getServerConfigKey());
		return isServerKeyRight;
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