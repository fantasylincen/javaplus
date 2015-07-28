package cn.javaplus.crazy.handlers;

import cn.javaplus.crazy.base.AbstractHandler;
import cn.javaplus.crazy.base.GateException;
import cn.javaplus.crazy.base.Parameters;
import cn.javaplus.crazy.base.Response;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.crazy.mongo.UserDao;
import cn.javaplus.crazy.mongo.UserDto;
import cn.javaplus.util.Util;

/**
 * 一键注册账号(oneKeyRegist) in:
 * 
 * out-1: required uname required password 明文
 */
public class OneKeyRegistHandler extends AbstractHandler {

	public class UserBean {
		private String uname;
		private String pwd;

		public boolean exist() {
			UserDao dao = Daos.getUserDao();
			return dao.get(uname) != null;
		}

	}

	@Override
	protected void doGet(Response response, Parameters ps) {

		UserBean b = null;

		for (int i = 0; i < 100; i++) {
			b = getRandomUser();
			if (!b.exist()) {
				break;
			}
		}
		if (b == null) {
			throw new GateException("regist faild please try again");
		}
		saveUser(b);
		response.put("uname", b.uname);
		response.put("password", b.pwd);
	}

	private void saveUser(UserBean b) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.createDTO();
		dto.setKey(Util.Secure.md5(b.pwd));
		dto.setUname(b.uname);
		dao.save(dto);
	}

	private UserBean getRandomUser() {
		UserBean b = new UserBean();
		b.uname = "u" + Util.Random.getRandomString(10).toLowerCase();
		b.pwd = Util.Random.getRandomString(8);
		return b;
	}

	@Override
	public String getPath() {
		return "oneKeyRegist";
	}

}
