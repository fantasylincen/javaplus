package cn.mxz.base.prize;

import java.util.Collection;
import java.util.List;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.ConsumableTemplet;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class PrizeInExcelRandomImpl {

	// private class RandomBean {
	//
	// private int type;
	// private int count;
	//
	// private RandomBean(int type, int count) {
	// this.type = type;
	// this.count = count;
	// }
	//
	// public int getType() {
	// return type;
	// }
	//
	// public int getCount() {
	// return count;
	// }
	//
	// public void setType(int type) {
	// this.type = type;
	// }
	//
	// public void setCount(int count) {
	// this.count = count;
	// }
	//
	// }

	private ConsumableTemplet	temp;
	private String				awards;
	private City				city;

	public PrizeInExcelRandomImpl(ConsumableTemplet temp, City city) {
		this.temp = temp;
		this.city = city;
		awards = temp.getAwards();
		replaceCount();
	}

	// public static void main(String[] args) {
	// ConsumableTempletConfig.load();
	// List<ConsumableTemplet> ts = ConsumableTempletConfig.getAll();
	// for (ConsumableTemplet temp : ts) {
	// PrizeInExcelRandomImpl t = new PrizeInExcelRandomImpl(temp);
	// Collection<? extends AwardAble> awards = t.getAwards();
	// System.out.println(awards.size());
	// }
	// }

	private void replaceCount() {
		String[] split = awards.split("\\|");
		for (int i = 0; i < split.length; i++) {
			split[i] = replaceCount(split[i]);
		}
		awards = Util.Collection.linkWith("|", split);
	}

	private String replaceCount(String cc) {
		String[] split = cc.split(",");
		int count = new Integer(split[split.length - 1]);

		int mx = temp.getAdditionMax();
		int mn = temp.getAdditionMin();
		int x = Random.get(mn, mx);
		split[split.length - 1] = count * x + "";

		return Util.Collection.linkWith(",", split);
	}

	public List<Prize> getAwards() {

		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
		List<Prize> all = ps.buildPrizes(city.getPlayer(), awards);

		int max = temp.getCountMax();
		int min = temp.getCountMin();
		int count = Random.get(min, max);
		if (count > 100) {
			count = 100;
		}

		all = getByWeight(all, count);

		return all;
		//
		//
		//
		//
		//
		//
		//
		//
		// String[] split = awards.split("\\|");
		//
		// List<RandomBean> ls = build(split);
		//
		// StringBuilder sb = new StringBuilder();
		//
		// ls = random(ls);
		//
		// Iterator<RandomBean> it = ls.iterator();
		//
		// while (it.hasNext()) {
		//
		// RandomBean next = it.next();
		//
		// sb.append(next.getType());
		//
		// sb.append("," + next.getCount());
		//
		// if (it.hasNext()) {
		//
		// sb.append("|");
		// }
		// }
		//
		// return sb + "";

	}

	private List<Prize> getByWeight(final List<Prize> all, int count) {
		int[] ws = Util.Array.asArray(temp.getWeight());


//		ws = addByLastTime(ws); // 根据最后一次抽取宝箱的情况, 增加部分宝箱出现的几率

		// 130034,1|130035,1|130036,1|140028,1|140029,1|BX,21,1|BX,22,1|BX,6,1|BX,7,1|BX,9,1|BX,10,1
		// BX,21,1-20|BX,7,1-21
		// 500,500,500,100,50,5,1,50,10,50,10
		// 0,0,0,0,20,10,10,20,30,20,10

		Collection<Integer> indexs = Util.Random.getRandomIndexs(count, ws);

//		recordReciveStatus(indexs); // 根据宝箱的出现情况, 增加部分宝箱, 下次出现的几率

		List<Prize> ls = Lists.newArrayList();
		for (Integer index : indexs) {
			ls.add(all.get(index));
		}

		return ls;
	}

//	private int[] addByLastTime(int[] ws) {
//		KeyValueCollection<Object, String> c = KeyValueCollectionFactory.getMongoCollection();
//
//		String key = key();
//
//		String s = c.get(key);
//
//		List<Integer> probabilitys = getProbabilitys(s);
//
//		if(probabilitys.size() != ws.length) {
//			throw new RuntimeException("几率长度和权重长度不相等!" + probabilitys.size() + ":" + ws.length);
//		}
//
//		for (int i = 0; i < ws.length; i++) {
//			ws[i] += probabilitys.get(i);
//		}
//
//		return ws;
//	}
//	private void recordReciveStatus(Collection<Integer> indexs) {
//
//		KeyValueCollection<Object, String> c = KeyValueCollectionFactory.getMongoCollection();
//
//		String key = key();
//
//		String s = c.get(key);
//
//		List<Integer> probabilitys = getProbabilitys(s);
//
//		s = build(indexs, probabilitys);
//
//		c.put(key, s);
//	}

	private String key() {
		return city.getId() + ":" + temp.getId();
	}

//	/**
//	 * 这次抽到的宝箱索引ID
//	 *
//	 * @param indexs
//	 * @param s
//	 *            上次宝箱几率增量
//	 * @return
//	 */
//	private String build(Collection<Integer> indexs, List<Integer> probabilitys) {
//
//		// 没出现的宝箱, 这一次的几率增量
//		List<Integer> ps = Util.Collection.getIntegers(temp.getWeightAddition());
//
//		int idex = 0;
//		for (Integer add : ps) {	//没出现过的宝箱几率增加 add
//
//			if (!indexs.contains(idex)) {
//				probabilitys.set(idex, probabilitys.get(idex) + add);
//			}
//
//			idex++;
//		}
//
//		for (Integer i : indexs) { // 出现了的宝箱置为0
//			probabilitys.set(i, 0);
//		}
//
//		return Util.Collection.linkWith(",", probabilitys);
//	}

//	private List<Integer> getProbabilitys(String s) {
//		if(s == null) {
//
//			String weightAddition = temp.getWeightAddition();
//			List<Integer> ps = Util.Collection.getIntegers(weightAddition);
//
//			List<Integer> ls = Lists.newArrayList();
//
//			for (int i = 0; i < ps.size(); i++) {
//				ls.add(0);
//			}
//			return ls;
//		}
//
//
//		return Util.Collection.getIntegers(s);
//	}

	// private Collection<? extends AwardAble> buildByX(int x, List<Prize> all)
	// {
	// List<? extends AwardAble> ls = Lists.newArrayList();
	// for (Prize prize : all) {
	// ls.add(new PrizeAdaptor(x, prize));
	// }
	//
	// }
	//
	// private List<RandomBean> random(List<RandomBean> ls) {
	//
	// cn.javaplus.common.util.Util.Collection.upset(ls);
	//
	// int max = temp.getCountMax();
	//
	// int min = temp.getCountMin();
	//
	// int count = Random.get(min, max);
	//
	// if (count > 100) {
	//
	// count = 100;
	// }
	//
	// ls = cn.javaplus.common.util.Util.Collection.sub(ls, count);
	//
	// for (RandomBean r : ls) {
	//
	// int mx = temp.getAdditionMax();
	//
	// int mn = temp.getAdditionMin();
	//
	// int x = Random.get(mn, mx);
	//
	// r.setCount(r.getCount() * x);
	// }
	//
	// return ls;
	// }
	//
	// private List<RandomBean> build(String[] split) {
	//
	// List<RandomBean> list = new ArrayList<RandomBean>();
	//
	// for (String s : split) {
	//
	// if (s.isEmpty()) {
	//
	// continue;
	// }
	//
	// list.add(build(s));
	// }
	//
	// return list;
	// }
	//
	// private RandomBean build(String s) {
	//
	// String[] split = s.split(",");
	//
	// int type = new Integer(split[0]);
	//
	// int count = new Integer(split[1]);
	//
	// return new RandomBean(type, count);
	// }

}
