package cn.mxz.keyvalue;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 *  服务器提供给客户端的键值数据库
 * @author 林岑
 *
 */
@Communication
public interface KeyValueTransform {

	/**
	 * 放入一个值, 会覆盖之前的值
	 * @param key
	 * @param value
	 */
	void put(String key, String value);
	
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	Value getValue(String key);
	
	void setUser(City user);
}
