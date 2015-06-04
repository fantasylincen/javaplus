package cn.mxz.task.achieve;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.counter.Counter;
import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.equipment.Equipment;
import cn.mxz.equipment.EquipmentManager;
import cn.mxz.friend.Friend;
import cn.mxz.mission.IMissionManager;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.skill.SkillManager;
import cn.mxz.team.Skill;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;
//getSkillLevelUpTimes() ;
//getTacticalLevelUpTimes() ;

public class AchieveTaskPlayerImpl implements AchieveTaskPlayer {

	private City	city;

	public AchieveTaskPlayerImpl(City city) {
		this.city = city;
	}

	@Override
	public int getMaxMissionId() {
		return city.getMission().getMaxMissionId();

	}

	// @Override
	// public int getMaxEquipmentLevel() {
	// EquipmentManager manager = city.getEquipmentManager();
	// Map<Integer, Equipment> all = manager.getAll();
	// int max = 0;
	// for (Equipment e : all.values()) {
	// int level = e.getLevel();
	// if (level > max) {
	// max = level;
	// }
	// }
	// return max;
	// }

	@Override
	public int getFriendCount() {
		List<Friend> all = city.getFriendManager().getAll();
		return all.size();
	}

	// @Override
	// public int getMixEquipmentCount() {
	// return get(CounterKey.EQUIPMENT_GENERATE_COUNT);
	// }
	//
	// @Override
	// public int getDailyTaskCount() {
	// DailyTaskPlayer player = DailyTaskObjects.getPlayer(city.getId());
	// return player.getTaskFinishCountToday();
	// }
	//
	// @Override
	// public boolean hasPracticePassTwoHour() {
	// PracticeCenter instance = PracticeCenterImpl.getInstance();
	// Group group = instance.getGroup(city.getId());
	// if (group == null) {
	// return false;
	// }
	// return group.getPracticeTime() >= 2 * 60 * 60;
	// }
	//
	// @Override
	// public int getPracticeNiceTimes() {
	// return get(CounterKey.PRACTICE_FINISH_TIMES);
	// }
	//
	// @Override
	// public int getDogzCount() {
	// DogzManager manager = city.getDogzManager();
	// Map<Integer, Dogz> dogzAll = manager.getDogzAll();
	// return dogzAll.size();
	// }

	// @Override
	// public int getRankUpTimesToday() {
	// UserCounter counter = city.getUserCounter();
	// int rank = counter.get(CounterKey.PVP_RANK_START_TODAY);
	//
	// PvpPlayer player = PvpFactory.getPvpPlayer(city.getId());
	// int rankInAll = player.getRankInAll();
	// int up = rankInAll - rank;
	// if(up < 0) {
	// up = 0;
	// }
	// return up;
	// }

	// @Override
	// public int getWinningStreakTimes() {
	// return get(CounterKey.PVP_WINNING_STREAK_MAX);
	// }
	//
	// @Override
	// public int getInheritTimes() {
	// return get(CounterKey.INHERIT_TIMES);
	// }
	//
	// @Override
	// public int getCoronaTimes() {
	// return get(CounterKey.CORONA_RUN_TIMES);
	// }
	//
	// @Override
	// public int getCornucopiaTimes() {
	// return get(CounterKey.CORNUCOPIA_RUN_TIMES);
	// }
	//
	// @Override
	// public int getDogzDunWuTimes() {
	// return get(CounterKey.DOGZ_DUNWU_TIMES);
	// }
	//
	// @Override
	// public int getDogzFeedTimes() {
	// return get(CounterKey.DOGZ_FEED_TIMES);
	// }

	private int get(CounterKey d, Object... args) {
		UserCounter his = city.getUserCounterHistory();
		return his.get(d, args);
	}

