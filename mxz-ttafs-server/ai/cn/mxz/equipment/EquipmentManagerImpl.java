package cn.mxz.equipment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import message.S;
import cn.javaplus.collections.counter.Counter;
import cn.javaplus.collections.counter.ICounter;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Collection;
import cn.mxz.EquipmentTemplet;
import cn.mxz.EquipmentTempletConfig;
import cn.mxz.bag.BagAuto;
import cn.mxz.bag.BagRouter;
import cn.mxz.bag.BagRouterImpl;
import cn.mxz.bag.GridDao;
import cn.mxz.base.ServerCommand;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.battle.Battle;
import cn.mxz.battle.WarSituation;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.equipment.snatch.SnatchManager;
import cn.mxz.events.EquipmentCreateEvent;
import cn.mxz.events.EquipmentLevelUpEvent;
import cn.mxz.events.Events;
import cn.mxz.events.snatch.SnatchFaildEvent;
import cn.mxz.events.snatch.SnatchOverEvent;
import cn.mxz.events.snatch.SnatchSuccessEvent;
import cn.mxz.newpvp.RandomRewardSender;
import cn.mxz.protocols.user.equipment.EquipmentP.SnatchResultPro;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.needs.NeedsChecker;
import cn.mxz.util.needs.NeedsFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import db.dao.impl.AvoidFighterDao;
import db.dao.impl.DaoFactory;
import db.dao.impl.NewEquipmentDao;
import db.domain.AvoidFighter;
import db.domain.AvoidFighterImpl;
import db.domain.DBGrid;
import db.domain.NewEquipment;
import db.domain.NewEquipmentImpl;
import define.D;

public class EquipmentManagerImpl implements EquipmentManager {

	private static final int COUNT = 8;
	private City city;
	private Map<Integer, EquipmentImpl> all;
	private AtomicInteger nextEquipmentId;

	public EquipmentManagerImpl(City city) {
		this.city = city;
		init();
		nextEquipmentId = new AtomicInteger(nextEquipmentId(city));
	}

	private void init() {

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();

		List<NewEquipment> all = DAO.getAll();

		this.all = Maps.newConcurrentMap();

		for (NewEquipment e : all) {

			// Iterator<NewEquipment> it = all.iterator();

			if (e.getUname().equals(city.getId())) {

				this.all.put(e.getEquipmentId(), new EquipmentImpl(e));
			}
		}
	}

	@Override
	public Map<Integer, EquipmentImpl> getAll() {
		Map<Integer, EquipmentImpl> map = Maps.newHashMap();
		map.putAll(all);
		return map;
	}

