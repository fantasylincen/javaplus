package cn.mxz.util.message;

import java.util.Collection;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;


/**
 * 发送给所有玩家的消息发送器 (顶部文字消息提示)
 *
 * @author 	林岑
 * @time	2013年8月15日 10:49:40
 */
public class MessageSenderToAllUp {

	/**
	 * 发送消息
	 * @param code  消息号
	 * @param infos 各个动态字符串
	 */
	public void sendMessage(int code, Object... infos) {
		World w = WorldFactory.getWorld();
		Collection<City> all = w.getOnlineAll();
		for (City city : all) {
			city.getNoticeSender().send(code, infos);
		}
	}

}
