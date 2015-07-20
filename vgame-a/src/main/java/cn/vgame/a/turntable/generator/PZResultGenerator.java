package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Result;
import cn.vgame.a.turntable.ResultGenerator;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;
import cn.vgame.a.turntable.TurntableUtil;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.share.Xml;

public class PZResultGenerator implements ResultGenerator {

	public class TunFenC implements Comparator<Xs> {

		@Override
		public int compare(Xs o1, Xs o2) {
			return (int) (o2.getProfit() - o1.getProfit());
		}

	}

	public class TuFenC implements Comparator<Xs> {

		@Override
		public int compare(Xs o1, Xs o2) {
			return (int) (o1.getProfit() - o2.getProfit());
		}

	}

	/**
	 * 各个动物的倍率, 以及各个动物压了多少分
	 */
	public class Xs {
		String type;
		int x;
		private long coin;
		private final long roleCoinAll;

		public Xs(long roleCoinAll, String type, int x, long coin) {
			this.roleCoinAll = roleCoinAll;
			this.type = type;
			this.x = x;
			this.coin = coin;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public long getCoin() {
			return coin;
		}

		public void setCoin(long coin) {
			this.coin = coin;
		}

		/**
		 * 如果出这个结果, 系统盈利
		 */
		public long getProfit() {
			return roleCoinAll - x * coin;
		}

		@Override
		public String toString() {
			return TurntableUtil.toChinese(getType()) + ":" + getCoin() + ":"
					+ getProfit();
		}

	}

	private int randomXNumber = Util.Random.get(1, 6);

	@Override
	public Result generateReward(SwitchAll switchs) {
		List<Xs> xss = getXss(switchs);

		List<Row> all = getAllRows();

		List<Row> randoms = random(xss, all);
		Result r = new Result();
		r.setResult(randoms);
		return r;
	}

	@Override
	public void updateRandomXNumber() {
		randomXNumber = Util.Random.get(1, 6);
	}

	private List<Row> getAllRows() {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		return all;
	}

	/**
	 * @param xss
	 *            下注情况, 内容包含了 每个灯下注情况, 每个灯的倍率, 每个灯出现后的盈利
	 * @param all
	 * @return
	 */
	private List<Row> random(List<Xs> xss, List<Row> all) {
		Controller c = Turntable.getInstance().getController();
		long kuCun = c.getKuCun();

		double pro = Server.getConst().getDouble("TUN_FEN_GAI_LV");

		if (Util.Random.isHappen(pro) || kuCun < 0) {
			return generateTunFen(xss, all);
		} else {
			return generateTuFen(xss, all);
		}
	}

	private List<Row> generateTuFen(List<Xs> xss, List<Row> all) {

		ArrayList<Row> ls = Lists.newArrayList();
		List<Xs> fu = getFu(xss);

		removeShaYuTu(fu);
		double p = Server.getConst()
				.getDouble("IS_TU_FEN_BY_PERSON_COUNT_PROB");
		Row first;
		
		String maxType = getMaxType();
		
		if (Turntable.getInstance().getMustGenerateId() > 0) { // 本轮第一个必出
			first = getMust(all);

		} else if (maxType != null && Util.Random.isHappen(p) ) { // 吐到下注玩家数量最多的灯上玩家最多的

			first = getPlayerCountMax(all, maxType);

		} else if (fu.isEmpty()) {
			Collections.sort(xss, new TuFenC());
			Xs xs = xss.get(0);
			first = get(all, xs.getType());
		} else {
			Util.Collection.upset(fu);
			first = get(all, fu.get(0).getType());
		}
		ls.add(first);
		new RandomByExcel().randomSongDeng(all, ls, first, randomXNumber);
		return ls;
	}

	private Row getPlayerCountMax(List<Row> all, String type) {
		Log.d("压注玩家最多的灯", TurntableUtil.toChinese(type));
		return get(all, type);
	}

	private String getMaxType() {
		Counter<String> counts = getXiaZhuPlayerCount();
		String maxType = null;
		int maxCount = -1;

		List<TypeCount> cs = parse(counts);
		for (TypeCount tc : cs) {
			int count = tc.getCount();
			String type = tc.getType();
			Log.d(TurntableUtil.toChinese(type), count);
			if (count > maxCount) {
				maxCount = count;
				maxType = type;
			}
		}

		return maxType;
	}

