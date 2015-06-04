package cn.mxz.newpvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import message.S;
import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.PvpWarSituationDao;
import mongo.gen.MongoGen.PvpWarSituationDto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.page.Page;
import cn.javaplus.time.colddown.ColdDown;
import cn.javaplus.util.Util;
import cn.mxz.bag.BagAuto;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.battle.WarSituationBuilder;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Events;
import cn.mxz.events.pvp.PvpBattleWinEvent;
import cn.mxz.handler.PvpService;
import cn.mxz.protocols.pvp.PvpP.DanRewardPro;
import cn.mxz.protocols.pvp.PvpP.PvpRandomResultPro;
import cn.mxz.protocols.pvp.PvpP.PvpRewardPro;
import cn.mxz.protocols.pvp.PvpP.PvpRewardStatusPro;
import cn.mxz.protocols.pvp.PvpP.PvpScenePro;
import cn.mxz.protocols.pvp.PvpP.PvpUsersPro;
import cn.mxz.protocols.pvp.PvpP.PvpWarsituationPro;
import cn.mxz.protocols.pvp.PvpP.TaskRewardsPro;
import cn.mxz.protocols.pvp.PvpP.WarSituationsPro;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.util.cd.CDKey;
import cn.mxz.util.checker.Checker;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;

import define.D;

@Component("pvpService")
@Scope("prototype")
public class PvpServiceImpl extends AbstractService implements PvpService {

	@Override
	public PvpUsersPro getTops(int page, int count) {
		if (count > 100) {
			throw new IllegalArgumentException("count must < 100    : count = "
					+ count);
		}

		PvpPlace ins = PvpPlaceImpl.getInstance();

		List<PvpPlayer> ps = ins.getAll();

		Page<PvpPlayer> pg = new Page<PvpPlayer>(ps, count);

		ps = pg.getPage(page);

		updateShenJia(ps);

		return new PvpUsersBuilder().build(getCity(), build(ps), page,
				pg.getPageAll(), count);
	}

	private List<PvpFightUser> build(List<PvpPlayer> ps) {
		ArrayList<PvpFightUser> ls = Lists.newArrayList();
		for (PvpPlayer newPvpPlayer : ps) {
			ls.add(new NormalPvpFightUser(newPvpPlayer));
		}

		return ls;
	}

	private void updateShenJia(List<PvpPlayer> ps) {
		for (PvpPlayer p : ps) {
			updateShenJia(p);
		}
	}

	private void updateShenJia(PvpPlayer p) {

		final World world = WorldFactory.getWorld();

		if (world.isOnline(p.getCity().getId())) {
			p.getCity().getPlayer().updateShenJia();
		}
	}

	@Override
	public PvpRewardPro getReward() {
		PvpManager pm = getCity().getNewPvpManager();
		PvpReward reward = pm.getPvpReward();

		return new PvpRewardBuilder().build(reward);
	}

	@Override
	public void receiveReward(int rewardType) {
		PvpManager pm = getCity().getNewPvpManager();
		PvpReward reward = pm.getPvpReward();
		reward.receive(rewardType);
	}

	@Override
	public void kowtow() {
		PvpManager pm = getCity().getNewPvpManager();
		pm.kowtow();
	}

