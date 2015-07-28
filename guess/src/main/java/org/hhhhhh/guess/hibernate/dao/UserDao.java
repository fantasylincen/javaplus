package org.hhhhhh.guess.hibernate.dao;

import java.util.List;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDao {

	public void save(UserDto dto) {
		new DbUtil().save(dto);
	}

	public UserDto get(String id) {
		List<UserDto> it = find("id", id);
		if (!it.isEmpty())
			return it.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserDto> find(String field, String v) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		try {
			String hql = "from UserDto where " + field + "=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, v);
			query.setCacheable(true);
			return query.list();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserDto> findRankingList() {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		try {
			String hql = "from UserDto order by jiFen";
			Query query = session.createQuery(hql);
			query.setMaxResults(100);
			query.setCacheable(true);
			return query.list();
		} finally {
			session.close();
		}

	}
}