package cn.javaplus.time.colddown.compulsive;

/**
 * 强制冷却时间, 该冷却时间在检测是否freezing的时候, 是根据cdAdd加上之前的时间来判定
 */
public abstract class ColdDownCheckBeforAdd  extends ColdDownCheckAfterAdd {

	public ColdDownCheckBeforAdd(long cdEndTime, int cdAll, int addEveryTime) {
		super(cdEndTime, cdAll, addEveryTime);
	}

	@Override
	public boolean isFreezing() {
		return getRemainingMillis() >= timeFreezing - addEveryTime;
	}
}