	@Override
	public PvpWarsituationPro fighting(String userId) {

		PvpManager p = getCity().getNewPvpManager();

		PvpFightUser fightingUser = p.getUserRandomed(userId);

		if (fightingUser == null) {
			// throw new RuntimeException("玩家没有随机");
			fightingUser = new NormalPvpFightUser(PvpPlaceImpl.getInstance()
					.get(userId));
		}

		checkFighting(userId);
		PvpBattle b = new PvpBattle(getCity(), fightingUser);
		b.fighting();

		long t2 = System.currentTimeMillis();

		WarSituationBuilder builder = new WarSituationBuilder();
		WarSituationPro build = builder.build(getCity(), b.getWarSituation());
		PvpWarSituationManager manager = new PvpWarSituationManagerImpl();

		// 保存战况信息
		int id = manager.save(build, getId(), userId);

		RandomRewardSender s = new RandomRewardSender();

		if (b.isWin()) {
			Events.getInstance().dispatch(new PvpBattleWinEvent(b, id));
			s.sendReward(getCity());
		}

		addFightingTimes();

		getCity().getNewPvpManager().getPlayer().reduceProtectTime();

		p.clearUsersRandomed(); // 清空所有缓存的随机PVP玩家

		Debuger.debug("PvpServiceImpl.fighting() 我的胜率:"
				+ p.getPlayer().getWinPercent());
		Debuger.debug("{enclosing_type}.fighting 耗时:"
				+ (System.currentTimeMillis() - t2));

		return new PvpWarsituationBuilder().build(s, b.isLastUpMatch(),
				b.isUp(), build, b.getWinPointsReceived());

	}

	private void addFightingTimes() {
		UserCounterSetter uc = getCity().getUserCounter();
		uc.add(CounterKey.PVP_FIGNTING_TIMES, 1);
	}

	private void checkFighting(String userId) {

		checkRemainTimes();

	}

	private void checkRemainTimes() {
		PvpManager m = getCity().getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		if (p.getRemainTimes() <= 0) {
			throw new SureIllegalOperationException("-剩余挑战次数不够!");
		}
	}

	private void reduce(int count) {
		BagAuto bag = getCity().getBagAuto();
		bag.remove(D.PVP_FUWEN_ID, count);
	}

	private void check(int count) {
		Checker c = getCity().getChecker();
		c.checkProp(D.PVP_FUWEN_ID, count);
	}

	@Override
	public PvpScenePro getMyData() {
		// throw new NullPointerException();
		PvpPlayer player = PvpPlaceImpl.getInstance().get(getId());

		getCity().getNewPvpManager().getPlayer().updateShenJia();

		PvpScenePro build = new PvpSceneBuilder().build(player);

		player.setFirstUp(false);
		player.commit();

		return build;
	}

	@Override
	public PvpScenePro getOther(String userId) {
		PvpPlayer player = PvpPlaceImpl.getInstance().get(userId);

		if (player == null) {
			PvpManager pm = getCity().getNewPvpManager();
			PvpFightUser u = pm.getUserRandomed(userId);

			return new PvpSceneBuilder().build(u);
		}

		return new PvpSceneBuilder().build(player);
	}

	@Override
	public PvpRandomResultPro getRandomUser2(Boolean isRefresh) {

		if (isRefresh) {

			City city = getCity();
			ColdDown cd = city.getCDManager().get(CDKey.PVP_CHALLENGE);

			// 直接扣钱刷新
			if (cd.isFreezing()) {
				getCity().getPlayer().reduceGoldOrJinDing(D.PVP_REFRESH_NEED);
				cd.clear();
			}

			// cd.check();

			PvpPlace instance = PvpPlaceImpl.getInstance();
			List<PvpFightUser> players = instance.getRandomUsers(getCity());

			cd.add();
			int remainingSec = cd.getRemainingSec();
			// Debuger.debug("PvpServiceImpl.getRandomUser()" + remainingSec);
			return extracted(players, remainingSec);
		} else {

			List<PvpFightUser> users = getCity().getNewPvpManager()
					.getUsersRandomed();

			if (users == null || users.isEmpty()) {
				PvpPlace instance = PvpPlaceImpl.getInstance();
				users = instance.getRandomUsers(getCity());
			}
			ColdDown cd = getCity().getCDManager().get(CDKey.PVP_CHALLENGE);

			int remainingSec = cd.getRemainingSec();
			return extracted(users, remainingSec);
		}
	}

	private PvpRandomResultPro extracted(List<PvpFightUser> users,
			int remainingSec) {
		PvpUsersPro build = new PvpUsersBuilder().build(getCity(), users, 1, 1,
				D.PVP_PERSON_COUNT);
		return new PvpRandomResultBuilder().build(build, remainingSec);
	}

