package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.TurntableUtil;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.share.Xml;

public class ProfitCalc {
	/**
	 * 各个动物的倍率, 以及各个动物压了多少分
	 */
	public static class Xs {
		String type;
		int x;
		private long coin;
		private final long roleCoinAll;
		private final int profitFqZs;

		public Xs(long roleCoinAll, String type, int x, long coin,
				int profitFqZs) {
			this.roleCoinAll = roleCoinAll;
			this.type = type;
			this.x = x;
			this.coin = coin;
			this.profitFqZs = profitFqZs;
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
			return roleCoinAll - x * coin + profitFqZs;
		}

		@Override
		public String toString() {
			return TurntableUtil.toChinese(getType()) + ":" + getCoin() + ":"
					+ getProfit();
		}

	}

	public static List<Xs> getXss(SwitchAll switchs, int randomXNumber) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		List<Row> all = sheet.getAll();
		ArrayList<Xs> ls = Lists.newArrayList();

		SwitchWithOutRobot sr = new SwitchWithOutRobot(switchs);

		long roleCoinAll = TurntableUtil.getCountAllWithOutAAndD(sr);

		int feiQinCount = switchs.getByTypeWithOutRobot("A");
		int zouShouCount = switchs.getByTypeWithOutRobot("D");

		for (Row r : all) {
			int x = r.getInt("x-1-" + randomXNumber);
			String type = r.get("type");

			if (Sets.newHashSet("A", "D").contains(type)) {
				continue;
			}

			String categray = r.get("categray");
			int profitFqZs = getProfitFqzs(categray, feiQinCount, zouShouCount); // 算上飞禽走兽的系统盈利

			long coin = switchs.getByTypeWithOutRobot(type);
			Xs xs = new Xs(roleCoinAll, type, x, coin, profitFqZs);
			ls.add(xs);
		}

		return ls;
	}

	/**
	 * 计算出飞禽和出走兽系统盈利
	 * 
	 * @param categray
	 * @param feiQinCount
	 * @param zouShouCount
	 * @return
	 */
	public static int getProfitFqzs(String categray, int feiQinCount,
			int zouShouCount) {
		int in;
		int out;
		if ("A".equals(categray)) { // 如果出飞禽
			in = zouShouCount;
			out = feiQinCount * 2;
		} else if ("D".equals(categray)) { // 如果出飞禽
			in = feiQinCount;
			out = zouShouCount * 2;
		} else {
			in = zouShouCount + feiQinCount;
			out = 0;
		}
		return in - out;
	}
}
