package cn.mxz.fighter;

import cn.mxz.BasisTemplet;
import cn.mxz.HostTemplet;
import cn.mxz.SkillBasisTempletConfig;
import cn.mxz.SkillHostTempletConfig;

class SkillProbabilityCalc extends AbstractProbabilityCalc {

	@Override
	protected BasisTemplet getBasisTemplet(String id) {
		return SkillBasisTempletConfig.get(id);
	}

	@Override
	protected HostTemplet getHostTemplet(String id) {
		return SkillHostTempletConfig.get(id);
	}



}
