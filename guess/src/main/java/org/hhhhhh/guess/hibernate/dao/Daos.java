package org.hhhhhh.guess.hibernate.dao;

import java.util.Iterator;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hhhhhh.guess.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

	public static class UserDao {

		public void save(UserDto dto) {
			new DbCommit().save(dto);
		}

		public UserDto get(String id) {
			UserDtoCursor it = find("id", id);
			if (it.hasNext())
				return it.next();
			return null;
		}

		public UserDtoCursor find(String field, String v) {
			SessionFactory sf = HibernateSessionFactory.getSessionFactory();
			Session session = sf.openSession();
			
			String hql = "from UserDto where " + field + "=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, v);
			query.setCacheable(true);
			Iterator<UserDto> it = query.iterate();
			
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
