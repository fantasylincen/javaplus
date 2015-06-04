package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.gen.dto.MongoGen.CaiJinLogDao;
import cn.vgame.a.gen.dto.MongoGen.CaiJinLogDao.CaiJinLogDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.CaiJinLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;

/**
 * 获得彩金历史记录
 * 
 * -----------------
 * 
 * A.正常情况: 
 * 
 * {
 * 		historys: [
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量},
 *			{ id:历史记录id, roleId:获得彩金的角色id, nick获得彩金的角色昵称, caiJin获得彩金的数量}
 *		]
 *  }
 * 
 * B.错误: 标准错误
 */

public class GetCaiJinHistoryAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -77594421627978148L;


	public class CaiJinHistory {

		private final CaiJinLogDto dto;

		public CaiJinHistory(CaiJinLogDto dto) {
			this.dto = dto;
		}

		public String getId() {
			return dto.getId();
		}

		public String getRoleId() {
			return dto.getRoleId();
		}

		public String getNick() {
			return dto.getNick();
		}

		public long getCaiJin() {
			return dto.getCaiJin();
		}
		
	}

	public class CaiJinHistoryResult {
		
		private static final int LIST_SIZE = 20;

		public List<CaiJinHistory> getHistorys() {
			CaiJinLogDao dao = Daos.getCaiJinLogDao();
			CaiJinLogDtoCursor find = dao.find();
			int count = find.getCount();
			int skip = count - LIST_SIZE;
			skip = Math.max(0, skip);
			find.skip(skip);
			ArrayList<CaiJinHistory> ls = Lists.newArrayList();
			for (CaiJinLogDto dto : find) {
				ls.add(new CaiJinHistory(dto));
			}
			return ls;
		}
	}


	@Override
	protected Object run() {
		return new CaiJinHistoryResult();
	}
}
