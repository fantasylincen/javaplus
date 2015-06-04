package cn.mxz.equipment;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import message.S;
import net.sf.ehcache.Cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.EquipmentAddEvent;
import cn.mxz.events.EquipmentChangeEvent;
import cn.mxz.events.EquipmentGenerate2Event;
import cn.mxz.events.EquipmentGenerate3Event;
import cn.mxz.events.EquipmentSellEvent;
import cn.mxz.events.EquipmentTakeOffEvent;
import cn.mxz.events.Events;
import cn.mxz.fighter.CacheFactory;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.formation.AdditionType;
import cn.mxz.handler.EquipmentService;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.prop.equipment.UserRandom;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentHadPro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentPro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentSnatchListPro;
import cn.mxz.protocols.user.equipment.EquipmentP.EquipmentsPro;
import cn.mxz.protocols.user.equipment.EquipmentP.LogSnatchPro;
import cn.mxz.protocols.user.equipment.EquipmentP.ProtectFighter;
import cn.mxz.protocols.user.equipment.EquipmentP.ProtectFighter.Builder;
import cn.mxz.protocols.user.equipment.EquipmentP.SnatchResultPro;
import cn.mxz.user.builder.UserBuilder;
import cn.mxz.user.team.Team;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.protobuf.InvalidProtocolBufferException;

import db.dao.impl.AvoidFighterDao;
import db.dao.impl.DaoFactory;
import db.dao.impl.WarSituationDao;
import db.domain.AvoidFighter;
import db.domain.WarSituation;
import define.D;

