package cn.mxz.system;

import java.util.Set;

import cn.mxz.city.City;
import cn.mxz.handler.PacketDefine;

import com.google.common.collect.Sets;

/**
 * 消息发送器开关
 * @author 林岑
 *
 */
public class MessageSenderSwitch {

	private static final Set<Integer>	ALL	= Sets.newHashSet();

	//加到ALL里面的通信包, 消息发送器都不会发送任何消息
	static {
		ALL.add(PacketDefine.MissionOpenChest);
	}

	/**
	 * 关闭用户MessageSender
	 *
	 * @param user
	 */
	public static void close(City user) {
		user.getMessageSender().close();
	}

	public static boolean contains(int id) {
		return ALL.contains(id);
	}

	/**
	 * 开启用户MessageSender
	 *
	 * @param user
	 */
	public static void open(City user) {
		user.getMessageSender().open();
	}

}
