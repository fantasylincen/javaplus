package com.cnbizmedia.config;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.SystemKeyValueDao;
import com.cnbizmedia.gen.dto.MongoGen.SystemKeyValueDto;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

public class ServerConfig {

	private String myIp;

	public String getMyIp() {
		return myIp;
	}

	public void init() {
		try {
			myIp = Util.IP.getMyIp();
			saveMyIpToDb();
		} catch (Exception e) {
			e.printStackTrace();
			
			myIp = getFromDb();
			Log.d("load my ip from db", myIp);
		}
		
		Log.d("load my ip from ip138.com", myIp);
	}

	private void saveMyIpToDb() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		dao.save(createIpDto());		
	}

	private String getFromDb() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = dao.get("MY_IP");
		if(dto == null)
			throw new NullPointerException("my ip is null!");
		
		return dto.getValue();
	}

	private SystemKeyValueDto createIpDto() {
		SystemKeyValueDto dto = new SystemKeyValueDto();
		dto.setKey("MY_IP");
		dto.setValue(getMyIp());
		return dto;
	}

}
