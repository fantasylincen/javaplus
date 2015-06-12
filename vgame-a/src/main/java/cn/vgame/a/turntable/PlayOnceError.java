package cn.vgame.a.turntable;

import cn.javaplus.log.Log;

public class PlayOnceError {

	/**
	 * 
	 */
	private final Turntable turntable;
	private final String error;

	public PlayOnceError(Turntable turntable, String error) {
		this.turntable = turntable;
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public long getCd() {
		
		long cd = this.turntable.getCd();
		Log.d("error request", cd);
		return cd;
	}

}