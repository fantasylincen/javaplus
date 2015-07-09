package org.hhhhhh.prophet.hibernate.dao;

import org.hhhhhh.prophet.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.prophet.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.prophet.hibernate.dto.UserDto;


public class Daos {

	public static class SystemKeyValueDao {

		public void save(SystemKeyValueDto dto) {
	.		
		}

		public SystemKeyValueDto get(String key) {
		}

	}
	public static class UserDao {

		public void save(UserDto dto) {
	.		
		}

		public UserDto get(String id) {
		}

		public UserDtoCursor find(String field, String v) {
		}

	}

	public static class UserDtoCursor {

		public boolean hasNext() {
		}

		public UserDto next() {
		}
		
	}
	public static UserDao getUserDao() {
		return new UserDao();
	}

	public static SystemKeyValueDao getSystemKeyValueDao() {
		return new SystemKeyValueDao();
	}

}
