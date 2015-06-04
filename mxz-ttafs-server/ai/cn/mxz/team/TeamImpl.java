package cn.mxz.team;

import java.util.List;
import java.util.Map;
import java.util.Set;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.FighterHadDao;
import mongo.gen.MongoGen.FighterHadDto;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.IFighterTemplet;
import cn.mxz.city.City;
import cn.mxz.events.CreateNewHeroEvent;
import cn.mxz.events.Events;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.fighter.PlayerHeroImpl;
import cn.mxz.spirite.SpiriteManager;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Maps;

import db.dao.impl.DaoFactory;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;
import define.D;

public class TeamImpl extends FighterCollection implements Team {

	public TeamImpl(City city) {
		setCity(city);
	}

	@Override
	public PlayerHero getPlayer() {

		for (Hero g : fighters.values()) {
			if (g.isPlayer()) {
				return (PlayerHero) g;
			}
		}
		int type = FighterTempletConfig.findByCategory(3).get(0).getType();

		createNewHero(type);

		return getPlayer();
//		throw new NullPointerException("玩家没有主角神将" + getCity().getId());
	}

	// @Override
	// public Map<Integer, Hero> getFightersInTeam() {
	//
	// return getFighters(true);
	// }
	//
	// @Override
	// public Map<Integer, Hero> getFighterInDepot() {
	//
	// return getFighters(false);
	// }

	// private Map<Integer, Hero> getFighters(boolean isInTeam) {
	//
	// final Map<Integer, Hero> fs = new HashMap<Integer, Hero>(fighters);
	//
	// final Set<Entry<Integer, Hero>> set = fs.entrySet();
	//
	// final Iterator<Entry<Integer, Hero>> it = set.iterator();
	//
	// while (it.hasNext()) {
	//
	// final Map.Entry<Integer, Hero> e = it.next();
	//
	// // List<Hero> fighters2 = city.getFormation().getFighters();
	//
	// Formation formation = city.getFormation();
	//
	// final boolean inTeam = formation.contains(e.getValue());//.isInTeam();
	//
	// if(isInTeam != inTeam) {
	//
	// it.remove();
	// }
	// }
	//
	// return fs;
	// }

	@Override
	public void put(Hero h) {
		if (fighters.containsKey(h.getTypeId())) {
			throw new FighterExistException(h.getTypeId());
		}
		fighters.put(h.getTypeId(), h);
	}

	@Override
	public void remove(Hero h) {

		remove(h.getTypeId());
	}

	@Override
	public void remove(Integer id) {

		fighters.remove(id);

		DaoFactory.getNewFighterDao().delete(id, getCity().getId());
	}

