package cn.vgame.a.turntable.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.set.Sets;
import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.Xml;

public class RandomByExcel {

	final class WeightFetcherImplementation implements
			WeightFetcher<Row> {
		
		private final int randomXNumber;
		public WeightFetcherImplementation(int randomXNumber) {
			this.randomXNumber = randomXNumber;
		}
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
	
	public Row randomOne(List<Row> all, int randomXNumber) {

		WeightFetcher<Row> fet = new WeightFetcherImplementation(randomXNumber);

		Row row = Util.Random.getRandomOneByWeight(all, fet);
		return row;
	}
	void randomSongDeng(List<Row> all, ArrayList<Row> ls, Row row, int randomXNumber) {
		int count = getSongDengCount(row);
		List<Row> tmp = filter(all, row);
		for (int i = 0; i < count; i++) {
			Row random = new RandomByExcel().randomOne(tmp, randomXNumber);
			ls.add(random);
			tmp = filter(tmp, random); // 不能出相同的东西
		}
	}


	private List<Row> filter(List<Row> all, Row row) {
		ArrayList<Row> ls = Lists.newArrayList();
		String t2 = row.get("type");

		for (Row r : all) {

			String t1 = r.get("type");

			if(Sets.newHashSet("B", "C").contains(t1)) {//如果是鲨鱼 就跳过
				continue;
			}
			
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
}
