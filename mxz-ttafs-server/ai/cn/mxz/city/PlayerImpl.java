package cn.mxz.city;

import message.S;
import cn.javaplus.math.Fraction;
import cn.javaplus.time.Time;
import cn.javaplus.time.TimeUpTimer;
import cn.mxz.AuthorityTemplet;
import cn.mxz.AuthorityTempletConfig;
import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.events.Events;
import cn.mxz.events.PlayerPropertyChangeEvent;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.temp.TempKey;
import cn.mxz.thirdpaty.ThirdPartyException;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.thirdpaty.ThirdPartyRole;
import cn.mxz.user.Player;
import cn.mxz.user.team.Formation;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.debuger.Debuger;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDataDao;
import db.domain.UserBase;
import db.domain.UserData;
import db.domain.UserDataImpl;
import define.D;

public class PlayerImpl implements Player {


	private City		city;

	private UserBase	base;

	private UserData	data;

	private TimeUpTimer	shenJiaTimer	= new TimeUpTimer(3 * Time.MILES_ONE_MIN);

	/**
	 * 每xxx分钟, 从第三方平台更新元宝数量
	 */
	private TimeUpTimer	goldUpdateTimer	= new TimeUpTimer(5 * Time.MILES_ONE_MIN);

	public PlayerImpl(City city, UserBase base) {

		this.city = city;

		this.base = base;
	}

	public City getCity() {
		return city;
	}
	
	@Override
	public int getLevel() {
		return city.getTeam().getPlayer().getLevel();
	}

	@Override
	public Fraction getExp() {

		final Hero p = city.getTeam().getPlayer();

		return p.getExp();
	}

	@Override
	public Fraction getHeroInfo() {
		Formation f = city.getFormation();
		int count = f.getMaxCount();
		PlayerCamp selected = f.getSelected();
		return new Fraction(selected.getFighters().size(), count);
	}

	@Override
	public Fraction getPhysical() {
		return new Fraction(get(PlayerProperty.PHYSICAL), getPhysicalMax());
	}

	@Override
	public Fraction getPower() {
		return new Fraction(get(PlayerProperty.POWER), getPowerMax());
	}


	@Override
	public String getNick() {
		return base.getNick();
	}

	@Override
	public String getId() {
		return base.getUname();
	}

	@Override
	public void set(PlayerProperty playerProperty, int set) {

		lazyGet().setV(playerProperty.getValue(), set);

		commit();
	}

	@Override
	public void addExp(int count) {
		city.getTeam().getPlayer().addExp(count);
	}

	private void commit() {
		UserData dto = lazyGet();
		DaoFactory.getUserDataDao().update(dto);
		DaoFactory.getUserBaseDao().update(base);
	}

	@Override
	public boolean isMan() {

		final int typeId = city.getTeam().getPlayer().getTypeId();

		final FighterTemplet temp = FighterTempletConfig.get(typeId);

		return temp.getSex() == 1;
	}

	@Override
	public String getSearchedField() {
		return getNick();
	}

	@Override
	public int get(PlayerProperty property) {
		return lazyGet().getV(property.getValue());
	}

	@Override
	public void add(PlayerProperty p, int add) {

		int old = lazyGet().getV(p.getValue());

		int now = old + add;
		set(p, now);

		if (old != now) {
			PlayerPropertyChangeEvent event = new PlayerPropertyChangeEvent(city, this, p, false, old, now);
			Events.getInstance().dispatch(event);
		}
	}

	@Override
	public void reduce(PlayerProperty p, int reduce) {

		city.getChecker().checkPlayerProperty(p, reduce);

		int old = lazyGet().getV(p.getValue());

		int now = old - reduce;

		set(p, now);
		if (old != now) {
			PlayerPropertyChangeEvent event = new PlayerPropertyChangeEvent(city, this, p, false, old, now);
			Events.getInstance().dispatch(event);
		}
	}

	/**
	 * 尽量扣, 返回扣除后 还需要扣除多少
	 *
	 * @param p
	 * @param value
	 * @return
	 */
	private int reduce2(PlayerProperty p, int value) {
		int count = get(p);
		if (count >= value) {
			reduce(p, value);
			return 0;
		} else {
			reduce(p, count);
			return value - count;
		}
	}

	private UserData lazyGet() {

		if (data == null) {

			UserDataDao DAO = DaoFactory.getUserDataDao();
			String id = getId();
			data = DAO.get(id);

			if (data == null) {

				data = new UserDataImpl();
				data.setUname(id);
				data.setNick(getNick());
				data.setInvitationCode("");

				DAO.add(data);
			}
		}

		return data;
	}

