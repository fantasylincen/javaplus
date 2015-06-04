package cn.mxz.activity.boss;

import java.util.List;

import cn.mxz.AdditionMultiplier;
import cn.mxz.battle.AbstractCamp;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.PlayerHeroImpl;
import cn.mxz.fighter.SuperHeroImpl;
import cn.mxz.fighter.SuperPlayerImpl;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;


public class PlayerCampOnBossActivity extends AbstractCamp<Hero> implements PlayerCamp {

	private List<Hero>		heros	= Lists.newArrayList();
	private PlayerCamp	playerCamp;
	private PlayerHeroImpl player;

	public PlayerCampOnBossActivity(PlayerCamp playerCamp, AdditionMultiplier attributeX) {
		this.playerCamp = playerCamp;
		List<Hero> fightersFront = playerCamp.getFighters();
		for (Hero hero : fightersFront) {
			if (hero instanceof HeroImpl) {
				HeroImpl h = (HeroImpl) hero;
				heros.add(copyHero(h, attributeX));
			} else if (hero instanceof PlayerHeroImpl) {
				PlayerHeroImpl h = (PlayerHeroImpl) hero;
				player = copyPlayer(h, attributeX);
				heros.add(player);
			} else {
				throw new RuntimeException("无法识别的类型:" + hero.getClass());
			}
		}
	}

	private PlayerHeroImpl copyPlayer(PlayerHeroImpl h, AdditionMultiplier attributeX) {
		SuperPlayerImpl superPlayerImpl = new SuperPlayerImpl(h, attributeX);
		return superPlayerImpl;
	}

	/**
	 * 拷贝一个战士, 用于打仗
	 * @param h 原战士
	 * @param attributeX 属性加成
	 * @return
	 */
	private HeroImpl copyHero(HeroImpl h, AdditionMultiplier attributeX) {
		SuperHeroImpl superHeroImpl = new SuperHeroImpl(h, attributeX);
		return superHeroImpl;
	}

	@Override
	public List<Hero> getFighters() {
		return heros;
	}

	@Override
	public int getPosition(Fighter f) {
		return playerCamp.getPosition(f);
	}

	@Override
	public City getCity() {
		return playerCamp.getCity();
	}

	@Override
	public int getShenJia() {
		return playerCamp.getShenJia();
	}

	@Override
	public Fighter getMainFighter() {
		return player;
	}

	@Override
	public Dogz getDogz() {
		return playerCamp.getDogz();
	}

	@Override
	public String getUserId() {
		return playerCamp.getUserId();
	}

	
}
