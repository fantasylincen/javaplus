package org.hhhhhh.prophet.gm;

import java.util.Iterator;
import java.util.List;

import org.hhhhhh.prophet.gen.dto.MongoGen;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.GameXmlFileDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.KeyValueDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ProjectDto;
import org.hhhhhh.prophet.gen.dto.MongoGen.ZoneDto;
import org.hhhhhh.prophet.gm.gamexml.ClientXml;
import org.hhhhhh.prophet.gm.gamexml.GameXml;

import cn.javaplus.util.Util;


public class Zone {

	private final ZoneDto dto;
	private final ProjectDto pdto;

	public Zone(ProjectDto pdto, ZoneDto dto) {
		this.pdto = pdto;
		this.dto = dto;
	}

	public ZoneDto getDto() {
		return dto;
	}
	
	public String getName() {
		return dto.getName();
	}

	public String getValue(String key) {
		List<MongoGen.KeyValueDto> ks = getProperties();
		for (MongoGen.KeyValueDto e : ks) {
			if (e.getKey().equals(key)) {
				return e.getValue();
			}
		}

		return null;
	}

	public String getServerUrl() {
		String host = getValue("host");
		String webContextRoot = getValue("webContextRoot");
		return "http://" + host + "/" + webContextRoot;
	}

	public GameXml getGameXml() {
		GameXmlFileDto gxl = dto.getGameXmlFile();
		if(gxl == null)
			return null;
//			throw new RuntimeException("please upload server xml file");
		return new GameXml(gxl);
	}

	public ClientXml getClientXml() {
		GameXmlFileDto gxl = dto.getClientXmlFile();
		if(gxl == null)
			return null;
//			throw new RuntimeException("please upload client xml file");
		return new ClientXml(gxl);
	}

	public void putProperty(String key, String value, String dsc,
			boolean isClientVisible) {
		List<KeyValueDto> p = getProperties();
		KeyValueDto d = find(key);
		if (d == null) {
			d = new KeyValueDto();
			d.setKey(key);
			p.add(d);
		}

		d.setDsc(dsc);
		d.setValue(value);
		d.setIsClientVisible(isClientVisible);
	}

	private KeyValueDto find(String key) {
		List<KeyValueDto> p = getProperties();
		for (KeyValueDto k : p) {
			if (k.getKey().equals(key))
				return k;
		}
		return null;
	}

	public List<KeyValueDto> getProperties() {
		List<KeyValueDto> properties = dto.getProperties();
		properties = Util.Collection.nullToEmpty(properties);
		return properties;
	}

	public void save() {
		Daos.getProjectDao().save(pdto);
	}

	public void deleteProperty(String key) {
		List<KeyValueDto> properties = dto.getProperties();
		if (properties == null)
			return;
		Iterator<KeyValueDto> it = properties.iterator();
		while (it.hasNext()) {
			MongoGen.KeyValueDto dto = (MongoGen.KeyValueDto) it.next();
			if (dto.getKey().equals(key))
				it.remove();
		}
	}

	public void saveGameXmlFile(GameXmlFileDto dto) {
		this.dto.setGameXmlFile(dto);
	}

	public void saveClientXmlFile(GameXmlFileDto dto2) {
		this.dto.setClientXmlFile(dto2);
	}

	public void setName(String newName) {
		dto.setName(newName);
	}

}
