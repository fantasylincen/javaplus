package cn.mxz.base.prize;

import java.util.Arrays;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.bossbattle.Prize;
import cn.mxz.mission.old.PrizeImpl;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

/**
 *
 * 奖励发放器
 *
 * @author 林岑
 *
 */
public class PrizeSenderImpl implements PrizeSender {

	/**
	 *
	 * 构造奖励
	 *
	 * @param reward
	 *            单个奖励
	 * @return
	 */
	private Prize buildPrize(String reward) {

		// 送魂魄1000001 1个: 　　　　　HP,1000001,1
		// 指定必须送魂魄
		if (reward.startsWith("HP,")) {
			return buildHP(reward.replaceFirst("HP,", ""));
		}

		// 送装备 攻击类玩家  得攻击类装备, 防御类的 得防御类
		// 针对玩家的攻击方式送装备: ZB,154117,154201,1
		if (reward.startsWith("ZB,")) {
			return buildZB(reward.replaceFirst("ZB,", ""));
		}
		// 情况3：宝箱中开出随机一样物品 BX,15,1 TODO LC
		// 宝箱
		if (reward.startsWith("BX,")) {
			return buildBX(reward.replaceFirst("BX,", ""));
		}

		return buildNormal(reward);
	}

	private Prize buildBX(String reward) {
//		BX,2,2

		final String[] split = reward.split(",");

		final Integer type = new Integer(split[0]);

		final Integer count = new Integer(split[1]);

		return new BXPrize(type, count);
	}

	private Prize buildZB(String reward) {
		List<Integer> ls = Util.Collection.getIntegers(reward);
		return new ZBPrize(ls);
	}

	private Prize buildHP(String reward) {

		final String[] split = reward.split(",");

		final Integer id = new Integer(split[0]);

		final Integer count = new Integer(split[1]);

		return new HPPrize(id, count);
	}

	/**
	 * 普通奖励
	 *
	 * @param reward
	 * @return
	 */
	private Prize buildNormal(String reward) {

		final String[] split = reward.split(",");

		final Integer id = new Integer(split[0]);

		Integer count;
		try {
			count = new Integer(split[1]);
		} catch (RuntimeException e) {
			throw new RuntimeException("" + Arrays.toString(split));
		}

		return new PrizeImpl(id, count);
	}

	@Override
	public List<Prize> buildPrizes(Player player, final String prize) {

		final List<Prize> all = Lists.newArrayList();

		final String[] split = prize.split("\\|");

		for (String reward : split) {

			if (!reward.isEmpty()) {

				all.add(buildPrize(reward.trim()));
			}
		}

		return all;
	}

	@Override
	public List<Prize> send(Player player, String prize) {

		if (prize == null) {

			throw new NullPointerException();
		}

		final List<Prize> ps = buildPrizes(player, prize);

		for (Prize p : ps) {

			p.award(player);
		}

		return ps;

	}

}
