package cn.vgame.b.account;

import cn.javaplus.util.Util;
import cn.vgame.b.Server;
import cn.vgame.b.bag.Bag;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.MongoMap;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.mission.Mission;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.KeyValue;
import cn.vgame.share.KeyValueSaveOnly;

public class Role implements IRole {

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
	private Bag bag;
    private Mission mission;
	private String zoneId;
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

	public long getMasonry() {
		return dto.getMasonry();
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
		KeyValueForever kv = new KeyValueForever();
		return kv;
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


	public boolean hasJinYan() {
		return dto.getHasJinYan();
	}

	public boolean hasFengHao() {
		return dto.getHasFengHao();
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

	public long getCoinAll() {
		long coin = getCoin() + getMasonry();
		if (coin < 0)
			return getCoin();
		return coin;
	}

	public Bag getBag() {
		if (bag == null)
			bag = new Bag(this);
		return bag;
	}
	
	public Mission getMission() {
		if (mission == null)
			mission = new Mission(this);
		return mission;
	}


	public void logout() {
		dto.setIsOnline(false);
		RoleDao dao = Daos.getRoleDao();
		dao.save(dto);
	}

	public void addRechargeHistory(long add) {
		dto.setRechargeHistory(dto.getRechargeHistory() + add);
		Daos.getRoleDao().save(dto);
	}

	public int getHead() {
		return dto.getHead();
	}
//	PHYSICAL_MAX
//	PHYSICAL_ADD_RATE
//	PHYSICAL_ADD_EVERY_TIMES

	public int getPhysical() {
		int physicalNeedAdd = calcPhysicalNeedAdd();
		if(physicalNeedAdd != 0) {
			dto.setPhysical(physicalNeedAdd + dto.getPhysical());
			int max = Server.getConst().getInt("PHYSICAL_MAX");
			
			if(dto.getPhysical() > max)
				dto.setPhysical(max);
			
			Daos.getRoleDao().save(dto);

			getKeyValueSaveOnly().set("LAST_ADD_PHYSICAL_TIME", System.currentTimeMillis());
		}
		return dto.getPhysical();
	}

	private int calcPhysicalNeedAdd() {
		long last = getKeyValueForever().getLong("LAST_ADD_PHYSICAL_TIME");
		long now = System.currentTimeMillis();
		int rateSec = Server.getConst().getInt("PHYSICAL_ADD_RATE");
		int add = Server.getConst().getInt("PHYSICAL_ADD_EVERY_TIMES");
		
		
		return (int) ((now - last) / 1000 / rateSec * add);
	}
	public long getRechargeHistory() {
		return dto.getRechargeHistory();
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	public String getZoneId() {
		return zoneId;
	}
}
