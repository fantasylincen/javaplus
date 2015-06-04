package cn.mxz.vip;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;
import cn.javaplus.math.Fraction;
import cn.mxz.VipGiftTemplet;
import cn.mxz.VipGiftTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

import com.google.common.collect.Lists;

public class VipPlayerImpl implements VipPlayer {

	private City user;
	private int vipLevelCache = 0;
	private int vipGrowthCache = -1;

	public VipPlayerImpl(City city) {
		this.user = city;
	}

	@Override
	public void buy(int vipLevel) {
		checkAreadyBuy(vipLevel);
		checkLevel(vipLevel);
		VipGiftTemplet vip = VipGiftTempletConfig.get(vipLevel);
		int id = vip.getGiftId();
		int gold = vip.getGoldNew();
		user.getPlayer().reduceGold(gold);
		user.getBagAuto().addProp(id, 1);

		markBuy(vipLevel);
	}

	@Override
	public void setLevel(int level) {
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) level);
		if (temp == null) {

			throw new RuntimeException("非法vip等级");
		}
		UserCounter g = user.getUserCounterHistory();
		g.set(CounterKey.VIP_GROWTH, temp.getGrowth());
	}

	private void checkLevel(int vipLevel) {
		int level = user.getVipPlayer().getLevel();
		if (level < vipLevel) {
			throw new OperationFaildException(S.S10235);
		}
	}

	private void markBuy(int vipLevel) {
		UserCounter his = user.getUserCounterHistory();
		his.mark(CounterKey.VIP_GIFT_BOUGHT, vipLevel);
	}

	private void checkAreadyBuy(int vipLevel) {
		VipGiftStatus status = getGiftStatus();
		List<Gift> gifts = status.getGifts();
		for (Gift gift : gifts) {
			if (vipLevel == gift.getLevel()) {
				if (gift.getHasBought()) {
					throw new OperationFaildException(S.S10247);
				}
				break;
			}
		}
	}

	@Override
	public VipGiftStatus getGiftStatus() {
		return new VipGiftStausImpl(user);
	}

	@Override
	public int getLevel() {
		UserCounter g = user.getUserCounterHistory();
		int growth = g.get(CounterKey.VIP_GROWTH);

		if (!isChanged(growth)) {
			return vipLevelCache;
		}

		return updateLevel(growth);
	}

	private boolean isChanged(int growth) {
		return vipGrowthCache != growth;
	}

	private int updateLevel(int growth) {
		List<VipPrivilegeTemplet> all = getAllHigherThan(growth);
		VipPrivilegeTemplet t;
		if (all.isEmpty()) {
			t = VipPrivilegeTempletConfig.get(VipPrivilegeTempletConfig
					.getMaxKey());
		} else {
			t = findMaxLevel(all);
		}

		vipLevelCache = t.getLevel();
		vipGrowthCache = growth;
		return vipLevelCache;
	}

	/**
	 * 所有比g大(且相等)的
	 * 
	 * @param g
	 * @return
	 */
	private List<VipPrivilegeTemplet> getAllHigherThan(int g) {
		List<VipPrivilegeTemplet> all = VipPrivilegeTempletConfig.getAll();
		List<VipPrivilegeTemplet> ls = Lists.newArrayList();
		for (VipPrivilegeTemplet v : all) {
			if (g >= v.getGrowth()) {
				ls.add(v);
			}
		}
		return ls;
	}

	/**
	 * 找出等级最小的那个
	 * 
	 * @param all
	 * @return
	 */
	private VipPrivilegeTemplet findMaxLevel(List<VipPrivilegeTemplet> all) {
		Comparator<VipPrivilegeTemplet> c = new Comparator<VipPrivilegeTemplet>() {

			@Override
			public int compare(VipPrivilegeTemplet o1, VipPrivilegeTemplet o2) {
				return o2.getGrowth() - o1.getGrowth();
			}
		};
		Collections.sort(all, c);
		return all.get(0);
	}

	@Override
	public Fraction getGrowth() {
		UserCounter g = user.getUserCounterHistory();
		int growth = g.get(CounterKey.VIP_GROWTH);
		return new Fraction(growth, findNextNeed(growth));
	}

	public VipPrivilegeTemplet findNextNeed2(int g) {
		List<Byte> keys = Lists.newArrayList(VipPrivilegeTempletConfig
				.getKeys());

		for (Byte id : keys) {
			VipPrivilegeTemplet t = VipPrivilegeTempletConfig.get(id);
			int growth = t.getGrowth();
			if (g < growth) {
				return t;
			}
		}

		VipPrivilegeTemplet t = VipPrivilegeTempletConfig
				.get(VipPrivilegeTempletConfig.getMaxKey());
		return t;
	}

	private int findNextNeed(int g) {
		VipPrivilegeTemplet findMax2 = findNextNeed2(g);
		return findMax2.getGrowth();
	}

}
