package cn.mxz.pvp;

import cn.mxz.AdditionMultiplier;
import cn.mxz.activity.boss.CopyedCamp;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.PlayerHeroImpl;
import cn.mxz.fighter.SuperHeroImpl;
import cn.mxz.fighter.SuperPlayerImpl;
import cn.mxz.formation.PlayerCamp;

public class SuperCamp extends CopyedCamp {

	/**
	 * @param camp
	 * @param attributeX	属性倍数  		实际属性 = 原属性 x attributeX
	 */
	public SuperCamp(PlayerCamp camp, double attributeX) {
		super(camp, attributeX);
	}

	public SuperCamp(PlayerCamp camp, AdditionMultiplier attributeX) {
		super(camp, attributeX);
	}

	@Override
	protected PlayerHeroImpl copyPlayer(PlayerHeroImpl h, AdditionMultiplier attributeX) {
		SuperPlayerImpl superPlayerImpl = new SuperPlayerImpl(h, attributeX);
		return superPlayerImpl;
	}

	@Override
	protected HeroImpl copyHero(HeroImpl h, AdditionMultiplier attributeX) {
		SuperHeroImpl superHeroImpl = new SuperHeroImpl(h, attributeX);
		return superHeroImpl;
	}

	@Override
	public int getShenJia() {
		return camp.getShenJia();
	}

	@Override
	public Fighter getMainFighter() {
		return camp.getMainFighter();
	}


}
