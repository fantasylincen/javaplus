package org.hhhhhh.guess.hibernate.dao;

import org.hhhhhh.guess.hibernate.HibernateSessionFactory;
import org.hhhhhh.guess.hibernate.dto.AnswersDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AnswersDao {

	public AnswersDto get(String key) {
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		try {
			AnswersDto s = (AnswersDto) session.get(AnswersDto.class, key);
			return s;
		} finally {
			session.close();
		}
	}

	public void save(AnswersDto dto) {
		new DbCommit().save(dto);
	}

}
