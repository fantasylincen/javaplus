package cn.vgame.a.turntable;

/**
 * 系统库存从 正数变成负数, 或者从负数变成正数时
 */
public class KuCunToZeroEvent {

	private final long now;

	public KuCunToZeroEvent(long now) {
		this.now = now;
	}

	/**
	 * 当前库存
	 */
	public long getKuCunNow() {
		return now;
	}
}
