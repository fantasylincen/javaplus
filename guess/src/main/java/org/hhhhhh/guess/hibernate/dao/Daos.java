package org.hhhhhh.guess.hibernate.dao;



public class Daos {

	public static UserDao getUserDao() {
		return new UserDao();
	}

	public static SystemKeyValueDao getSystemKeyValueDao() {
		return new SystemKeyValueDao();
	}

	public static AnswersDao getAnswersDao() {
		return new AnswersDao();
	}

}
