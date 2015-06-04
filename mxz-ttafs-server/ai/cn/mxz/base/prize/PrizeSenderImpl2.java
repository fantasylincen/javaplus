package cn.mxz.base.prize;

import java.util.Collection;
import java.util.List;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Random;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.MissionRewardLimitTemplet;
import cn.mxz.MissionRewardLimitTempletConfig;
import cn.mxz.battle.AbstractBattle.MissionPrize;
import cn.mxz.battle.PropPrizeImpl;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.FunctionOpenManager;
import cn.mxz.user.Player;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import define.D;

public class PrizeSenderImpl2 implements PrizeSender {

	// private int getRandomCount(String countScope) {
	// String[] split = countScope.split(",");
	// int min = new Integer(split[0]);
	// int max = new Integer(split[1]);
	// return Random.get(min, max);
	// }

	private Collection<MissionPrize> random(List<MissionPrize> split, int count) {

		// int[] ws = Util.Collection.getArrayByOneFields(new
		// IntegerFetcher<MissionPrize>() {
		// @Override
		// public Integer get(MissionPrize t) {
		// return (int) (t.getProbability() * 1000000);
		// }
		// }, split);
		//
		// // int index = Util.getRandomIndex(ws);
		//
		// Collection<Integer> indexs = Util.Random.getRandomIndexs(count, ws);
		//
		// return get(split, indexs);

		List<MissionPrize> ls = Lists.newArrayList();
		for (MissionPrize mp : split) {
			if (Util.Random.isHappen(mp.getProbability())) {
				ls.add(mp);
			}
		}
		return ls;
	}

	// private Collection<MissionPrize> get(List<MissionPrize> split,
	// Collection<Integer> indexs) {
	// ArrayList<MissionPrize> ls = Lists.newArrayList();
	// for (Integer index : indexs) {
	// ls.add(split.get(index));
	// }
	// return ls;
	// }

	private List<MissionPrize> getPrizes(String drop, Player player) {
		try {
			List<MissionPrize> ls = Lists.newArrayList();

			List<String> list = get(drop);
			for (String string : list) {
				ls.add(buildOne(string, player));
			}
			return ls;
		} catch (RuntimeException e) {
			Debuger.error("副本掉落报错了:" + drop);
			throw e;
		}
	}

	private MissionPrize buildOne(String string, Player player) {
		MissionPrize mp = new MissionPrize();
		String[] split = string.split(":");
		float probaility = new Float(split[2]);
		int min = new Integer(split[1].split(",")[0]);
		int max = new Integer(split[1].split(",")[1]);
		int count = Random.get(min, max);

		mp.setCount(count);
		mp.setProbaility(probaility);

		int id = -1;
		String type = split[0];

		probaility = limitByFunctionOpen(probaility, type, player);// 根据功能开启对
																	// 一个物品是否掉落,
																	// 做限制

		
		if (probaility == 0 || type.equals("NONE")) {
			mp.setIsEmpty(true);
			return mp;
		}

		if (type.startsWith("ZXSJ-")) {
			String[] split2 = type.split("-");
			if (split2.length < 2) {
				throw new IllegalArgumentException("" + string);
			}
			int t = new Integer(split2[1]);
			Debuger.debug("PrizeSenderImpl2.buildOne() " + string + ", " + t);
			id = new ZXSJGenerator(t, player).generate();
		} else {
			id = new Integer(type);
		}

		if (id == 0 || id == -1) {
			mp.setIsEmpty(true);
		}

		mp.setId(id);

		return mp;
	}

	/**
	 * 根据 模块开启做限制
	 * 
	 * @param probaility
	 * @param type
	 * @param player
	 * @return
	 */
	private float limitByFunctionOpen(float probaility, String type,
			Player player) {
		List<MissionRewardLimitTemplet> all = MissionRewardLimitTempletConfig
				.getAll();
		for (MissionRewardLimitTemplet m : all) {
			if (type.startsWith(m.getHead() + "")) {
				FunctionOpenManager mm = player.getCity()
						.getFunctionOpenManager();
				boolean open = mm.isOpen(m.getModuleId());
				if (!open) {
					return 0; // 不可能出现
				}
			}
		}
		return probaility;
	}

	// public static void main(String[] args) {
	// String temp = "aaa-bbb";
	// String[] split = temp.split("-");
	// for (String string : split) {
	// System.out.println(string);
	// }
	// }

	private List<String> get(String drop) {

		String[] split = drop.split("\\|");

		List<String> ls = Lists.newArrayList();

		for (String string : split) {

			ls.add(string);
		}

		return ls;
	}

	@Override
	public List<Prize> buildPrizes(Player player, String ps) {

		List<Prize> ls = Lists.newArrayList();

		ps = ps.trim();

		if (ps.isEmpty()) {
			return Lists.newArrayList();
		}

		// String[] s = ps.split("/");
		//
		// ps = s[0];
		//
		// String countScope = s[1];

		List<MissionPrize> split = getPrizes(ps, player);

		int count /* = getRandomCount(countScope); */= 1;

		Collection<MissionPrize> prizes = random(split, count);

		for (MissionPrize prize : prizes) {
			if (!prize.isEmpty()) {
				PropPrizeImpl o = new PropPrizeImpl(prize.getId(),
						prize.getCount());
				if (player.getLevel() <= D.EQUIPMENT_STUFF_DROP_LIMIT) {
					EquipmentStuffDropChecker checker = new EquipmentStuffDropChecker();
					boolean isEquipment = EquipmentTempletConfig.get(o.getId()) != null;
					if (!isEquipment) { // 如果不是装备
						if (checker.needCheck(o) && !checker.hasEquipment(player, o)) {
							continue;
						}
					}
				}
				ls.add(o);
			}
		}
		return ls;
	}

	@Override
	public List<Prize> send(Player player, String drop) {
		List<Prize> all = buildPrizes(player, drop);
		for (Prize prize : all) {
			prize.award(player);
		}
		return all;
	}

}