	private List<TypeCount> parse(Counter<String> counts) {
		ArrayList<TypeCount> ls = Lists.newArrayList();
		for (String type : counts.keySet()) {
			int count = counts.get(type);

			TypeCount tc = new TypeCount();
			tc.setCount(count);
			tc.setType(type);
			ls.add(tc);
		}
		Util.Collection.upset(ls);
		return ls;
	}

	private Counter<String> getXiaZhuPlayerCount() {

		SwitchAll switchs = Turntable.getInstance().getSwitchs();

		Counter<String> c = new Counter<String>();
		Set<String> all = switchs.getAll();

		List<String> types = TurntableUtil.getAllTypesWithOutAAndD();

		for (String roleId : all) {
			if (Server.getRobotManager().isRobot(roleId))
				continue;
			ISwitchs s = switchs.get(roleId);
			for (String type : types) {
				int count = TurntableUtil.getByType(s, type);
				if (count > 0) {
					c.add(type, 1); // 该灯人数加1
				}
			}
		}

		return c;
	}

	private void removeShaYuTu(List<Xs> fu) {
		Iterator<Xs> it = fu.iterator();
		while (it.hasNext()) {
			PZResultGenerator.Xs xs = (PZResultGenerator.Xs) it.next();

			if ("B".equals(xs.getType())) {
				if (Util.Random.isHappen(0.8)) {
					it.remove();
				}
			} else if ("C".equals(xs.getType())) {
				if (Util.Random.isHappen(0.9)) {
					it.remove();
				}
			}

		}
	}

	private Row getMust(List<Row> all) {
		for (Row row : all) {
			if (row.getInt("id") == Turntable.getInstance().getMustGenerateId())
				return row;
		}
		throw new NullPointerException("row "
				+ Turntable.getInstance().getMustGenerateId() + "not found");
	}

	private List<Xs> getFu(List<Xs> xss) {

		ArrayList<Xs> ls = Lists.newArrayList();
		for (Xs xs : xss) {
			long p = xs.getProfit();
			// Log.d(p, xs.getType());
			if (p <= 0)
				ls.add(xs);
		}

		return ls;
	}

	private List<Xs> getZheng(List<Xs> xss) {

		ArrayList<Xs> ls = Lists.newArrayList();
		for (Xs xs : xss) {
			long p = xs.getProfit();

			if (p >= 0)
				ls.add(xs);
		}

		return ls;
	}

	private List<Row> generateTunFen(List<Xs> xss, List<Row> all) {

		ArrayList<Row> ls = Lists.newArrayList();
		List<Xs> fu = getZheng(xss);

		removeShaYu(fu);

		Row first;

		if (Turntable.getInstance().getMustGenerateId() > 0) { // 本轮第一个必出
			first = getMust(all);
		} else if (fu.isEmpty()) {
			Collections.sort(xss, new TunFenC());
			Xs xs = xss.get(0);
			first = get(all, xs.getType());
		} else {
			Util.Collection.upset(fu);
			first = get(all, fu.get(0).getType());
		}
		ls.add(first);
		new RandomByExcel().randomSongDeng(all, ls, first, randomXNumber);
		return ls;
	}

	private void removeShaYu(List<Xs> fu) {
		Iterator<Xs> it = fu.iterator();
		while (it.hasNext()) {
			PZResultGenerator.Xs xs = (PZResultGenerator.Xs) it.next();
			if (Sets.newHashSet("B", "C").contains(xs.getType())) { // 如果是鲨鱼,
																	// 就有1%的概率出
				if (Util.Random.isHappen(0.99)) {
					it.remove();
				}
			}
		}
	}

	private Row get(List<Row> all, String type) {
		for (Row row : all) {
			String t = row.get("type");
			if (t.equals(type)) {
				return row;
			}
		}
		throw new NullPointerException("type not found " + type);
	}

	private List<Xs> getXss(SwitchAll switchs) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		List<Row> all = sheet.getAll();
		ArrayList<Xs> ls = Lists.newArrayList();

		SwitchWithOutRobot sr = new SwitchWithOutRobot(switchs);

		long roleCoinAll = TurntableUtil.getCountAllWithOutAAndD(sr);

		for (Row r : all) {
			int x = r.getInt("x-1-" + randomXNumber);
			String type = r.get("type");

			if (Sets.newHashSet("A", "D").contains(type)) {
				continue;
			}

			long coin = switchs.getByTypeWithOutRobot(type);
			Xs xs = new Xs(roleCoinAll, type, x, coin);
			ls.add(xs);
		}

		return ls;
	}

	@Override
	public int getRandomXNumber() {
		return randomXNumber;
	}

}