	// @Override
	// public int getFighterLevelUpTimes() {
	// return get(CounterKey.FIGHTER_LEVEL_UP_TIMES_MAX);
	// }
	//
	// @Override
	// public int getFighterSacrificeTimes() {
	// return get(CounterKey.FIGHTER_SACRIFICE_COUNT);
	// }
	//
	// @Override
	// public int getFightingCapacity() {
	// return city.getFormation().getSelected().getShenJia();
	// }

	// @Override
	// public int getFishCount(int id) {
	// return get(CounterKey.FISH_SUCCESS_COUNT, id);
	// }

	@Override
	public int getFishTimes() {
		return get(CounterKey.FISH_TIMES);
	}

	// @Override
	// public int getCash() {
	// return city.getPlayer().get(PlayerProperty.CASH);
	// }

	@Override
	public int getFindFighterTimes() {
		return getR(1) + getR(2) + getR(3);
	}

	private int getR(int i) {
		return get(CounterKey.RECRUIT_TIMES, i);
	}

	// @Override
	// public int getFighterCountByQuality(int i) {
	// Map<Integer, Hero> all = city.getTeam().getFightersAll();
	// int count = 0;
	// for (Hero h : all.values()) {
	// if (h.getStep() == i) {
	// count++;
	// }
	// }
	// return count;
	// }

	@Override
	public int getShangXiangTimes() {
		int times = get(CounterKey.SHANG_XIANG_TIMES);
		return times;
	}

	// @Override
	// public int getSnatchSuccessTimes() {
	// return get(CounterKey.SNATCH_SUCCESS_TIMES);
	// }
	//
	// @Override
	// public int getLevel() {
	// return city.getLevel();
	// }

	// @Override
	// public int getRankInPvp() {
	// PvpPlayer pvp = PvpFactory.getPvpPlayer(city.getId());
	// return pvp.getRankInAll();
	// }
	//
	// @Override
	// public int getDemonStar(int mapId, int demonId) {
	// return get(CounterKey.MISSION_DEMON_STAR, ":" + mapId + ":", demonId);
	// }
	//
	// @Override
	// public boolean isFinishAllSendGold() {
	// LevelUpRewardPlayer p = LevelUpRewardObjects.getPlayer(city.getId());
	// return p.isFinishAllSendGold();
	// }

	// @Override
	// public boolean hadReceiveFirstRecharge() {
	// UserCounter his = city.getUserCounterHistory();
	// int i = his.get(CounterKey.FIRST_GOLD_COUNT);
	// return i == -1;
	// }

	@Override
	public int getGongDe() {
		UserCounter uc = city.getUserCounter();
		return uc.get(CounterKey.GONG_DE);
	}

	@Override
	public int getBossChallengeTimes() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.SHEN_MO_CHALLENGE_TIMES);
	}

	@Override
	public int getYuanShenResetTimes() {

		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.YUAN_SHEN_RESET);
	}

