package cn.vgame.b.account;

import java.util.ArrayList;
import java.util.List;

import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.b.result.ErrorResult;

import com.google.common.collect.Lists;

/**
 * 获取角色列表
 * 
 * -----------------
 * 
 * A.正常情况:
 * 	{
 * 		roles:[
 * 				角色ID1,角色ID2,角色ID3,角色ID4,角色ID5
 * 		]
 * 	}
 * 
 * B.错误:
 *  标准错误: 
 *  		10005 you not enter server
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
public class GetRoleListAction extends JsonAction {

	public class RoleListResult {

		private final String userId;

		public RoleListResult(String userId) {
			this.userId = userId;
		}

		/**
		 * 该玩家在服务器内, 所有的角色
		 */
		public List<String> getRoles() {
			RoleDao dao = Daos.getRoleDao();
			RoleDtoCursor c = dao.findByOwnerId(userId);
			ArrayList<String> ls = Lists.newArrayList();
			for (RoleDto r : c) {
				ls.add(r.getId());
			}
			return ls;
		}
	}


	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public Object exec() {
		String userId = (String) session.getAttribute("userId");
		if(userId == null)
			return new ErrorResult(10005);
		return new RoleListResult(userId);
	}

}
