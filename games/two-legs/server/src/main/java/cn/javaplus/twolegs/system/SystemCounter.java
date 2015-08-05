package cn.javaplus.twolegs.system;

import java.util.HashMap;
import java.util.Map;

import cn.javaplus.twolegs.mongo.MongoGen;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.SystemCounterDao;
import cn.javaplus.twolegs.mongo.MongoGen.SystemCounterDto;
import cn.javaplus.twolegs.mongo.MongoGen.SystemCounterDao.SystemCounterDtoCursor;

/**
 * 系统级计数器
 * 
 * @author 林岑
 *
 */
public class SystemCounter {

	private Map<String, SystemCounterDto> cache = new HashMap<String, MongoGen.SystemCounterDto>();

	SystemCounter() {
		loadCache();
	}

	private void loadCache() {
		SystemCounterDao dao = Daos.getSystemCounterDao();
		SystemCounterDtoCursor all = dao.find();
		for (SystemCounterDto s : all) {
			cache(s);
		}
	}

	private void cache(SystemCounterDto s) {
		String key = s.getKey();
		cache.put(key, s);
	}

	/**
	 * 增加某个键 对应的值
	 * 
	 * @param key
	 * @param count
	 */
	public void add(Key key, int count) {
		int old = get(key);
		set(key, count + old);
	}

	/**
	 * 设置某个键 对应的值
	 * 
	 * @param key
	 * @param count
	 */
	public void set(Key key, int count) {
		SystemCounterDto dto = getDto(key);
		dto.setCount(count);
		SystemCounterDao dao = Daos.getSystemCounterDao();
		dao.save(dto);
		cache(dto);
	}

	/**
	 * 获取某个键 对应的值
	 * 
	 * @param key
	 */
	public int get(Key key) {
		SystemCounterDto c = getDto(key);
		return c.getCount();
	}

	/**
	 * 不可能为null
	 * 
	 * @param key
	 * @return
	 */
	private SystemCounterDto getDto(Key key) {
		SystemCounterDao dao = Daos.getSystemCounterDao();

		String k = key + "";

		SystemCounterDto dto = cache.get(k);
		if (dto != null) {
			return dto;
		}

		SystemCounterDto c = dao.get(k);
		if (c == null) {
			c = init(k);
			cache(c);
		}
		return c;
	}

	private SystemCounterDto init(String k) {
		SystemCounterDto c;
		c = new SystemCounterDto();
		c.setKey(k);
		Daos.getSystemCounterDao().save(c);
		return c;
	}

}
