package cn.mxz.dogz;

import cn.mxz.protocols.user.DogzP.DogzPro;
import cn.mxz.util.FractionBuilder;

public class DogzBuilder {

	public DogzPro build(Dogz dogz) {

		DogzPro.Builder b = DogzPro.newBuilder();

		b.setGrowth(new FractionBuilder().build(1, 1));

		b.setIsFighting(dogz.isFighting());

		b.setIsProtected(false);

		b.setLevel(dogz.getLevel());

		b.setStep(1);

		b.setTypeId(dogz.getTypeId());

		b.setDunWuTimes(0);

		b.setFishCount(0);

		b.setHuiHunDanCount(0);

		b.setProtectProp(0);//保护道具数量

		b.setSkill(new DogzSkillBuilder().build(dogz.getSkill()));

		return b.build();
	}

}
