package org.hhhhhh.guess;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hhhhhh.guess.config.CacheManager;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.AnswersDto;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.user.User;

public class Server {

	public static final String KEY = "JJYSB";
	private static Manager manager;

	public static User getUser(HttpSession session) {
		String id = (String) session.getAttribute("userId");
		return getUser(id);
	}

	public static User getUser(String id) {

		if (id == null)
			return null;
		String k = key(id);
		User user = (User) CacheManager.get(k);

		if (user == null) {
			user = load(id);
			if (user != null)
				put(user);
		}
		return user;
	}

	private static User load(String id) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.get(id);
		if(dto == null)
			return null;
		return new User(dto);
	}

	public static void put(User user) {
		String username = user.getUsername();
		CacheManager.put(key(username), user);
	}

	private static String key(String id) {
		return KEY + ":" + id;
	}
	
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
		List<UserDto> c = dao.find("username", username);
		if (!c.isEmpty())
			return c.get(0);
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

	public static Manager getManager() {
		if(manager == null)
			manager = new Manager();
		return manager;
	}
}
