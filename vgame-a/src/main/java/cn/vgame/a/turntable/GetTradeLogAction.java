package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDao;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;

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
public class GetTradeLogAction extends JsonActionAfterRoleEnterGame {

	public class FindByFrom implements Finder {

		@Override
		public CoinLogDtoCursor find(String id) {
			CoinLogDao dao = Daos.getCoinLogDao();
			CoinLogDtoCursor c = dao.findByFrom(id);
			return c;
		}

	}

	public class FindByTo implements Finder {

		@Override
		public CoinLogDtoCursor find(String id) {
			CoinLogDao dao = Daos.getCoinLogDao();
			CoinLogDtoCursor c = dao.findByTo(id);
			return c;
		}

	}

	public class TradeLogItem {
		
		String otherId; // 赠送方ID
		String otherNick; // 赠送方昵称
		int type; // 1:赠送给别人 2:从别人那里收到
		long coin; // 赠送金豆数量
		String time; // 2015-07-03 13:21:35

		public TradeLogItem(int type, String otherId, CoinLogDto l) {
			this.type = type;
			this.otherId = otherId;
			this.otherNick = Server.getRole(otherId).getNick();
			this.coin = l.getCoin();
			this.time = l.getTime();
		}

		public String getOtherId() {
			return otherId;
		}

		public void setOtherId(String otherId) {
			this.otherId = otherId;
		}

		public String getOtherNick() {
			return otherNick;
		}

		public void setOtherNick(String otherNick) {
			this.otherNick = otherNick;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public long getCoin() {
			return coin;
		}

		public void setCoin(long coin) {
			this.coin = coin;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
	}

	public class TradeLogResult {
		private static final int COUNT = 8;

		public List<TradeLogItem> getTradeLogItems() {

			List<CoinLogDto> in = getIns();// 我收到的金币日志
			List<CoinLogDto> out = getOuts();// 我送出的金币日志

			ArrayList<TradeLogItem> ls = Lists.newArrayList();
			for (CoinLogDto l : in) {
				ls.add(new TradeLogItem(2, l.getFrom(), l));
			}
			for (CoinLogDto l : out) {
				ls.add(new TradeLogItem(1, l.getTo(), l));
			}

			return ls;
		}

		private List<CoinLogDto> getIns() {

			return find(new FindByTo());
		}

		private List<CoinLogDto> getOuts() {
			return find(new FindByFrom());
		}

		private List<CoinLogDto> find(Finder f) {

			CoinLogDtoCursor find = f.find(role.getId());
			int count = 0;
			ArrayList<CoinLogDto> ls = Lists.newArrayList();
			for (CoinLogDto dto : find) {
				String dsc = dto.getDsc();
				if (dsc.equals("send coin")) {
					ls.add(dto);
					count++;
					if (count >= COUNT)
						break;
				}
			}
			return ls;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3032947083094465599L;

	@Override
	protected Object run() {
		return new TradeLogResult();
	}

}
