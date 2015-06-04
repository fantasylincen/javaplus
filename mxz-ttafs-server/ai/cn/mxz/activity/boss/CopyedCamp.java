package cn.mxz.activity.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.mxz.AdditionMultiplier;
import cn.mxz.FighterConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.battle.AbstractCamp;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.PlayerHeroImpl;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;

public abstract class CopyedCamp extends AbstractCamp<Hero> implements PlayerCamp {

	protected PlayerCamp		camp;

	/**
	 * <位置, 战士>
	 */
	private Map<Integer, Hero>	heros	= new HashMap<Integer, Hero>();

	

	public CopyedCamp(PlayerCamp camp, AdditionMultiplier attributeX) {

		this.camp = camp;

		List<Hero> fightersFront = camp.getFighters();

		for (Hero hero : fightersFront) {

			int position = camp.getPosition(hero);

			if (position == -1) {

				throw new RuntimeException("位置错误!");
			}

			int tID = hero.getTypeId();
			IFighterTemplet temp = FighterConfig.get(tID);
			int c = temp.getCategory();


			if (hero.isPlayer()) {

				PlayerHeroImpl h = (PlayerHeroImpl) hero;
				
				heros.put(position, copyPlayer(h, attributeX));
				
			} else if (c == 4 || hero instanceof HeroImpl) {
				
				HeroImpl h = (HeroImpl) hero;

				heros.put(position, copyHero(h, attributeX));

			} else {

				throw new RuntimeException("无法识别的类型:" + hero.getClass());
			}
		}
	}
	@Override
	public String getUserId() {
		return camp.getUserId();
	}


	public CopyedCamp(PlayerCamp camp, double attributeX) {
		this(camp, new AdditionMultiplierImpl(attributeX));
	}

	protected abstract PlayerHeroImpl copyPlayer(PlayerHeroImpl h, AdditionMultiplier attributeX);

	protected abstract HeroImpl copyHero(HeroImpl h, AdditionMultiplier attributeX);

	@Override
	public List<Hero> getFighters() {

		return new ArrayList<Hero>(heros.values());
	}

	@Override
	public int getPosition(Fighter f) {

		for (Entry<Integer, Hero> s : heros.entrySet()) {

			Hero value = s.getValue();

			if (f.equals(value)) {

				return s.getKey();
			}
		}

		return -1;
	}

	@Override
	public City getCity() {

		return camp.getCity();
	}
	
	@Override
	public Dogz getDogz() {
		return camp.getDogz();
	}
}
