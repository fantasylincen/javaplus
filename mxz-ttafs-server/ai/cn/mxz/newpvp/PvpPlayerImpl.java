package cn.mxz.newpvp;

import java.util.Date;
import java.util.List;

import cn.javaplus.math.Fraction;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.RankRewardTemplet;
import cn.mxz.RankRewardTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.events.Events;
import cn.mxz.events.pvp.PvpDanIdDownEvent;
import cn.mxz.events.pvp.PvpEnterUpWatchEvent;
import cn.mxz.events.pvp.PvpNormalWatchWinEvent;
import cn.mxz.events.pvp.PvpUpSuccessEvent;
import cn.mxz.events.pvp.PvpUpWatchEndEvent;
import cn.mxz.events.pvp.PvpUpWatchFaildEvent;
import cn.mxz.events.pvp.PvpUpWatchWinEvent;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.pvp.DanLevelUpEvent;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.vip.VipPlayer;
import db.dao.impl.DaoFactory;
import db.dao.impl.PvpDao;
import db.dao.impl.PvpExtraDao;
import db.dao.impl.PvpRankRewardDao;
import db.dao.impl.PvpRankRewardDao2;
import db.domain.Pvp;
import db.domain.PvpExtra;
import db.domain.PvpExtraImpl;
import db.domain.PvpRankReward;
import define.D;

public class PvpPlayerImpl extends EventDispatcher2Impl implements PvpPlayer {

	private Pvp pvp;
	private PvpExtra extra;
	private boolean isFirstEnterUpWatch;

	// private Integer shenJiaCache;
	// private long lastUpdateTime;

	PvpPlayerImpl(Pvp pvp) {
		this.pvp = pvp;
	}

	@Override
	public int getCurrentWinStreak() {
		UserCounter c = getCity().getUserCounter();
		return c.get(CounterKey.PVP_WINNING_STREAK_TODAY);
	}

	@Override
	public int getLoseTimes() {
		UserCounter c = getCity().getUserCounterHistory();
		return c.get(CounterKey.PVP_LOSE_TIMES);
	}

	@Override
	public int getMaxWinStreak() {
		UserCounter c = getCity().getUserCounterHistory();
		return c.get(CounterKey.PVP_WINNING_STREAK_MAX);
	}

	@Override
	public int getPower() {
		return getCity().getPlayer().getPower().getNumerator();
	}

	@Override
	public int getRankInEightClock() {

		PvpRankRewardDao DAO = DaoFactory.getPvpRankRewardDao();

		PvpRankReward rank = DAO.get(pvp.getUname());

		if (rank == null) {
//			int[] steps = RankRewardTempletConfig.getArrayByStep();
//			int max = Util.Array.getMax(steps);
//			return max;
			return -1;
		}
		return rank.getRank();
	}

	@Override
	public float getWinPercent() {

		City city = getCity();
		if (city == null) {
			throw new NullPointerException(pvp.getUname());
		}
		UserCounter his = city.getUserCounterHistory();

		int w = his.get(CounterKey.PVP_WIN_TIMES);
		int l = his.get(CounterKey.PVP_LOSE_TIMES);

		if (w + l == 0) {
			return 0;
		}

		return w / (0f + w + l);
	}

	@Override
	public int getWinTimesToday() {
		UserCounter userCounter = getCity().getUserCounter();
		return userCounter.get(CounterKey.PVP_WIN_TIMES);
	}

	@Override
	public City getCity() {
		return CityFactory.getCity(pvp.getUname());
	}

	@Override
	public int getScore() {
		return get(PvpPlayerProperties.SCORE);
	}

	@Override
	public int getDan() {

		int dan = pvp.getDanId();

		if (DanRewardTempletConfig.get(dan) == null) {

			Integer mik = DanRewardTempletConfig.getMinKey();
			Integer mak = DanRewardTempletConfig.getMaxKey();

			if (dan > mak) {
				dan = mak;
			} else {
				dan = mik;
			}

			pvp.setDanId(dan);
			commit();
		}

		return dan;
	}

	@Override
	public Fraction getPractice() {
		int winPoints = getTemplet().getWinPoints();
		if(winPoints == -1) {
			winPoints = 100000000;
		}
		int numerator = getPracticeValue();
		return new Fraction(numerator, winPoints);
	}

	private int getPracticeValue() {
		return pvp.getPractice();
	}

