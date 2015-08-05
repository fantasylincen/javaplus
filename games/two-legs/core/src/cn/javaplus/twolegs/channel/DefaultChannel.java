package cn.javaplus.twolegs.channel;

import org.javaplus.game.common.channel.Channel;

import cn.javaplus.twolegs.App;

public class DefaultChannel implements Channel {

	@Override
	public void exit() {
		App.getOs().exit();
	}

	@Override
	public void gameOver() {
	}

//	@Override
//	public void exit() {
//
//		App.getAds().show(new ShowListener() {
//
//			@Override
//			public void onClose() {
//				App.getOs().exit();
//			}
//		});
//		Log.d("show ads");
//
//		App.getLogger().onCountEvent("showads");
//	}
//
//	@Override
//	public void gameOver() {
//		String value = App.getConfigs().getConfig("ADS_SHOW_ROUNDS", "");
//		String[] split = value.split(",");
//		IPreferences p = App.getPreferences();
//
//		int times = p.getInt("play-times");
//
//		for (String s : split) {
//			if (s.isEmpty()) {
//				continue;
//			}
//			Integer t = new Integer(s);
//			if (t.equals(times)) {
//				App.getAds().showPopAds();
//				return;
//			}
//		}
//	}

}
