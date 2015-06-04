package cn.mxz.xianshi;

import cn.mxz.protocols.user.god.FighterP.RecruitResaultPro;
import cn.mxz.team.builder.FighterBuilder;
import cn.mxz.user.team.god.Hero;

public class RecruitResaultBuilder {

	public RecruitResaultPro build(RecruitResault rr) {
		RecruitResaultPro.Builder b = RecruitResaultPro.newBuilder();
		b.setExtraSpiriteId(-1);

		Hero h = rr.getFighter();
		if (h != null) {
			h.getSkills();// 确保有天赋技能
			b.setFighter(new FighterBuilder().build(h));
		}

//		if (rr.getSpiriteId() != -1)
//			Debuger.debug("RecruitResaultBuilder.build()" + rr.getSpiriteId()
//					+ ":" + rr.getSpiriteCount());

		b.setSpriteHasNow(rr.getSpiriteHasNow());

		b.setSpiriteId(rr.getSpiriteId());

		b.setSpiriteCount(rr.getSpiriteCount());

		return b.build();
	}

}
