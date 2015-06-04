package cn.mxz.listeners;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.world.NickManager;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.Listener;
import cn.mxz.events.ServerStartEvent;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.init.ReadyUserImpl;
import cn.mxz.user.init.ReadyUser;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import db.domain.NewFighter;
import db.domain.NewFighterImpl;
import db.domain.Pvp;
import define.D;

/**
 * 初始化10个机器人 作为 PVP天尊
 * 
 * @author 林岑
 * 
 */
//创建pvp天尊
public class InitPvpTianZun implements Listener<ServerStartEvent> {

	public static class Define {
		public static final String PVP_ROBOT_ID_HEAD = "pvp_robot_sid_";
	}

	private final class ThreadExtension extends Thread {

		@Override
		public void run() {

			int robotCount = getRobotCount();
			while (true) {

				robotCount++;
				if (robotCount >= 10) {

					break;
				}

				createRobot(robotCount);
			}
		}

		private int getRobotCount() {

			List<Pvp> all2 = DaoFactory.getPvpDao().getAll();
			
			int count = 0;
			for (Pvp p : all2) {
				String id = p.getUname();
				if (id.startsWith(Define.PVP_ROBOT_ID_HEAD)) {
					count++;
				}
			}
			return count;
		}
	}

	private final class MovePlayerToFirstComparator implements Comparator<Hero> {
		@Override
		public int compare(Hero o1, Hero o2) {

			int a1 = 1;
			int a2 = 1;

			FighterTemplet t1 = FighterTempletConfig.get(o1.getTypeId());
			FighterTemplet t2 = FighterTempletConfig.get(o1.getTypeId());

			if (t1.getCategory() == 3) {
				a1 = 0;
			}
			if (t2.getCategory() == 3) {
				a2 = 0;
			}

			return a2 - a1;
		}
	}

	@Override
	public void onEvent(ServerStartEvent e) {

//		new ThreadExtension().start();

	}
	/**
	 * 创建一个PVP天尊
	 * 
	 * @param tianZunIndex
	 */
	private void createRobot(int robotCount) {

		ReadyUser ready = new ReadyUserImpl();

		NickManager manager = WorldFactory.getWorld().getNickManager();
		ready.setNick(manager.getRandomNick());
		ready.setClientType(3);
		int sid = ServerConfig.getServerId();
		ready.setAccounts(Define.PVP_ROBOT_ID_HEAD + sid + "i_" + robotCount);
		ready.setFighterTypeId(getRandomPlayerTypeId());
		ready.setRoleId(ready.getAccounts());

		City city = CityFactory.createNewCity(ready);

		createPlayer(city);
		
		setRandomLevel(city);
		createRandomFighters(city);
		moveToFormation(city);

//		toBeTianZun(city, robotCount);

		Debuger.debug("InitPvpTianZun.createTianZun() 创建了天尊机器人:" + city);
	}

	private void createPlayer(City city) {
		NewFighter f = new NewFighterImpl();

		f.setUname(city.getId());

		List<FighterTemplet> all = FighterTempletConfig.findByCategory(3);
		FighterTemplet t = Util.Collection.getRandomOne(all);
		
		int id = t.getId();
		f.setTypeId(id);

		f.setLevel(1);

		f.setV(HeroProperty.SKILL_LEVEL.getValue(), 1);

		f.setV(HeroProperty.HP.getValue(), Integer.MAX_VALUE / 3);

		DaoFactory.getNewFighterDao().save(f);		
	}
//	private void toBeTianZun(City city, int tianZunCount) {
//		PvpManager pm = city.getNewPvpManager();
//		PvpPlayer p = pm.getPlayer();
//		Pvp dto = p.getDto();
//
//		if (tianZunCount == 0) {
//			dto.setDanId(find(5, 1));
//		} else {
//			dto.setDanId(find(5, 2));
//		}
//
//		DaoFactory.getPvpDao().save(dto);
//	}

	private int find(int danLv, int level) {
		List<DanRewardTemplet> all = DanRewardTempletConfig.getAll();
		for (DanRewardTemplet da : all) {
			if (da.getDanLv() == danLv && da.getLevel() == level) {
				return da.getDan();
			}
		}
		throw new RuntimeException("没有找到对应的段位:" + danLv + ":" + level);
	}

	private void moveToFormation(City city) {
		List<Integer> positions = Lists.newArrayList(1, 3, 4, 5, 7);
		Util.Collection.upset(positions);

		List<Hero> all = Lists.newArrayList(city.getTeam().getAll());

		movePlayerToFirst(all);

		Iterator<Integer> pit = positions.iterator();

		Formation formation = city.getFormation();
		for (Hero hero : all) {
			if (!pit.hasNext()) {
				break;
			}
			Integer p = pit.next();
			try {
				formation.setHeroPosition(p, hero.getTypeId());
			} catch (Exception e) {
				Debuger.error("InitPvpTianZun.moveToFormation()" + e.getMessage());
			}
		}

	}

	/**
	 * 把主角移动到最前面
	 * 
	 * @param all
	 */
	private void movePlayerToFirst(List<Hero> all) {
		Comparator<Hero> c = new MovePlayerToFirstComparator();
		Collections.sort(all, c);
	}

	private void createRandomFighters(City city) {

		List<Integer> ls = Util.Collection
				.getIntegers(D.PVP_TIAN_ZUN_LEVEL_FIGHTERS_COUNT_SCOPE);

		String ids = D.PVP_TIAN_ZUN_FIGHTERS.replaceAll("\\[newline\\]", "");

		List<Integer> fighterIds = Util.Collection.getIntegers(ids);

		int min = ls.get(0);
		int max = ls.get(1);
		int count = Util.Random.get(min, max) - 1;

		for (int i = 0; i < count; i++) {
			Util.Collection.upset(fighterIds);
			Iterator<Integer> it = fighterIds.iterator();
			Integer id = it.next();
			it.remove();

			createFighter(id, city);
		}
	}

	private void createFighter(Integer id, City city) {

		Team team = city.getTeam();

		if (team.contains(id)) {
			return;
		}

		Hero hero = team.createNewHero(id);

		List<Integer> ls = Util.Collection
				.getIntegers(D.PVP_TIAN_ZUN_LEVEL_SCOPE);
		int min = ls.get(0);
		int max = ls.get(1);

		hero.getDto().setLevel(Util.Random.get(min, max));

		hero.commit();
	}

	private void setRandomLevel(City city) {
		PlayerHero p = city.getTeam().getPlayer();
		List<Integer> ls = Util.Collection
				.getIntegers(D.PVP_TIAN_ZUN_LEVEL_SCOPE);
		int min = ls.get(0);
		int max = ls.get(1);
		
//		int min = 8;
//		int max = 10;

		p.getDto().setLevel(Util.Random.get(min, max));
		p.commit();
	}

	private int getRandomPlayerTypeId() {
		List<FighterTemplet> temps = FighterTempletConfig.findByCategory(3);
		return Util.Collection.getRandomOne(temps).getId();
	}

}
