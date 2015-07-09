package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Result;
import cn.vgame.a.turntable.ResultGenerator;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;
import cn.vgame.a.turntable.swt.SwitchAll;
import cn.vgame.share.Xml;

public class ExcelGenerator implements ResultGenerator {

	@Override
	public Result generateReward(SwitchAll switchs) {
		
		randomXNumber = Util.Random.get(1, 6);
		
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("weights");
		List<Row> all = sheet.getAll();
		List<Row> randoms = random(all);
		Result r = new Result();
		r.setResult(randoms);
		return r;
	}
	
	public int getRandomXNumber() {
		return randomXNumber;
	}
	
	private List<Row> random(List<Row> all) {
		ArrayList<Row> ls = Lists.newArrayList();
		Row row = randomFirst(all); // 随机出第一个结果
		ls.add(row);
		randomSongDeng(all, ls, row); // 如果出鲨鱼, 送灯
		return ls;
	}


	private void randomSongDeng(List<Row> all, ArrayList<Row> ls, Row row) {
		int count = getSongDengCount(row);
		List<Row> tmp = filter(all, row);
		for (int i = 0; i < count; i++) {
			Row random = randomOne(tmp);
			ls.add(random);
			tmp = filter(tmp, random); // 不能出相同的东西
		}
	}

	private Row randomFirst(List<Row> all) {
		Row row;
		Controller c = Turntable.getInstance().getController();
		if (Turntable.getInstance().getMustGenerateId() > 0) { // 本轮第一个必出
			row = getMust(all);
		} else if (c.isZhengZaiGanShe() && c.isHappen()) { // 如果干涉程序正在运行,
			row = c.randomOne(all); // 则由干涉程序生成结果
		} else {
			row = randomOne(all);
		}
		return row;
	}
	


	private List<Row> filter(List<Row> all, Row row) {
		ArrayList<Row> ls = Lists.newArrayList();
		for (Row r : all) {

			String t1 = r.get("type");
			String t2 = row.get("type");

			if (!t1.equals(t2)) {
				ls.add(r);
			}
		}
		return ls;
	}

	private int getSongDengCount(Row row) {
		String type = row.get("type");
		Xml xml = Server.getXml();
		Sheet sheet = xml.get("x");
		Row r = sheet.get(type);
		String cs = r.get("songDengCount");
		List<Integer> cc = Util.Collection.getIntegers(cs);
		return Util.Random.get(cc.get(0), cc.get(1));
	}

	private final class WeightFetcherImplementation implements
			WeightFetcher<Row> {
		@Override
		public Integer get(Row t) {
			int id = t.getInt("id");

			int weightAdd = getWeightAdd(id); // 管理员配置的权重
			return t.getInt("weight-1-"
					+ randomXNumber)
					+ weightAdd;
		}
	}

	/**
	 * 管理员设置的权重
	 * 
	 * @param idInWeights
	 *            对应到game.xml weights表中的id
	 * @return
	 */
	public int getWeightAdd(int idInWeights) {
		Map<Integer, Integer> weightByGm = Turntable.getInstance().getWeightByGm();
		Integer weight = weightByGm.get(idInWeights);
		if (weight == null)
			weight = 0;
		return weight;
	}
	
	/** 随机倍率序号 */
	private int randomXNumber = 1;
	
	private Row randomOne(List<Row> all) {

		WeightFetcher<Row> fet = new WeightFetcherImplementation();

		Row row = Util.Random.getRandomOneByWeight(all, fet);
		return row;
	}

	private Row getMust(List<Row> all) {
		for (Row row : all) {
			if (row.getInt("id") == Turntable.getInstance().getMustGenerateId())
				return row;
		}
		throw new NullPointerException("row " + Turntable.getInstance().getMustGenerateId()
				+ "not found");
	}
}
