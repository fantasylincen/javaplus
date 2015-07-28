package cn.javaplus.twolegs.handlers;

import cn.javaplus.twolegs.base.AbstractHandler;
import cn.javaplus.twolegs.base.Parameters;
import cn.javaplus.twolegs.base.Response;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.NickDao;
import cn.javaplus.twolegs.mongo.MongoGen.NickDto;
import cn.javaplus.twolegs.mongo.MongoGen.NickEnDao;
import cn.javaplus.twolegs.mongo.MongoGen.NickEnDto;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDao;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDto;
import cn.javaplus.twolegs.system.GameSystem;
import cn.javaplus.twolegs.system.Key;
import cn.javaplus.twolegs.system.SystemCounter;
import cn.javaplus.util.Util;

public class OneKeyRegistHandler extends AbstractHandler {

	@Override
	protected void handle(Response response, Parameters ps) {

		SystemCounter c = GameSystem.getCounter();
		int id = c.get(Key.USER_ID);

		RoleDao dao = Daos.getRoleDao();
		RoleDto dto = dao.createDTO();

		String password = Util.Random.getRandomString(8);
		String passwordMd5 = Util.Secure.md5(password);

		dto.setPasswordMd5(passwordMd5);
		dto.setId(id + "");
		dto.setNick(getRandomNick());
		dao.save(dto);
		c.add(Key.USER_ID, 1);

		response.put("roleId", dto.getId());
		response.put("password", password);
		response.put("nick", dto.getNick());
	}

	private String getRandomNick() {
		if (Util.Random.isHappen(0.3f)) {
			return getCn();
		} else {
			return getEn();
		}
	}

	private String getEn() {
		NickEnDao dao = Daos.getNickEnDao();
		NickEnDto dto = dao.get(Util.Random.get(0, 28119));
		return dto.getNick();
	}

	private String getCn() {
		NickDao dao = Daos.getNickDao();
		NickDto dto = dao.get(Util.Random.get(0, 81044));
		return dto.getNick();
	}

}
