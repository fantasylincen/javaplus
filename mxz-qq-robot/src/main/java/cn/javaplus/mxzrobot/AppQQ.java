//package cn.javaplus.mxzrobot;
//
//import iqq.im.QQActionListener;
//import iqq.im.QQClient;
//import iqq.im.QQException;
//import iqq.im.WebQQClient;
//import iqq.im.actor.ThreadActorDispatcher;
//import iqq.im.bean.QQMsg;
//import iqq.im.bean.QQStatus;
//import iqq.im.bean.QQUser;
//import iqq.im.bean.content.TextItem;
//import iqq.im.core.QQConstants;
//import iqq.im.event.QQActionEvent;
//import iqq.im.event.QQActionEvent.Type;
//import iqq.im.event.QQNotifyEvent;
//import iqq.im.event.QQNotifyEventArgs;
//import iqq.im.event.QQNotifyHandler;
//import iqq.im.event.QQNotifyHandlerProxy;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import cn.javaplus.chatrobot.ChatRobot;
//import cn.javaplus.chatrobot.Mouth;
//import cn.javaplus.chatrobot.RobotDispatcher;
//import cn.javaplus.graphics.ImageFrame;
//import cn.javaplus.mxzrobot.db.Daos;
//import cn.javaplus.mxzrobot.db.MongoCollectionFetcher;
//import cn.javaplus.mxzrobot.recorder.ChatRecorderImpl;
//import cn.mxz.events.Events;
//
//import com.alibaba.fastjson.JSON;
//
///**
// * Hello world!
// */
//public class AppQQ {
//
//	public class MouthImpl implements Mouth {
//
//		private QQUser from;
//
//		public MouthImpl(QQUser from) {
//			this.from = from;
//		}
//
//		/*
//		 * (non-Javadoc)
//		 * 
//		 * @see cn.javaplus.qqrobot.IMouth#say(java.lang.String)
//		 */
//		public void say(String message) {
//			QQMsg sendMsg = new QQMsg();
//			sendMsg.setTo(from);
//			sendMsg.setType(QQMsg.Type.BUDDY_MSG);
//			sendMsg.addContentItem(new TextItem(message));
//			client.sendMsg(sendMsg, null);
//		}
//	}
//
//	private final class QQActionListenerImpl implements QQActionListener {
//
//		public void onActionEvent(QQActionEvent event) {
//
//			if (event.getType() == Type.EVT_OK) {
//
//				client.beginPollMsg();
//				System.out.println("登陆成功");
//			} else {
//				Exception target = (Exception) event.getTarget();
//				target.printStackTrace();
//				System.err.println("登陆失败" + target);
//			}
//		}
//	}
//
//	QQClient client;
//
//	RobotDispatcher dispatcher;
//
//	public AppQQ(String user, String pwd) {
//		QQNotifyHandlerProxy proxy = new QQNotifyHandlerProxy(this);
//		ThreadActorDispatcher dispatcher = new ThreadActorDispatcher();
//		client = new WebQQClient(user, pwd, proxy, dispatcher);
//		this.dispatcher = new RobotDispatcher();
//	}
//
//	/**
//	 * 程序入口
//	 */
//	public static void main(String[] args) {
//		Daos.setCollectionFetcher(new MongoCollectionFetcher());
//		Events.getInstance().loadListeners("cn.javaplus.mxzrobot.listeners");
//		AppQQ test = new AppQQ("2241431137", "258819045");
////		App test = new App("2832288524", "258819045");
//		test.login();
//	}
//
//	/**
//	 * 聊天消息通知，使用这个注解可以收到QQ消息 接收到消息然后组装消息发送回去
//	 */
//	@QQNotifyHandler(QQNotifyEvent.Type.CHAT_MSG)
//	public void processBuddyMsg(QQNotifyEvent event) throws QQException {
//		Object t = event.getTarget();
//
//		QQMsg msg = (QQMsg) t;
//		String content = msg.packContentList();
//		com.alibaba.fastjson.JSONArray array = JSON.parseArray(content);
//
//		QQUser from = msg.getFrom();
//
//		ChatRobot robot = dispatcher.getRobot(from.getUin());
//		robot.setRecorder(new ChatRecorderImpl());
//		robot.setMouth(new MouthImpl(from));
//		String message = array.get(1).toString();
//
//		robot.hear(message);
//	}
//
//	/**
//	 * 被踢下线通知
//	 */
//	@QQNotifyHandler(QQNotifyEvent.Type.KICK_OFFLINE)
//	protected void processKickOff(QQNotifyEvent event) {
//		System.out.println("被踢下线: " + (String) event.getTarget());
//	}
//
//	/**
//	 * 需要验证码通知
//	 */
//	@QQNotifyHandler(QQNotifyEvent.Type.CAPACHA_VERIFY)
//	protected void processVerify(QQNotifyEvent event) throws IOException {
//		Object t = event.getTarget();
//		QQNotifyEventArgs.ImageVerify verify = (QQNotifyEventArgs.ImageVerify) t;
//		ImageFrame image = new ImageFrame(verify.image);
//		System.out.println(verify.reason);
//		System.out.print("请输入验证码:");
//		InputStreamReader r = new InputStreamReader(System.in);
//
//		String code = new BufferedReader(r).readLine();
//		client.submitVerify(code, event);
//		image.dispose();
//	}
//
//	/**
//	 * 登录
//	 */
//	public void login() {
//		final QQActionListener listener = new QQActionListenerImpl();
//
//		String ua = "Mozilla/5.0 (@os.name; @os.version; @os.arch) AppleWebKit/537.36 (KHTML, like Gecko) @appName Safari/537.36";
//		ua = ua.replaceAll("@appName", QQConstants.USER_AGENT);
//		ua = ua.replaceAll("@os.name", System.getProperty("os.name"));
//		ua = ua.replaceAll("@os.version", System.getProperty("os.version"));
//		ua = ua.replaceAll("@os.arch", System.getProperty("os.arch"));
//		client.setHttpUserAgent(ua);
//		client.login(QQStatus.ONLINE, listener);
//	}
//}