	@Override
	public int getCount(int tradType) {
		int count = 0;
		for (Hero hero : getAll()) {
			if (hero.getTypeId() == tradType) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean contains(int heroId) {
		return fighters.containsKey(heroId);
	}

	@Override
	public boolean createNewHeroAuto(int find) {
		Hero hero = get(find);
		boolean needCreateNewHero = hero == null;
		SpiriteManager manager = city.getSpiriteManager();
		if (needCreateNewHero) {
			createNewHero(find);
		} else {
			int addSolCount = getAddSolCount(find);
			
			manager.add(find, addSolCount);
		}
		return needCreateNewHero;
	}

	public static int getAddSolCount(int find) {
		FighterTemplet temp = FighterTempletConfig.get(find);
		double soul = temp.getSoul();
		soul *= D.XUN_XIAN_HUN_PO_RATIO;
		soul = Math.max(soul, 0);
		soul += D.XUN_XIAN_HUN_PO_EXTRA;
		return (int) soul;
	}

	@Override
	public Hero createNewHero(int type) {

		final int fighterId = nextId();

		final NewFighter f = buildFighter(type, fighterId);

		f.setV(HeroProperty.HP.getValue(), Integer.MAX_VALUE / 3);

		final Hero h = createGod(f);

		put(h);

		markHad(type);

		DaoFactory.getNewFighterDao().add(f);

		Debuger.debug("CreateNewFighter", city.getId() + ";"
				+ city.getPlayer().getNick() + ";" + type);

		Events.getInstance().dispatch(new CreateNewHeroEvent(city, h));

		return h;
	}

	/**
	 * 制造一个神将
	 * 
	 * @param f
	 * @return
	 */
	private Hero createGod(NewFighter f) {

		int typeId = f.getTypeId();

		HeroImpl god;

		IFighterTemplet temp = FighterTempletConfig.get(typeId);

		if (temp == null) {
			throw new NullPointerException(typeId + "");
		}

		if (temp.getCategory() != 3) {

			god = new HeroImpl();

		} else {

			god = new PlayerHeroImpl();
		}

		god.setCity(city);

		god.setDto(f);

		city.getSkillManager().ensureTianFu(god);

		return god;
	}

	// /**
	// * 创建天赋技能
	// * @param god
	// */
	// private void createTianFuSkill(Hero hero) {
	// FighterTemplet temp = FighterTempletConfig.get(hero.getTypeId());
	// int skill = temp.getSkill();
	//
	//
	// Skills s = new SkillsImpl();
	// s.setExp(0);
	// s.setLevel(1);
	// s.setFighterTypeId(hero.getTypeId());
	// s.setSkillId(skill);
	// s.setUname(city.getId());
	// s.setIds(hero.getTypeId());
	//
	// }

	private void markHad(int id) {

		FighterHadDao DAO = Daos.getFighterHadDao();

		FighterHadDto had = DAO.get(city.getId(), id);

		if (had == null) {

			had = new FighterHadDto();

			had.setUname(city.getId());

			had.setFighterTypeId(id);

			DAO.save(had);
		}
	}

	private NewFighter buildFighter(int id, int fighterId) {

		final NewFighter f = new NewFighterImpl();

		f.setUname(city.getId());

		f.setTypeId(id);

		f.setV(HeroProperty.SKILL_LEVEL.getValue(), 1);

		FighterTemplet temp = FighterTempletConfig.get(id);

		if (temp == null) {
			throw new NullPointerException("" + fighterId + " " + id);
		}

		f.setV(HeroProperty.QUALITY.getValue(), temp.getQuality());

		f.setLevel(1);

		f.setV(HeroProperty.SKILL_ID.getValue(), temp.getSkill());

		return f;
	}

	private int nextId() {
		final Set<Integer> allKey = fighters.keySet();
		if (allKey.isEmpty()) {
			return 1;
		}
		final int fighterId = Util.Collection.getMax(allKey) + 1;
		return fighterId;
	}

	@Override
	public Map<Integer, Hero> createFighters() {

		final Map<Integer, Hero> fighters = Maps.newConcurrentMap();

		final List<NewFighter> all = DaoFactory.getNewFighterDao().findByUname(city.getId());

		for (NewFighter f : all) {

			fighters.put(f.getTypeId(), createGod(f));
		}

		return fighters;
	}

	// @Override
	// public List<TGrid> getTeamGrids() {
	//
	// Formation formation = city.getFormation();
	//
	// List<TGrid> ls = Lists.newArrayList();
	//
	// for (int i = 0; i < UserFormationFighter.FIGHTER_TYPE_ID_LEN; i++) {
	//
	// Hero hero = formation.getHeroByIndex(i);
	//
	// ls.add(new TGridImpl(city, hero, i));
	// }
	//
	// sort(city, ls);
	//
	// // for (TGrid tGrid : ls) {
	// //
	// // Debuger.debug("TeamBuilerImpl2.getTeamGrids() 是否开启: " + tGrid.isOpen()
	// + ", " + tGrid.getFighterId());
	// // }
	//
	// return ls;
	// }

	// private void sort(final City city, List<TGrid> ls) {
	//
	// Collections.sort(ls, new Comparator<TGrid>() {
	//
	// @Override
	// public int compare(TGrid o1, TGrid o2) {
	//
	// int a1 = o1.isOpen() ? 1 : 0;
	//
	// int a2 = o2.isOpen() ? 1 : 0;
	//
	// if(a1 == a2) { //1根据是否开启进行比较
	//
	// return byHasPersonAndCapacity(o1, o2);
	// }
	//
	// return a2 - a1;
	// }
	//
	// private int byHasPersonAndCapacity(TGrid o1, TGrid o2) { //根据是否有人和战斗力进行比较
	//
	// int h1 = hasPerson(o1) ? 1 : 0;
	//
	// int h2 = hasPerson(o2) ? 1 : 0;
	//
	// if(h1 == h2) { //2根据是否有人进行比较
	//
	// return byCapacity(o1, o2);
	// }
	//
	// return h2 - h1;
	// }
	//
	// //根据战斗力比较
	// private int byCapacity(TGrid o1, TGrid o2) {
	//
	// int c1 = o1.getFighterId();
	//
	// int c2 = o2.getFighterId();
	//
	// return c1 - c2;
	// }
	//
	// private boolean hasPerson(TGrid o1) {
	//
	// int fighterId = o1.getFighterId();
	//
	// return city.getTeam().getGod(fighterId) != null;
	// }
	// });
	// }
}
