package cn.javaplus.crazy.handlers;

import cn.javaplus.crazy.base.AbstractHandler;
import cn.javaplus.crazy.base.GateException;
import cn.javaplus.crazy.base.Parameters;
import cn.javaplus.crazy.base.Response;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.crazy.mongo.RoleDao;
import cn.javaplus.crazy.mongo.RoleDto;
import cn.javaplus.crazy.mongo.UserDao;
import cn.javaplus.crazy.mongo.UserDto;
import cn.javaplus.crazy.token.TokenGenerator;
import cn.javaplus.crazy.token.UserDataBuilder;

/**
 * 登录请求(login) in: uname pwdMd5
 * 
 * out-1: required error out-2: required token optional roleData 加密后的角色数据
 * 
 * 表示 比如请求: GateUrl/login?uname=574907580@qq.com&password=
 * ADOIU546456131AFS1216544S1216544 会有2种返回: 1.{"error":"xxxxx"}
 * 2.{"token":xxxxxxxxxx,"roleData":"XXXXXXXX"} 或者 {"token":xxxxxxxxxx}
 * 
 * required 表示必须有的 optional 表示可能没有的
 */
public class LoginHandler extends AbstractHandler {

	@Override
	protected void doGet(Response response, Parameters ps) {
		String uname = ps.getString("uname");
		String key = ps.getString("pwdMd5");
		check(uname, key);
		String token = TokenGenerator.generate(uname);
		response.put("token", token);

		UserDto dto = Daos.getUserDao().get(uname);
		int currentRoleId = dto.getCurrentRoleId();
		if (currentRoleId != 0) {
			RoleDao roleDao = Daos.getRoleDao();
			RoleDto roleDto = roleDao.get(uname, currentRoleId);
			UserDataBuilder b = new UserDataBuilder();
			response.put("roleData", b.build(new RoleDataAdaptor(roleDto)));
		}
	}

	private void check(String uname, String key) {
		if (key == null) {
			throw new GateException("pwdMd5 is null");
		}
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.get(uname);

		if(dto == null) {
			throw new GateException("user not exist");
		}
		
		if (dto == null || !dto.getKey().equals(key)) {
			throw new GateException("uname or pwdMd5 error");
		}
	}

	@Override
	public String getPath() {
		return "login";
	}

}
