package cn.vgame.a.turntable;

/**
 * 系统库存发生变化时
 */
public class KuCunUpdateEvent {

	private final long now;

	public KuCunUpdateEvent(long now) {
		this.now = now;
	}

	/**
	 * 现在的库存
	 */
	public long getKuCunNow() {
		return now;
	}

}