	DanRewardTemplet getTemplet() {
		return DanRewardTempletConfig.get(getDan());
	}

	@Override
	public String getStatus() {
		PvpExtra p = getExtraDto();
		return p.getStatus();
	}

	@Override
	public PvpExtra getExtraDto() {
		if (extra == null) {
			PvpExtraDao DAO = DaoFactory.getPvpExtraDao();
			extra = DAO.get(pvp.getUname());
			if (extra == null) {
				extra = initExtra();
			}
		}
		return extra;
	}

	private PvpExtra initExtra() {
		PvpExtra p = new PvpExtraImpl();
		p.setUname(pvp.getUname());
		p.setPvpUpTime(new Date());
		p.setStatus("");
		PvpExtraDao DAO = DaoFactory.getPvpExtraDao();
		DAO.add(p);
		return p;
	}

	@Override
	public int getRemainTimes() {
		if (getCity().isTester()) {
			return 100;
		}
		VipPlayer player = getCity().getVipPlayer();
		int level = player.getLevel();
		VipPrivilegeTemplet v = VipPrivilegeTempletConfig.get((byte) level);

		if (v == null) {
			return 0;
		}

		int max = v.getChallengeTimes();

		int free = max
				- getCity().getUserCounter().get(
						CounterKey.PVP_REDUCE_FREE_TIMES_TIMES);
		free = Math.max(free, 0);

		return free + get(PvpPlayerProperties.BUY_TIMES);
	}

	private int get(PvpPlayerProperties b) {
		return getExtraDto().getV(b.toNum());
	}

	@Override
	public void addRemainTimes(int times) {
		add(PvpPlayerProperties.BUY_TIMES, times);
		commit();
	}

	@Override
	public void commit() {
		PvpDao DAO = DaoFactory.getPvpDao();
		DAO.update(pvp);

		PvpExtraDao DAO2 = DaoFactory.getPvpExtraDao();
		DAO2.update(getExtraDto());
	}

	private void add(PvpPlayerProperties p, int times) {
		int now = get(p);
		getExtraDto().setV(p.toNum(), now + times);

		Debuger.debug("NewPvpPlayerImpl.add() :" + p + ":" + times);
	}

	@Override
	public void reduceRemainTimes(int times) {
		if (get(PvpPlayerProperties.BUY_TIMES) > 0) {
			add(PvpPlayerProperties.BUY_TIMES, -1);
		} else {
			getCity().getUserCounter().add(
					CounterKey.PVP_REDUCE_FREE_TIMES_TIMES, 1);
		}

		commit();
	}

	@Override
	public boolean isInUpMatch() {

		return get(PvpPlayerProperties.IS_UP) == 1;
	}

	@Override
	public boolean canEnterUpWatch() {

		DanRewardTemplet temp = DanRewardTempletConfig.get(getDan());

		// 天尊不能进入晋级赛
		int dan = temp.getDan();
		if (dan == D.XIAO_TIAN_ZUN_DAN || dan == D.DA_TIAN_ZUN_DAN) {
			return false;
		}

		PvpPlace ins = PvpPlaceImpl.getInstance();
		List<PvpPlayer> tianZuns = ins.getTianZuns();
		if (tianZuns.size() >= 10 && getTemplet().getNeedCompareToTianZun() == 1) { // 如果需要和天尊的胜率比较,
			// 才能进入晋级赛的话,
			// 就进行比较

			// 最后一位天尊
			PvpPlayer last = ins.getLastTianZun();

			if (last != null) {
				float winPercent = getWinPercent();
				float winPercent2 = last.getWinPercent();
				if (winPercent < winPercent2) {
					return false;
				}
			}
		}

		int points = getPracticeValue();

		boolean pointsReached = points >= temp.getWinPoints();

		if (ins.getTianZuns().size() < 10) {
			return pointsReached;
		}

		// if (pointsReached && getTemplet().getNeedCompareToTianZun() == 1) {
		// // 如果需要和天尊的胜率比较,
		// // 才能晋级的话,
		// // 就进行比较
		//
		// // 最后一位天尊
		// PvpPlayer last = PvpPlaceImpl.getInstance().getLastTianZun();
		//
		// if (last != null) {
		// float winPercent = getWinPercent();
		// float winPercent2 = last.getWinPercent();
		// return winPercent > winPercent2;
		// }
		// }

		return pointsReached;
	}

