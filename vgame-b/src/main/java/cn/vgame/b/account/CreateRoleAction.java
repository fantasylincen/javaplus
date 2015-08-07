package cn.vgame.b.account;

import cn.javaplus.util.Util;
import cn.vgame.b.Server;
import cn.vgame.b.events.Events;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.b.util.RoleIdGenerator;
import cn.vgame.share.EncodingUtil;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;

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
	private String deviceId;
    private int  head;
	private String ip;

	@Override
	public Object exec() {

		ip = request.getRemoteAddr();

		boolean canUse = Util.Sencitive.canUse(getNick());
		if (!canUse) {
			return new ErrorResult(10004);
		}

		if (isAreadyUse()) {
			return new ErrorResult(10025);
		}

		if (deviceId != null && deviceCreateTooMany()) {
			return new ErrorResult(10110);
		}

		if (ipCreateTooMany()) {
			return new ErrorResult(10110);
		}

		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			return new ErrorResult(10005);
		RoleDto dto = createNewUser(userId);
		Role role = new Role(dto);

		Events.dispatch(new SelectRoleEnterGameEvent(role, session, request));
		Events.dispatch(new CreateRoleEvent(role, session));

		KeyValueSaveOnly da = Server.getKeyValueSaveOnly();
		da.add("CREATE_ROLE_COUNT", 1);

		if (deviceId != null)
			da.add("CREATE_ROLE_COUNT:" + deviceId, 1);
		
		da.add("CREATE_ROLE_COUNT:" + ip, 1);//记录该IP创建角色的次数

		return new CreateRoleResult(role, session);
	}
	

	private boolean ipCreateTooMany() {
		return false;
//		KeyValue da = Server.getKeyValueDaily();
//		int createCount = da.getInt("CREATE_ROLE_COUNT:" + ip);
//		return createCount > 5; // 一个设备每天最多创建5个帐户
	}

	private boolean deviceCreateTooMany() {
		KeyValue da = Server.getKeyValueDaily();
		int createCount = da.getInt("CREATE_ROLE_COUNT:" + deviceId);
		return createCount > 5; // 一个设备每天最多创建5个帐户
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
		dto.setHead(getHead());
		dto.setOwnerId(userId);
		dto.setCreateTime(System.currentTimeMillis());
		dto.setCreateIp(ip);
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public int getHead() {
		return head;
	}


	public void setHead(int head) {
		this.head = head;
	}

}
