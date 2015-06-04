package com.cnbizmedia;

import cn.javaplus.util.Util;

import com.cnbizmedia.config.ServerConfig;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.UserDao;
import com.cnbizmedia.gen.dto.MongoGen.UserDao.UserDtoCursor;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;
import com.cnbizmedia.gm.ProjectManager;
import com.cnbizmedia.recharge.RechargeManager;
import com.cnbizmedia.user.User;
import com.cnbizmedia.zfb.ZfbRechargeManager;

public class Server {

	private static ProjectManager projectManager;

	public static ProjectManager getProjectManager() {
		if (projectManager == null) {
			projectManager = new ProjectManager();
		}
		return projectManager;
	}
//	
//	private static ResourceDir resources;
//
//	public static ResourceDir getResources() {
//		if (resources == null) {
//			resources = new ResourceDir();
//		}
//		return resources;
//	}
	
	private static RechargeManager rechargeManager;
	private static ServerConfig config;

	public static RechargeManager getRechargeManager() {
		if (rechargeManager == null) {
			rechargeManager = new RechargeManager();
			Thread t = new Thread(rechargeManager);
			t.setDaemon(true);
			
			t.start();
		}
		return rechargeManager;
	}
	
	private static ZfbRechargeManager zfbRechargeManager;
	
	public static ZfbRechargeManager getZfbRechargeManager() {
		if (zfbRechargeManager == null) {
			zfbRechargeManager = new ZfbRechargeManager();
		}
		return zfbRechargeManager;
	}

	/**
	 * 每次获得到的对象都不一样
	 */
	public static User loadUserByEmail(String email) {

		UserDto dto = findByEmail(email);
		if (dto == null)
			return null;
		return new User(dto);
	}

	private static UserDto findByEmail(String email) {
		if(email == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.findByEmail(email);
		if (c.hasNext())
			return c.next();
		return null;
	}

	/**
	 * 每次获得到的对象都不一样
	 */
	public static User loadUserById(String id) {
		if(id == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDto c = dao.get(id);
		if (c == null)
			return null;
		return new User(c);
	}


	public static ServerConfig getConfig() {
		if(config == null)
			config = new ServerConfig();
		return config;
	}


	public static User load(String input) {
		if(Util.Str.isEmail(input))
			return loadUserByEmail(input);
		else 
			return loadUserById(input);
	}
}
