package cn.mxz.listeners.chat;

import java.net.URLEncoder;
import java.util.HashMap;

import cn.javaplus.http.HttpRequester;
import cn.javaplus.http.HttpRespons;
import cn.javaplus.util.Util;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.chat.ChatMonitorResponseChecker;
import cn.mxz.chat.Message;
import cn.mxz.city.City;
import cn.mxz.config.ConfigProperties;
import cn.mxz.events.Listener;
import cn.mxz.events.chat.ReceiveUserChatEvent;
import cn.mxz.task.TaskQueue;
import cn.mxz.task.TaskQueueManager;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Maps;
import com.linekong.platform.protocol.erating.define.D;

public class SendUserChatToEratingListener implements
		Listener<ReceiveUserChatEvent> {

	TaskQueue tasks = new TaskQueue();

	@Override
	public void onEvent(final ReceiveUserChatEvent e) {
//		ThirdPartyPlatform ee = ThirdPartyPlatformFactory
//				.getThirdPartyPlatform();
//		
//		if (!ee.isPlatformUserId(e.getSender().getId())) {
//			return;
//		}
//		
//		if (!ee.isPlatformUserId(e.getReceiver().getId())) {
//			return;
//		}
		
		TaskQueue tasks = TaskQueueManager.getQueue();
		tasks.push(new Runnable() {

			@Override
			public void run() {
				sendToErating(e);
			}

		});

		if (!tasks.isRunning()) {
			tasks.start();
		}
	}

	private void sendToErating(ReceiveUserChatEvent e) {
		Message message = e.getMessage();
		City sender = e.getSender();
		City receiver = e.getReceiver();

		HttpRequester request = new HttpRequester();
		HttpRespons hr;
		try {
			HashMap<String, String> ps = Maps.newHashMap();
			
			ps.put("gameId",  getGameId(e) + "");
			ps.put("gatewayId",  ServerConfig.getServerId() + "");
			ps.put("channelId",  "-1");
			ps.put("fromRoleId",  getIdInteger(sender.getId()) + "");
			ps.put("fromRoleName",  encode(sender.getPlayer().getNick()) + "");
			ps.put("toRoleId",  getIdInteger(receiver.getId()));
			ps.put("toRoleName",  encode(receiver.getPlayer().getNick()));
			ps.put("message",  encode(getMessage(message)));
			ps.put("msgDate",  System.currentTimeMillis() / 1000 + "");
			ps.put("union",  "-1");
			ps.put("key",  encode(getKey()));
			
//			gameId	String	游戏ID（必须）
//gatewayId	String	网关ID(必须)
//channelId	String	频道ID (必须) 
//fromRoleId	String	发送信息的角色ID(必须)
//fromRoleName	String	发送信息的角色名称,并且进行URLEncoder转码，字符集utf-8
//（必须）
//toRoleId	String	接收方的角色ID（必须）
//toRoleName	String	接收方的角色名称,并且进行URLEncoder转码，字符集utf-8
//（必须）
//message	String	发送的消息内容,并且进行URLEncoder转码，字符集utf-8
//（必须）
//msgDate	String	发送的时间，时间为UNIX时间戳精确到秒（必须）
//union	String	公会ID，并且进行URLEncoder转码，字符集utf-8（可选）
//key	String	MD5(gameId+gatewayId+双方协定值)
//
//1.2.2	返回数据格式和结果标识
//返回数据的格式是json格式
//{"result":"value"}
//Value值对应的错误信息
//          返回value值	             对应信息
//-100	Key值认证错误
//-101	服务端没有配置接收该游戏日志信息
//1	服务端接收信息成功
			
			hr = request.sendGet(ConfigProperties.getString("chatMonitorUrl"), ps);

			String content = hr.getContent();
			new ChatMonitorResponseChecker().check(content);
			
		} catch (Throwable e1) {
			Debuger.error("发送私聊消息到Erating时 出现故障!", e1);
//			throw new RuntimeException(e1);
		}
	}

	private long getGameId(ReceiveUserChatEvent e) {
		try {
			return e.getSender().getGameIdManager().getGameId();
		} catch (Exception e1) {
			return D.GAME_ID;
		}
	}

	private String getMessage(Message message) {
		String[] split = message.getMessage().split("\\|");
		return split[split.length - 1];
	}

	private String getIdInteger(String string) {
		if(string.matches("[0-9]+")) {
			return string;
		}
		return string.hashCode() + "";
	}

	@SuppressWarnings("deprecation")
	private String encode(String t) {
		return URLEncoder.encode(t);
	}

	private String getKey() {
		return Util.Secure.md5(D.GAME_ID + "" + ServerConfig.getServerId() + ConfigProperties.getString("chatMonitorKey"));
	}

}
