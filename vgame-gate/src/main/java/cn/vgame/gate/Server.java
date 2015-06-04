package cn.vgame.gate;

import cn.javaplus.util.Util;
import cn.vgame.gate.gen.dto.MongoGen.Daos;
import cn.vgame.gate.gen.dto.MongoGen.UserDao;
import cn.vgame.gate.gen.dto.MongoGen.UserDto;
import cn.vgame.gate.user.User;


public class Server {


	/**
	 * 每次获得到的对象都不一样
	 */
	public static User loadUserById(String id) {
		Util.Exception.checkNull(id, "id null");
		UserDao dao = Daos.getUserDao();
		UserDto c = dao.get(id);
		if (c == null)
			return null;
		return new User(c);
	}
}
