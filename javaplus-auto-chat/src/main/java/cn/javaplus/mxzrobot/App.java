package cn.javaplus.mxzrobot;

import iqq.im.QQActionListener;
import iqq.im.QQClient;
import iqq.im.QQException;
import iqq.im.WebQQClient;
import iqq.im.actor.ThreadActorDispatcher;
import iqq.im.bean.QQMsg;
import iqq.im.bean.QQStatus;
import iqq.im.core.QQConstants;
import iqq.im.event.QQActionEvent;
import iqq.im.event.QQActionEvent.Type;
import iqq.im.event.QQNotifyEvent;
import iqq.im.event.QQNotifyEventArgs;
import iqq.im.event.QQNotifyHandler;
import iqq.im.event.QQNotifyHandlerProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.javaplus.file.IProperties;
import cn.javaplus.graphics.ImageFrame;
import cn.javaplus.mxzrobot.events.LoginOKEvent;
import cn.javaplus.mxzrobot.events.ReceiveMessageEvent;
import cn.javaplus.util.Util;
import cn.mxz.events.Events;

/**
 * Hello world!
 */
public class App {

	private final class QQActionListenerImpl implements QQActionListener {

		public void onActionEvent(QQActionEvent event) {

			System.out.println("登陆结果:" + event.getType());
			if (event.getType() == Type.EVT_OK) {

				client.beginPollMsg();
				
				Events.getInstance().dispatch(new LoginOKEvent(client));
			}
		}
	}

	QQClient client;

	public App(String user, String pwd) {
		Events.getInstance().loadListeners("cn.javaplus.mxzrobot.listeners");
		QQNotifyHandlerProxy proxy = new QQNotifyHandlerProxy(this);
		ThreadActorDispatcher dispatcher = new ThreadActorDispatcher();
		client = new WebQQClient(user, pwd, proxy, dispatcher);
	}

	/**
	 * 程序入口
	 */
	public static void main(String[] args) {
		IProperties p = Util.Property.getProperties("properties.properties");
		App test = new App(p.getProperty("QQ"), p.getProperty("PWD"));
		test.login();
	}

	/**
	 * 聊天消息通知，使用这个注解可以收到QQ消息 接收到消息然后组装消息发送回去
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.CHAT_MSG)
	public void processBuddyMsg(QQNotifyEvent event) throws QQException {
		Object t = event.getTarget();

		QQMsg msg = (QQMsg) t;

		Events.getInstance().dispatch(new ReceiveMessageEvent(msg));
	}

	/**
	 * 被踢下线通知
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.KICK_OFFLINE)
	protected void processKickOff(QQNotifyEvent event) {
		System.out.println("被踢下线: " + (String) event.getTarget());
	}

	/**
	 * 需要验证码通知
	 */
	@QQNotifyHandler(QQNotifyEvent.Type.CAPACHA_VERIFY)
	protected void processVerify(QQNotifyEvent event) throws IOException {
		Object t = event.getTarget();
		QQNotifyEventArgs.ImageVerify verify = (QQNotifyEventArgs.ImageVerify) t;
		ImageFrame image = new ImageFrame(verify.image);
		System.out.println(verify.reason);
		System.out.print("请输入验证码:");
		InputStreamReader r = new InputStreamReader(System.in);

		String code = new BufferedReader(r).readLine();
		client.submitVerify(code, event);
		image.dispose();
	}

	/**
	 * 登录
	 */
	public void login() {
		final QQActionListener listener = new QQActionListenerImpl();

		String ua = "Mozilla/5.0 (@os.name; @os.version; @os.arch) AppleWebKit/537.36 (KHTML, like Gecko) @appName Safari/537.36";
		ua = ua.replaceAll("@appName", QQConstants.USER_AGENT);
		ua = ua.replaceAll("@os.name", System.getProperty("os.name"));
		ua = ua.replaceAll("@os.version", System.getProperty("os.version"));
		ua = ua.replaceAll("@os.arch", System.getProperty("os.arch"));
		client.setHttpUserAgent(ua);
		client.login(QQStatus.ONLINE, listener);
	}
}