	private void set(PvpPlayerProperties v, int i) {

		PvpExtra dto = getExtraDto();
		dto.setV(v.toNum(), i);
	}

	@Override
	public void enterUpWatch() {

		set(PvpPlayerProperties.IS_UP, 1);
		
		this.isFirstEnterUpWatch = true;
//		set(PvpPlayerProperties.IS_UP_AGO, 1);
		
		PvpExtra dto = getExtraDto();

		dto.setPvpUpTime(new Date());

		commit();

		Debuger.debug("PvpPlayerImpl.toUp():" + new Date());

		Events.getInstance().dispatch(new PvpEnterUpWatchEvent(this));
	}

	@Override
	public int getWinTimesInUpWatch() {

		PvpExtra dto = getExtraDto();
		String record = dto.getStatus();

		String[] split = record.split("");

		int times = 0;

		for (String string : split) {

			string = string.trim();

			if (string.equals("w")) {

				times++;
			}
		}

		return times;
	}

	@Override
	public int getFightingTimes() {

		PvpExtra dto = getExtraDto();
		String record = dto.getStatus();

		return record.length();
	}

	private void saveWin(boolean isWin) {

		PvpExtra dto = getExtraDto();
		dto.addStatus(isWin ? "w" : "l");
	}

	@Override
	public void upWatchEnd(boolean isWin) {

		saveWin(isWin);

		commit();

		Events.getInstance().dispatch(new PvpUpWatchEndEvent(this));

		if (isWin) {
			Events.getInstance().dispatch(new PvpUpWatchWinEvent(this));
		}
	}

	@Override
	public void normalWatchWin(PvpBattle battle) {

		PracticeCaculator c = new PracticeCaculator();

		PlayerCamp camp = battle.getUpperPlayerCamp();

		City other = camp.getCity();
		PvpManager pm = other.getNewPvpManager();
		PvpPlayer player = pm.getPlayer();

		int winPoints = c.calc(getCity(), new NormalPvpFightUser(player));

		int wp1 = getPracticeValue() + winPoints;
		setPracticeValue(wp1);

		battle.setWinPointReceived(winPoints);

		DanRewardTemplet temp = getTemplet();
		int wp = temp.getWinPoints();

		if (wp != -1 && getPracticeValue() > wp) {
			setPracticeValue(wp);
		}

		commit();

		Events.getInstance().dispatch(new PvpNormalWatchWinEvent(battle));
	}

	private void setPracticeValue(int wp) {
		pvp.setPractice(wp);
	}

	@Override
	public void toNormal() {

		set(PvpPlayerProperties.IS_UP, 0);

		PvpExtra dto = getExtraDto();

		long t = System.currentTimeMillis();
		dto.setPvpUpTime(new Date(t - 1000 * Time.MILES_ONE_DAY));

		dto.setStatus("");

		commit();
	}

//	@Override
//	public void sendPrize() {
//
//		DanRewardTemplet temp = DanRewardTempletConfig.get(getDan());
//
//		PrizeSender s = PrizeSenderFactory.getPrizeSender();
//
//		Player player = PlayerFactory.getPlayer(pvp.getUname());
//
//		s.send(player, temp.getAwards());
//	}

	@Override
	public void levelUp() {

		setPracticeValue(0);

		pvp.addDanId(1);

		dispatchEvent(new DanLevelUpEvent(getDan()));

		toBeProtected();

		commit();

		Events.getInstance().dispatch(new PvpUpSuccessEvent(this));

		lastTianZunDown();
	}

	private void lastTianZunDown() {

		List<PvpPlayer> all = PvpPlaceImpl.getInstance().getTianZuns();

		// Debuger.debug("PvpPlayerImpl.lastTianZunDown() -------------------- ");
		for (int i = all.size() - 1; i >= 10; i--) {
			// Debuger.debug("PvpPlayerImpl.lastTianZunDown() ----------- " +
			// i);
			PvpPlayer n = all.get(i);
			Pvp dto = n.getDto();
			dto.setDanId(D.XUAN_XIAN_1_DAN);
			dto.setPractice(dto.getPractice() * getScale() / 100);
			n.commit();
		}

//		ensureOneDaTianZun();
		//
		// if (all.size() >= 10) {
		// PvpPlayer n = get(all);
		// Pvp dto = n.getDto();
		// dto.addDanId(-1);
		// dto.setPractice(dto.getPractice() * getScale() / 100);
		// n.commit();
		// }
	}

//	/** 保证只有1个大天尊 */
//	private void ensureOneDaTianZun() {
//		List<PvpPlayer> all = PvpPlaceImpl.getInstance().getTianZuns();
//		boolean hasDaTianZun = false;
//		for (PvpPlayer p : all) {
//			if (hasDaTianZun && p.getDan() == D.DA_TIAN_ZUN_DAN) {
//				p.getDto().setDanId(D.XIAO_TIAN_ZUN_DAN);
//				p.commit();
//			}
//			if (p.getDan() == D.DA_TIAN_ZUN_DAN) {
//				hasDaTianZun = true;
//			}
//		}
//	}