	@Override
	public PvpUsersPro getUsersNearMe(int count) {

		if (count > 100) {
			throw new IllegalArgumentException("count must < 100    : count = "
					+ count);
		}

		PvpManager pm = getCity().getNewPvpManager();

		PvpPlayer player = pm.getPlayer();

		PvpPlace ins = PvpPlaceImpl.getInstance();

		List<PvpPlayer> ps = ins.getAll();
		List<PvpPlayer> players = Lists.newArrayList();

		int index = ps.indexOf(player);
		int rank = index + 1;

		int near = 10;
		if (index != -1) {

			for (int i = index - near; i < index + near; i++) {
				if (i < 0 || i >= ps.size()) {
					continue;
				}
				players.add(ps.get(i));
			}
		}

		PvpUsersBuilder bd = new PvpUsersBuilder();
		int rankStart = rank - near;
		if (rankStart < 1) {
			rankStart = 1;
		}
		return bd.build(getCity(), players, rankStart /* + 1 */);
	}

	@Override
	public PvpUsersPro getTops2(int page, int count) {
		return getTops(page, count);
	}

	@Override
	public void recieveReward(int taskId) {

	}

	@Override
	public TaskRewardsPro getTaskReward() {
		return null;
	}

	@Override
	public void recieveRankReward() {
		City city = getCity();
		PvpManager pm = city.getNewPvpManager();
		PvpPlayer player = pm.getPlayer();
		// if(player.isCreateToday()) {
		// throw new IllegalOperationException("角色创建当天不可领取");
		// }
		player.receiveRankReward();
	}

	@Override
	public int buyTimes(int count) {
		check(count);
		reduce(count);
		PvpManager m = getCity().getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		p.addRemainTimes(count * D.PVP_TIMES_CHA);

		return p.getRemainTimes();
	}

	@Override
	public int buyFuWen() {
		PvpFuWenBuyer buyer = new PvpFuWenBuyer(getCity());
		buyer.buyFuWen();
		getCity().getBagAuto().remove(D.PVP_FUWEN_ID, 1);
		buyer.sendFuWenReward();
		PvpManager m = getCity().getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		return p.getRemainTimes();
	}

	@Override
	public int useFuWen() {

		PvpFuWenBuyer buyer = new PvpFuWenBuyer(getCity());
		getCity().getBagAuto().remove(D.PVP_FUWEN_ID, 1);
		buyer.sendFuWenReward();

		PvpManager m = getCity().getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		return p.getRemainTimes();
	}

