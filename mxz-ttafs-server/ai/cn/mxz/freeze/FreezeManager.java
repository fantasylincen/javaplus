package cn.mxz.freeze;

import message.S;
import cn.mxz.city.City;
import cn.mxz.generator.MessageTempletConfig;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

/**
 * 禁止用户登陆, 禁止发言等等
 * @author 林岑
 *
 */
public class FreezeManager {

	private City	city;

	public FreezeManager(City city) {
		this.city = city;
	}

	public boolean isFreeze() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.FREEZE);
	}

	public String getReason() {
		return MessageTempletConfig.get(S.S10198).getContent();
	}

	/**
	 * 封禁
	 */
	public void freeze() {
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.FREEZE);
	}

	/**
	 * 解除封禁
	 */
	public void unFreeze() {
		UserCounter his = city.getUserCounterHistory();
		his.clear(CounterKey.FREEZE);
	}

}
