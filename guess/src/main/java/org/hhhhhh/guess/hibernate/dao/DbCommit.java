package org.hhhhhh.guess.hibernate.dao;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DbCommit {

	public void save(UserDto dto) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			s.save(dto);
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}

}
