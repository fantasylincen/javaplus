package cn.javaplus.twolegs.android;

import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.Gps;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.OS;
import org.javaplus.game.common.PreferencesImpl;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.cache.BaiduBucketResources;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.http.HttpAsyncClient;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Util;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.widget.Toast;
import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.channel.ChannelExp10086Cn;
import cn.javaplus.twolegs.channel.DefaultChannel;
import cn.javaplus.twolegs.wxapi.ShareAndroid;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.update.UmengUpdateAgent;

public class AndroidOS implements OS {

	private Activity activity;
	private TwoLegsHttpClient http;
	private IPreferences preferences;
	private Ads ads;
	private Logger logger;
	private Configs configs;
	private Share share;
	private Channel channel;
	private RemoteResources cache;
	// private ScriptManagerAsync scriptManager;
	private HttpClient httpBase;

	// private ScriptManager scriptManager2;

	public AndroidOS(Activity activity) {
		this.activity = activity;

		configs = new UmengConfigs(activity);
		logger = new UmengLogger(activity);
		ads = new WanPuAds(activity);
		// ads = new GuangDianTongAds(activity);
		share = new ShareAndroid(activity);
		httpBase = new HttpAsyncClient(5000);
		http = new TwoLegsHttpClient(httpBase);

		preferences = new PreferencesImpl();
	}

	@Override
	public Gps getGps() {
		return new AndroidGps(activity);
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
	public TwoLegsHttpClient getHttp() {
		return http;
	}

	@Override
	public HttpClient getHttpBase() {
		return httpBase;
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
	public String getValue(Object key) {

		Object value = null;

		PackageManager packageManager = activity.getPackageManager();

		ApplicationInfo applicationInfo;

		try {

			applicationInfo = packageManager.getApplicationInfo(activity

			.getPackageName(), 128);

			if (applicationInfo != null && applicationInfo.metaData != null) {

				value = applicationInfo.metaData.get(key.toString());

			}

		} catch (NameNotFoundException e) {

			throw new RuntimeException(

			"Could not read the name in the manifest file.", e);

		}

		if (value == null) {

			throw new RuntimeException("The name '" + key

			+ "' is not defined in the manifest file's meta data.");

		}

		return value.toString();

	}

	@Override
	public Channel getChannel() {

		if (channel == null) {
			channel = createChannel();
		}
		return channel;
	}

	private Channel createChannel() {

		String um = AnalyticsConfig.getChannel(activity);
		Log.d("AndroidLauncher", "getChannel", um);
		if ("10086.cn".equals(um)) {
			return new DefaultChannel();
		}
		if ("10086.cn-exp".equals(um)) {
			return new ChannelExp10086Cn();
		}
		if ("360.cn".equals(um)) {
			return new DefaultChannel();
		}
		if ("wandoujia.com".equals(um)) {
			return new DefaultChannel();
		}
		if ("qq.com".equals(um)) {
			return new DefaultChannel();
		}

		throw new RuntimeException("无法识别的渠道:" + um);
	}

	@Override
	public void create() {

		Util.Thread.runOnThread(new Runnable() {
			public void run() {
				App.getLogger().onCountEvent("start");
				logInstall();
				UmengUpdateAgent.update(activity); // 检查更新
			}
		});

		activity.runOnUiThread(new Runnable() {
			public void run() {
				App.getShare().init();
			}
		});
	}

	private void logInstall() {
		if (isRunFirst()) {
			App.getLogger().onCountEvent("install");
			markRun();
		}
	}

	private static final String INSTALL_MARK = "has-install";

	private void markRun() {
		App.getPreferences().put(INSTALL_MARK, "yes");
	}

	private boolean isRunFirst() {
		String str = App.getPreferences().getString(INSTALL_MARK);
		return "yes".equals(str);
	}

	@Override
	public RemoteResources getCache() {
		if (cache == null)
			cache = new BaiduBucketResources(getHttpBase());
		return cache;
	}
	//
	// @Override
	// public ScriptManagerAsync getScriptMangerAsync() {
	// if (scriptManager == null)
	// scriptManager = new AndroidScriptManager(activity);
	// return scriptManager;
	// }
	//
	//
	// @Override
	// public ScriptManager getScriptManger() {
	// if (scriptManager2 == null)
	// scriptManager2 = new DroidScriptManager();
	// return scriptManager2;
	// }
}
