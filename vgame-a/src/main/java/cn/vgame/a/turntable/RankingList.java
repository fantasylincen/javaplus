package cn.vgame.a.turntable;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.ConcurrentSortList;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.zhuang.Zhuang;
import cn.vgame.a.zhuang.ZhuangManager;

public class RankingList {

	private Zhuang zhuang;

	private ConcurrentSortList<IProfit> results;

	private IProfit zhuangProfit;

	public RankingList() {
		ZhuangManager manager = Server.getZhuangManager();
		zhuang = manager.getZhuang();
		results = new ConcurrentSortList<IProfit>(6);
		Log.d("init ranking list");
	}

	public void add(IProfit result) {
		results.add(result);
//		Log.d("update ranking list", results.values());
	}

	public Profit get(String id) {
		if (isZhuang(id))
			return new Profit(zhuangProfit);
		Turntable ins = Turntable.getInstance();
		IProfit r = ins.getSettlementResult(id);
		if (r == null)
			return null;
		return new Profit(r);
	}

	private boolean isZhuang(String id) {
		if (zhuang == null)
			return false;
		return zhuang.getRoleId().equals(id);
	}

	public List<Profit> getProfits() {
		ArrayList<Profit> ls = Lists.newArrayList();
		List<IProfit> values = results.values();
		for (IProfit p : values) {
			ls.add(new Profit(p));
		}
		return ls;
	}

	public Profit getZhuang() {
		if (zhuangProfit == null)
			return null;
		return new Profit(zhuangProfit);
	}

	public void setZhuang(IProfit zhuangProfit) {
		this.zhuangProfit = zhuangProfit;
	}

}
