package org.hhhhhh.house.hibernate.dao;

import org.hhhhhh.house.hibernate.HibernateSessionFactory;
import org.hhhhhh.house.hibernate.dto.HouseDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DbCommit {

	public void save(HouseDto dto) {
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

}
