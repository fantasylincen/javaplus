package cn.mxz.fighter;

import java.util.List;

interface ProbabilityCalc {

	/**
	 * 获得升级成功率
	 * @param step_level 被升级的Id
	 * @param ids			被消耗的id列表
	 * @param ID
	 * @param uname
	 *
	 * @return	是否升级成功
	 */
	boolean getProbability(StepLevel sl, List<? extends StepLevel> ids, String uname);

}
