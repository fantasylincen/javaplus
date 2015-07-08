package org.hhhhhh.prophet;

import org.hhhhhh.prophet.user.User;

import cn.javaplus.util.Util;

public class Server {


	//
	// private static ResourceDir resources;
	//
	// public static ResourceDir getResources() {
	// if (resources == null) {
	// resources = new ResourceDir();
	// }
	// return resources;
	// }

	private static ServerConfig config;

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
		if (email == null)
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
		if (id == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDto c = dao.get(id);
		if (c == null)
			return null;
		return new User(c);
	}

	public static ServerConfig getConfig() {
		if (config == null)
			config = new ServerConfig();
		return config;
	}

	public static User load(String input) {
		if (Util.Str.isEmail(input))
			return loadUserByEmail(input);
		else
			return loadUserById(input);
	}
}