//	@Override
//	public boolean hasHeroStepIs(int a0) {
//
//		Team team = city.getTeam();
//
//		Collection<Hero> values = team.getAll();
//
//		for (Hero hero : values) {
//
//			if (hero instanceof PlayerHero) {
//				continue;
//			}
//
//			if (hero.getStar() >= a0) {
//				return true;
//			}
//		}
//		return false;
//	}

	@Override
	public int getFriendAttackTimes() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.FRIEND_ATTACK_TIMES);
	}

	@Override
	public int getPvpChallengeTimes() {

		return get(CounterKey.PVP_CHALLENGE_TIMES);
	}

	// @Override
	// public boolean hasDogz(int a0) {
	// if (city.getLevel() <
	// DogzOpenTempletConfig.getAll().get(0).getUserLevel()) {
	// return false;
	// }
	// DogzManager dogzManager = city.getDogzManager();
	// Dogz d = dogzManager.getDogz(a0);
	// return d != null;
	// }
	//
	// @Override
	// public boolean containsEquipmentLevelIs(int a0) {
	// EquipmentManager em = city.getEquipmentManager();
	// Collection<? extends Equipment> values = em.getAll().values();
	//
	// for (Equipment equipment : values) {
	// int level = equipment.getLevel();
	// if (level >= a0) {
	// return true;
	// }
	// }
	// return false;
	// }

	@Override
	public int getSkillLevelUpTimes() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.SKILL_LEVEL_UP_TIMES);
	}

	@Override
	public int getDailyTaskFinishCount() {
//		DailyTaskManager dm = city.getDailyTaskManager();
//		Collection<Task<DailyTaskDto>> all = dm.getAll();
//		int count = 0;
//		for (Task<DailyTaskDto> task : all) {
//			if (task.isFinishAll() && task.isGiveBack()) {
//				count++;
//			}
//		}
//		// Debuger.debug("AchieveTaskPlayerImpl.getDailyTaskFinishCount()" +
//		// count);
//		Debuger.debug("AchieveTaskPlayerImpl.getDailyTaskFinishCount()" + count);
//		return count;
		int i = get(CounterKey.DAILY_TASK_FINISH_COUNT_HISTORY);
		
		return i;
	}

	// @Override
	// public boolean isOneHeroWearEquipmentStepOver(int a0) {
	// Formation formation = city.getFormation();
	// PlayerCamp selected = formation.getSelected();
	// List<Hero> fighters = selected.getFighters();
	// for (Hero hero : fighters) {
	// List<Equipment> equipments = hero.getEquipments();
	// if (equipments.isEmpty()) {
	// continue;
	// }
	//
	// //
	// Debuger.debug("AchieveTaskPlayerImpl.isOneHeroWearEquipmentStepOver() name:"
	// // + hero.getName() + ":" + equipments.size());
	// if (isAllStepOver(equipments, a0)) {
	// return true;
	// }
	// }
	// return false;
	// }
