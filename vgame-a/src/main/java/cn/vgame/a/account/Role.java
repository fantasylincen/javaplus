package cn.vgame.a.account;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;
import cn.vgame.a.Server;
import cn.vgame.a.bag.Bag;
import cn.vgame.a.bank.Bank;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDao;
import cn.vgame.a.gen.dto.MongoGen.CoinLogDto;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.MongoMap;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.receivecoin.CoinStatus;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.a.system.Const;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;

public class Role implements IRole {

	private static final SimpleDateFormat SF = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private final class KeyValueSaveOnlyImplementation implements
			KeyValueSaveOnly {
		@Override
		public void set(Object key, Object value) {
			getKeyValueDaily().set(key, value);
			getKeyValueForever().set(key, value);
		}

		@Override
		public void add(Object key, long add) {
			getKeyValueDaily().add(key, add);
			getKeyValueForever().add(key, add);
		}
	}

	@Override
	public long getJiangQuan() {
		return dto.getJiangQuan();
	}

	public class KeyValueForever implements KeyValue {

		@Override
		public int getInt(Object key) {
			return new Integer(getString(key, "0"));
		}

		@Override
		public double getDouble(Object key) {
			return new Double(getString(key, "0"));
		}

		@Override
		public boolean getBoolean(Object key) {
			return new Boolean(getString(key, "false"));
		}

		@Override
		public long getLong(Object key) {
			return new Long(getString(key, "0"));
		}

		@Override
		public String getString(Object key) {
			MongoMap<String> daily = dto.getKeyValueDaily();
			String ss = daily.get(key.toString());
			return ss;
		}

		private String getString(Object key, String def) {
			String s = getString(key);
			if (s == null)
				return def;
			return s;
		}

		@Override
		public void add(Object key, long add) {
			long value = getLong(key);
			value += add;
			set(key, value);
		}

		@Override
		public void set(Object key, Object value) {
			MongoMap<String> daily = dto.getKeyValueDaily();
			daily.put(key.toString(), value.toString());
			Daos.getRoleDao().save(dto);
		}
	}

	public class KeyValueDaily implements KeyValue {

		@Override
		public int getInt(Object key) {
			return new Integer(getString(key, "0"));
		}

		@Override
		public long getLong(Object key) {
			return new Long(getString(key, "0"));
		}

		@Override
		public double getDouble(Object key) {
			return new Double(getString(key, "0"));
		}

		@Override
		public boolean getBoolean(Object key) {
			return new Boolean(getString(key, "false"));
		}

		@Override
		public String getString(Object key) {
			MongoMap<String> daily = dto.getKeyValueDaily();
			String buildKey = buildKey(key);

			String ss = daily.get(buildKey);
			return ss;
		}

		private String buildKey(Object key) {
			String k = key.toString() + ":" + Util.Time.getCurrentFormatDay();
			return k;
		}

		private String getString(Object key, String def) {
			String s = getString(key);
			if (s == null)
				return def;
			return s;
		}

		@Override
		public void add(Object key, long add) {
			long value = getLong(key);
			value += add;
			set(key, value);
		}

		@Override
		public void set(Object key, Object value) {
			MongoMap<String> daily = dto.getKeyValueDaily();
			String buildKey = buildKey(key);
			daily.put(buildKey, value.toString());
			Daos.getRoleDao().save(dto);
		}
	}

	private final RoleDto dto;
	private Bank bank;
	private Bag bag;

	public long getCreateTime() {
		return dto.getCreateTime();
	}

	public long getLastLoginTime() {
		return getKeyValueForever().getLong("LAST_LOGIN_TIME");
	}

	public Role(RoleDto dto) {
		this.dto = dto;
	}

	public String getId() {
		return dto.getId();
	}

	public String getOwnerId() {
		return dto.getOwnerId();
	}

	public String getNick() {
		return dto.getNick();
	}

	public long getCoin() {
		return dto.getCoin();
	}

	public void reduceCoin(long reduce) {
		if (reduce == 0)
			return;

		long coin = getCoin();
		if (reduce < 0 || reduce > coin) {
			throw new ErrorResult(10030).toException();
		}
		dto.setCoin(dto.getCoin() - reduce);
		Daos.getRoleDao().save(dto);

		KeyValueSaveOnly save = getKeyValueSaveOnly();
		save.add("COIN_OUT", reduce);
	}

