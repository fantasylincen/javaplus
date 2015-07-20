package org.hhhhhh.house.hibernate.dao;

import java.util.List;

import org.hhhhhh.house.hibernate.HibernateSessionFactory;
import org.hhhhhh.house.hibernate.dto.HouseDto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HouseDao {

	public void save(HouseDto dto) {
		new DbCommit().save(dto);
	}

	public HouseDto get(String id) {
		List<HouseDto> it = find("id", id);
		if (!it.isEmpty())
			return it.get(0);
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HouseDto> find(String field, Object v) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		List list;
		try {
			String hql = "from HouseDto where " + field + "=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, v);
			query.setCacheable(true);
			list = query.list();
		} finally {
			session.close();
		}
		return list;
		// Iterator<HouseDto> it = query.iterate();
		//
		// return new HouseDtoCursor(it);
	}

	// @SuppressWarnings("unchecked")
	// public HouseDtoCursor find() {
	// SessionFactory sf = HibernateSessionFactory.getSessionFactory();
	// Session session = sf.openSession();
	//
	// String hql = "from HouseDto";
	// Query query = session.createQuery(hql);
	// query.setCacheable(true);
	// Iterator<HouseDto> it = query.iterate();
	//
	// return new HouseDtoCursor(it);
	// }
	// @SuppressWarnings("unchecked")
	// public HouseDtoCursor findSortBy(String sort) {
	// SessionFactory sf = HibernateSessionFactory.getSessionFactory();
	// Session session = sf.openSession();
	//
	// String hql = "from HouseDto order by " + sort + " desc";
	// Query query = session.createQuery(hql);
	// query.setCacheable(true);
	// Iterator<HouseDto> it = query.iterate();
	//
	// return new HouseDtoCursor(it);
	// }

	@SuppressWarnings("unchecked")
	public List<HouseDto> findSortByLimit(String sort, int limit) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();

		try {
			String hql = "from HouseDto order by " + sort + " desc limit = "
					+ limit;
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			return query.list();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
	}

	public void save(List<HouseDto> dtos) {
		new DbCommit().save(dtos);
	}
}