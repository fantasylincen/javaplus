package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Result;
import cn.vgame.a.turntable.ResultGenerator;
import cn.vgame.a.turntable.TurntableUtil;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.share.Xml;

public class PZResultGenerator implements ResultGenerator {

	/**
	 * 各个动物的倍率, 以及各个动物压了多少分
	 */
	public class Xs {
		String type;
		int x;
		private long coin;

		public Xs(String type, int x, int coin) {
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
			return x * coin - coin;
		}

	}

	private int randomXNumber;

	@Override
	public Result generateReward(SwitchAll switchs) {
		randomXNumber = Util.Random.get(1, 6);
		List<Xs> xss = getXss(switchs);
		
		
	}

	private List<Xs> getXss(SwitchAll switchs) {
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		List<Row> all = sheet.getAll();
		ArrayList<Xs> ls = Lists.newArrayList();
		for (Row r : all) {
			int x = r.getInt("x-1-" + randomXNumber);
			String type = r.get("type");
			int coin = switchs.getByType(type);
			Xs xs = new Xs(type, x, coin);
			ls.add(xs);
		}

		return ls;
	}

	@Override
	public int getRandomXNumber() {
		return randomXNumber;
	}

}
