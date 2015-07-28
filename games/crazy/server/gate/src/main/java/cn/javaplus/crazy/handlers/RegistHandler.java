package cn.javaplus.crazy.handlers;

import cn.javaplus.crazy.base.AbstractHandler;
import cn.javaplus.crazy.base.GateException;
import cn.javaplus.crazy.base.Parameters;
import cn.javaplus.crazy.base.Response;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.crazy.mongo.UserDao;
import cn.javaplus.crazy.mongo.UserDto;
import cn.javaplus.crazy.uname.UnameMather;

/**
 * 注册账号(regist) in: uname pwdMd5
 * 
 * out-1: error out-2: uname
 */
public class RegistHandler extends AbstractHandler {

	@Override
	protected void doGet(Response response, Parameters ps) {
		String uname = ps.getString("uname");
		String pwd = ps.getString("pwdMd5");
		check(uname, pwd);
		saveUser(uname, pwd);
		response.put("uname", uname);
	}

	private void saveUser(String uname, String pwd) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.createDTO();
		dto.setKey(pwd);
		dto.setUname(uname);
		dao.save(dto);
	}

	private void check(String uname, String pwd) {
		if (uname == null) {
			throw new GateException("uname is null");
		}
		checkuname(uname);
		checkPwd(pwd);
		checkExist(uname);
	}

	private void checkExist(String uname) {
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.get(uname);
		if (dto != null) {
			throw new GateException("user aready exist");
		}
	}

	private void checkPwd(String pwd) {
		if (pwd == null || pwd.length() != 32) {
			throw new GateException("pwd format error");
		}
	}

	private void checkuname(String uname) {
		if (!UnameMather.matches(uname)) {
			throw new GateException("uname format error");
		}
	}

	@Override
	public String getPath() {
		return "regist";
	}

}
