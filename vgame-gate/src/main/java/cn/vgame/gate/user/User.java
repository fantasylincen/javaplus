package cn.vgame.gate.user;

import cn.vgame.gate.gen.dto.MongoGen.UserDto;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}

	public String getPassword() {
		return dto.getPassword();
	}

}
