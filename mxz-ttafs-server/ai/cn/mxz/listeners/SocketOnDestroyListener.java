package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.SocketOnDestroyEvent;
import cn.mxz.log.Logs;
import cn.mxz.thirdpaty.ThirdPartyPlatform;
import cn.mxz.thirdpaty.ThirdPartyPlatformFactory;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

//socket 断开时
public class SocketOnDestroyListener implements Listener<SocketOnDestroyEvent> {

	@Override
	public void onEvent(SocketOnDestroyEvent e) {

		City city = e.getUser();

		if (city != null) {

			notifyEratingLogOut(city);

			city.freeMemory();

			saveOnlineTime(city);

			Logs.loginLog.addLoginOutLog(city);
		}
	}

	/**
	 * 通知Erating服务器玩家下线
	 * 
	 * @param city
	 */
	private void notifyEratingLogOut(City city) {
		if (city.getPlayer().isThirdPartyPlayer()) {
			ThirdPartyPlatform c = ThirdPartyPlatformFactory
					.getThirdPartyPlatform();
			c.logout(ThirdPartyPlatformFactory.createRole(city));
		}
	}

	/**
	 * 记录在线时长
	 * 
	 * @param city
	 */
	private void saveOnlineTime(City city) {

		UserCounterSetter his = city.getUserCounterHistory();

		long lastTime = city.getLastLoginMillis();
		
		if (lastTime == 0) {
			
			return;
		}

		int sec = getOnlineTime(lastTime);
		
		Debuger.debug("SocketOnDestroyListener.saveOnlineTime() 本次在线时长:" + sec + " 秒");

		his.add(CounterKey.ONLINE_TIME, sec);

		city.getUserCounter().add(CounterKey.ONLINE_TIME, sec);
	}

	/**
	 * 
	 * @param lastTime
	 * @return
	 */
	private int getOnlineTime(long lastTime) {

		long onlineTime = System.currentTimeMillis() - lastTime;

		int i = (int) (onlineTime / 1000);

		return i;
	}

}
