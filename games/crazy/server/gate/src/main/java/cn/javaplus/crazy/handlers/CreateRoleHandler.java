package cn.javaplus.crazy.handlers;

import cn.javaplus.crazy.base.AbstractHandler;
import cn.javaplus.crazy.base.GateException;
import cn.javaplus.crazy.base.Parameters;
import cn.javaplus.crazy.base.Response;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.crazy.mongo.RoleDao;
import cn.javaplus.crazy.mongo.RoleDao.RoleDtoCursor;
import cn.javaplus.crazy.mongo.RoleDto;
import cn.javaplus.crazy.mongo.UserDao;
import cn.javaplus.crazy.mongo.UserDto;
import cn.javaplus.crazy.token.TokenGenerator;
import cn.javaplus.crazy.token.UserDataBuilder;
import cn.javaplus.util.Util;

/**
 * in: uname token nick personId
 * 
 * out-1: error
 * 
 * out-2: roleData
 */
public class CreateRoleHandler extends AbstractHandler {

	@Override
	protected void doGet(Response response, Parameters ps) {
		String uname = ps.getString("uname");
		String token = ps.getString("token");
		String nick = ps.getString("nick");
		Integer personId = ps.getInteger("personId");

		checkToken(uname, token);
		checkNick(nick);
		checkPersonId(personId);

		UserDao userDao = Daos.getUserDao();
		UserDto userDto = userDao.get(uname);

		int id = userDto.getCurrentRoleId() + 1;

		RoleDto role = createRole(id, uname, nick, personId);

		userDto.setCurrentRoleId(id);
		userDao.save(userDto);

		UserDataBuilder bd = new UserDataBuilder();
		RoleDataAdaptor data = new RoleDataAdaptor(role);
		response.put("roleData", bd.build(data));
	}

	private RoleDto createRole(int id, String uname, String nick,
			Integer personId) {
		RoleDao dao = Daos.getRoleDao();
		RoleDto dto = dao.createDTO();
		dto.setId(id);
		dto.setUname(uname);
		dto.setNick(nick);
		dto.setPersonId(personId);
		dao.save(dto);
		return dto;
	}

	private void checkNick(String nick) {
		checkLength(nick);
		checkSensive(nick);
		checkExist(nick);
	}

	private void checkExist(String nick) {
		RoleDtoCursor c = Daos.getRoleDao().findByNick(nick);
		for (RoleDto d : c) {
			throw new GateException("aready exist:" + d.getNick());
		}
	}

	private void checkSensive(String nick) {
		boolean canUse = Util.Sencitive.canUse(nick);
		if (!canUse) {
			throw new GateException("contains sencitive");
		}
	}

	private void checkLength(String nick) {
		if (nick.length() < 1) {
			throw new GateException("lenth too short");
		}
	}

	private void checkPersonId(int personId) {
		if (personId < 0 || personId > 5) {
			throw new GateException("personId error");
		}
	}

	private void checkToken(String uname, String token) {
		boolean checkToken = TokenGenerator.checkToken(uname, token);

		if(!checkToken) {
			throw new GateException("token error:" + token);
		}
	}

	@Override
	public String getPath() {
		return "createRole";
	}

}