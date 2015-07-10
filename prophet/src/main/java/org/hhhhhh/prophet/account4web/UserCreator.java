package org.hhhhhh.prophet.account4web;

import javax.servlet.http.HttpSession;

import org.hhhhhh.prophet.exception.RegistException;
import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.SystemKeyValueDao;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hhhhhh.prophet.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.prophet.hibernate.dto.UserDto;

import cn.javaplus.util.Util;

public class UserCreator {

	/**
	 * throw RegistException
	 * 
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	public UserDto createNewUser(HttpSession session, String username,
			String password) {
		return createNewUser(session, username, password, createUserId());
	}

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
			String password, String userId) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = new UserDto();
		dto.setUsername(username);

		if (userId != null) {
			checkUserId(userId);
			dto.setId(userId);
		} else {
			dto.setId(createUserId());
		}

		dto.setPassword(password);
		dto.setNick("");
		dao.save(dto);
		session.setAttribute("userId", dto.getId());
		return dto;
	}

	private void checkUserId(String userId) {
		if (!userId.matches("[a-zA-Z][a-zA-Z0-9]{5,15}")) {
			throw new RegistException("format error");
		}
	}

	/**
	 * 创建用户ID
	 */
	private String createUserId() {

		String id;

		while (true) {
			long createId = createId();
			id = "u" + createId;

			if (!exists(id)) {
				break;
			}
		}
		return id;

	}

	private boolean exists(String id) {
		UserDao dao = Daos.getUserDao();
		return dao.get(id) != null;
	}

	private long createId() {

		long id = getId();
		id += Util.Random.get(0, 20);
		save(id);
		return id;
	}

	private void save(long id) {
		SystemKeyValueDto dto = new SystemKeyValueDto();
		dto.setKey("USER_ID");
		dto.setValue(id + "");

		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		dao.save(dto);
	}

	private long getId() {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		SystemKeyValueDto dto = dao.get("USER_ID");
		if (dto == null) {
			dto = new SystemKeyValueDto();
			dto.setKey("USER_ID");
			dto.setValue("100000000");
			dao.save(dto);
		}
		return new Long(dto.getValue());
	}

}
