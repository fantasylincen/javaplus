package org.hhhhhh.guess.user;

import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getUsername() {
		return dto.getUsername();
	}

	public String getNick() {

		if (!isNullOrEmpty(dto.getNick())) {
			return dto.getNick();
		}
		return dto.getUsername();
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public int getJiFen() {
		return dto.getJiFen();
	}

	public UserDto getDto() {
		return dto;
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
			throw new GuessException("积分不够");
	}

	public void addJiFen(int jiFenAdd) {
		if (jiFenAdd <= 0)
			throw new IllegalArgumentException(jiFenAdd + "");
		dto.setJiFen(dto.getJiFen() + jiFenAdd);
		UserDao dao = Daos.getUserDao();
		dao.save(dto);
	}

	public String getPassword() {
		return dto.getPassword();
	}
}