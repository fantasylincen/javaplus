package org.hhhhhh.guess.util;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.KeyValueDao;
import org.hhhhhh.guess.hibernate.dto.KeyValueDto;

public class SystemKeyValueForever implements KeyValue {

	public static void main(String[] args) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.createDTO();
		dto.setK("1");
		dto.setV("2");
		dao.save(dto);
	}
	
	@Override
	public void set(Object key, Object value) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.createDTO();
		dto.setK(key.toString());
		dto.setV(value.toString());
		dao.save(dto);
	}

	@Override
	public long getLong(Object key) {
		String string = getString(key, "0");
		return new Long(string);
	}

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
	public String getString(Object key) {
		KeyValueDao dao = Daos.getKeyValueDao();
		KeyValueDto dto = dao.get(key.toString());
		if (dto == null)
			return null;
		return dto.getV();
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

}
