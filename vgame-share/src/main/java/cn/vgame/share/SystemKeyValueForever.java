package cn.vgame.share;


public class SystemKeyValueForever implements KeyValue {

	private final ISystemKeyValueDao dao;

	public SystemKeyValueForever(ISystemKeyValueDao dao) {
		this.dao = dao;
	}
	@Override
	public void set(Object key, Object value) {
		ISystemKeyValueDto dto = dao.createDTO();
		dto.setKey(key.toString());
		dto.setValue(value.toString());
		dao.save(dto);
	}

	@Override
	public long getLong(Object key) {
		return new Long(getString(key, "0"));
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
		ISystemKeyValueDto dto = dao.get(key.toString());
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
