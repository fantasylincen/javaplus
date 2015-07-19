package org.hhhhhh.house.hibernate.dao;

import java.util.Iterator;

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
		HouseDtoCursor it = find("id", id);
		if (it.hasNext())
			return it.next();
		return null;
	}

	@SuppressWarnings("unchecked")
	public HouseDtoCursor find(String field, String v) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from HouseDto where " + field + "=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, v);
		query.setCacheable(true);
		Iterator<HouseDto> it = query.iterate();
		
		return new HouseDtoCursor(it);
	}
	
	@SuppressWarnings("unchecked")
	public HouseDtoCursor find() {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from HouseDto";
		Query query = session.createQuery(hql);
		query.setCacheable(true);
		Iterator<HouseDto> it = query.iterate();
		
		return new HouseDtoCursor(it);
	}
}