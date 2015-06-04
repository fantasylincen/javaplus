package cn.mxz.battle;

import cn.mxz.protocols.user.battle.WarSituationP.BuffDisappearPro;

class BuffDisapearProBuilder {

	public BuffDisappearPro build(BuffDisappear bd) {
		BuffDisappearPro.Builder b = BuffDisappearPro.newBuilder();
		b.setBuffId(bd.getBuffId());
		b.setPosition(bd.getPosition());
		b.setIsUnder(bd.isUnder());
//		Debuger.debug("communication: BuffDisapearProBuilder.build() 失效 bufferId = " + BuffTempletConfig.get(bd.getBuffId()).getName() + ", position = " + bd.getPosition());
		return b.build();
	}

}
