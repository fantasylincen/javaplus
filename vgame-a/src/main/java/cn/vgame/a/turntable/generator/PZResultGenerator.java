package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Result;
import cn.vgame.a.turntable.ResultGenerator;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;
import cn.vgame.a.turntable.TurntableUtil;
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
		randomXNumber = Util.Random.get(1, 6);
		List<Xs> xss = getXss(switchs);

		List<Row> all = getAllRows();

		List<Row> randoms = random(xss, all);
		Result r = new Result();
		r.setResult(randoms);
		return r;
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
		Row first;
		if (fu.isEmpty()) {
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
		if (fu.isEmpty()) {
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
