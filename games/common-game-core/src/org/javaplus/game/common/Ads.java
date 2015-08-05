package org.javaplus.game.common;

import org.javaplus.game.common.game.ShowListener;


public interface Ads {
	
	void show(ShowListener listener);

	boolean isShowing();

	boolean isInitOver();

	void showPopAds();
}
