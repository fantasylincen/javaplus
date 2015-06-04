package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.events.Listener;
import cn.mxz.events.SocketBindEvent;
import cn.mxz.log.Logs;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

// socket 绑定时
public class SocketOnBindListener implements Listener<SocketBindEvent> {

	@Override
	public void onEvent(SocketBindEvent e) {

		City u = e.getUser();

		markLoginLastTime(u);

		addLoginTimes(u);

		addLoginTimesToday(u);

		Logs.loginLog.addLoginLog(u);
	}

	/*
	 * 增加今日累计登陆次数
	 */
	private void addLoginTimesToday(City u) {

		final UserCounterSetter c = u.getUserCounter();

		c.add(CounterKey.USER_LOGIN_TIMES_TODAY, 1);
	}

	/*
	 * 增加累计登陆次数
	 */
	private void addLoginTimes(City u) {

		Player player = u.getPlayer();

		player.add(PlayerProperty.LOGIN_TIMES_HISTORY, 1);
	}

	/*
	 * 标记最后一次登陆时间
	 */
	private void markLoginLastTime(City u) {

		u.setLastLoginSec((int) (System.currentTimeMillis() / 1000));
	}

}