	@Override
	public WarSituationPro getWarSituation(int situationId) {
		PvpWarSituationDao DAO = Daos.getPvpWarSituationDao();
		PvpWarSituationDto w = DAO.get(situationId);

		if (w == null) {
			throw new SureIllegalOperationException("没有找到录像:" + situationId);
		}

		try {
			return WarSituationPro.parseFrom(w.getData());
		} catch (InvalidProtocolBufferException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PvpUsersPro getTopsToday() {
		City city = getCity();
		List<PvpPlayer> players = PvpPlaceImpl.getInstance().getTops(1,
				D.PVP_PERSON_COUNT);
		return new PvpUsersBuilder().build(city, players);
	}

	@Override
	public WarSituationsPro getWarsituations(int page, int count) {

		List<PvpWarSituationDto> all = warSituationTop10();

		for (PvpWarSituationDto p : all) {
			p.setIsSaw(true);
			Daos.getPvpWarSituationDao().save(p);
//			Debuger.debug("PvpServiceImpl.getWarsituations()" );
		}
		return new WarSituationsBuilder(getCity()).build(all);
	}

	private List<PvpWarSituationDto> warSituationTop10() {
		PvpManager pm = getCity().getNewPvpManager();

		List<PvpWarSituationDto> all = pm.getWarSituationsAll();
		Collections.reverse(all);

		Comparator<PvpWarSituationDto> c = new Comparator<PvpWarSituationDto>() {

			@Override
			public int compare(PvpWarSituationDto o1, PvpWarSituationDto o2) {
				return o2.getCreateTime() - o1.getCreateTime();
			}
		};
		Collections.sort(all, c);
		all = Util.Collection.sub(all, 10);
		UserCounterSetter his = getCity().getUserCounterHistory();
		his.clear(CounterKey.HAS_NEW_PVP_MESSAGE);
		return all;
	}

	@Override
	public WarSituationsPro getWarsituationsCs(int page, int count) {
		List<PvpWarSituationDto> all = warSituationTop10();
		return new WarSituationsBuilder(getCity()).buildCs(all);
	}

	// private List<PvpWarSituation> getPage(int page, int count) {
	// PvpManager pm = getCity().getNewPvpManager();
	//
	// List<PvpWarSituation> ws = pm.getWarSituationsBeHit();
	// ws.addAll(pm.getWarSituationsAll());
	// Collections.reverse(ws);
	// Page<PvpWarSituation> p = new Page<PvpWarSituation>(ws, count);
	// List<PvpWarSituation> page2 = p.getPage(page);
	//
	// Comparator<PvpWarSituation> c = new Comparator<PvpWarSituation>() {
	//
	// @Override
	// public int compare(PvpWarSituation o1, PvpWarSituation o2) {
	// return o2.getCreateTime() - o1.getCreateTime();
	// }
	// };
	// Collections.sort(page2, c);
	//
	// UserCounterSetter his = getCity().getUserCounterHistory();
	// his.clear(CounterKey.HAS_NEW_PVP_MESSAGE);
	// return page2;
	// }

	@Override
	public void receiveDanReward() {
		PvpManager pm = getCity().getNewPvpManager();
		DanReward reward = pm.getDanReward();
		if (!reward.canReiceive()) {
			throw new OperationFaildException(S.S10246);
		}
		reward.receive();
	}

	@Override
	public PvpScenePro recieveRankReward2() {
		recieveRankReward();
		return getMyData();
	}

	@Override
	public PvpScenePro receiveDanReward2() {
		receiveDanReward();
		return getMyData();
	}

	@Override
	public PvpRewardStatusPro getRewardStatus() {

		City city = getCity();
		PvpManager m = city.getNewPvpManager();

		PvpPlayer player = m.getPlayer();

		PvpRewardStatusPro.Builder b = PvpRewardStatusPro.newBuilder();

		DanReward reward = m.getDanReward();
		DanRewardPro rPro = new DanRewardBuilder().build(reward);

		b.setReward(rPro);

		b.setRongYu(city.getPlayer().get(PlayerProperty.RONG_YU));
		boolean canNotReceiveRankReward = player.canNotReceiveRankReward();
		b.setHasReceived(canNotReceiveRankReward);
		int rankRewardRemainSec = player.getRankRewardRemainSec();
		b.setRankRewardRemainSec(rankRewardRemainSec);
		b.setRankOnEightClock(player.getRankInEightClock());

		Debuger.debug("PvpServiceImpl.getRewardStatus()"
				+ canNotReceiveRankReward + " " + rankRewardRemainSec);

		return b.build();
	}

	@Override
	public void clearRefreshCd() {

		getCity().getPlayer().reduceGoldOrJinDing(D.PVP_REFRESH_NEED);

		City city = getCity();
		ColdDown cd = city.getCDManager().get(CDKey.PVP_CHALLENGE);
		cd.clear();
	}

	@Override
	public PvpUsersPro getRandomUser() {

		City city = getCity();
		ColdDown cd = city.getCDManager().get(CDKey.PVP_CHALLENGE);

		// 直接扣钱刷新
		if (cd.isFreezing()) {
			getCity().getPlayer().reduceGoldOrJinDing(D.PVP_REFRESH_NEED);
			cd.clear();
		}

		// cd.check();

		PvpPlace instance = PvpPlaceImpl.getInstance();
		List<PvpFightUser> players = instance.getRandomUsers(getCity());

		cd.add();
		return new PvpUsersBuilder().build(getCity(), players, 1, 1,
				D.PVP_PERSON_COUNT);
	}

}
