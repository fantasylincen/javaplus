package org.hhhhhh.guess.account4app;

import javax.servlet.http.HttpSession;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;

public class UserCreator {

	/**
	 * throw RegistException
	 * 
	 * @param session
	 * @param username
	 * @param password
	 * @param userId
	 * @return
	 */
	public UserDto createNewUser(HttpSession session, String username,
			String password) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = new UserDto();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setNick("");
		dao.save(dto);
		session.setAttribute("username", dto.getUsername());
		return dto;
	}
}
