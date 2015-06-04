package cn.mxz.fish;

import com.lemon.commons.socket.ISocket;
import com.lemon.commons.user.IUser;

/**
 * 渔夫
 * @author 	林岑
 * @time	2013-5-14
 */
class Fisher implements IUser {

	private static final long STOP = -1;

	private IUser user;

	/**
	 * 玩家进入等待状态的时间
	 */
	private long waitStartTime = STOP;

	//	/**
	//	 * 玩家进入挂机状态的时间
	//	 */
	//	private long hookingStartTime = STOP;

	private boolean isAutoBuy;

	
	public Fisher(IUser user) {
		this.user = user;
	}

	@Override
	public String getId() {
		return user.getId();
	}

	@Override
	public ISocket getSocket() {
		return user.getSocket();
	}

	/**
	 * 是否正在等待鱼儿上钩
	 * @return
	 */
	public boolean isWaiting() {
		return waitStartTime != STOP;
	}
	//
	//	/**
	//	 * 是否正在挂机
	//	 * @return
	//	 */
	//	public boolean isOnHook() {
	//		return hookingStartTime != STOP;
	//	}

	/**
	 * 开始钓鱼(进入等待状态)
	 */
	void startWait() {

		//		stopHook();

		this.waitStartTime = System.currentTimeMillis();
	}

	/**
	 * 结束等待状态
	 */
	private void stopWait() {
		this.waitStartTime = STOP;
	}
	//
	//	/**
	//	 * 停止挂机
	//	 */
	//	public void stopHook() {
	//		this.hookingStartTime = STOP;
	//	}

	/**
	 * 收获
	 */
	public void fetch() {

		stopWait();

		//		stopHook();
	}

	//	/**
	//	 * 开始挂机钓鱼
	//	 * @param isAutoBuy
	//	 * @param baitId
	//	 */
	//	public void startHook(boolean isAutoBuy) {
	//
	//		stopWait();
	//
	//		this.setAutoBuy(isAutoBuy);
	//
	//		this.hookingStartTime = System.currentTimeMillis();
	//	}

	public boolean isAutoBuy() {
		return isAutoBuy;
	}

	public void setAutoBuy(boolean isAutoBuy) {
		this.isAutoBuy = isAutoBuy;
	}

	//	/**
	//	 * 已挂机时长
	//	 * @return	毫秒
	//	 */
	//	public long getHookingMiles() {
	//
	//		if(!isOnHook()) {
	//
	//			return 0;
	//		}
	//
	//		final long miles = System.currentTimeMillis() - hookingStartTime;
	//
	//		return miles <= 0 ? 0 : miles;
	//	}

	/**
	 * 获得等待时长
	 * @return
	 */
	public long getWaitMiles() {

		if(!isWaiting()) {

			return 0;
		}

		final long miles = System.currentTimeMillis() - waitStartTime;

		return miles <= 0 ? 0 : miles;
	}

	@Override
	public int getLevel() {

		return user.getLevel();
	}
}
