package com.linekong.platform.protocol.erating;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;
import cn.mxz.base.config.ServerConfig;

/**
 * 蓝港服务器密码获取器
 * @author 林岑
 *
 */
public class LineKongServerPassword {

	private static IProperties	properties;
	private static String password;

	/**
	 * 获得某个服务器ID 对应的密码
	 * @param serverId
	 * @return
	 */
	public static String get(int serverId) {
		if(password != null) {
			return password;
		}
		if (properties == null) {
			properties = Util.Property.getProperties("config/passwords.properties");
		}
		return properties.getProperty(serverId + "");
	}

	public static String getPassword() {
		return get(ServerConfig.getServerId());
	}

	public static void init(String[] args) {
		for (String string : args) {
			if(string != null && string.startsWith("-pwd:")) {
				password = string.replaceAll("-pwd:", "").trim();
			}
		}
	}

}
