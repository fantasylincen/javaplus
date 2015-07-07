package org.hhhhhh.prophet.user;

import org.hhhhhh.prophet.exception.VGameException;
import org.hhhhhh.prophet.gen.dto.MongoGen.Daos;
import org.hhhhhh.prophet.gen.dto.MongoGen.UserDao;
import org.hhhhhh.prophet.gen.dto.MongoGen.UserDto;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getEmail() {
		return dto.getEmail();
	}

	public String getPwdMD5() {
		return dto.getPwdMD5();
	}

	public String getNick() {

		if (!isNullOrEmpty(dto.getNick())) {
			return dto.getNick();
		}
		if (!isNullOrEmpty(dto.getEmail())) {
			return dto.getEmail();
		}
		return dto.getQQOpenId();
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	/**
	 * V币
	 * 
	 * @return
	 */
	public int getVb() {
		return dto.getVb();
	}

	public UserDto getDto() {
		return dto;
	}

	public String getId() {
		return dto.getId();
	}

	public void reduceVb(int vbNeed) {
		if (vbNeed < 0)
			throw new IllegalArgumentException(vbNeed + "");
		
		checkEnouph(vbNeed);
		dto.setVb(dto.getVb() - vbNeed);
		Daos.getUserDao().save(dto);
	}

	private void checkEnouph(int vbNeed) {
		if (dto.getVb() < vbNeed)
			throw new VGameException("V币不够");
	}

	public void addVb(int vbAdd) {
		if (vbAdd <= 0)
			throw new IllegalArgumentException(vbAdd + "");
		dto.setVb(dto.getVb() + vbAdd);
		UserDao dao = Daos.getUserDao();
		dao.save(dto);
	}
}