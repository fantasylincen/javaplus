package org.hhhhhh.guess.hibernate.dao;


public class Daos {

	public static UserDao getUserDao() {
		return new UserDao();
	}

	public static KeyValueDao getKeyValueDao() {
		return new KeyValueDao();
	}


	public static RoundDao getRoundDao() {
		return new RoundDao();
	}

}