	@Override
	public int getLastLoginSec() {

		long lastLoginTime = city.getLastLoginMillis();

		if (lastLoginTime <= 0) {
			lastLoginTime = System.currentTimeMillis() - Time.MILES_ONE_DAY * 30;
			lastLoginTime /= 1000;
			city.setLastLoginSec((int) lastLoginTime);
			lastLoginTime *= 1000;
		}


		long currentTimeMillis = System.currentTimeMillis();

		long d = currentTimeMillis - lastLoginTime;

		return (int) (d / 1000);
	}


	@Override
	public void setNick(String newNick) {
		base.setNick(newNick);
		commit();
	}

	@Override
	public void setInvitationCode(String code) {
		lazyGet().setInvitationCode(code);
	}

	@Override
	public String getInvitationCode() {
		return lazyGet().getInvitationCode();
	}

	@Override
	public void updateShenJia() {

		if (shenJiaTimer.isTimeUp()) {
			int s = city.getFormation().getShenJia();
			set(PlayerProperty.SHEN_JIA_CACHE, s);
			shenJiaTimer.restart();
		}
	}

	@Override
	public boolean isThirdPartyPlayer() {
		String userId = getThirdPartyId();
		ThirdPartyPlatform c = ThirdPartyPlatformFactory.getThirdPartyPlatform();
		if(userId == null) {
			return false;
		}
		return c.isPlatformUserId(userId);
	}

	@Override
	public String getThirdPartyId() {
		String userId = (String) city.getTempCollection().get(TempKey.USER_ID);
		return userId;
	}

	@Override
	public void saveThirdPartyId(String userId) {
		city.getTempCollection().put(TempKey.USER_ID, userId);
	}

	@Override
	public void saveClientType(int clientType) {
		city.getUserCounterHistory().set(CounterKey.CLIENT_TYPE, clientType);
		
//		city.getTempCollection().put(TempKey.CLIENT_TYPE, clientType);
		
//		暂时不敢改成下面
//		if(clientType == 0) {
//			return;
//		}
//		
//		UserCounter his = city.getUserCounterHistory();
//		int t = his.get(CounterKey.CLIENT_TYPE);
//		
//		if(t == 0) {
//			his.set(CounterKey.CLIENT_TYPE, clientType);
//		}
	}

	/**
	 * 玩家客户端类型
	 * 客户端类型:1 ios;2 android;
	 * @return
	 */
	
	@Override
	public int getClientType() {
		int type = city.getUserCounterHistory().get(CounterKey.CLIENT_TYPE);
		
		if(type == 0) {
			return 1;
		}
		
//		Object object = city.getTempCollection().get(TempKey.CLIENT_TYPE);
//		if(object == null) {
//			return 3;
//		}
//		return (Integer) object;
		return type;
	}
	
	private int reduceGiftGold(int value) {
		int count = lazyGet().getCoupon();
		if (count >= value) {
			lazyGet().addCoupon(-value);
			return 0;
		} else {
			lazyGet().addCoupon(-count);
			return value - count;
		}
	}

	@Override
	public void reduceGoldOrJinDing(int value) {

		if (value > getGoldAndJinDing()) {
			throw new OperationFaildException(S.S10007);
		}

		value = reduce2(PlayerProperty.NEW_GOLD, value); // 尽量扣, 返回扣除后 还需要扣除多少

		if (value > 0) {
			reduceGold(value);
		}
	}

	@Override
	public int getGoldAndJinDing() {
		return getGold() + get(PlayerProperty.NEW_GOLD);
	}

	@Override
	public void addGiftGold(int count) {

		int old = getGold();

		lazyGet().addCoupon(count);

		dispatchEvent(old);

		commit();
	}

	@Override
	public void addGold(int add) {
		int old = getGold();
		add(PlayerProperty.GOLD_HISTORY, add); // 记录历史元宝数
		lazyGet().addGold(add);
		dispatchEvent(old);
		updateGoldFromThirdParty();
	}

	/**
	 * 从第三方平台更新当前玩家的元宝到本地
	 */
	public void updateGoldFromThirdParty() {
		ThirdPartyPlatform e = ThirdPartyPlatformFactory.getThirdPartyPlatform();
		if (isThirdPartyPlayer()) {
			try {
				ThirdPartyRole role = ThirdPartyPlatformFactory.createRole(city);
				int gold = e.getGold(role);
				if (gold != lazyGet().getGold()) {
					error(gold);
				}
				lazyGet().setGold(gold);
			} catch (Exception e1) {
				errorMessage(e1);
			}
		}
		commit();
	}

	private void errorMessage(Exception e1) {
		e1.printStackTrace();
		System.err.println("与第三方平台服务器通信失败!");
		System.err.println("与第三方平台服务器通信失败!");
		System.err.println("与第三方平台服务器通信失败!");
	}

