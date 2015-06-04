//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.battle.FightingLoseEvent;
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//
//import com.google.common.collect.Lists;
//
//@Listen(eventClass = FightingLoseEvent.class)
//public class CallHelperListener implements Listener {
//
//	private Boss	boss;
//
//	CallHelperListener(Boss boss) {
//		this.boss = boss;
//	}
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		callHelper(boss); // 向其他玩家求助
//
//	}
//
//	/**
//	 * 自动分配给其他玩家进行攻击
//	 * 
//	 * @param boss
//	 */
//	private void callHelper(Boss boss) {
//
//		if (isFoundJustNow(boss)) { // 如果Boss是刚才才发现的
//
//			// 分配给20个
//
//			random20(boss);
//
//		} else {
//
//			// 让玩家继续打
//		}
//	}
//
//	/**
//	 * 是否是刚被发现的boss
//	 * 
//	 * @param boss
//	 * @return
//	 */
//	private boolean isFoundJustNow(Boss boss) {
//
//		return boss.getBossChallengers().size() <= 1;
//	}
//
//	/**
//	 * 将该Boss随机分配给20个Boss活动中的玩家进行攻击
//	 * 
//	 * @param boss
//	 */
//	private void random20(Boss boss) {
//
//		BossActivity activity = BossActivityImpl.getInstance();
//
//		List<BossChallenger> ls = Lists.newArrayList(activity.getAll());
//
//		List<BossChallenger> bossChallengers = boss.getBossChallengers();
//
//		ls.removeAll(bossChallengers);
//
//		Util.upset(ls);
//
//		boss.changeHelper(Util.sub(ls, 20));
//	}
//
//}
