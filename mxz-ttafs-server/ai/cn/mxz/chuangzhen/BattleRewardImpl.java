package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.vip.VipPlayer;

import com.google.common.collect.Lists;

import define.D;

public class BattleRewardImpl implements BattleReward {

	private List<PropReward>		rs;
	private ChuangZhenPlayerImpl	player;

	public BattleRewardImpl(List<PropReward> battleReward, ChuangZhenPlayerImpl player) {
		this.rs = battleReward;
		this.player = player;
	}

	@Override
	public List<PropReward> getPropReward() {
		List<PropReward> ls = Lists.newArrayList();
		ls.add(new CashReward(getCashEveryStar(), getStar()));
		for (PropReward t : rs) {
			ls.add(t);
		}
		return ls;
	}

	@Override
	public int getCashEveryStar() {
		if(isVipAdd()) {//vip特权用户增加 50 %
			return (int) (D.CHUANG_ZHEN_BASE_CASH * 1.5);
		}
		return D.CHUANG_ZHEN_BASE_CASH/* * (player.getCurrentFloor() % D.CHUANG_ZHEN_REWARD_FLOOR + 1)*/;
	}

	/**
	 * 是否有IP增加 50% 铜钱产出的特权
	 * @return
	 */
	private boolean isVipAdd() {
		String id = player.getId();
		City city = CityFactory.getCity(id);
		VipPlayer p = city.getVipPlayer();
		int level = p.getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) level);
		return temp.getCopterAward() == 1;
	}

	@Override
	public int getFloorMin() {
		return getFloorMax() - 4;
	}

	@Override
	public int getFloorMax() {
		int f = player.getCurrentFloor() - 1;
		
		while ( f % 5 != 0) {
			f--;
		}
		
		if (f < 5) {
			f = 5;
		}
		
		return f;
	}

	@Override
	public int getStar() {
		String id = player.getId();
		City city = CityFactory.getCity(id);
		UserCounter his = city.getUserCounterHistory();

		int count = 0;
		for (int f = getFloorMin(); f <= getFloorMax(); f++) {
			int c = his.get(CounterKey.CHUANG_ZHEN_STAR_RECEIVE, f);
			count += c;
		}
		return count;
	}
}
