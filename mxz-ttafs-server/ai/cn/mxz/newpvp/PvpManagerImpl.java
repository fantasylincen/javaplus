package cn.mxz.newpvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.PvpWarSituationDao;
import mongo.gen.MongoGen.PvpWarSituationDao.PvpWarSituationDtoCursor;
import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;
import cn.mxz.DanRewardTemplet;
import cn.mxz.DanRewardTempletConfig;
import cn.mxz.PresentTemplet;
import cn.mxz.PresentTempletConfig;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.prize.PrizeSender;
import cn.mxz.base.prize.PrizeSenderFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.dogz.Dogz;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;

import db.dao.impl.DaoFactory;
import define.D;

public class PvpManagerImpl implements PvpManager {

	private City city;
	private PvpPlayer player;
	private List<PvpFightUser> randomUsers = Lists.newArrayList();

	public PvpManagerImpl(City city) {
		this.city = city;
	}

	@Override
	public PvpPlayer getPlayer() {
		if (player == null) {
			PvpPlace instance = PvpPlaceImpl.getInstance();
			player = instance.get(city.getId());
		}
		return player;
	}

	@Override
	public PvpReward getPvpReward() {
		return new PvpRewardImpl();
	}

	@Override
	public void kowtow() {

		checkKowtow();

		PvpPlace instance = PvpPlaceImpl.getInstance();

		PvpPlayer first = instance.getFirst();

		first.beKowtow();

		city.getUserCounter().add(CounterKey.PVP_KOWTOW_TIMES, 1);

		sendKowtowReward();
	}

	private void sendKowtowReward() {
		for (PresentTemplet temp : PresentTempletConfig.getAll()) {
			if (temp.getKey().equals("ARENA_WORSHIP")) {
				PrizeSender s = PrizeSenderFactory.getPrizeSender();
				s.send(city.getPlayer(), temp.getAwards());
			}
		}
	}

	private void checkKowtow() {

		int c = city.getUserCounter().get(CounterKey.PVP_KOWTOW_TIMES);
		if (c >= D.PVP_MAX_KOWTOW_TIMES) {
			throw new SureIllegalOperationException("-已达最大膜拜次数");
		}
	}

	@Override
	public List<PvpWarSituationDto> getWarSituationsBeHit() {
		PvpWarSituationDao DAO = Daos.getPvpWarSituationDao();
		List<PvpWarSituationDto> all = getAll(DAO
				.findByDefenderId(city.getId()));
		return findWarsituation(all);
	}

	private List<PvpWarSituationDto> getAll(
			PvpWarSituationDtoCursor findByDefenderId) {
		ArrayList<PvpWarSituationDto> ls = Lists.newArrayList();
		for (PvpWarSituationDto dto : findByDefenderId) {
			ls.add(dto);
		}
		return ls;
	}

	private List<PvpWarSituationDto> findWarsituation(
			List<PvpWarSituationDto> all) {

		// List<PvpWarSituation> a2 = DAO.findBy("challenger_id", city.getId());
		// all.addAll(a2);

		st(all);
		clearTimeUp(all);
		List<PvpWarSituationDto> sub = Util.Collection.sub(all, 100);
		return Lists.newArrayList(sub);
	}

	private void clearTimeUp(List<PvpWarSituationDto> all) {
		for (PvpWarSituationDto warSituation : all) {
			Date createTime = new Date(warSituation.getCreateTime() * 1000L);
			if (System.currentTimeMillis() - createTime.getTime() >= Time.MILES_ONE_DAY * 5) {
				DaoFactory.getWarSituationDao().delete(
						warSituation.getSituationId());
			}
		}

		deleteAfter(60, all);
	}

	private void deleteAfter(int count, List<PvpWarSituationDto> all) {

		for (PvpWarSituationDto warSituation : all) {
			count--;
			if (count <= 0) {
				Daos.getPvpWarSituationDao().delete(
						warSituation.getSituationId());
			}
		}
	}

