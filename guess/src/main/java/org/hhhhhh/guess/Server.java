package org.hhhhhh.guess;

import javax.servlet.http.HttpSession;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.question.Manager;
import org.hhhhhh.guess.user.User;

public class Server {

	public static final String KEY = "JJYSB";
	private static Manager manager;

	public static User getUser(HttpSession session) {
		String username = (String) session.getAttribute("username");
		return getUser(username);
	}

	public static User getUser(String username) {

		if (username == null)
			return null;
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.get(username);
		if (dto == null)
			return null;
		return new User(dto);
	}

	public static Manager getManager() {
		if (manager == null)
			manager = new Manager();
		return manager;
	}
}