	private void error(int gold) {
		UserData d = lazyGet();
		Debuger.debug("[Gold Error] ThirdParty:" + gold + " GameServer:" + d.getGold() + " Error:" + (gold - d.getGold()) + " UserId:" + getId() + " Nick:" + getNick());
		System.err.println("第三方平台和我们的游戏服务器元宝存在误差 ThirdParty:" + gold + " GameServer:" + d.getGold() + " Error:" + (gold - d.getGold()));
		System.err.println("第三方平台和我们的游戏服务器元宝存在误差 ThirdParty:" + gold + " GameServer:" + d.getGold() + " Error:" + (gold - d.getGold()));
		System.err.println("第三方平台和我们的游戏服务器元宝存在误差 ThirdParty:" + gold + " GameServer:" + d.getGold() + " Error:" + (gold - d.getGold()));
	}

	@Override
	public int getGold() {
		if (goldUpdateTimer.isTimeUp()) {
			updateGoldFromThirdParty();
			goldUpdateTimer.restart();
		}
		UserData dto = lazyGet();
		int gold = dto.getGold();
		int coupon = dto.getCoupon();
		return gold + coupon;
	}

	@Override
	public void reduceGold(int value) {

		int goldOld = lazyGet().getGold();
		int couponOld = lazyGet().getCoupon();

		int old = getGold();

		checkEnouph(value);

		int rechargeGoldNeed = reduceGiftGold(value); // 尽量扣, 返回扣除后 还需要扣除多少

		if (rechargeGoldNeed > 0) {

			lazyGet().addGold(-rechargeGoldNeed); 
		}
		
		int systemGoldNeed = value - rechargeGoldNeed;
		
		if(ThirdPartyPlatformFactory.getThirdPartyPlatform().isPlatformUserId(getId())) {
			notifyThirdParty(rechargeGoldNeed, systemGoldNeed, goldOld, couponOld);
		}

		commit();

		dispatchEvent(old);
	}

	private void dispatchEvent(int old) {

		int now = getGold();

		PlayerPropertyChangeEvent event = new PlayerPropertyChangeEvent(city, this, null, true, old, now);

		Events.getInstance().dispatch(event);
	}

	private void checkEnouph(int value) {
		if (value > getGold()) {
			throw new OperationFaildException(S.S10007);
		}
	}

	private void notifyThirdParty(int rechargeGoldNeed, int systemGoldNeed, int goldOld, int couponOld) {
		try {

			// 通知第三方合作平台
			ThirdPartyPlatform p = ThirdPartyPlatformFactory.getThirdPartyPlatform();
			ThirdPartyRole r = ThirdPartyPlatformFactory.createRole(city);
			Debuger.debug("PlayerImpl.notifyThirdParty()		 " + rechargeGoldNeed);
			p.pay(r, rechargeGoldNeed, systemGoldNeed);
			Debuger.debug("PlayerImpl.notifyThirdParty() success " + rechargeGoldNeed);
			
		} catch (Throwable e) {

			// 回滚
			lazyGet().setGold(goldOld);
			lazyGet().setCoupon(couponOld);
			Debuger.error(e);
			e.printStackTrace();
			throw new ThirdPartyException(e.getMessage());
		}
	}

	@Override
	public int getVipLevel() {
		return city.getVipPlayer().getLevel();
	}

	@Override
	public int getDanId() {
		return city.getNewPvpManager().getPlayer().getDan();
	}

	@Override
	public int getTotalRechargeGold() {
		return city.getUserCounterHistory().get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT);
	}

	@Override
	public int getMainFighterTypeId() {
		return city.getTeam().getPlayer().getTypeId();
	}

	@Override
	public int getShenJia() {
		int shenJia = city.getFormation().getShenJia();
		return shenJia;
	}

	@Override
	public int getPhysicalMax() {
		return D.MAX_PHYSICAL + getLevelPhysicalAdd() + getVipPhysicalAdd();
	}

	private int getVipPhysicalAdd() {
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) getVipLevel());
		return temp.getVigorLimit();
	}

	private int getLevelPhysicalAdd() {
		AuthorityTemplet temp = AuthorityTempletConfig.get(getLevel());
		return temp.getVigorLimit();
	}
	
	public int getPowerMax() {
		return D.MAX_POWER + getLevelPowerAdd() + getVipPowerAdd();
	}

	private int getVipPowerAdd() {
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) getVipLevel());
		return temp.getPowerLimit();
	}

	private int getLevelPowerAdd() {
		AuthorityTemplet temp = AuthorityTempletConfig.get(getLevel());
		return temp.getPowerLimit();
	}

	@Override
	public int getGiftGold() {
		UserData dto = lazyGet();
		return dto.getCoupon();
	}

	@Override
	public int getRechargeGold() {
		UserData dto = lazyGet();
		return dto.getGold();
	}
}
