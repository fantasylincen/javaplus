package cn.javaplus.time.coolingtimer;

import cn.javaplus.util.Cup;
import cn.javaplus.util.CupFullException;

/**
 * 冷却时间控制器,
 * 你可以把他想象成一个杯子, 这个杯子每毫秒会丢失1个单位的水
 * 
 * @author 	林岑
 * @since	2013年1月6日 15:03:08
 *
 */
public class CDCup implements Cup{

	private final long capacity;

	private	LastTimeRecorder lastTimeRecorder;

	/**
	 * @param capacity	杯子容量
	 */
	public CDCup(LastTimeRecorder lt, long capacity) {
		this.capacity = capacity;
		this.lastTimeRecorder = lt;
	}

	/**
	 * @param water	放入水的容量
	 */
	@Override
	public void putWater(long water) {
		if(canPut(water)) {
			if(isEmpty()) {
				lastTimeRecorder.setLastTime(System.currentTimeMillis() - getCapacity() + water);
			} else {
				lastTimeRecorder.setLastTime(lastTimeRecorder.getLastTime() + water);
			}
		} else {
			throw new CupFullException("放入的水太多了!");
		}
	}

	@Override
	public boolean isEmpty() {
		return getHaveNow() == 0;
	}

	@Override
	public long getCapacity() {
		return capacity;
	}

	@Override
	public boolean canPut(long water) {
		return getFree() >= water;
	}
	
	@Override
	public long getHaveNow() {
		return getCapacity() - getFree() ;
	}
	
	private long getFree() {
		long free = System.currentTimeMillis() - lastTimeRecorder.getLastTime();
		if(free < 0 || free > getCapacity()) {
			return getCapacity();
		}
		return free;
	}
}
