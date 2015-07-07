package org.hhhhhh.prophet.gm.gamexml;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hhhhhh.prophet.gen.dto.MongoGen.GameXmlFileDto;

import cn.javaplus.util.Util;


public class ClientXml {

	private final GameXmlFileDto dto;

	public ClientXml(GameXmlFileDto dto) {
		this.dto = dto;
	}

	public byte[] getData() {
		return dto.getData();
	}

	public String getUploadTime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date(dto.getUpdateTime()));
	}

	public int getVersion() {
		return dto.getVersion();
	}

	public String getMd5() {
		return Util.Secure.md5(getData());
	}

	public boolean exist() {
		return dto != null;
	}

}
