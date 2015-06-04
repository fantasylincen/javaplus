package cn.mxz.base.prize;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.random.Weightable;
import cn.javaplus.util.Util;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

public class BoxOpenManager {

	public class PrizeBean implements Weightable {

		private Integer k;
		private Integer w;

		public PrizeBean(String ss) {
			String[] split = ss.split(",");
			this.k = new Integer(split[0]);
			this.w = new Integer(split[1]);
		}

		@Override
		public int getWeight() {
			return w;
		}

		public int getKu() {
			return this.k;
		}

	}

	private int ku;
	private BoxTemplet gb;
	private City city;

	public BoxOpenManager(City city) {
		this.city = city;
	}

	public List<Prize> open(BoxTemplet gb) {
		this.gb = gb;
		ku = randomKu();
		String ps = new BXPrize(ku, 1).buildPrize(city.getPlayer());
		return city.getPrizeSender1().buildPrizes(ps);
	}

	private int randomKu() {
		String award = getAward();
		List<PrizeBean> ls = getPrizeBean(award);

		PrizeBean pb = Util.Random.getRandomOneByWeight(ls);
		return pb.getKu();
	}

	private String getAward() {
		if(city.getLevel() <= 10) {
			return gb.getAwards10();
		}
		return gb.getAwards11();
	}

	private List<PrizeBean> getPrizeBean(String award) {

		ArrayList<PrizeBean> ls = Lists.newArrayList();
		String[] s = award.split("\\|");
		for (String ss : s) {
			if (!ss.trim().isEmpty()) {
				ls.add(new PrizeBean(ss));
			}
		}
		return ls;
	}

	public boolean isFindWell() {
		return gb.getStop() == ku;
	}

	public int getKu() {
		return ku;
	}

}