//
//	private boolean isAllStepOver(List<Equipment> equipments, int a0) {
//		if (equipments.size() < 5) {
//			return false;
//		}
//
//		for (Equipment equipment : equipments) {
//			int step = equipment.getStep();
//			if (step < a0) {
//				return false;
//			}
//		}
//		return true;
//	}

	@Override
	public int getGenerateSkillCount() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.SKILL_GENERATE_COUNT);
	}

	@Override
	public int getPvpDanId() {
		PvpManager newPvpManager = city.getNewPvpManager();
		PvpPlayer player = newPvpManager.getPlayer();
		return player.getDan();
	}

	@Override
	public int getMaxHeroShenJia() {
		Team team = city.getTeam();
		Collection<Hero> values = team.getAll();
		IntegerFetcher<Hero> fetcher = new IntegerFetcher<Hero>() {

			@Override
			public Integer get(Hero t) {
				return t.getShenJia();
			}
		};
//		[8264, 6772, 7656, 8140, 7836, 10395, 10604, 10790, 9859, 10854, 9779, 4396]
		List<Integer> listByOneFields = Util.Collection.getListByOneFields(fetcher, values);
		int max = Util.Collection.getMax(listByOneFields);
		return max;
	}

	@Override
	public int getTacticalLevelUpTimes() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.TACTICAL_LEVEL_UP_TIMES);
	}

	// @Override
	// public int getOnFormationCount() {
	// Formation formation = city.getFormation();
	// PlayerCamp selected = formation.getSelected();
	// return selected.getFighters().size();
	// }

	@Override
	public int getYuanShenLevelUpTimes() {

		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.YUAN_SHEN_LEVEL_UP_TIMES);
	}

	@Override
	public int getPlayerLevel() {
		Player player = city.getPlayer();
		return player.getLevel();
	}

	// @Override
	// public int getSkillsCount(int a0) {
	// SkillManager sm = city.getSkillManager();
	// List<Skill> all = sm.getAll();
	// int count = 0;
	// for (Skill skill : all) {
	// int step = skill.getStep();
	// if (step >= a0) {
	// count++;
	// }
	// }
	// return count;
	// }

	@Override
	public int getGenerateTacticalCount() {

		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.TACTICAL_GENERATE_COUNT);
	}

	@Override
	public int getPvpWinStreakTimes() {
		PvpManager pv = city.getNewPvpManager();
		PvpPlayer player = pv.getPlayer();
		return player.getCurrentWinStreak();
	}

	// @Override
	// public int getTacticalsCount(int a0) {
	// Formation formation = city.getFormation();
	// return formation.getTacticalsCount(a0);
	// }

	@Override
	public int getTeamShenJia() {
		Formation formation = city.getFormation();
		int shenJia = formation.getShenJia();
//		Debuger.debug("AchieveTaskPlayerImpl.getTeamShenJia()" + shenJia);
		return shenJia;
	}

	@Override
	public int getRevengeTimes() {

		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.REVENGE_TIMES);
	}

	// @Override
	// public int getGenerateEquipmentCount() {
	//
	// UserCounter uc = city.getUserCounterHistory();
	// return uc.get(CounterKey.GENERATE_EQUIPMENT_COUNT);
	// }

	@Override
	public int getYaoQianShuTimes() {
		UserCounter uc = city.getUserCounterHistory();
		return uc.get(CounterKey.CORNUCOPIA_RUN_TIMES);
	}

	@Override
	public boolean isPerfectMission(int a0) {

		IMissionManager ms = city.getMission();
		return ms.isPerfect(a0);

		// int start = MissionTempletConfig.getMinKey();
		//
		// int count = 0;
		// int max = 0;
		//
		// for (int i = start; i <= a0; i++) {
		// MissionStar mis = manager.getStarByMissionId(i);
		// count += mis.getCount();
		// max += mis.getMax();
		// }
		//
		// return (0f + count) / max >= D.PERFECT_PASS_MISSION;
	}

	@Override
	public int getTiBuWeiNiZhuanTimes() {
		UserCounter his = city.getUserCounterHistory();
		int i = his.get(CounterKey.TI_BU_WEI_NI_ZHUAN_TIMES);
		// Debuger.debug("AchieveTaskPlayerImpl.getTiBuWeiNiZhuanTimes()" + i);
		return i;
	}

	// @Override
	// public int getEquipmentsCount(int a0) {
	// EquipmentManager em = city.getEquipmentManager();
	// Collection<Equipment> values = em.getAll().values();
	//
	// int count = 0;
	// for (Equipment equipment : values) {
	// int step = equipment.getStep();
	// if (step >= a0) {
	// count++;
	// }
	// }
	// return count;
	// }

	// @Override
	// public int getTianMingCount() {
	// Formation formation = city.getFormation();
	// Collection<? extends Hero> all = formation.getAlternate().getFighters();
	// all = Lists.newArrayList(all);
	//
	// List<Hero> fighters = formation.getSelected().getFighters();
	// int count = 0;
	// for (Hero hero : fighters) {
	// HeroTianMing tianMing = hero.getTianMing();
	// List<Integer> tianMingIds = tianMing.getTianMingIds();
	// if (!tianMingIds.isEmpty()) {
	// count++;
	// }
	// }
	// return count;
	// }
	//
	// @Override
	// public int getFighterCountStarIs(int a0) {
	// Team team = this.city.getTeam();
	// Map<Integer, Hero> all = team.getFightersAll();
	// int count = 0;
	// for (Hero h : all.values()) {
	// if (h instanceof PlayerHero) {
	// continue;
	// }
	// if (h.getStar() == a0) {
	// count++;
	// }
	// }
	// return count;
	// }

	@Override
	public int getSkillCountQualityIs(int a0) {
		SkillManager sm = this.city.getSkillManager();
		List<Skill> all = sm.getAll();
		int count = 0;
		for (Skill skill : all) {
			int fId = skill.getFighterId();

			FighterTemplet temp = FighterTempletConfig.get(fId);

			if (temp != null) {

				int s = temp.getSkill();
				if (skill.getId() == s) {// 天赋技能
					continue;
				}
			}

			if (skill.getStep() == a0) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getEquipmentLevelIs(int a0) {
		EquipmentManager em = city.getEquipmentManager();
		Map<Integer, ? extends Equipment> all = em.getAll();
		int count = 0;
		for (Equipment e : all.values()) {
			int level = e.getLevel();
			// Debuger.debug("AchieveTaskPlayerImpl.getEquipmentLevelIs() level = "
			// + level);
			if (level >= a0) {
				count++;
			}
		}
		// Debuger.debug("AchieveTaskPlayerImpl.getEquipmentLevelIs()" + count/
		// + "," + a0);
		return count;
	}

//	@Override
//	public int getTacticalCountQualityIs(int a0) {
//		Formation formation = city.getFormation();
//		int tacticalsCount = formation.getTacticalsCount(a0);
//		return tacticalsCount;
//	}
	
	@Override
	public int getTacticalCount() {
		Formation formation = city.getFormation();
		int tacticalsCount = formation.getTacticalsAll().size();
		return tacticalsCount;
	}

	// @Override
	// public int getFighterCountStepIs(int a0) {
	// Team team = this.city.getTeam();
	// Map<Integer, Hero> all = team.getFightersAll();
	// int count = 0;
	// for (Hero h : all.values()) {
	// int typeId = h.getTypeId();
	// IFighterTemplet temp = FighterTempletConfig.get(typeId);
	// int quality = temp.getQuality();
	//
	// GodQurlityTemplet gt = GodQurlityTempletConfig.get(quality);
	//
	// int step = gt.getStep();
	//
	// if (step == a0) {
	// count++;
	// }
	// }
	// return count;
	// }

	@Override
	public int getEquipmentCountStepIs(int a0) {
		EquipmentManager em = city.getEquipmentManager();
		Map<Integer, ? extends Equipment> all = em.getAll();
		int count = 0;
		for (Equipment e : all.values()) {

			try {
				if (a0 == e.getStep()) {
					count++;
				}
			} catch (Exception e1) {
				System.err.println(e.getTypeId());
				e1.printStackTrace();
			}
		}
		return count;
	}

	@Override
	public int getJiHuoTianMmingCount() {
		int count = 0;
		for (Hero h : city.getTeam().getAll()) {
			List<Integer> ids = h.getTianMing().getTianMingIds();
			List<Integer> ids2 = h.getEquipmentTianMing().getTianMingIds();
			List<Integer> ids3 = h.getSkillTianMing().getIds();
			count += ids.size() + ids2.size()  +  ids3.size();
		}
		return count;
	}

	@Override
	public int getJiaJiHuoBanCount() {
		int count = 0;
		for (Hero h : city.getTeam().getAll()) {
			if(h.isPlayer()) {
				continue;
			}
			int s = h.getStep();
			boolean isJia = s == 4 || s == 5;
			if(isJia) {
				count ++;
			}
		}
		return count;
	}

	@Override
	public int getHeroWearEquipmentStepOver(int a0) {
		EquipmentManager m = city.getEquipmentManager();
		Map<Integer, ? extends Equipment> all = m.getAll();

		Counter<Integer> c = new Counter<Integer>();

		for (Equipment e : all.values()) {
			int step = e.getStep();
			if(step != a0) {
				continue;
			}
			int heroId = e.getHeroId();
			c.add(heroId);
		}

		int count = 0;
		for (Integer heroId : c.keySet()) {
			if(c.get(heroId) >= D.SUIT_COUNT) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getHeroCountStarIs(int a0) {
		Collection<Hero> all = city.getTeam().getAll();
		int count = 0;
		for (Hero hero : all) {
			int star = hero.getStar();
			if(star >= a0) {
				count ++;
			}
		}
		return count;
	}
}
