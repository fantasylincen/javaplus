package cn.javaplus.twolegs.handlers;

import cn.javaplus.twolegs.base.AbstractHandler;
import cn.javaplus.twolegs.base.GameServerException;
import cn.javaplus.twolegs.base.Parameters;
import cn.javaplus.twolegs.base.Response;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDao;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDto;
import cn.javaplus.twolegs.token.TokenGenerator;
import cn.javaplus.util.Util;

public class LoginHandler extends AbstractHandler {

	@Override
	protected void handle(Response response, Parameters ps) {
		String roleId = ps.getString("roleId");
		String key = getPasswordMd5(ps);

		check(roleId, key);
		String token = TokenGenerator.generate(roleId);
		response.put("token", token);
		response.put("roleId", roleId);
	}

	private String getPasswordMd5(Parameters ps) {
		String key = ps.getString("passwordMd5");

		if (key == null) {
			key = ps.getString("password");
			key = Util.Secure.md5(key);
		}
		return key;
	}

	private void check(String roleId, String passwordMd5) {
		if (passwordMd5 == null) {
			throw new GameServerException("passwordMd5 is null");
		}
		RoleDao dao = Daos.getRoleDao();
		RoleDto dto = dao.get(roleId);

		if (dto == null) {
			throw new GameServerException("role not exist:" + roleId);
		}

		if (dto == null || !dto.getPasswordMd5().equals(passwordMd5)) {
			throw new GameServerException("id or passwordMd5 error");
		}
	}
}
