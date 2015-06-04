package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 获取本轮排行榜列表 -----------------
 * 
 * A.正常情况: { zhuang:{//庄家 nick:昵称 roleId:角色id coin: 获得金豆 }
 * 
 * me: { //本家 nick:昵称 roleId:角色id coin: 获得金豆 }
 * 
 * rankingList: [ { nick:昵称, roleId:角色id, coin: 获得金豆}, { nick:昵称, roleId:角色id,
 * coin: 获得金豆}, { nick:昵称, roleId:角色id, coin: 获得金豆}, { nick:昵称, roleId:角色id,
 * coin: 获得金豆}, { nick:昵称, roleId:角色id, coin: 获得金豆}, ... ] }
 * 
 * B.错误: 标准错误
 */
public class GetRankingListAction extends JsonActionAfterRoleEnterGame {

	public class Item {
		private final Profit p;

		public Item(Profit p) {
			this.p = p;
		}

		public String getNick() {
			return p.getNick();
		}

		public String getRoleId() {
			return p.getRoleId();
		}

		public long getCoin() {
			return p.getCoin();
		}
	}

	public class RankingListResult {

		private final RankingList rankingList;

		public RankingListResult(RankingList rankingList) {
			this.rankingList = rankingList;
		}

		public Item getZhuang() {
			Profit profit = rankingList.getZhuang();
			if (profit == null)
				return null;
			return new Item(profit);
		}

		public Item getMe() {
			Profit profit = rankingList.get(role.getId());
			if (profit == null)
				return null;
			return new Item(profit);
		}

		public List<Item> getRankingList() {
			List<Profit> pr = rankingList.getProfits();
			ArrayList<Item> ls = Lists.newArrayList();
			for (Profit p : pr) {
				if (p.getCoin() > 0) {
					ls.add(new Item(p));
				}
			}
			return ls;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3605646833178193437L;

	@Override
	public Object run() {
		RankingList rl = Turntable.getInstance().getRankingList();
		return new RankingListResult(rl);
	}

}
