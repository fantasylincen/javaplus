package org.hhhhhh.prophet.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {

//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
//		Session session = sessionFactory.openSession();
//
//		String hql = "from Emp";
//
//		Query query = session.createQuery(hql);
//
//		Iterator<UserDto> it = erate();
//
//		while (it.hasNext()) {
//
//			Emp emp = it.next();
//
//			System.out.println(emp);
//
//		}
//
//		session.close();
		// UserDao dao = Daos.getUserDao();
		// Configuration c = new Configuration();
		// SessionFactory sf = c.configure().buildSessionFactory();
		// dao.setSessionFactory(sf);
		// dao.get("111111");
		
		Configuration c = new Configuration();
		 SessionFactory sf = c.configure().buildSessionFactory();
		 
		 
	     String hsql="from user";
	        Session session = sf.getCurrentSession();
	        Query query = session.createQuery(hsql);
	        
	         List list = query.list();
	         System.out.println(list);
	}
}