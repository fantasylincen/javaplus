package org.hhhhhh.guess.account4app;

import org.hhhhhh.guess.hibernate.dto.UserDto;

public class RegistSuccessfulResult {

	private final UserDto dto;

	public RegistSuccessfulResult(UserDto dto) {
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}

	public String getUsername() {
		return dto.getUsername();
	}

	public String getNick() {
		return dto.getNick();
	}

	public int getJiFen() {
		return dto.getJiFen();
	}

}
