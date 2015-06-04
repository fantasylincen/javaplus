package cn.mxz.fighter;

import java.util.List;

import cn.javaplus.util.Util.Random;
import cn.mxz.BasisTemplet;
import cn.mxz.HostTemplet;
import cn.mxz.util.debuger.Debuger;
import db.domain.TacticalDTO;

abstract class AbstractProbabilityCalc<T extends TacticalDTO> implements
		ProbabilityCalc {

	protected abstract BasisTemplet getBasisTemplet(String id);

	protected abstract HostTemplet getHostTemplet(String id);

	@Override
	public final boolean getProbability(StepLevel sl, List<? extends StepLevel> ids,
			String uname) {

		float allAccumulate = getPro2(sl, ids);

		return Random.isHappen(allAccumulate);
	}

	public float getPro2(StepLevel sl, List<? extends StepLevel> ids) {
		String key = sl.getStep() + "," + sl.getLevel();
		HostTemplet host = getHostTemplet(key);

		if(host == null) {
			throw new IllegalArgumentException("丁品质技能不可升级!");
		}

		float base = host.getHostPro();// 获取 源 阵策略的基础概率
		float allAccumulate = 0;

		for (StepLevel ta : ids) {
			int level = ta.getLevel();
			String k = ta.getStep() + "," + level;// 获取品质等级key
			float basis = getBasisTemplet(k).getBasisPro();
			allAccumulate += basis * base;
		}

		Debuger.debug("AbstractProbabilityCalc.getProbability() 技能升级概率: " + allAccumulate);
		return allAccumulate;
	}
}