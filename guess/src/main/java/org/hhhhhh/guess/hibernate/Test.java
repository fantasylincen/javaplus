package org.hhhhhh.guess.hibernate;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dto.UserDto;

public class Test {
	public static void main(String[] args) {
		UserDto dto = new UserDto();
		dto.setId("123123");
		dto.setJiFen(1);
		dto.setNick("lakj");
		dto.setPassword("falskdj");
		dto.setUsername("poiqwer");
		Daos.getUserDao().save(dto);
	}
}
