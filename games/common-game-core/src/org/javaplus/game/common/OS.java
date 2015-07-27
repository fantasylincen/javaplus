package org.javaplus.game.common;

import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;

/**
 * 系统
 * 
 * @author 林岑
 * 
 */
public interface OS {

	Gps getGps();

	int getPlayTimes();

	void sendMessage(String text);

	void exit();

	TwoLegsHttpClient getHttp();

	Ads getAds();

	Logger getLogger();

	Configs getConfigs();

	Share getShare();

	IPreferences getPreferences();

	String getValue(Object key);

	Channel getChannel();

	/**
	 * 系统启动时调用
	 */
	void create();

	RemoteResources getCache();

//	ScriptManagerAsync getScriptMangerAsync();

	HttpClient getHttpBase();

//	ScriptManager getScriptManger();
}
