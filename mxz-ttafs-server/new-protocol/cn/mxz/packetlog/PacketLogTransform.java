package cn.mxz.packetlog;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface PacketLogTransform {

	/**
	 * 记录用户操作了一次某个功能
	 * @param packetName	操作名字
	 */
	void add(String packetName);

	void setUser(City city);
}
