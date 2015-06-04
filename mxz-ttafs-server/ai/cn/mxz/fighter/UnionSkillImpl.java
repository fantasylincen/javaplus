package cn.mxz.fighter;

import cn.mxz.team.builder.UnionSkill;

class UnionSkillImpl implements UnionSkill {

	private int id;

	UnionSkillImpl(int id) {

		this.id = id;
	}

	@Override
	public int getId() {

		return id;
	}
//
//	@Override
//	public Attribute getAddition() {
//
////		return UnionSkillTempletConfig.get(id);
//		return new AttributeEmpty();
//	}
//
//	@Override
//	public AdditionMultiplier getAdditionMultiplier() {
//
////		return UnionSkillTempletConfig.get(id);
//		return new TalentEmpty();
//	}

}
