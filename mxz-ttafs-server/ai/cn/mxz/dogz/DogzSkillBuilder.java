package cn.mxz.dogz;

import cn.mxz.protocols.user.DogzP.DogzSkillPro;

class DogzSkillBuilder {

	public DogzSkillPro build(DogzSkill skill) {

		DogzSkillPro.Builder b = DogzSkillPro.newBuilder();

		b.setId(skill.getId());

		b.setSkillAddition(10);

		b.setSkillProbability((int) (skill.getProbablilty() * 100));

		return b.build();
	}

}
