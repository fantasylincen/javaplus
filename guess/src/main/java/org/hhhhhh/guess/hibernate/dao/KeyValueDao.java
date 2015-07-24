package org.hhhhhh.guess.hibernate.dao;

import org.hhhhhh.guess.hibernate.dto.KeyValueDto;


public class KeyValueDao {

	public KeyValueDto createDTO() {
		return new KeyValueDto();
	}

	public KeyValueDto get(String key) {
		return DbUtil.get(KeyValueDto.class, key);
	}


}