	/**
	 * 喇叭数量
	 * 
	 * @return
	 */

	public long getLaBa() {
		return getBag().getCount(10001);
	}

	public void addCoin(long add) {
		if (add == 0)
			return;

		if (add < 0 || add + dto.getCoin() <= 0) {
			throw new ErrorResult(20001, add).toException();
		}
		dto.setCoin(dto.getCoin() + add);
		Daos.getRoleDao().save(dto);

		KeyValueSaveOnly save = getKeyValueSaveOnly();
		save.add("COIN_IN", add);
	}

	public long getBankCoin() {
		return dto.getBankCoin();
	}

	public long getCd() {
		long cd = Turntable.getInstance().getCd();
		return cd;
	}

	/**
	 * 银行密码状态 0 未设置安全密码 1 已设置安全密码 2 强制解锁中
	 * 
	 * @return
	 */
	public int getBankPasswordStatus() {
		return getBank().getBankPasswordStatus();
	}

	/**
	 * 银行密码强制解锁cd
	 * 
	 * @return
	 */
	public int getBankPasswordCd() {
		return getBank().getBankPasswordCd();
	}

	public String getBankPassword() {
		return getBank().getBankPassword();
	}

	/**
	 * 每日清空的值存储器
	 */
	public KeyValue getKeyValueDaily() {
		return new KeyValueDaily();
	}

	/**
	 * 值存储器,永久有效
	 */
	public KeyValue getKeyValueForever() {
		return new KeyValueForever();
	}

	/**
	 * 值存储器,只存储, 他会同事存储到每日和永久的存储器里面
	 */
	public KeyValueSaveOnly getKeyValueSaveOnly() {
		return new KeyValueSaveOnlyImplementation();
	}

	public RoleDto getDto() {
		return dto;
	}

	public CoinStatus getCoinStatus() {
		return new CoinStatus(this);
	}

	public void receiveCoin() {
		CoinStatus status = getCoinStatus();
		if (!status.isCanReceive()) {
			throw new ErrorResult(10013).toException();
		}
		long coin = status.getCoin();
		addCoin(coin);
		markReceive();

		addCoinLog(coin, "system", "receive coin");
	}

	public boolean hasJinYan() {
		return dto.getHasJinYan();
	}

	public boolean hasFengHao() {
		return dto.getHasFengHao();
	}

	/**
	 * 标记下次领取时间
	 */
	private void markReceive() {
		KeyValue forever = getKeyValueForever();
		Const c = Server.getConst();
		long space = c.getLong("COIN_RECEIVE_TIME_SPACE");
		forever.set("NEXT_RECEIVE_COIN_TIME", System.currentTimeMillis()
				+ space);
	}

	/**
	 * 判断该玩家是否是一个机器人
	 */
	public boolean isRobot() {
		return dto.getIsRobot();
	}

	public void setRobot(boolean isRobot) {
		dto.setIsRobot(isRobot);
		Daos.getRoleDao().save(dto);
	}

	public void toBank(long coin) {
		getBank().toBank(coin);
	}

	public void fromBank(long coin, String password) {
		getBank().checkPassword(password);
		getBank().fromBank(coin);
	}

	public void sendCoin(String roleId, long coin, String password) {
		getBank().checkPassword(password);
		if (coin < 0 || coin > getCoin()) {
			return;
		}

		Role otherRole = Server.getRole(roleId);
		check(coin, otherRole);
		reduceCoin(coin);
		otherRole.addCoin(coin);
		otherRole.reduceCoin(getPoundage(coin));// 支付手续费

		Log.d("send coin", getId(), getNick(), getCoin(), otherRole.getId(),
				otherRole.getNick(), otherRole.getCoin());

		otherRole.addCoinLog(coin, getId(), "send coin");
	}