	//
	// private PvpPlayer get(List<PvpPlayer> all) {
	// PvpPlayer n = all.get(10);
	// if (n.getId().equals(getId())) {
	// return all.get(9);
	// }
	// return n;
	// }

	@Override
	public void upWatchFaild(City upper) {

		setPracticeValue(getScale() * getPracticeValue() / 100);

		toNormal();

		Events.getInstance().dispatch(new PvpUpWatchFaildEvent(this));
	}

	private int getScale() {
		return Util.Random.get(60, 90);
	}

	@Override
	public void addBeAttackMessage(PlayerCamp under) {
	}

	@Override
	public int getRankInAll() {
		PvpPlace p = PvpPlaceImpl.getInstance();
		return p.getRankInAll(pvp.getUname());
	}

	@Override
	public Pvp getDto() {
		return pvp;
	}

	@Override
	public int compareTo(PvpPlayer o) {

		Pvp dto = o.getDto();

		int danId = dto.getDanId();
		int danId2 = pvp.getDanId();

		if(danId == D.DA_TIAN_ZUN_DAN ) {
			danId = D.XIAO_TIAN_ZUN_DAN;
		}
		if(danId2 == D.DA_TIAN_ZUN_DAN ) {
			danId2 = D.XIAO_TIAN_ZUN_DAN;
		}
		
		
		int ov1 = danId * 1000000 + dto.getPractice();
		int ov2 = danId2 * 1000000 + pvp.getPractice();

		if (ov1 == ov2) {
			return (int) (o.getWinPercent() * 10000 - getWinPercent() * 10000);
		}

		return ov1 - ov2;
	}

