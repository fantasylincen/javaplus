package org.hhhhhh.guess.hibernate.dao;

import java.util.List;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DbCommit {

	public void save(Object dto) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			s.saveOrUpdate(dto);
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}
	

	public void save(List<Object> dtos) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			
			for (Object dto : dtos) {
				s.saveOrUpdate(dto);
			}
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}

}
