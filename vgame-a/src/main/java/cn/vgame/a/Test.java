package cn.vgame.a;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.log.Log;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDao;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDao.CoinLogDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;

public class Test {
	public static void clearCoins() {

		Counter<String> recharges = getRecarges();

		RoleDao dao = Daos.getRoleDao();
		RoleDtoCursor find = dao.find();
		for (RoleDto dto : find) {
			if (!dto.getId().startsWith("r")) {
				dto.setBankCoin(0);
				dto.setCoin(0);
				dto.setJiangQuan(0);
//				Log.d(dto.getNick(), dto.getKeyValueDaily().get("BAG:10001"));
				dto.getKeyValueDaily().put("BAG:10001", "5");
				int coin = recharges.get(dto.getId());
				
				if (coin > 0) {
					dto.setCoin(coin);
					dto.setJiangQuan(coin / 2 / 100);
					Log.d("reset coin", dto.getId(), dto.getNick(), dto.getBankCoin());
				}
				dao.save(dto);
			}
		}
	}

	private static Counter<String> getRecarges() {
		CoinLogDao dao = Daos.getCoinLogDao();
		CoinLogDtoCursor c = dao.findByDsc("recharge");
		Counter<String> counter = new Counter<String>();
		for (CoinLogDto dto : c) {
			long coin = dto.getCoin();
			
			counter.add(dto.getTo(), (int) (coin * 2));
			
			Log.d("recharge log" , dto.getId(), dto.getCoin(), dto.getFromTo());
		}
		return counter;
	}
}