@Component("equipmentService")
@Scope("prototype")
public class EquipmentServiceImpl extends AbstractService implements
		EquipmentService {

	private static final int RANDOM_USER_COUNT = 4;

	@Override
	public EquipmentsPro getEquippedAll() {

		Map<Integer, ? extends Equipment> equipments = getEquipmentManager()
				.getEquippedAll();

		return new EquipmentsBuilder().build(equipments.values());
	}

	@Override
	public EquipmentsPro getInStorageAll() {

		Map<Integer, ? extends Equipment> equipments = getEquipmentManager()
				.getAll();

		return new EquipmentsBuilder().build(equipments.values());
	}

	@Override
	public EquipmentPro change(int id1, int id2) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		int oldShenJia = getCity().getFormation().getShenJia();

		Hero h = getHero(id1, id2);
		TianMingSnapsort tms = new TianMingSnapsort(h);
		tms.snapsort();

		// Events.getInstance().dispatch(new BeforeEquipmentChangeEvent(id1,
		// id2, getCity()));

		List<Equipment> change;
		if (h != null) {
			MessageAttributeChange m = new MessageAttributeChange(getCity());

			m.saveOld(h);
			change = getEquipmentManager().change(id1, id2);
			m.saveNew(h);
			m.sendMessage();
		} else {
			change = getEquipmentManager().change(id1, id2);
		}

		Events.getInstance().dispatch(
				new EquipmentChangeEvent(change, id1, id2, getCity()));

		clearEquipmentCache();

		Equipment e1 = getEquipmentManager().getAll().get(id1);

		Equipment e2 = getEquipmentManager().getAll().get(id2);

		if (e1.getHero() != null) {

			return new EquipmentBuilder().build(e1);
		}

		s.snapshoot();

		tms.snapsort();

		Events.getInstance().dispatch(
				new AttributeChangeEvent(getCity(), oldShenJia));

		return new EquipmentBuilder().build(e2);
	}

	private Hero getHero(int id1, int id2) {

		EquipmentManager em = getEquipmentManager();
		Map<Integer, ? extends Equipment> all = em.getEquippedAll();

		Equipment e1 = all.get(id1);

		if (e1 != null) {
			Hero hero = e1.getHero();
			if (hero != null)
				return hero;
		}

		Equipment e2 = all.get(id2);
		if (e2 != null) {
			Hero hero = e2.getHero();
			if (hero != null)
				return hero;
		}
		return null;
	}

	// 移除装备缓存
	private void clearEquipmentCache() {

		Cache cache = CacheFactory.getCache("equipmentAdditionCache", 2);

		cache.removeAll();
	}

	@Override
	public EquipmentPro levelUp(int id, int add) {

		City city = getCity();
		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();

		getEquipmentManager().levelUp(id, add);

		Equipment e1 = getEquipmentManager().getAll().get(id);

		s.snapshoot();

		return new EquipmentBuilder().build(e1);
	}

	private EquipmentManager getEquipmentManager() {
		return getCity().getEquipmentManager();
	}

	@Override
	public SnatchResultPro snatch(String userId, int stuffTempletId) {

		if (stuffTempletId == -1) {

			stuffTempletId = getEquipmentManager().snatchRamdomId(userId);
		}
		return getEquipmentManager().snatch(userId, stuffTempletId);
	}

	@Override
	public ProtectFighter protect() {

		Builder b = ProtectFighter.newBuilder();

		getEquipmentManager().protect();

		b.setCountDown(3 * 3600);

		return b.build();
	}

	@Override
	public EquipmentSnatchListPro getSnatchList(int stuffId) {

		int shenJia = getCity().getFormation().getShenJia();

		List<FightingUser> allUser = Lists.newArrayList();

		// 添加有这个材料的战士, 而且战斗力差不多的
		addWhoHasStuff(stuffId, allUser, shenJia);

		// 根据自己的属性, 创建足够多的机器人
		ensureRobotEnouph(allUser);

		getCity().getSnatchManager().updateSnatchList(allUser);

		Util.Collection.upset(allUser);

		sort(allUser);

		EquipmentSnatchListBuilder b = new EquipmentSnatchListBuilder();

		return b.build(getCity(), allUser, stuffId);
	}

	private void ensureRobotEnouph(List<FightingUser> allUser) {
		while (allUser.size() < RANDOM_USER_COUNT) {
			addRobotBySelf(allUser);
		}
	}

	private void sort(List<FightingUser> allUser) {
		Comparator<FightingUser> c = new Comparator<FightingUser>() {

			@Override
			public int compare(FightingUser o1, FightingUser o2) {
				int a1 = o1 instanceof SnatchRobotUser ? 1 : 0;
				int a2 = o2 instanceof SnatchRobotUser ? 1 : 0;
				return a1 - a2;
			}

		};
		Collections.sort(allUser, c);
	}

	private void addRobotBySelf(List<FightingUser> allUser) {
		allUser.add(new SnatchRobotUser(getCity(), D.ROBOT_SHEN_JIA_MIN_PAR,
				D.ROBOT_SHEN_JIA_MAX_PAR));
	}

	private void addWhoHasStuff(int stuffId, List<FightingUser> allUser,
			int shenJia) {

//		if (getCity().isTester()) {
//			allUser.add(new UnRobotUser(CityFactory.getCity("lc1")));
//			allUser.add(new UnRobotUser(CityFactory.getCity("lc122")));
//			allUser.add(new UnRobotUser(CityFactory.getCity("100103")));
//			return;
//		}

		List<String> unames = new UserRandom().random(getCity(), stuffId,
				shenJia);

		for (String u : unames) {
			City c = CityFactory.getCity(u);
			allUser.add(new UnRobotUser(c));
		}
	}

	// /**
	// * 寻找附近的PVP玩家
	// *
	// * @param ls
	// */
	// private void addPvpNearest(List<FightingUser> ls) {
	//
	// NewPvpPlayer player = getCity().getNewPvpManager().getPlayer();
	//
	// PvpPlace ins = PvpPlaceImpl.getInstance();
	//
	// List<NewPvpPlayer> nearests =
	// Lists.newArrayList(ins.getNearests(player.getId()));
	// nearests.remove(player);
	// Util.Collection.upset(nearests);
	//
	// Iterator<NewPvpPlayer> it = nearests.iterator();
	//
	// while (it.hasNext() && ls.size() < RANDOM_USER_COUNT) {
	// NewPvpPlayer next = it.next();
	// City city = next.getCity();
	// UnRobotUser user = new UnRobotUser(city);
	// ls.add(user);
	// }
	// }

	//
	// private void ensureUserCount(List<FightingUser> allUser, int shenJia) {
	// List<String> unames;
	// int robotCount = RANDOM_USER_COUNT - allUser.size();
	//
	// if (robotCount > 0) {
	//
	// int s = shenJia - 1000;
	// if (s < 100) {
	// s = 100;
	// }
	// unames = new UserRandom().random(0, s, robotCount);
	// while (unames.size() < robotCount) {
	// unames.add(getId());
	// }
	//
	// for (String u : unames) {
	// City c = CityFactory.getCity(u);
	// allUser.add(new RobotUser(c, D.ROBOT_SHEN_JIA_MIN_PAR,
	// D.ROBOT_SHEN_JIA_MAX_PAR));
	// }
	// }
	// }

	@Override
	public void sell(String equipmentIds) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		List<Integer> integers = Collection.getIntegers(equipmentIds);

		// Events.getInstance().dispatch(new
		// BeforeEquipmentSellEvent(equipmentIds, getCity()));

		List<Equipment> ls = Lists.newArrayList();
		for (Integer id : integers) {
			Equipment sell = getEquipmentManager().sell(id);
			ls.add(sell);
		}

		Events.getInstance().dispatch(
				new EquipmentSellEvent(ls, equipmentIds, getCity()));
		s.snapshoot();
	}

	@Override
	public int takeOff(int fighterId, int equipmentId) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		int oldShenJia = getCity().getFormation().getShenJia();

		Team team = getCity().getTeam();
		Hero h = team.get(fighterId);

		// Events.getInstance().dispatch(new
		// BeforeEquipmentTakeOffEvent(fighterId, equipmentId, getCity()));

		Equipment takeOff;
		if (h != null) {
			MessageAttributeChange m = new MessageAttributeChange(getCity());

			m.saveOld(h);

			takeOff = getEquipmentManager().takeOff(equipmentId);
			m.saveNew(h);
			m.sendMessage();
		} else {
			takeOff = getEquipmentManager().takeOff(equipmentId);
		}

		Events.getInstance().dispatch(
				new EquipmentTakeOffEvent(takeOff, fighterId, equipmentId,
						getCity()));

		Debuger.debug("EquipmentServiceImpl.takeOff() 装备ID:" + equipmentId);

		Equipment equipment = getEquipmentManager().getAll().get(equipmentId);

		Debuger.debug("EquipmentServiceImpl.takeOff()" + equipment.getHero());

		clearEquipmentCache();

		s.snapshoot();

		Events.getInstance().dispatch(
				new AttributeChangeEvent(getCity(), oldShenJia));

		return equipmentId;
	}

	@Override
	public LogSnatchPro snatchLog(int page) {

		City city = getCity();
		List<SnatchLog> logs = city.getSnatchManager().getLogs();

		Comparator<SnatchLog> c = new Comparator<SnatchLog>() {

			@Override
			public int compare(SnatchLog o1, SnatchLog o2) {
				return o2.getTime().compareTo(o1.getTime());
			}
		};
		Collections.sort(logs, c);
		logs = Util.Collection.sub(logs, 10);

		for (SnatchLog lg : logs) {
			if (!lg.isQuilt()) {
				lg.markSaw();
			}
		}

		MessageFactory.getUser().onUpdateUserList(city.getSocket(),
				new UserBuilder().build(city));

		return new LogSnatchBuilder().build(city, logs);
	}

	@Override
	public EquipmentsPro getAllByPart(String parts) {

		Map<Integer, Equipment> equipments = Maps.newHashMap();

		Set<Integer> pts = Sets.newHashSet(Collection.getIntegers(parts));
		for (Integer integer : pts) {
			Map<Integer, Equipment> byPart = getByPart(integer);
			equipments.putAll(byPart);
		}

		return new EquipmentsBuilder().build(equipments.values());
	}

	private Map<Integer, Equipment> getByPart(int part) {

		Map<Integer, Equipment> equipments = new HashMap<Integer, Equipment>(
				getEquipmentManager().getAll());

		Iterator<Entry<Integer, Equipment>> iterator = equipments.entrySet()
				.iterator();

		while (iterator.hasNext()) {

			Entry<Integer, Equipment> next = iterator.next();

			Equipment value = next.getValue();

			EquipmentTemplet temp = EquipmentTempletConfig.get(value
					.getTypeId());

			if (part != temp.getBaseAdditionType()) {

				iterator.remove();
			}
		}
		return equipments;
	}

	// @Override
	// public EquipmentsHadPro getEquipmentsHadAll() {
	//
	// return new EquipmentsHadBuilder().build(getCity());
	// }

	@Override
	public ProtectFighter countDownTime() {

		Builder b = ProtectFighter.newBuilder();

		AvoidFighterDao DAO = DaoFactory.getAvoidFighterDao();

		String id = getPlayer().getId();

		AvoidFighter avoidFighter = DAO.get(id);

		int countdown = 0;

		if (avoidFighter != null) {

			int startTime = avoidFighter.getStartTime();

			countdown = (int) System.currentTimeMillis() / 1000 - startTime;

		} else {

			countdown = 3 * 3600;
		}

		if (countdown > 3 * 3600) {

			b.setCountDown(0);

		} else {

			b.setCountDown(3 * 3600 - countdown);
		}
		return b.build();
	}

	@Override
	public EquipmentHadPro getEquipmentHad() {
		return new EquipmentHadBuilder().build(getEquipmentManager());
	}

	@Override
	public EquipmentPro add(int id, int fighterTypeId) {

		int oldShenJia = getCity().getFormation().getShenJia();

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		TianMingSnapsort tms = new TianMingSnapsort(getCity().getTeam().get(
				fighterTypeId));
		tms.snapsort();

		Hero h = getCity().getTeam().get(fighterTypeId);
		MessageAttributeChange m = new MessageAttributeChange(getCity());

		m.saveOld(h);

		// Events.getInstance().dispatch(new BeforeEquipmentAddEvent(id,
		// fighterTypeId, getCity()));

		Equipment add = getEquipmentManager().add(id, fighterTypeId);

		Events.getInstance().dispatch(
				new EquipmentAddEvent(add, id, fighterTypeId, getCity()));

		m.saveNew(h);
		m.sendMessage();

		Events.getInstance().dispatch(
				new AttributeChangeEvent(getCity(), oldShenJia));

		s.snapshoot();

		tms.snapsort();

		return new EquipmentBuilder().build(getEquipmentManager().getAll().get(
				id));
	}

	@Override
	public WarSituationPro getWarSituation(int situationId) {

		WarSituationDao DAO = DaoFactory.getWarSituationDao();
		WarSituation w = DAO.get(situationId);

		if (w == null) {
			throw new OperationFaildException(S.S10188);
		}

		WarSituationPro par;
		try {
			par = WarSituationPro.parseFrom(w.getData());
		} catch (InvalidProtocolBufferException e) {
			throw new RuntimeException(e);
		}
		return par;
	}

	@Override
	public EquipmentPro changeEquipment(int fighterId, int id1, int id2) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();
		EquipmentPro change = change(id1, id2);
		s.snapshoot();
		return change;
	}

	@Override
	public int getLevelUpAddition(int id) {
		EquipmentManager manager = getCity().getEquipmentManager();
		Equipment e = manager.getAll().get(id);
		int type = e.getAdditionType();
		AdditionType a = AdditionType.fromNum(type);
		e = new EquipmentNextLevel(e);
		return a.get(e.getAddition());
	}

	@Override
	public EquipmentPro getAfterGenerate(int srcEquipmentId) {
		EquipmentManager ma = getCity().getEquipmentManager();
		Equipment e = ma.getAll().get(srcEquipmentId);

		return new EquipmentBuilder().build(new EquipmentAfterLevelUp(
				(EquipmentImpl) e));
	}

	@Override
	public EquipmentPro generate2(int srcEquipmentId, int dstEquipmentTempletId) {
		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		// Events.getInstance().dispatch(new
		// BeforeEquipmentGenerate2Event(srcEquipmentId, dstEquipmentTempletId,
		// getCity()));

		Equipment e = getEquipmentManager().generate2(srcEquipmentId,
				dstEquipmentTempletId);

		Events.getInstance().dispatch(
				new EquipmentGenerate2Event(e, srcEquipmentId,
						dstEquipmentTempletId, getCity()));

		Equipment e1 = getEquipmentManager().getAll().get(srcEquipmentId);
		s.snapshoot();
		return new EquipmentBuilder().build(e1);
	}

	@Override
	public EquipmentPro generate3(int stuffId) {

		FighterSnapshoot s = new FighterSnapshoot(getCity());
		s.snapshoot();

		// Events.getInstance().dispatch(new
		// BeforeEquipmentGenerate3Event(stuffId, getCity()));

		Equipment e = getEquipmentManager().generate3(stuffId);

		Events.getInstance().dispatch(
				new EquipmentGenerate3Event(e, stuffId, getCity()));

		// Equipment e1 = getEquipmentManager().getAll().get(equipmentId);
		s.snapshoot();
		return new EquipmentBuilder().build(e);
	}

}
