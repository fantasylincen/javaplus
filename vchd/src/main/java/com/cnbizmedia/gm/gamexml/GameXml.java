package com.cnbizmedia.gm.gamexml;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.util.Util;

import com.cnbizmedia.gen.dto.MongoGen.GameXmlFileDto;

public class GameXml {


	private final GameXmlFileDto dto;

	public GameXml(GameXmlFileDto dto) {
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
		if(dto == null)
			throw new NullPointerException("请先上传各个服务器的配置表, 前后端都要传");
		return dto.getVersion();
	}

	public String getMd5() {
		return Util.Secure.md5(getData());
	}

	public boolean exist() {
		return dto != null;
	}

	public static String key2(String pId, String zId) {
		return "xml-" + pId + "-" + zId;
	}

}