	// public static void main(String[] args) {
	// Lists.newArrayList().subList(0, 0);
	// }

	private void st(List<PvpWarSituationDto> all) {
		Comparator<PvpWarSituationDto> c = new Comparator<PvpWarSituationDto>() {

			@Override
			public int compare(PvpWarSituationDto o1, PvpWarSituationDto o2) {
				long a = o2.getCreateTime() * 1000L - o1.getCreateTime()
						* 1000L;
				return a > 0 ? 1 : -1;
			}
		};
		Collections.sort(all, c);
	}

	@Override
	public void addPvpWinTimes(int i) {

		UserCounterSetter c = city.getUserCounterAuto();
		c.add(CounterKey.PVP_WIN_TIMES, i);
	}

	@Override
	public void addPvpWinSteakToday(int i) {
		UserCounter c = city.getUserCounter();
		c.add(CounterKey.PVP_WINNING_STREAK_TODAY, i);

		int max = c.get(CounterKey.PVP_WINNING_STREAK_MAX);

		PvpManager pm = city.getNewPvpManager();
		PvpPlayer player = pm.getPlayer();
		int s = player.getCurrentWinStreak();
		if (s > max) {
			c.set(CounterKey.PVP_WINNING_STREAK_MAX, s);
		}

	}

	@Override
	public void addPvpLoseTimes(int i) {

		UserCounterSetter c = city.getUserCounterAuto();
		c.add(CounterKey.PVP_LOSE_TIMES, i);
	}

	@Override
	public void clearPvpWinSteakToday() {
		UserCounterSetter c = city.getUserCounter();
		c.set(CounterKey.PVP_WINNING_STREAK_TODAY, 0);
	}

	@Override
	public int getShenJia() {
		int lv = getPlayer().getDan();
		DanRewardTemplet temp = DanRewardTempletConfig.get(lv);
		Util.Exception.checkNull(temp, lv);
		return temp.getSocial();
	}

	@Override
	public List<PvpWarSituationDto> getWarSituationsAttack() {
		PvpWarSituationDao DAO = Daos.getPvpWarSituationDao();

		PvpWarSituationDtoCursor all = DAO.findByChallengerId(city.getId());
		List<PvpWarSituationDto> a = getAll(all);
		return findWarsituation(a);
	}

	@Override
	public List<PvpFightUser> getUsersRandomed() {
		return randomUsers;
	}

	@Override
	public void saveRandomUsers(List<PvpFightUser> randomUsers) {
		this.randomUsers = Lists.newArrayList(randomUsers);
//		for (PvpFightUser pfu : randomUsers) {
//			if (!pfu.isRobot()) {
//
//				String id = pfu.getPlayer().getId();
//				City city = CityFactory.getCity(id);
//
//				Debuger.debug("PvpPlaceImpl.getRandomPlayer() 随机到非机器人:"
//						+ city.getPlayer().getNick());
//				Dogz f = city.getDogzManager().getFighting();
//				if (f != null) {
//					Debuger.debug("PvpPlaceImpl.getRandomPlayer() 随机到有神兽的玩家");
//				}
//			}
//		}
	}

	@Override
	public PvpFightUser getUserRandomed(String userId) {
		for (PvpFightUser p : randomUsers) {
			if (p.getPlayer().getId().equals(userId)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void clearUsersRandomed() {
		randomUsers.clear();
	}

	@Override
	public DanReward getDanReward() {
		return new DanReward(this);
	}

	@Override
	public City getCity() {
		return city;
	}

	// @Override
	// public IDanReward getNextDanReward() {
	// return new NextDanReward(this);
	// }

	@Override
	public List<PvpWarSituationDto> getWarSituationsAll() {
		List<PvpWarSituationDto> all = getWarSituationsAttack();
		all.addAll(getWarSituationsBeHit());
		st(all);
		return all;
	}
}
