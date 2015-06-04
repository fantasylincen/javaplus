package cn.mxz.util.cd;

import java.util.List;

import message.S;
import cn.javaplus.time.colddown.ColdDown;
import cn.javaplus.time.colddown.ColdDownListener;
import cn.javaplus.time.colddown.exception.ColdDownAddException;
import cn.mxz.base.exception.OperationFaildException;

import com.google.common.collect.Lists;

public class ColdDownImpl implements ColdDown {

	private final List<ColdDownListener> coldDownListener = Lists
			.newArrayList();

	private long freezing;

	private long end;

	private int every;

	@Override
	public boolean isFreezing() {
		if (getRemainingMillis() == 0)
			return false;
		return getRemainingMillis() >= freezing - getEvery();
	}

	private boolean isTimeUp() {
		return end < System.currentTimeMillis();
	}

	@Override
	public void add() {

		if (isFreezing()) {

			throw new ColdDownAddException("正在冷却中");

		} else if (isTimeUp()) { // 如果时间到了

			end = System.currentTimeMillis();
		}

		end += getEvery();

		for (ColdDownListener c : coldDownListener) {

			c.onAdd();
		}
	}

	@Override
	public void clear() {

		end = System.currentTimeMillis();

		for (ColdDownListener c : coldDownListener) {

			c.onClear();
		}
	}

	@Override
	public long getEndTime() {
		return end;
	}

	/*
	 * 剩余毫秒
	 */
	private long getRemainingMillis() {
		final long m = end - System.currentTimeMillis();
		return m < 0 ? 0 : m;
	}

	@Override
	public int getRemainingSec() {
		final long m = getRemainingMillis();
		return (int) (m / 1000);
	}

	@Override
	public int getRemainingMin() {
		final int sec = getRemainingSec();
		if (sec % 60 == 0) {
			return sec / 60;
		} else {
			return sec / 60 + 1;
		}
	}

	@Override
	public long getTimeFreezing() {
		return freezing;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public void setEndTime(long end) {
		this.end = end;
		for (ColdDownListener c : coldDownListener) {
			c.onSet();
		}
	}

	public void setFreezing(long freezing) {
		this.freezing = freezing;
	}

	@Override
	public void addListener(ColdDownListener cl) {
		this.coldDownListener.add(cl);
	}

	public int getEvery() {
		return every;
	}

	public void setEvery(int every) {
		this.every = every;
	}

	@Override
	public void check() {

		if (isFreezing()) {

			throw new OperationFaildException(S.S10086);
		}
	}
}
