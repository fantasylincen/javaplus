package org.hhhhhh.guess.hibernate.dao;

import java.util.List;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@SuppressWarnings("rawtypes")
public class DbUtil {

	public static void save(Object dto) {
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

	public static void save(List<Object> dtos) {
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

	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> clazz, String key) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		try {
			T s = (T) session.get(clazz, key);
			return s;
		} finally {
			session.close();
		}
	}

	public static List find(String tableName, String field, String v) {

		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		try {
			String hql = "from " + tableName + " where " + field + "=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, v);
			query.setCacheable(true);
			return query.list();
		} finally {
			session.close();
		}
	}

	public static List find(String tableName) {

		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		try {
			String hql = "from " + tableName;
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			return query.list();
		} finally {
			session.close();
		}
	}

	public static void delete(Object dto) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session s = null;
		Transaction t = null;
		try {
			s = sf.openSession();
			t = s.beginTransaction();
			s.delete(dto);
			t.commit();
		} catch (Exception err) {
			t.rollback();
			err.printStackTrace();
		} finally {
			s.close();
		}
	}

}
