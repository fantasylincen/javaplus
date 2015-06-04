package cn.mxz.server.handlers;

import cn.javaplus.util.Util;
import cn.mxz.server.base.AbstractHandler;
import cn.mxz.server.base.Parameters;
import cn.mxz.server.base.Response;
import cn.mxz.server.mongo.MongoGen.Daos;
import cn.mxz.server.mongo.MongoGen.RoleDao;
import cn.mxz.server.mongo.MongoGen.RoleDto;
import cn.mxz.server.system.GameSystem;
import cn.mxz.server.system.Key;
import cn.mxz.server.system.SystemCounter;

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
		dto.setNick(dto.getId());
		dao.save(dto);
		c.add(Key.USER_ID, 1);

		response.put("roldId", dto.getId());
		response.put("password", password);
	}

}
