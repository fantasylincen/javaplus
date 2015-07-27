package cn.javaplus.twolegs;

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

import cn.javaplus.twolegs.channel.DefaultChannel;
import cn.javaplus.twolegs.metadata.MetaDataGetter;
import cn.javaplus.twolegs.share.ShareWindows;

public class Windows implements OS {

	private IPreferences preferences;
	private Ads ads;
	private Logger logger;
	private Configs configs;
	private Share share;
	private DefaultChannel channel;
	private TwoLegsHttpClient http;
	private HttpClient httpBase;
	private RemoteResources cache;

	// private ScriptManagerAsync scriptManager;
	// private ScriptManager scriptManager2;

	public Windows() {
		configs = new WindowsConfigs();
		ads = new AdsWindows();
		logger = new LoggerWindows();
		httpBase = new HttpAsyncClient(5000);
		http = new TwoLegsHttpClient(httpBase);
		share = new ShareWindows();
		preferences = new PreferencesImpl();
	}

	@Override
	public Gps getGps() {
		return new DesktopGps();
	}

	@Override
	public int getPlayTimes() {
		return 0;
	}

	@Override
	public void sendMessage(String text) {
		Log.d(text);
	}

	@Override
	public void exit() {
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
	public String getValue(Object key) {
		return MetaDataGetter.get(key);
	}

	@Override
	public Channel getChannel() {
		if (channel == null)
			channel = new DefaultChannel();
		return channel;
	}

	@Override
	public void create() {

	}

	@Override
	public RemoteResources getCache() {
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
