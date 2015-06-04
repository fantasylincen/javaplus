package cn.vgame.a.turntable;

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
		return this.turntable.getCd();
	}

}