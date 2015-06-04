package cn.mxz.mission.type;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.fighter.Fighter;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 *
 * 	这个阵营是为了提高战斗计算效率
 * 	这个阵营里面的战士全部都是拷贝出来的, 战斗过程中, 会改变每个英雄的dto对象的属性.
 *
 * @param user
 * @return
 */
public class CacheCamp implements PlayerCamp {

	private Map<Integer, Hero>	heros;
	private City	city;

	public CacheCamp(City city) {

		Formation formation = city.getFormation();
		PlayerCamp camp = formation.getSelected();

		heros = buildCacheHero(camp);
		this.city = city;
	}

	private Map<Integer, Hero> buildCacheHero(PlayerCamp camp) {
		Map<Integer, Hero> map = Maps.newHashMap();
		List<Hero> fs = camp.getFighters();
		for (Hero hero : fs) {
			int position = camp.getPosition(hero);
			map.put(position, buildHero(hero));
		}
		return map;
	}

	protected Hero buildHero(Hero hero) {
		return new CacheHero(hero);
	}


	protected Hero buildHero(PlayerHero hero) {
		return new CacheHero(hero);
	}

	@Override
	public List<Hero> getFighters() {
		return Lists.newArrayList(heros.values());
	}

	@Override
	public int getPosition(Fighter f) {
		Set<Entry<Integer, Hero>> es = heros.entrySet();
		for (Entry<Integer, Hero> e : es) {
			Hero v = e.getValue();
			if(f.equals(v)) {
				return e.getKey();
			}
		}
		throw new IllegalArgumentException("该战士不在阵容中!" + f.getTypeId() + " | " + city.getId());
	}

	@Override
	public Fighter get(int position) {
		return heros.get(position);
	}

	@Override
	public City getCity() {
		return city;
	}


	@Override
	public int getShenJia() {
		int a = 0;
		for (Hero h : heros.values()) {
			a += h.getShenJia();
		}
		return a;
	}

	@Override
	public Fighter getMainFighter() {
		List<Hero> all = getFighters();
		for (Hero hero : all) {
			if(hero.isPlayer()) {
				return hero;
			}
		}
		return null;
	}

	@Override
	public Dogz getDogz() {
		return city.getDogzManager().getFighting();
	}

	@Override
	public String getUserId() {
		return city.getId();
	}
}