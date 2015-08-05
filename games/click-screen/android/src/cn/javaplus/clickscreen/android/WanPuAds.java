package cn.javaplus.clickscreen.android;

import org.javaplus.game.common.Ads;
import org.javaplus.game.common.log.Log;

import android.app.Activity;
import cn.waps.AppConnect;

public final class WanPuAds implements Ads {
	/**
	 * 
	 */
	private final Activity activity;

	/**
	 * @param androidOS
	 */
	public WanPuAds(final Activity activity) {
		this.activity = activity;

		new Thread(new Runnable() {
			public void run() {
				AppConnect.getInstance(activity).initPopAd(activity);
				AppConnect.getInstance(activity).setPopAdBack(true);// 返回键关闭
				Log.d("init ads");
			}
		}).start();

	}


	@Override
	public boolean isShowing() {
		return AppConnect.getInstance(activity).hasPopAd(activity);
	}

	@Override
	public boolean isInitOver() {
		return AppConnect.getInstance(activity).hasPopAd(activity);
	}

	@Override
	public void showPopAds() {
		Log.d("show ads");
		AppConnect.getInstance(activity).showPopAd(activity);
	}

	@Override
	public void show(org.javaplus.game.common.game.ShowListener listener) {

		if (listener == null)
			return;
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				QuitPopAd.getInstance().show(WanPuAds.this.activity);
				AppConnect.getInstance(WanPuAds.this.activity).setPopAdBack(
						true);
			}
		});		
	}
}