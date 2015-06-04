package cn.mxz.base.prize;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.util.Util;
import cn.mxz.RandomGoodsLibraryTemplet;
import cn.mxz.RandomGoodsLibraryTempletConfig;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

public class BXPrize implements Prize {

	private Integer type;
	private Integer count;
	private Integer id;

	public BXPrize(Integer type, Integer count) {
		this.type = type;
		this.count = count;
	}

	public void award(City city) {
		award(city.getPlayer());
	}

	@Override
	public void award(Player player) {

		String prize = buildPrize(player);

		getSender().send(player, prize);
	}

	private PrizeSender getSender() {
		return PrizeSenderFactory.getPrizeSender();
	}

	public String buildPrize(Player player) {
		List<RandomGoodsLibraryTemplet> s = getList(player);

		RandomGoodsLibraryTemplet r = getRandomOne(s);

		Debuger.debug("---------------BXPrize.award()-------------"
				+ r.getName() + "," + r.getPropMin() + "," + r.getPropMax()
				+ "," + r.getReward());

		count = getRealCount(r);
		String prize = r.getReward() + "," + count;

		PrizeSender sd = getSender();

		List<Prize> bs = sd.buildPrizes(player, prize);
		id = bs.get(0).getId();
		return prize;
	}

	private Integer getRealCount(RandomGoodsLibraryTemplet r) {

		int max = r.getPropMax();
		int min = r.getPropMin();

		int random;
		try {
			random = Util.Random.get(min, max);
		} catch (Exception e) {
			e.printStackTrace();
			Debuger.error("[数值表异常]" + r.getIdfen() + "," + r.getName() + ","
					+ r.getName() + "," + r.getPropMin() + "," + r.getPropMax());
			random = 1;
		}

		return count * random;
	}

	private RandomGoodsLibraryTemplet getRandomOne(
			List<RandomGoodsLibraryTemplet> s) {
		WeightFetcher<RandomGoodsLibraryTemplet> weightAble = new WeightFetcher<RandomGoodsLibraryTemplet>() {

			@Override
			public Integer get(RandomGoodsLibraryTemplet t) {
				return t.getWeight();
			}
		};

		if (s.isEmpty()) {
			throw new RuntimeException();
		}

		RandomGoodsLibraryTemplet r = Util.Random.getRandomOneByWeight(s,
				weightAble);
		return r;
	}

	private List<RandomGoodsLibraryTemplet> getList(Player player) {
		City city = player.getCity();

		List<RandomGoodsLibraryTemplet> s = Lists
				.newArrayList(RandomGoodsLibraryTempletConfig.getAll());
		Iterator<RandomGoodsLibraryTemplet> it = s.iterator();
		while (it.hasNext()) {
			RandomGoodsLibraryTemplet r = it.next();
			if (!city.getFunctionOpenManager().isOpen(r.getModulesId())) {
				it.remove();
				continue;
			}
			String range = r.getRange();
			List<Integer> all = Util.Collection.getIntegers(range);

			if (!all.contains(type)) {
				it.remove();
			}
		}
		return s;
	}

	@Override
	public int getId() {
		if (id == null) {
			throw new RuntimeException("奖励类型在赠送前无法确定");
		}
		return id;
	}

	@Override
	public int getCount() {
		if (id == null) {
			throw new RuntimeException("奖励数量在赠送前无法确定");
		}
		return count;
	}

}
