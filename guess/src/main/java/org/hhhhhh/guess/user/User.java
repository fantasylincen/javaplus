package org.hhhhhh.guess.user;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.exception.GuessException;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.util.KeyValue;

import cn.javaplus.util.Util;

public class User {

	private final UserDto dto;

	public User(UserDto dto) {
		this.dto = dto;
	}

	public String getUsername() {
		return dto.getUsername();
	}

	public String getNick() {

		if (!isNullOrEmpty(dto.getNick())) {
			return dto.getNick();
		}
		return dto.getUsername();
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public int getJiFen() {
		return dto.getJiFen();
	}

	public UserDto getDto() {
		return dto;
	}

	public void reduceJiFen(int jiFenNeed) {
		if (jiFenNeed < 0)
			throw new IllegalArgumentException(jiFenNeed + "");

		checkEnouph(jiFenNeed);
		dto.setJiFen(dto.getJiFen() - jiFenNeed);
		Daos.getUserDao().save(dto);
	}

	private void checkEnouph(int jiFenNeed) {
		if (dto.getJiFen() < jiFenNeed)
			throw new GuessException("积分不够");
	}

	public void addJiFen(int jiFenAdd) {
		if (jiFenAdd <= 0)
			throw new IllegalArgumentException(jiFenAdd + "");
		dto.setJiFen(dto.getJiFen() + jiFenAdd);
		UserDao dao = Daos.getUserDao();
		dao.save(dto);
	}

	public String getPassword() {
		return dto.getPassword();
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
		public String getString(Object key) {
			if (key == null)
				throw new NullPointerException();
			KeyValue kv = Server.getKeyValueForever();
			String ss = kv.getString(buildKey(key));
			return ss;
		}

		private String buildKey(Object key) {
			return getUsername() + ":" + key.toString();
		}

		@Override
		public void set(Object key, Object value) {
			KeyValue kv = Server.getKeyValueForever();
			kv.set(buildKey(key), value);
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
		public String getString(Object key) {
			KeyValue kv = getKeyValueForever();
			String buildKey = buildKey(key);
			String ss = kv.getString(buildKey);
			return ss;
		}

		@Override
		public void set(Object key, Object value) {
			KeyValue kv = getKeyValueForever();
			String buildKey = buildKey(key);
			kv.set(buildKey, value.toString());
		}
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
}