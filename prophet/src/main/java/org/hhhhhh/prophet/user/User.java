package org.hhhhhh.prophet.user;

import org.hhhhhh.prophet.exception.VGameException;
import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hhhhhh.prophet.hibernate.dto.UserDto;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getEmail() {
		return dto.getUsername();
	}

	public String getNick() {

		if (!isNullOrEmpty(dto.getNick())) {
			return dto.getNick();
		}
		if (!isNullOrEmpty(dto.getUsername())) {
			return dto.getUsername();
		}
		return dto.getId();
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	/**
	 * V币
	 * 
	 * @return
	 */
	public int getJiFen() {
		return dto.getJiFen();
	}

	public UserDto getDto() {
		return dto;
	}

	public String getId() {
		return dto.getId();
	}

	public void reduceJiFen(int jiFenNeed) {
		if (jiFenNeed < 0)
			throw new IllegalArgumentException(jiFenNeed + "");

		checkEnouph(jiFenNeed);
		dto.setJiFen(dto.getJiFen() - jiFenNeed);
		Daos.getUserDao().save(dto);
	}

	private void checkEnouph(int jiFenNeed) {
		if (dto.getJiFen() < jiFenNeed)
			throw new VGameException("V币不够");
	}

	public void addJiFen(int jiFenAdd) {
		if (jiFenAdd <= 0)
			throw new IllegalArgumentException(jiFenAdd + "");
		dto.setJiFen(dto.getJiFen() + jiFenAdd);
		UserDao dao = Daos.getUserDao();
		dao.save(dto);
	}

	public String getPwd() {
		return dto.getPassword();
	}
}