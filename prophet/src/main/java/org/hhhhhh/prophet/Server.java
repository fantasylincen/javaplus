package org.hhhhhh.prophet;

import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.prophet.hibernate.dto.UserDto;
import org.hhhhhh.prophet.user.User;

import cn.javaplus.util.Util;

public class Server {

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
		UserDtoCursor c = dao.find("email", email);
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

	public static User load(String input) {
		if (Util.Str.isEmail(input))
			return loadUserByEmail(input);
		else
			return loadUserById(input);
	}
}