	@Override
	public Map<Integer, EquipmentImpl> getEquippedAll() {
		Map<Integer, EquipmentImpl> es = Maps.newHashMap(getAll());
		Iterator<Entry<Integer, EquipmentImpl>> it = es.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, EquipmentImpl> next = it.next();
			Equipment e = next.getValue();
			if (!e.isEquipped()) {
				it.remove();
			}
		}
		return es;
	}

	@Override
	public List<Equipment> change(int id1, int id2) {

		List<Equipment> ls = Lists.newArrayList();

		Map<Integer, EquipmentImpl> equiments = getAll();

		Equipment e1 = equiments.get(id1);

		Equipment e2 = equiments.get(id2);

		checkChange(e1, e2);

		e1.exchangePosition(e2);

		ls.add(e1);
		ls.add(e2);
		return ls;
	}

	@Override
	public void levelUp(int id, int levelAdd) {

		EquipmentImpl equip = all.get(id);
		
		levelAdd = getMaxLevelAdd(equip, levelAdd);
		
		if(levelAdd <= 0) {
			throw new OperationFaildException(S.S10006);
		}

		ServerCommand c = new EquipmentLevelUpCommand(equip, levelAdd, city);

		c.check();

		c.run();
		
		EquipmentLevelUpEvent e = new EquipmentLevelUpEvent(equip, id, levelAdd, city);

		Events.getInstance().dispatch(e);
	}

	/**
	 * 获得最大能够升几级
	 * @param e
	 * @param levelAdd 客户端发送过来的等级增量
	 * @return
	 */
	private int getMaxLevelAdd(EquipmentImpl e, int levelAdd) {
		int levelUpNeed = Integer.MAX_VALUE;
		while(true) {
			levelUpNeed = EquipmentConfig.getLevelUpNeed(e.getTypeId(), e.getLevel(), levelAdd);
			if(city.getPlayer().get(PlayerProperty.CASH) >= levelUpNeed) {
				return levelAdd;
			}
			levelAdd--;
		}
	}

	@Override
	public void protect() {

		AvoidFighterDao DAO = DaoFactory.getAvoidFighterDao();

		AvoidFighter a = new AvoidFighterImpl();

		a.setUname(city.getPlayer().getId());

		a.setIsFighter(true);

		int currentTime = (int) System.currentTimeMillis() / 1000;

		a.setStartTime(currentTime);

		DAO.save(a);
	}

	@Override
	public SnatchResultPro snatch(String userId, int stuffTempletId) {

		SnatchManager sm = city.getSnatchManager();

		if (isProtect(userId)) {

			throw new OperationFaildException(S.S10097);
		}

		FightingUser u = sm.getUser(userId);

		boolean isRobot = u.isRobot();

		city.getPlayer().reduce(PlayerProperty.POWER, 1);

		Battle battle = fighting(u, isRobot);

		RandomRewardSender s = new RandomRewardSender();

		final boolean isSuccess = battle.isWin()
				&& isSnatchSuccessFul(u, stuffTempletId);
		if (battle.isWin()) {
			s.sendReward(city);
		}

		SnatchResultBuilder bd = new SnatchResultBuilder();
		WarSituation ws = battle.getWarSituation();

		SnatchResultPro build = bd
				.build(s, isSuccess, stuffTempletId, ws, city);

		WarSituationManager manager = new WarSituationManagerImpl();

		int warsituationId = manager.save(build.getSituation());

		if (isSuccess) {

			if (!isRobot) {

				removeData(userId, stuffTempletId);

			}

			Debuger.debug("EquipmentManagerImpl.snatch()夺宝成功:" + userId + ":"
					+ stuffTempletId);

			addMyData(userId, stuffTempletId);

			Events.getInstance().dispatch(
					new SnatchSuccessEvent(city, battle, isRobot, userId,
							stuffTempletId, warsituationId));

			UserCounter c = city.getUserCounterHistory();
			c.add(CounterKey.SNATCH_TIMES_FOR_MUST_DROP, 1, stuffTempletId);

		} else {
			Debuger.debug("EquipmentManagerImpl.snatch() 夺宝失败");

			UserCounter c = city.getUserCounterHistory();
			c.set(CounterKey.SNATCH_TIMES_FOR_MUST_DROP, 0, stuffTempletId);

			Events.getInstance().dispatch(
					new SnatchFaildEvent(city, battle, isRobot, userId,
							stuffTempletId, warsituationId));

		}

		UserCounterSetter uc = city.getUserCounter();
		uc.add(CounterKey.SNATCH_SUCCESS_TIMES, 1);

		Events.getInstance().dispatch(
				new SnatchOverEvent(city, battle, isRobot, userId,
						stuffTempletId, warsituationId, isSuccess));

		return build;
	}

	@Override
	public Map<Integer, EquipmentImpl> getEquipmentAll(Hero h) {

		Map<Integer, EquipmentImpl> list = new HashMap<Integer, EquipmentImpl>();

		Map<Integer, EquipmentImpl> equippedAll = getEquippedAll();

		for (EquipmentImpl e : equippedAll.values()) {

			Hero hero = e.getHero();
			if (hero == null) {
				continue;
			}
			if (h.equals(hero)) {
				list.put(e.getId(), e);
			}
		}

		return list;
	}

	@Override
	public List<City> getUsersWhoHas(int stuffId) {

		List<String> a = randomIds(stuffId);

		List<City> citys = new ArrayList<City>();

		for (String id : a) {

			if (id != city.getPlayer().getId()) {

				citys.add(WorldFactory.getWorld().get(id));
			}
		}

		return citys;
	}

	@Override
	public Equipment sell(int equipmentId) {

		Equipment equipment = getAll().get(equipmentId);

		if (equipment == null) {

			throw new NullPointerException(equipmentId + "");
		}

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();

		DAO.delete(equipment.getId(), city.getId());

		all.remove(equipment.getId());

		city.getPlayer().add(PlayerProperty.CASH,
				equipment.getPriceLevelUpHistory());

		return equipment;
	}

	@Override
	public Equipment takeOff(int equipmentId) {
		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();
		EquipmentImpl eq = all.get(equipmentId);
		NewEquipment e = eq.getDto();
		e.setFighterTypeId(-1);
		DAO.save(e);
		return eq;
	}

	@Override
	public void removeAllSnatchLog() {

		city.getSnatchManager().removeAllLogs();
	}

	@Override
	public int snatchRamdomId(String userId) {
		return 0;
	}

	@Override
	public Equipment add(int id, int fighterTypeId) {

		checkAdd(id, fighterTypeId);

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();
		EquipmentImpl eq = all.get(id);
		NewEquipment e = eq.getDto();
		e.setFighterTypeId(fighterTypeId);
		DAO.update(e);

		return eq;
	}

	/**
	 * 创建一个新的装备
	 * 
	 * @param id
	 * @param count2
	 * @param city
	 */
	@Override
	public void createNewEquipment(int... ids) {

		long time = System.currentTimeMillis();

		List<NewEquipment> ls = Lists.newArrayList();

		NewEquipmentDao DAO = DaoFactory.getNewEquipmentDao();

		String id2 = city.getId();

		String nick = city.getPlayer().getNick();

		for (int id : ids) {

			NewEquipment e = new NewEquipmentImpl();

			e.setFighterTypeId(-1);

			e.setTempletId(id);

			e.setUname(id2);

			e.setEquipmentId(nextEquipmentId.addAndGet(1));

			ls.add(e);

			String log = "EquipmentManagerImpl.CreateNewEquipment: 生成新装备:"
					+ id2 + "," + nick + "," + id;

			Debuger.debug(log);

		}
		DAO.addAll(ls);

		List<EquipmentImpl> a = Lists.newArrayList();

		for (NewEquipment e : ls) {
			EquipmentImpl value = new EquipmentImpl(e);
			int id = e.getEquipmentId();
			a.add(value);
			all.put(id, value);
		}

		Events.getInstance().dispatch(new EquipmentCreateEvent(a, city));
		Debuger.debug("{enclosing_type}.createNewEquipment 耗时:"
				+ (System.currentTimeMillis() - time));
	}

	@Override
	public void createNewEquipment(List<Integer> all) {
		int[] ids = new int[all.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = all.get(i);
		}
		createNewEquipment(ids);
	}

	private int nextEquipmentId(City city) {

		if (all.isEmpty()) {

			return 1;
		}

		int id = Collection.getMax(all.keySet());

		return id + 1;
	}

	private void checkChange(Equipment e1, Equipment e2) {

		EquipmentTemplet t1 = EquipmentTempletConfig.get(e1.getTypeId());

		EquipmentTemplet t2 = EquipmentTempletConfig.get(e2.getTypeId());

		checkPart(t1, t2);

		checkDressLevel(t1, t2);
	}

	/**
	 * 穿戴等级检查
	 * 
	 * @param t1
	 * @param t2
	 */
	private void checkDressLevel(EquipmentTemplet t1, EquipmentTemplet t2) {

		int level = city.getLevel();

		if (level < t1.getDressLevel() || level < t2.getDressLevel()) {

			throw new OperationFaildException(S.S10118);
		}
	}

	/**
	 * 部位检查
	 * 
	 * @param t1
	 * @param t2
	 */
	private void checkPart(EquipmentTemplet t1, EquipmentTemplet t2) {

		int a1 = t1.getBaseAdditionType();
		int a2 = t2.getBaseAdditionType();

		if (a1 == 2) {
			a1 = 1;
		}

		if (a2 == 2) {
			a2 = 1;
		}

		if (a1 != a2) {

			throw new OperationFaildException(S.S10119);
		}
	}

	private boolean isSnatchSuccessFul(FightingUser u, int stuffTempletId) {

		float probability = u.getProbability(city, stuffTempletId);

		Debuger.debug("EquipmentManagerImpl.isSnatchSuccessFul() 夺宝概率:"
				+ probability);

		return Util.Random.isHappen(probability);
	}

	/**
	 * 开始战斗
	 * 
	 * @param u
	 * @param isRobot
	 * @return
	 */
	private Battle fighting(FightingUser u, boolean isRobot) {

		Battle battle = new SnatchBattle(city, u, isRobot);

		battle.fighting();

		return battle;
	}

	private void addMyData(String userId, int stuffTempletId) {

		BagAuto bag = city.getBagAuto();

		bag.addProp(stuffTempletId, D.EQUIPMENT_SNATCH_COUNT);

	}

	private void removeData(String userId, int stuffTempletId) {

		City city2 = WorldFactory.getWorld().get(userId);

		BagAuto bag = city2.getBagAuto();
		if (bag.getCount(stuffTempletId) >= D.EQUIPMENT_SNATCH_COUNT) {
			bag.remove(stuffTempletId, D.EQUIPMENT_SNATCH_COUNT);
		}
	}

	/**
	 * 是否受保护
	 * 
	 * @param userId
	 * @return
	 */
	private boolean isProtect(String userId) {

		AvoidFighterDao DAO = DaoFactory.getAvoidFighterDao();

		AvoidFighter af = DAO.get(userId);

		long currentTimeMillis = System.currentTimeMillis();

		long a = currentTimeMillis / 1000;

		if (af != null && 3 * 60 * 60 - (a - af.getStartTime()) > 0) {

			return true;

		} else {

			return false;
		}
	}

	/**
	 * 随机获得玩家帐号
	 * 
	 * @param stuffId
	 * @return
	 */
	private List<String> randomIds(int stuffId) {

		BagRouter rotour = new BagRouterImpl();

		GridDao DAO = rotour.getDAO(stuffId);

		List<DBGrid> findBy = DAO.findByTypeId(stuffId);

		ICounter<String> counter = new Counter<String>();

		Set<String> allId = new HashSet<String>();

		for (DBGrid userGrid : findBy) {

			String n = userGrid.getUname();
			counter.add(n);
			if (counter.get(n) >= 2) { // 有2个以上材料的玩家, 才能够被掠夺
				allId.add(n);
			}
		}

		List<String> a = new ArrayList<String>(allId);

		cn.javaplus.util.Util.Collection.upset(a);

		a = cn.javaplus.util.Util.Collection.sub(a, COUNT);

		if (a.isEmpty()) {
			a = buildFake();// 假的
		}

		return a;
	}

	private List<String> buildFake() {
		Set<String> ls = Sets.newHashSet();

		java.util.Collection<City> all = WorldFactory.getWorld().getAll();

		int i = 0;

		while (ls.size() < COUNT && i++ < 1000) {
			City random = Util.Collection.getRandomOne(all);
			if (!random.equals(city)) {
				ls.add(random.getId());
			}
		}
		return Lists.newArrayList(ls);
	}

	private void checkAdd(int id, int fighterTypeId) {
		checkFighterExist(fighterTypeId);
		checkEquipmentExist(id);
		checkEquipped(id, fighterTypeId); // 是否已经装备了同种装备
	}

	private void checkEquipmentExist(int id) {
		EquipmentManager m = city.getEquipmentManager();
		Equipment e = m.getAll().get(id);
		if (e == null) {
			throw new SureIllegalOperationException("-装备不存在!");
		}
	}

	private void checkFighterExist(int fighterTypeId) {
		Hero hero = city.getTeam().get(fighterTypeId);
		if (hero == null) {
			throw new SureIllegalOperationException("-神将不存在!");
		}
	}

	private void checkEquipped(int id, int fighterTypeId) {
		Hero hero = city.getTeam().get(fighterTypeId);

		EquipmentManager manager = city.getEquipmentManager();
		Map<Integer, EquipmentImpl> equipmentAll = manager
				.getEquipmentAll(hero);
		Equipment equipment = manager.getAll().get(id);
		for (Equipment e : equipmentAll.values()) {
			if (equipment.getAdditionType() == e.getAdditionType()) {
				throw new SureIllegalOperationException("-已经穿戴同类装备了!");
			}
		}
	}

	@Override
	public Equipment generate2(int equipmentId, int dstEquipmentTempletId) {
		check(equipmentId, dstEquipmentTempletId);
		redece(dstEquipmentTempletId);
		return changeTypeTo(equipmentId, dstEquipmentTempletId);
	}

	private EquipmentImpl changeTypeTo(int equipmentId,
			int dstEquipmentTempletId) {
		EquipmentImpl e = getAll().get(equipmentId);
		int level = EquipmentConfig.getLevelParse(e.getTypeId(), e.getLevel(),
				dstEquipmentTempletId);
		e.getDto().setLevel(level);
		e.getDto().setTempletId(dstEquipmentTempletId);
		DaoFactory.getNewEquipmentDao().update(e.getDto());
		
		Debuger.debug("EquipmentManagerImpl.changeTypeTo()" + e.getDto().getTempletId());
		
		return e;
	}

	private void redece(int dstEquipmentTempletId) {
		EquipmentTemplet temp = EquipmentTempletConfig
				.get(dstEquipmentTempletId);

		String si = temp.getScrollId();
		String st = temp.getStuffId();
		reduce(si);
		reduce(st);
	}

	private void reduce(String si) {
		NeedsChecker c = NeedsFactory.getNeedsCheckerImpl2(si);
		c.deduct(city.getPlayer());
	}

	private void check(int equipmentId, int dstEquipmentTempletId) {

		// 合成所需低级装备id 合成所需卷轴id 合成所需材料
		// 0 0
		// String String String
		// equipmentId scrollId stuffId

		Equipment eSrc = getAll().get(equipmentId);

		if (eSrc == null) {
			throw new OperationFaildException(S.S10244);
		}

		EquipmentTemplet temp = EquipmentTempletConfig
				.get(dstEquipmentTempletId);

		String ei = temp.getEquipmentId();
		String si = temp.getScrollId();
		String st = temp.getStuffId();

		checkEmpty(ei);
		checkEmpty(si);
		checkEmpty(st);

		checkPropEnouph(si);
		checkPropEnouph(st);
	}

	private void checkPropEnouph(String si) {
		NeedsChecker c = NeedsFactory.getNeedsCheckerImpl2(si);
		try {
			c.check(city.getPlayer());
		} catch (OperationFaildException e) {
			throw new OperationFaildException(S.S10255);
		}
	}

	private void checkEmpty(String ei) {
		if (ei.trim().isEmpty()) {
			throw new OperationFaildException(S.S10237);
		}
	}

	@Override
	public Equipment generate3(int stuffId) {

		EquipmentTemplet et = find(stuffId);

		int equipmentId = findEquipment(getFirst(et.getEquipmentId()));
		int dstEquipmentTempletId = et.getId();

		return generate2(equipmentId, dstEquipmentTempletId);
	}

	private EquipmentTemplet find(int stuffId) {
		List<EquipmentTemplet> all = EquipmentTempletConfig.getAll();
		for (EquipmentTemplet e : all) {
			String scrollId = e.getScrollId();
			List<Integer> ids = Util.Collection.getIntegers(scrollId);
			if(ids.isEmpty()) {
//				throw new RuntimeException("" + scrollId + ", " + e.getId());
				continue;
			}
			Integer id = ids.get(0);
			if (id.equals(stuffId)) {
				return e;
			}
		}
		throw new RuntimeException("没有找到对应的装备模板");
	}

	private int findEquipment(int first) {
		Map<Integer, EquipmentImpl> all = getAll();
		ArrayList<EquipmentImpl> ls = Lists.newArrayList();
		for (EquipmentImpl e : all.values()) {
			if (e.getTypeId() == first) {
				ls.add(e);
			}
		}
		if (ls.isEmpty()) {
			throw new RuntimeException("没有找到类型ID为" + first + "的装备!");
		}
		return getLevelMax(ls);
	}

	/**
	 * 获得等级最大的装备的唯一ID
	 * 
	 * @param ls
	 * @return
	 */
	private int getLevelMax(ArrayList<EquipmentImpl> ls) {
		Comparator<EquipmentImpl> c = new Comparator<EquipmentImpl>() {

			@Override
			public int compare(EquipmentImpl o1, EquipmentImpl o2) {
				return o1.getLevel() - o2.getLevel();
			}
		};
		Collections.sort(ls, c);
		return ls.get(0).getId();
	}

	private int getFirst(String equipmentId) {
		Integer id = Util.Collection.getIntegers(equipmentId).get(0);
		return id;
	}

}
