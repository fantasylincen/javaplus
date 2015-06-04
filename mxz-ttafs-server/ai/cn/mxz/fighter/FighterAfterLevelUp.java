package cn.mxz.fighter;

import cn.mxz.GodQurlityTemplet;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.user.team.god.Hero;

public class FighterAfterLevelUp extends HeroImpl {

	private Hero	hero;

	public FighterAfterLevelUp(Hero hero) {
		super(hero);
		this.hero = hero;
	}

	@Override
	public int getQuality() {
		int quality = super.getQuality();
		GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
		int q = temp.getQualityIdAfterLevelUp();
		if (q == -1) {
			return quality;
		}
		return q;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hero == null) ? 0 : hero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == hero) {
			return true;
		}

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FighterAfterLevelUp other = (FighterAfterLevelUp) obj;
		if (hero == null) {
			if (other.hero != null)
				return false;
		} else if (!hero.equals(other.hero))
			return false;
		return true;
	}
}
