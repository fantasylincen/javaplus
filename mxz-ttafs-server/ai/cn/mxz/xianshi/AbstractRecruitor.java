package cn.mxz.xianshi;

import java.util.Iterator;
import java.util.List;

import cn.javaplus.random.WeightFetcher;
import cn.javaplus.time.colddown.ColdDown;
import cn.javaplus.util.Util;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.GodQurlityTempletConfig;
import cn.mxz.GodTypeTemplet;
import cn.mxz.GodTypeTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.spirite.Spirite;
import cn.mxz.team.TeamImpl;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.cd.CDManager;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import define.D;

abstract class AbstractRecruitor implements Recruitor {

	private final class RecruitResaultImpl implements RecruitResault {
		private final boolean hasCreateNewHero;
		private final int find;
		private int count;

		private RecruitResaultImpl(boolean hasCreateNewHero, int find, int count) {
			this.hasCreateNewHero = hasCreateNewHero;
			this.find = find;
			this.count = count;
		}

		@Override
		public int getSpiriteId() {
			if (!hasCreateNewHero) {
				return find;
			}
			return -1;
		}

		@Override
		public int getSpiriteCount() {
			if (!hasCreateNewHero) {
				return count;
			}
			return -1;
		}

		@Override
		public Hero getFighter() {
			if (hasCreateNewHero) {
				return city.getTeam().get(find);
			}
			return null;
		}

		@Override
		public int getSpiriteHasNow() {
			int sid = getSpiriteId();
			if (sid == -1) {
				return 0;
			}

			Spirite spirite = city.getSpiriteManager().get(sid);
			if (spirite == null) {
				return 0;
			}

			return spirite.getCount();

		}

		@Override
		public int getStep() {
			Hero fighter = getFighter();
			if (fighter != null) {
				return fighter.getStep();
			}

			FighterTemplet t = FighterTempletConfig.get(find);
			int q = t.getQuality();
			return GodQurlityTempletConfig.get(q).getStep();
		}
	}

	protected City city;
	private int recruitType;
	protected GodTypeTemplet templet;
	private boolean hasFree;
	private boolean hasCd;
	private boolean needProp;

	@Override
	public void check(double discount, int times) {
		if (needProp) {
			checkProp(times, discount);
		} else {
			checkCD();
		}
	}

	private void checkProp(int times, double discount) {
		double needs = getNeed(times, discount);

		city.getChecker().checkProp(D.ID_XUN_XIAN_LING, (int) needs);
	}

	private int getNeed(int times, double discount) {
		double needs = templet.getXunXianLingNeed();

		needs *= discount * times;
		return (int) needs;
	}

	private void checkCD() {
		if (!hasFree) {
			ColdDown cd = getCD();
			cd.check();
		}
	}

	private void afterRecruit(double discount) {
		markTimes();
		reduce(discount);

		if (!needProp) {
			try {
				getCD().add();
			} catch (Exception e) {
				Debuger.error("AbstractRecruitor.afterRecruit() CD增加失败!");
			}
		}
	}

	private void reduce(double discount) {
		if (needProp) {
			double needs = templet.getXunXianLingNeed();

			needs *= discount;

			city.getBagAuto().remove(D.ID_XUN_XIAN_LING, (int) needs);

			// Debuger.debug("AbstractRecruitor.reduce() --------- " + needs);

			city.getPlayer().add(PlayerProperty.JU_HUN,
					templet.getXunXianLingNeed()); // 招募后
													// 增加聚魂值
			UserCounterSetter c = city.getUserCounter();
			c.add(CounterKey.RECRUIT_WITH_OUT_FREE_TIMES, 1, recruitType);
		}
	}

	@Override
	public int getTimes() {
		UserCounter his = city.getUserCounterHistory();
		return his.get(CounterKey.RECRUIT_TIMES, recruitType);
	}

	private void markTimes() {

		UserCounterSetter c = city.getUserCounterAuto();
		c.add(CounterKey.RECRUIT_TIMES, 1, recruitType);

		if (!needProp) {
			c = city.getUserCounter();
			c.add(CounterKey.RECRUIT_FREE_TIMES, 1, recruitType);
		}
	}