	/**
	 * 计算手续费
	 */
	private long getPoundage(long coin) {

		// SEND_GOLD_POUNDAGE 0.01 赠送金币手续费率, 由获得方支付
		// SEND_GOLD_POUNDAGE_MAX 100000 赠送金豆最高手续费

		double SEND_GOLD_POUNDAGE = getDouble("SEND_GOLD_POUNDAGE");
		long SEND_GOLD_POUNDAGE_MAX = getLong("SEND_GOLD_POUNDAGE_MAX");

		double p = coin * SEND_GOLD_POUNDAGE;
		p = Math.min(SEND_GOLD_POUNDAGE_MAX, p);

		return (long) p;
	}

	private void check(long coin, Role otherRole) {
		long otherCoin = otherRole.getCoin();

		if (otherCoin + coin < 0)
			throw new ErrorResult(20002).toException();

		// SEND_GOLD_MIN 10000 赠送金豆 最低额度
		// SEND_GOLD_MAX 100000000 赠送金豆 最高额度
		// SEND_GOLD_UNIT 10000 赠送金豆的单位, 必须为该值的整数倍
		// SEND_GOLD_REMAIN_MIN 10000 赠送金币后, 自身至少应该留多少钱

		long SEND_GOLD_MIN = getLong("SEND_GOLD_MIN");
		long SEND_GOLD_MAX = getLong("SEND_GOLD_MAX");
		long SEND_GOLD_UNIT = getLong("SEND_GOLD_UNIT");
		long SEND_GOLD_REMAIN_MIN = getLong("SEND_GOLD_REMAIN_MIN");

		if (coin < SEND_GOLD_MIN) {
			throw new ErrorResult(10020, SEND_GOLD_MIN).toException();
		}
		if (coin > SEND_GOLD_MAX) {
			throw new ErrorResult(10021, SEND_GOLD_MAX).toException();
		}
		if (coin % SEND_GOLD_UNIT != 0) {
			throw new ErrorResult(10019, SEND_GOLD_UNIT).toException();
		}
		if (getCoin() - coin < SEND_GOLD_REMAIN_MIN) {
			throw new ErrorResult(10018, SEND_GOLD_REMAIN_MIN).toException();
		}
	}

	private double getDouble(String string) {
		return Server.getConst().getDouble(string);
	}

	private long getLong(String k) {
		return Server.getConst().getLong(k);
	}

	public long getCoinAll() {
		long coin = getCoin() + getBankCoin();
		if (coin < 0)
			return getCoin();
		return coin;
	}

	public Bank getBank() {
		if (bank == null)
			bank = new Bank(this);
		return bank;
	}

	public Bag getBag() {
		if (bag == null)
			bag = new Bag(this);
		return bag;
	}

	public void logout() {
		dto.setIsOnline(false);
		RoleDao dao = Daos.getRoleDao();
		dao.save(dto);
	}

	/**
	 * @param add
	 * @param from
	 *            充值渠道
	 */
	public void addRechargeHistory(long add, String from) {
		dto.setRechargeHistory(dto.getRechargeHistory() + add);
		Daos.getRoleDao().save(dto);

		addCoinLog(add, from, "recharge");
	}
	

	@Override
	public void addCoinLog(long coin, Object from, String dsc) {
		CoinLogDao dao = Daos.getCoinLogDao();
		CoinLogDto dto = dao.createDTO();
		Date dt = new Date(System.currentTimeMillis());

		dto.setCoin(coin);
		dto.setId(Util.ID.createId());
		dto.setDsc(dsc);
		dto.setFrom(from + "");
		dto.setTime(SF.format(dt));
		dto.setTo(getId());
		dto.setFromTo(from + "|" + getId());
		dao.save(dto);
	}

	/**
	 * 增加奖券
	 * @param add
	 */
	@Override
	public void addJiangQuan(long add) {
		if(add == 0)
			return;
		if(add < 0)
			add = 0;
		dto.setJiangQuan(dto.getJiangQuan() + add);
		Daos.getRoleDao().save(dto);
	}
	
	public void reduceJiangQuan(int reduce) {
		if(reduce == 0)
			return;
		if(reduce < 0)
			reduce = 0;
		dto.setJiangQuan(dto.getJiangQuan() - reduce);
		Daos.getRoleDao().save(dto);
	}
}
