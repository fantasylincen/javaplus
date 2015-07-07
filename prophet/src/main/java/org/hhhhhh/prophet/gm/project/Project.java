package org.hhhhhh.prophet.gm.project;

import java.util.List;

import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ZoneDto;
import org.hhhhhh.prophet.gm.Zone;

import cn.javaplus.util.Util;


public class Project {

	private final ProjectDto dto;
	private GmScriptManager manager;

	public Project(ProjectDto dto) {
		this.dto = dto;
	}

	public void importGmScript(String text) {
		dto.setGmScript(text);
		if (manager != null)
			manager.clearCache();
	}

	public void save() {
		Daos.getProjectDao().save(dto);
	}

	public Zone getZone(String zoneId) {

		List<ZoneDto> zones = dto.getZones();
		zones = Util.Collection.nullToEmpty(zones);
		for (ZoneDto d : zones) {
			if (d.getId().equals(zoneId))
				return new Zone(dto, d);
		}
		return null;
	}

	public String getName() {
		return dto.getName();
	}

	public GmScriptManager getGmScriptManager() {
		if (manager == null) {
			manager = new GmScriptManager(dto);
		}
		return manager;
	}
}
