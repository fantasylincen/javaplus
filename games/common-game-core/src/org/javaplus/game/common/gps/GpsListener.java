package org.javaplus.game.common.gps;

public interface GpsListener {

	/**
	 * GPS位置发生变化时
	 * @param l
	 */
	void onChanged(GpsLocation l);

	/**
	 * 当前GPS状态为可见状态
	 */
	void onAvalilable();

	/**
	 * 当前GPS状态为服务区外状态
	 */
	void onOutOfService();

	/**
	 * 当前GPS状态为暂停服务状态
	 */
	void onTemporarilyUnavailable();

	/**
	 * GPS开启时
	 */
	void onProviderEnabled();

	/**
	 * GPS禁用时
	 */
	void onProviderDisabled();

	/**
	 * gps出错时
	 * @param message
	 */
	void onError(String message);
}
