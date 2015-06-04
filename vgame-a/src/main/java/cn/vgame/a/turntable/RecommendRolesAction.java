package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.util.Util;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

/**
 * 获得彩金历史记录
 * 
 * -----------------
 * 
 * A.正常情况:
 * 
 * 获得随机推荐20个玩家 传入参数 count 可选项, 如果不发,默认20个 返回 { count:数量, roles: [ { roleId:角色id,
 * nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
 * nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id, nick昵称}, { roleId:角色id,
 * nick昵称}, ] }
 * 
 * B.错误: 标准错误
 */

public class RecommendRolesAction extends JsonActionAfterRoleEnterGame {

	public class RoleResult {
		private final RoleDto dto;

		public RoleResult(RoleDto dto) {
			this.dto = dto;
		}

		public String getRoleId() {
			return dto.getId();
		}

		public String getNick() {
			return dto.getNick();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4097085491428663337L;

	public class RecommendRolesResult {

		private List<RoleResult> roles = Lists.newArrayList();
		private int count;

		/**
		 * 每次请求返回多少个玩家
		 */
		public final static int COUNT_EVERY_REQUEST = 20;

		public RecommendRolesResult() {
			RoleDao dao = Daos.getRoleDao();
			RoleDtoCursor all = dao.findByIsOnline(true);
			count = all.getCount();
			roles = Lists.newArrayList();

			int max = count - COUNT_EVERY_REQUEST - 1;
			if (max < 0) {
				max = 0;
			}

			int index = Util.Random.get(0, max);

			all.skip(index);
			all.limit(COUNT_EVERY_REQUEST);

			for (RoleDto r : all) {
				roles.add(new RoleResult(r));
				
			}

			int robotCount = getRobotCount(roles.size());
			roles.addAll(getRobots(robotCount));
		}

		private int getRobotCount(int size) {
			int a = COUNT_EVERY_REQUEST - size;
			if (a < 0)
				a = 0;
			return a;
		}

		private List<RoleResult> getRobots(int count) {
			ArrayList<RoleResult> ls = Lists.newArrayList();
			if (count <= 0) {
				return ls;
			}
			RoleDtoCursor find = Daos.getRoleDao().findByIsRobot(true);
			int c = find.getCount();

			int max = c - 1 - count;
			if (max < 0)
				max = 0;
			int startIndex = Util.Random.get(0, max);

			find.skip(startIndex);
			find.limit(count);

			for (RoleDto dto : find) {
				ls.add(new RoleResult(dto));
				this.count++;
			}
			return ls;
		}

		public int getCount() {
			return count;
		}

		public List<RoleResult> getRoles() {
			return roles;
		}
	}

	@Override
	protected Object run() {
		return new RecommendRolesResult();
	}
}
