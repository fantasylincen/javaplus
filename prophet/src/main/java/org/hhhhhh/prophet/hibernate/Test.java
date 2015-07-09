package org.hhhhhh.prophet.hibernate;

import org.hhhhhh.prophet.hibernate.dao.Daos;
import org.hhhhhh.prophet.hibernate.dao.Daos.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {

		// try {
		// SessionFactory sf =
		// new Configuration().configure().buildSessionFactory();
		// Session session = sf.openSession();
		// Transaction tx = session.beginTransaction();
		//
		// for (int i = 0; i < 200; i++) {
		// UserDto d = new UserDto();
		// d.setId("customer" + i);
		// d.setPwd("customer");
		// d.setJiFen(1);
		// d.setNick("");
		// d.setEmail("111@111.com");
		// session.save(d);
		// }
		//
		// tx.commit();
		// session.close();
		//
		// } catch (HibernateException e) {
		// e.printStackTrace();
		// }

		UserDao dao = Daos.getUserDao();
		Configuration c = new Configuration();
		SessionFactory sf = c.configure().buildSessionFactory();
		dao.setSessionFactory(sf);
		dao.get("111111");
	}
}