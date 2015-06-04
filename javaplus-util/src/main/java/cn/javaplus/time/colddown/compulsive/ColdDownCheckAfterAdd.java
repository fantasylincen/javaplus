package cn.javaplus.time.colddown.compulsive;

import java.util.ArrayList;
import java.util.List;

import cn.javaplus.time.colddown.ColdDownBase;
import cn.javaplus.time.colddown.ColdDownListener;




/**
 * 强制冷却时间, 该冷却时间在检测的时候, 是根据cdAdd加上之后的时间来判定是否freezing
 * @author 	林岑
 * @since	2013年2月16日 17:37:29
 */
public abstract class ColdDownCheckAfterAdd extends ColdDownBase {

	private List<ColdDownListener> coldDownListener = new ArrayList<ColdDownListener>();

	/**
	 * 总计所需冷却时间(毫秒)
	 */
	protected int timeFreezing;

	/**
	 * @param cdEndTime		结束时间
	 * @param timeFreezing	冻结时间
	 * @param addEveryTime	每次调用add方法所增加的时间
	 *
	 * 三个参数单位均为毫秒
	 */
	public ColdDownCheckAfterAdd(long cdEndTime, int timeFreezing, int addEveryTime) {

		super(cdEndTime, addEveryTime);

		this.endTime = cdEndTime;

		this.timeFreezing = timeFreezing;
	}

	/**
	 * 冻结时间
	 *
	 * 单位毫秒
	 *
	 */
	@Override
	public long getTimeFreezing() {

		return timeFreezing;
	}

	@Override
	public boolean isFreezing() {

		return getRemainingMillis() >= getTimeFreezing();
	}

	@Override
	public final void addListener(ColdDownListener cl) {

		this.coldDownListener.add(cl);
	}
}
