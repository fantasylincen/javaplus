package org.hhhhhh.guess.hibernate;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDao;
import org.hhhhhh.guess.hibernate.dao.Daos.UserDtoCursor;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDao dao = Daos.getUserDao();
		
//		UserDto dto = new UserDto();
//		dto.setId("ccc");
//		dto.setJiFen(100);
//		dto.setNick("nick");
//		dto.setPassword("pwd");
//		dto.setUsername("aaa");
//		dao.save(dto);
		
		UserDtoCursor c = dao.find("id", "ccc");
		System.out.println(c.hasNext());
	}

}
