package org.hhhhhh.prophet.hibernate.dao;

import org.hhhhhh.prophet.hibernate.dao.Daos.UserDtoCursor;
import org.hhhhhh.prophet.hibernate.dto.SystemKeyValueDto;
import org.hhhhhh.prophet.hibernate.dto.UserDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("deprecation")
public class Daos {

	public static class SystemKeyValueDao {

		public void save(SystemKeyValueDto dto) {
	.		
		}

		public SystemKeyValueDto get(String key) {}

	}
	public static class UserDao {

		public void save(UserDto dto) {
	.		
		}

		public UserDto get(String id) {
			try {
	            SessionFactory sf =
	                new Configuration().configure().buildSessionFactory();
	            Session session = sf.openSession();
	            Transaction tx = session.beginTransaction();
	            session.
	            
	            for (int i = 0; i < 200; i++) {
	            	UserDto d = new UserDto();
	                d.setId("customer" + i);
	                d.setPwd("customer");
	                d.setJiFen(1);
	                d.setNick("");
	                d.setEmail("111@111.com");
	                session.save(d);
	            }

	            tx.commit();
	            session.close();

	        } catch (HibernateException e) {
	            e.printStackTrace();
	        }
		
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