	private ColdDown getCD() {
		CDManager cm = city.getCDManager();
		if (recruitType == 1) {
			return cm.get(CDKey.RECRUIT1);
		} else if (recruitType == 2) {
			return cm.get(CDKey.RECRUIT2);
		} else {
			return cm.get(CDKey.RECRUIT3);
		}
	}

	protected AbstractRecruitor(City city, int recruitType,
			boolean isUseFreeTimes) {
		this.city = city;
		this.recruitType = recruitType;
		templet = GodTypeTempletConfig.get(recruitType);
		hasFree = getFreeTimes() > 0;
		hasCd = getCD().getRemainingSec() > 0;

		if (!isUseFreeTimes) {
			needProp = true;
		} else {
			needProp = hasCd || !hasFree;
		}
	}

	protected int getRecruitTimesToday() {
		UserCounter c = city.getUserCounter();
		int times = c.get(CounterKey.RECRUIT_FREE_TIMES, recruitType);
		return times;
	}

	private RecruitResault buildResult(final boolean hasCreateNewHero,
			final int find, int spiriteCount) {
		return new RecruitResaultImpl(hasCreateNewHero, find, spiriteCount);
	}

	private AddHeroResult addHero(int find) {

		if (!city.getTeam().createNewHeroAuto(find)) {
			FighterTemplet t = FighterTempletConfig.get(find);
			float soul = t.getSoul();
			int count = (int) (soul - 1);
			count = TeamImpl.getAddSolCount(find);
			return new AddHeroResult(false, count);
		}

		return new AddHeroResult(true, 0);

	}

	@Override
	public RecruitResault recruit(double discount, boolean noJia) {

		check(discount, 1);
		int times = getTimes();

		int find;

		if (times == 0 && recruitType == 2) { // 第一次百里寻仙 邓禅玉
			find = D.NEW_PLAYER_FIGHTER_ID;
		} else if (times == 0 && recruitType == 3) { // 第一次万里寻仙
			find = getRandom(D.WAN_LI_DI_YI_CI);
		} else {
			find = find(noJia);
		}

		// markFirst();

		AddHeroResult r = addHero(find);

		afterRecruit(discount);

		city.getPrizeSender1().send(templet.getCertainly());

		// Debuger.debug("AbstractRecruitor.recruit() " +
		// FighterTempletConfig.get(find).getName() + "," +
		// r.getSpiriteCount());

		return buildResult(r.hasCreateNewHero(), find, r.getSpiriteCount());
	}

	private int getRandom(String wanLiDiYiCi) {
		List<Integer> integers = Util.Collection.getIntegers(wanLiDiYiCi);
		return Util.Random.getRandomOne(integers);
	}

	// private void markFirst() {
	// UserCounter his = city.getUserCounterHistory();
	// his.mark(CounterKey.HAS_RECRUIT_FIRST_FIGHTER);
	// }
	//
	// private boolean isFirstFind() {
	// UserCounter his = city.getUserCounterHistory();
	// return !his.isMark(CounterKey.HAS_RECRUIT_FIRST_FIGHTER);
	// }

	protected abstract int find(boolean noJia);

	/**
	 * 随机获得某品质神将ID
	 * 
	 * @param q
	 *            品质
	 * @return
	 */
	protected int getRandomFighter(int q) {

		List<FighterTemplet> all = FighterTempletConfig.findByStep(q);

		new PlayerFilter().filter(all);

		Iterator<FighterTemplet> it = all.iterator();

		while (it.hasNext()) {
			FighterTemplet temp = it.next();
			String godType = temp.getGodType();
			String trim = godType.trim();
			if (!trim.equals(D.FIRST_FIND + "")) { // 库为1 的, 才能被第一次寻仙给寻到
				it.remove();
			}
		}

		WeightFetcher<FighterTemplet> weightAble = new WeightFetcher<FighterTemplet>() {

			@Override
			public Integer get(FighterTemplet t) {
				return t.getGodTypeWeight();
			}
		};

		FighterTemplet f = Util.Random.getRandomOneByWeight(all, weightAble);

		return f.getId();
	}

	@Override
	public int getNeed(double discount) {
		return getNeed(10, discount);
	}

}
