package cn.vgame.a.account;

import cn.vgame.a.events.Events;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.result.ErrorResult;

/**
 * 选定某个角色加入游戏
 * 
 * -----------------
 * 
 * A.正常情况:
 * 	{
 * 		role:{
 * 				id 角色ID,
 * 				ownerId 所属帐号,
 * 				nick 昵称,
 * 				coin 金币数量,
 * 				cd 开奖CD时间(秒)
 * 		}
 * 	}
 * 
 * B.错误:
 *  标准错误: 10002 10006
 * 
 * 
 * 说明: 标准错误, 客户端需要对所有包  统一处理
 *    {
 *    	String error 错误文字,
 *    	int code 这个错误对应到配置表 messages 里面的错误号,
 *      String args [    比如 消息: 10008    message too long len must < %s0     那么 args =[ 10] 时 , 该消息表示  message too long len must < 10
 *      	String arg1...
 *      	String arg2...
 *      	String arg3...
 *      ],
 *    }
 */
public class SelectRoleAction extends JsonAction {

	public class SelectRoleResult {

		private final Role role;

		public SelectRoleResult(Role role) {
			this.role = role;
		}

		public RoleResult getRole() {
			return new RoleResult(role);
		}
	}


	private static final long serialVersionUID = -6099859675509539457L;

	private String roleId;

	@Override
	public Object exec() {
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			return new ErrorResult(10002);
		RoleDao dao = Daos.getRoleDao();
		RoleDto dto = dao.get(getRoleId());
		if (dto == null)
			return new ErrorResult(10006);
		Role role = new Role(dto);
		
		Events.dispatch(new SelectRoleEnterGameEvent(role, session));
		return new SelectRoleResult(role);
	}

	/**
	 * 角色ID
	 */
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
