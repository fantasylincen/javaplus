package cn.mxz.chuangzhen;

import java.util.List;

import cn.mxz.CopterBuffTemplet;

import com.google.common.collect.Lists;

public class BattleAdditionImpl implements BattleAddition {

	private List<CopterBuffTemplet>	additionReward;

	public BattleAdditionImpl(List<CopterBuffTemplet> additionReward) {
		this.additionReward = additionReward;
	}

	@Override
	public List<Addition2> getAdditions() {
		List<Addition2> ls = Lists.newArrayList();
		for (CopterBuffTemplet t : additionReward) {
			ls.add(new AdditionImpl2(t));
		}
		return ls;
	}

}
