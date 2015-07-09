package org.hhhhhh.prophet;

import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.prophet.hibernate.dto.UserDto;
import org.hhhhhh.prophet.user.User;

import cn.javaplus.util.Util;

public class Server {

	public static final String KEY = "JJYSB";

	public static User loadUserByUsername(String username) {

		UserDto dto = findByUsername(username);
		if (dto == null)
			return null;
		return new User(dto);
	}

	private static UserDto findByUsername(String username) {
		if (username == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.find("email", username);
		if (c.hasNext())
			return c.next();
		return null;
	}

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
			return loadUserByUsername(input);
		else
			return loadUserById(input);
	}
}
