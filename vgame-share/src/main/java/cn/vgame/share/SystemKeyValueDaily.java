package cn.vgame.share;

import cn.javaplus.util.Util;

public class SystemKeyValueDaily implements KeyValue {

	private final ISystemKeyValueDao dao;

	public SystemKeyValueDaily(ISystemKeyValueDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void set(Object key, Object value) {
		ISystemKeyValueDto dto = dao.createDTO();
		dto.setKey(key.toString() + ":" + Util.Time.getCurrentFormatDay());
		dto.setValue(value.toString());
		dao.save(dto);
	}

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
		ISystemKeyValueDto dto = dao.get(key.toString() + ":"
				+ Util.Time.getCurrentFormatDay());
		if (dto == null)
			return null;
		return dto.getValue();
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
