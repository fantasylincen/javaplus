package org.hhhhhh.prophet.hibernate.dao;

import java.util.Iterator;

import org.hhhhhh.prophet.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.prophet.hibernate.dto.UserDto;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public class Daos {

	public static class SystemKeyValueDao {

		public void save(SystemKeyValueDto dto) {
			// TODO LC
		}

		public SystemKeyValueDto get(String key) {
			// TODO LC
			return null;
		}

	}

	public static class UserDao extends HibernateDaoSupport {

		public void save(UserDto dto) {
			getHibernateTemplate().saveOrUpdate(dto);
		}

		public UserDto get(String id) {
			HibernateTemplate t = getHibernateTemplate();
			UserDto userDto = t.get(UserDto.class, id);
			UserDto user = (UserDto) userDto;
			return user;
		}

		public UserDtoCursor find(String field, String v) {
			Iterator<UserDto> it = getHibernateTemplate().iterate(
					"from user where field='" + v + "'");
			return new UserDtoCursor(it);
		}

	}

	public static class UserDtoCursor {

		private final Iterator<UserDto> it;

		public UserDtoCursor(Iterator<UserDto> it) {
			this.it = it;
		}

		public boolean hasNext() {
			return it.hasNext();
		}

		public UserDto next() {
			return it.next();
		}

	}

	public static UserDao getUserDao() {
		return new UserDao();
	}

	public static SystemKeyValueDao getSystemKeyValueDao() {
		return new SystemKeyValueDao();
	}

}
