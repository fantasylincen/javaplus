package cn.javaplus.clickscreen;

import org.javaplus.clickscreen.script.ScriptExcutor;
import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;

/**
 * 系统
 * 
 * @author 林岑
 * 
 */
public interface OS {

	int getPlayTimes();

	void sendMessage(String text);

	void exit();

	TwoLegsHttpClient getHttp();

	Ads getAds();

	Logger getLogger();

	Configs getConfigs();

	Share getShare();

	IPreferences getPreferences();

	/**
	 * 系统启动时调用
	 */
	void create();

	RemoteResources getRemoteResources();

	// ScriptManagerAsync getScriptMangerAsync();

	HttpClient getHttpBase();

	ScriptExcutor getScript();

	// ScriptManager getScriptManger();
}
