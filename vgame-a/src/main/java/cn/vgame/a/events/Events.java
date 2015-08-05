package cn.vgame.a.events;import cn.javaplus.events.EventDispatcher;import cn.javaplus.events.Listener;/** * 系统事件派发器 *  * @author 林岑 */public class Events {	private static EventDispatcher dispatcher = new EventDispatcher();	static {		add(cn.vgame.a.account.CreateRoleEvent.class, new cn.vgame.a.account.CreateRoleSendCoin());
		add(cn.vgame.a.account.CreateRoleEvent.class, new cn.vgame.a.account.CreateRoleSendLaba());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameCheckHasFengHao());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameCheckIsRobot());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameMarkOnline());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameSaveLastLoginTime());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameSaveRole());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameSaveRoleZoneId());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.login.EnterServerSendLaba());
	}	/**	 * 	 * 派发一个事件	 * 	 * 注意如果用该方式读取的Listener的子类, 一定要注意这个侦听是系统级侦听, 所有客户端会共享这一个侦听	 * 所以侦听中, 不要有成员变量	 * @param e	 */	public static void dispatch(Object e) {		dispatcher.dispatch(e);	}		@SuppressWarnings({ "unchecked", "rawtypes", "unused" })	private static  void add(Class<?> c, Listener listener) {		dispatcher.addListener(c, listener);	}}