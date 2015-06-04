package com.cnbizmedia;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.UserDao;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;
import com.cnbizmedia.gen.dto.MongoGen.UserDao.UserDtoCursor;
import com.cnbizmedia.recharge.RechargeManager;
import com.cnbizmedia.user.User;

public class Server {

	private static RechargeManager rechargeManager;

	public static RechargeManager getRechargeManager() {
		if (rechargeManager == null) {
			rechargeManager = new RechargeManager();
			new Thread(rechargeManager).start();
		}
		return rechargeManager;
	}

	/**
	 * 每次获得到的对象都不一样
	 */
	public static User loadUserByEmail(String email) {

		UserDto dto = findByEmail(email);
		if (dto == null)
			return null;
		return new User(dto);
	}

	private static UserDto findByEmail(String email) {
		if(email == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDtoCursor c = dao.findByEmail(email);
		if (c.hasNext())
			return c.next();
		return null;
	}

	/**
	 * 每次获得到的对象都不一样
	 */
	public static User loadUserById(String id) {
		if(id == null)
			throw new NullPointerException();
		UserDao dao = Daos.getUserDao();
		UserDto c = dao.get(id);
		if (c == null)
			return null;
		return new User(c);
	}
}
