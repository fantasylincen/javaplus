package cn.javaplus.twolegs.events;import org.mxz.events.EventDispatcher;import org.mxz.events.Listener;/** * 系统事件派发器 *  * @author 林岑 */public class Events {	private static EventDispatcher dispatcher = new EventDispatcher();	static {		add(cn.javaplus.twolegs.game.ExitEvent.class, new cn.javaplus.twolegs.game.ShowAds());
		add(cn.javaplus.twolegs.game.GameOverEvent.class, new cn.javaplus.twolegs.ads.ShowPopAds());
		add(cn.javaplus.twolegs.game.GameOverEvent.class, new cn.javaplus.twolegs.game.PopScore());
		add(cn.javaplus.twolegs.game.GameOverEvent.class, new cn.javaplus.twolegs.game.SaveBestScore());
		add(cn.javaplus.twolegs.game.GameOverEvent.class, new cn.javaplus.twolegs.game.SaveLog());
		add(cn.javaplus.twolegs.game.GameOverEvent.class, new cn.javaplus.twolegs.game.SavePlayTimes());
		add(cn.javaplus.twolegs.game.GameStartEvent.class, new cn.javaplus.twolegs.game.GameStartLoadAssets());
		add(cn.javaplus.twolegs.game.GetBestScoreEvent.class, new cn.javaplus.twolegs.score.CommitScore());
	}	/**	 * 	 * 派发一个事件	 * 	 * 注意如果用该方式读取的Listener的子类, 一定要注意这个侦听是系统级侦听, 所有客户端会共享这一个侦听	 * 所以侦听中, 不要有成员变量	 * @param e	 */	public static void dispatch(Object e) {		dispatcher.dispatch(e);	}		@SuppressWarnings({ "unchecked", "rawtypes", "unused" })	private static  void add(Class<?> c, Listener listener) {		dispatcher.addListener(c, listener);	}}