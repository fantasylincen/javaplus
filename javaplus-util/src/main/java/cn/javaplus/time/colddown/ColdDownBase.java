package cn.javaplus.time.colddown;

import cn.javaplus.time.colddown.exception.ColdDownAddException;


public abstract class ColdDownBase implements ColdDown {

	/**
	 * cd结束时间(1970至今毫秒数)
	 */
	protected long endTime;

	/**
	 * 每次调用add方法, endTime增加值
	 */
	protected final int addEveryTime;

	protected ColdDownBase(long cdEndTime, int cdAdd) {
		this.addEveryTime = cdAdd;
		this.endTime = cdEndTime;
	}

	@Override
	public void add() {

		if(isFreezing()) {
			throw new ColdDownAddException("正在冷却中");
		} else if(isTimeUp()) {	//如果时间到了
			endTime = System.currentTimeMillis();
		}
		endTime += addEveryTime;
		updateEndTime();
	}

	private boolean isTimeUp() {
		return endTime < System.currentTimeMillis();
	}

	@Override
	public void clear() {
		endTime = System.currentTimeMillis();
		updateEndTime();
	}

	@Override
	public long getEndTime() {
		return endTime;
	}

	/*
	 * 剩余毫秒
	 */
	protected long getRemainingMillis() {
		long m = endTime - System.currentTimeMillis();
		return m < 0 ? 0 : m;
	}

	@Override
	public int getRemainingSec() {
		long m = getRemainingMillis();
		return (int) (m / 1000);
	}

	@Override
	public int getRemainingMin() {
		int sec = getRemainingSec();
		if(sec % 60 == 0) {
			return sec / 60;
		} else {
			return sec / 60 + 1;
		}
	}

	@Override
	public void check() {

		if(isFreezing()) {

			throw new ColdDownFreezingException();
		}
	}

	/***
	 * 在add和clear后, 回调该方法
	 */
	protected abstract void updateEndTime();
}
