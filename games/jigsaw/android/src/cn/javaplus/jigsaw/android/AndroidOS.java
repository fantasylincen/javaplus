package cn.javaplus.jigsaw.android;

import org.javaplus.game.common.Ads;
import org.javaplus.game.common.Configs;
import org.javaplus.game.common.Gps;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.Logger;
import org.javaplus.game.common.OS;
import org.javaplus.game.common.PreferencesImpl;
import org.javaplus.game.common.Share;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.channel.Channel;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.HttpClient;
import org.javaplus.game.common.log.Log;

import android.app.Activity;
import android.os.Process;
import android.widget.Toast;
import cn.javaplus.jigsaw.App;
import cn.javaplus.jigsaw.share.ShareAndroid;

public class AndroidOS implements OS {

	private Activity activity;
	private IPreferences preferences;
	private Ads ads;
	private Logger logger;
	private Configs configs;
	private Share share;

	public AndroidOS(Activity activity) {
		this.activity = activity;

		configs = new UmengConfigs(activity);
		logger = new UmengLogger(activity);
		ads = new WanPuAds(activity);

		share = new ShareAndroid(activity);
		preferences = new PreferencesImpl();
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
	public Gps getGps() {
		return null;
	}

	@Override
	public TwoLegsHttpClient getHttp() {
		return null;
	}

	@Override
	public String getValue(Object key) {
		return null;
	}

	@Override
	public Channel getChannel() {
		return null;
	}

	@Override
	public void create() {

	}

	@Override
	public RemoteResources getCache() {
		return null;
	}

	@Override
	public HttpClient getHttpBase() {
		return null;
	}
}