	@Override
	public void beKowtow() {

		PvpExtra dto = getExtraDto();
		int index = PvpPlayerProperties.BE_KOWTOW_TIMES.toNum();
		int now = dto.getV(index) + 1;
		dto.setV(index, now);

		commit();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [getCurrentWinStreak()=");
		builder.append(getCurrentWinStreak());
		builder.append("\r getLoseTimes()=");
		builder.append(getLoseTimes());
		builder.append("\r getMaxWinStreak()=");
		builder.append(getMaxWinStreak());
		builder.append("\r getPower()=");
		builder.append(getPower());
		builder.append("\r getRankInEightClock()=");
		builder.append(getRankInEightClock());
		builder.append("\r getWinPercent()=");
		builder.append(getWinPercent());
		builder.append("\r getWinTimesToday()=");
		builder.append(getWinTimesToday());
		builder.append("\r getScore()=");
		builder.append(getScore());
		builder.append("\r getDanId()=");
		builder.append(getDan());
		builder.append("\r getPractice()=");
		builder.append(getPractice());
		builder.append("\r getStatus()=");
		builder.append(getStatus());
		builder.append("\r getExtraDto()=");
		builder.append(getExtraDto());
		builder.append("\r getRemainTimes()=");
		builder.append(getRemainTimes());
		builder.append("\r isInUpMatch()=");
		builder.append(isInUpMatch());
		builder.append("\r canUp()=");
		builder.append(canEnterUpWatch());
		builder.append("\r getWinTimesInUpWatch()=");
		builder.append(getWinTimesInUpWatch());
		builder.append("\r getFightingTimes()=");
		builder.append(getFightingTimes());
		builder.append("\r getRankInAll()=");
		builder.append(getRankInAll());
		builder.append("\r getDto()=");
		builder.append(getDto());
		builder.append("\r getBeKowtowTimes()=");
		builder.append(getBeKowtowTimes());
		builder.append("\r getBestRank()=");
		builder.append(getBestRank());
		builder.append("\r hasReceiveRankReward()=");
		builder.append(canNotReceiveRankReward());
		builder.append("\r getKowtowTimes()=");
		builder.append(getKowtowTimes());
		builder.append("\r getRemainUseFuWenTimes()=");
		builder.append(getRemainUseFuWenTimes());
		builder.append("\r getWarsituationId()=");
		builder.append(getWarsituationId());
		builder.append("\r getDanLevel()=");
		builder.append(getDanLevel());
		builder.append("\r getShenJia()=");
		builder.append(getShenJia());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int getBeKowtowTimes() {
		return get(PvpPlayerProperties.BE_KOWTOW_TIMES);
	}

	@Override
	public int getBestRank() {
		int rank = get(PvpPlayerProperties.BEST_RANK);
		if (rank == 0) {
			return getRankInAll();
		}
		return rank;
	}

	@Override
	public void updateBestRank(int newRank, int id) {

		set(PvpPlayerProperties.BEST_RANK, newRank);

		set(PvpPlayerProperties.WAR_SITUATION_ID, id);
	}

	@Override
	public boolean canNotReceiveRankReward() {

		return hasReceiveRankReward();
		
	}

	private boolean hasReceiveRankReward() {
		City city = getCity();
//
//		PvpPlayer player = city.getNewPvpManager().getPlayer();
//		if (player.isCreateToday()) {
//			return true;
//		}
//		UserCounter uc = city.getUserCounter();
//		return uc.isMark(CounterKey.HAS_RECEIVE_PVP_RANK_REWARD);
		PvpRankRewardDao2 dao = DaoFactory.getPvpRankRewardDao();
		PvpRankReward reward = dao.get(city.getId());
		return reward == null;
	}

	@Override
	public void receiveRankReward() {

		List<RankRewardTemplet> all = RankRewardTempletConfig.getAll();

		int rank = getRankInEightClock();
		if(rank == -1) {
			throw new SureIllegalOperationException("已经领取过了排名奖励");
		}

		for (RankRewardTemplet rt : all) {
			int start = rt.getStep();
			int end = rt.getEnd();

			DanRewardTemplet t = DanRewardTempletConfig.get(getDan());
			if (rank >= start && rank <= end && rt.getDanLv() == t.getDanLv()){
				sendReward(rt);
				break;
			}
		}

	}

	private void sendReward(RankRewardTemplet rt) {

		PrizeSender s = PrizeSenderFactory.getPrizeSender();
		s.send(getCity().getPlayer(), rt.getAwards());

		deleteLastRankRecord();
//		City city = getCity();
//		UserCounter uc = city.getUserCounter();
//		uc.mark(CounterKey.HAS_RECEIVE_PVP_RANK_REWARD);
	}

	private void deleteLastRankRecord() {
		PvpRankRewardDao2 dao = DaoFactory.getPvpRankRewardDao();
		dao.delete(this.getId());
	}

	@Override
	public int getKowtowTimes() {

		UserCounter uc = getCity().getUserCounter();
		return uc.get(CounterKey.PVP_KOWTOW_TIMES);
	}

	@Override
	public int getRemainUseFuWenTimes() {

		City city = getCity();
		UserCounter uc = city.getUserCounter();
		int times = uc.get(CounterKey.USE_FU_WEN_TIMES);

		int vipLevel = city.getVipPlayer().getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig
				.get((byte) vipLevel);

		int t = temp.getAthleticsTimes() - times;

		t = Math.max(t, 0);

		return t;
	}

	@Override
	public int getWarsituationId() {
		return get(PvpPlayerProperties.WAR_SITUATION_ID);
	}

	@Override
	public void updateWarSituationId(int id) {
		set(PvpPlayerProperties.WAR_SITUATION_ID, id);
	}

	@Override
	public int getDanLevel() {
		DanRewardTemplet t = DanRewardTempletConfig.get(getDan());
		return t.getLevel();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + ((pvp == null) ? 0 : pvp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PvpPlayerImpl other = (PvpPlayerImpl) obj;

		return other.pvp.getUname().equals(pvp.getUname());
	}

	@Override
	public int getShenJia() {
		return pvp.getShenJia();
	}

	@Override
	public int getWinTimes() {
		UserCounter his = getCity().getUserCounterHistory();
		return his.get(CounterKey.PVP_WIN_TIMES);
	}

	@Override
	public void updateShenJia() {
		City city = CityFactory.getCity(pvp.getUname());
		int s = city.getFormation().getShenJia();
		pvp.setShenJia(s);

		long lastUpdateSec = System.currentTimeMillis();
		lastUpdateSec /= 1000;
		set(PvpPlayerProperties.LAST_UPDATE_SHEN_JIA_CACHE_SEC,
				(int) lastUpdateSec);

		commit();
	}

	@Override
	public void reducePractice(int reduce) {

		int remain = pvp.getPractice() - reduce;

		if (remain < 0) {

			if (getCanDanDown()) {

				pvp.addDanId(-1);
				pvp.setPractice(99);

				toBeProtected();

				Events.getInstance().dispatch(new PvpDanIdDownEvent(this));
			}

			int i = getScale() * pvp.getPractice();
			
			pvp.setPractice(i / 100);
		} else {

			pvp.setPractice(remain);
		}

		commit();

	}

	private boolean getCanDanDown() {
		int dan = getDan();
		
		DanRewardTemplet temp2 = DanRewardTempletConfig.get(dan - 1);
		if(temp2 == null) {
			return false;
		}
		DanRewardTemplet temp1 = getTemplet();
		
		return temp1.getDanLv() == temp2.getDanLv();
	}

	@Override
	public boolean isProtected() {
		UserCounter uc = getCity().getUserCounterHistory();
		int t = uc.get(CounterKey.PVP_PROTECT_TIMES);
		return t > 0;
	}

	@Override
	public void reduceProtectTime() {
		UserCounter uc = getCity().getUserCounterHistory();
		uc.add(CounterKey.PVP_PROTECT_TIMES, -1);
	}

	private void toBeProtected() {
		UserCounter uc = getCity().getUserCounterHistory();
		uc.set(CounterKey.PVP_PROTECT_TIMES, 3);
	}

	@Override
	public int getLoseStreak() {
		UserCounter c = getCity().getUserCounter();
		return c.get(CounterKey.PVP_LOSE_STREAK_TODAY);
	}

	@Override
	public String getId() {
		return pvp.getUname();
	}
//
//	@Override
//	public boolean isUpAgo() {
//		return get(PvpPlayerProperties.IS_UP_AGO) == 1;
//	}
//
//	@Override
//	public void setIsFirstUp(boolean b) {
//		set(PvpPlayerProperties.IS_UP_AGO, b ? 0 : 1);
//	}

	@Override
	public int getCurrentWinTimesInDanId() {
		return get(PvpPlayerProperties.CURRENT_WIN_TIMES_IN_DAN_ID);
	}

	@Override
	public void setCurrentWinTimesInDanId(int times) {
		set(PvpPlayerProperties.CURRENT_WIN_TIMES_IN_DAN_ID, times);
	}

	@Override
	public boolean isTianZun() {
		return getTemplet().getDanLv() == 5;
	}

	@Override
	public boolean isCreateToday() {
		UserCounter his = getCity().getUserCounterHistory();
		long sec = his.get(CounterKey.PVP_INIT_SEC);
		// long sec = getCity().getPlayer().get(PlayerProperty.CREATE_SEC);
		int day = Util.Time.getDay(sec * 1000 + 8 * Util.Time.MILES_ONE_HOUR);
		int d = Util.Time.getCurrentDay();
		return d == day;
	}

	@Override
	public boolean hasInit() {
		UserCounter his = getCity().getUserCounterHistory();
		int sec = his.get(CounterKey.PVP_INIT_SEC);
		return sec != 0;
	}

	@Override
	public void saveCreateDay() {
		UserCounter his = getCity().getUserCounterHistory();
		his.set(CounterKey.PVP_INIT_SEC, Util.Time.getCurrentSec());
	}

	@Override
	public boolean isFirstUp() {
		return isFirstEnterUpWatch;
	}

	@Override
	public void setFirstUp(boolean b) {
		this.isFirstEnterUpWatch = b;
	}

	@Override
	public int getRankRewardRemainSec() {
		PvpRankRewardDao2 dao = DaoFactory.getPvpRankRewardDao();
		PvpRankReward reward = dao.get(getId());
		if(reward != null) {
			return 0;
		}
		return Util.Time.getRemainSec(D.PVP_RANK_REWARD_SURE_TIME);
	}


}
