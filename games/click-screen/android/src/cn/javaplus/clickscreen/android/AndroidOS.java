package cn.javaplus.clickscreen.android;

import org.javaplus.clickscreen.script.RhinoScriptExcutor;
import org.javaplus.clickscreen.script.ScriptExcutor;
import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.PreferencesImpl;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.cache.BaiduBucketResources;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.http.HttpAsyncClient;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;

import android.app.Activity;
import android.os.Process;
import android.widget.Toast;
import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.OS;
import cn.javaplus.clickscreen.share.ShareAndroid;

public class AndroidOS implements OS {

	private Activity activity;
	private IPreferences preferences;
	private Ads ads;
	private Logger logger;
	private Configs configs;
	private Share share;
	private ScriptExcutor script;
	private RemoteResources cache;
	private HttpClient httpBase;
	private TwoLegsHttpClient http;

	public AndroidOS(Activity activity) {
		this.activity = activity;

		configs = new TempConfig();
		logger = new TempLogger();
		ads = new TempAds();

		share = new ShareAndroid(activity);
		preferences = new PreferencesImpl();
		script = new RhinoScriptExcutor(App.getApp(), App.getOs());
		httpBase = new HttpAsyncClient(5000);
		http = new TwoLegsHttpClient(httpBase);
	}

	@Override
	public int getPlayTimes() {
		IPreferences preferences = App.getPreferences();
		int integer = preferences.getTempInt("play-times");
		Log.d("play-times:" + integer);
		return integer;
	}

	@Override
	public void sendMessage(String text) {
		Toast.makeText(activity, text, Toast.LENGTH_LONG).show();
	}

	public Activity getActivity() {
		return activity;
	}

	@Override
	public void exit() {
		Process.killProcess(Process.myPid());
		System.exit(0);
	}

	@Override
	public Ads getAds() {
		return ads;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public Configs getConfigs() {
		return configs;
	}

	@Override
	public Share getShare() {
		return share;
	}

	@Override
	public IPreferences getPreferences() {
		return preferences;
	}

	@Override
	public ScriptExcutor getScript() {
		return script;
	}

	@Override
	public void create() {

	}

	@Override
	public RemoteResources getRemoteResources() {
		if (cache == null)
			cache = new BaiduBucketResources(getHttpBase());
		return cache;
	}

	@Override
	public HttpClient getHttpBase() {
		return httpBase;
	}

	@Override
	public TwoLegsHttpClient getHttp() {
		return http;
	}
}
