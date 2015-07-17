package cn.vgame.a.account;

import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.events.Events;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.a.util.RoleIdGenerator;
import cn.vgame.share.EncodingUtil;

/**
 * 创建角色
 * 
 * -----------------
 * 
 * A.正常情况: { role:{ id 角色ID, ownerId 所属帐号, nick 昵称, coin 金币数量, cd 开奖CD时间(秒) } }
 * 
 * B.错误: 标准错误: 10004 nick contains sencitive word 10005 you not enter server
 * 
 * 
 * 说明: 标准错误, 客户端需要对所有包 统一处理 { String error 错误文字, int code 这个错误对应到配置表 messages
 * 里面的错误号, String args [ 比如 消息: 10008 message too long len must < %s0 那么 args =[
 * 10] 时 , 该消息表示 message too long len must < 10 String arg1... String arg2...
 * String arg3... ], }
 */
public class CreateRoleAction extends JsonAction {

	public class ErrorJson {

		private final String error;

		public ErrorJson(String error) {
			this.error = error;
		}

		public String getError() {
			return error;
		}
	}

	private static final long serialVersionUID = -6099859675509539457L;

	private String nick;

	@Override
	public Object exec() {
		boolean canUse = Util.Sencitive.canUse(getNick());
		if (!canUse) {
			return new ErrorResult(10004);
		}
		
		if(isAreadyUse()) {
			return new ErrorResult(10025);
		}
		
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			return new ErrorResult(10005);
		RoleDto dto = createNewUser(userId);
		Role role = new Role(dto);

		Events.dispatch(new SelectRoleEnterGameEvent(role, session));
		Events.dispatch(new CreateRoleEvent(role, session));
		
		Server.getKeyValueSaveOnly().add("CREATE_ROLE_COUNT", 1);
		
		return new CreateRoleResult(role, session);
	}

	private boolean isAreadyUse() {
		RoleDtoCursor d = Daos.getRoleDao().findByNick(nick);
		return d.hasNext();
	}

	private RoleDto createNewUser(String userId) {
		
		RoleDao dao = Daos.getRoleDao();
		
		RoleDto dto = dao.createDTO();
		dto.setId(RoleIdGenerator.createRoleId());
		dto.setNick(nick);
		dto.setOwnerId(userId);
		dto.setCreateTime(System.currentTimeMillis());
		dao.save(dto);
		return dto;
	}

	/**
	 * 昵称
	 */
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		nick = EncodingUtil.iso2Utf8(nick);
		this.nick = nick;
	}

}
