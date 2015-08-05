package cn.vgame.b.account;

import java.util.ArrayList;
import java.util.List;

import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.result.ErrorResult;

import com.google.common.collect.Lists;

/**
 * 获取角色列表
 * 
 * -----------------
 * 
 * A.正常情况:
 * 	{
 * 	}
 * 
 * B.错误:
 *  标准错误: 
 *  		10005 you not enter server
 * 
 * 
 * 说明: 标准错误, 客户端需要对所有包  统一处理
 *    {
 *    }
 */
public class GetRoleList2Action extends JsonAction {

	public class RoleListResult {

		private final String userId;

		public RoleListResult(String userId) {
			this.userId = userId;
		}

		/**
		 * 该玩家在服务器内, 所有的角色
		 */
		public List<RoleResult> getRoles() {
			RoleDao dao = Daos.getRoleDao();
			RoleDtoCursor c = dao.findByOwnerId(userId);
			ArrayList<RoleResult> ls = Lists.newArrayList();
			for (RoleDto r : c) {
				ls.add(new RoleResult(r));
			}
			return ls;
		}
	}
	
	public class RoleResult {
		private final RoleDto r;
		public RoleResult(RoleDto r) {
			this.r = r;
		}
		public String getId() {
			return r.getId();
		}
		public String getNick() {
			return r.getNick();
		}
		public long getCoin() {
			return r.getCoin();
		}
		public boolean getHasJinYan() {
			return r.getHasJinYan();
		}
		public boolean getHasFengHao() {
			return r.getHasFengHao();
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
