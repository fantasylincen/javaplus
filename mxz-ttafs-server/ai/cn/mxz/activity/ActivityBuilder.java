//package cn.mxz.activity;
//
//import java.util.List;
//
//import cn.mxz.BossEventIntegralTemplet;
//import cn.mxz.BossEventIntegralTempletConfig;
//import cn.mxz.activity.boss.BossActivityImpl;
//import cn.mxz.activity.fish.FishActivityImpl;
//import cn.mxz.activity.friend.ActivityFriendManager;
//import cn.mxz.activity.tower.TowerActivityImpl;
//import cn.mxz.base.prize.Prize;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.protocols.user.activity.ActivityP.ActivityPro;
//import cn.mxz.rankinglist.RankingListImpl;
//import cn.mxz.user.City;
//import cn.mxz.util.debuger.Debuger;
//
//public class ActivityBuilder {
//
//	ActivityPro build(City city) {
//
//
//		RankingListImpl instance = RankingListImpl.getInstance();
//
//		int rank = instance.getRank(city.getId(), PlayerProperty.ACTIVITY_SCORE);
//
//		ActivityPro.Builder b = ActivityPro.newBuilder();
//
//		b.setRank(rank);
//
//		// 下一奖励积分
//		ActivityFriendManager activityFriendManager = city.getActivityFriendManager();
//
//		int findNextScore = activityFriendManager.findNextScore();
//
//		Debuger.debug("ActivityBuilder.build() 下一奖励积分为:" +  findNextScore);
//		
//		b.setNextScore(findNextScore);
//
//		int value = city.getPlayer().get(PlayerProperty.ACTIVITY_SCORE);
//
//		b.setScore(value);
//
//		setPropReward(findNextScore, b);
//
//		b.setIsFishActivityStart(FishActivityImpl.getInstance().isStart());
//
//		b.setIsBossStart(BossActivityImpl.getInstance().isStart());
//
//		b.setIsTowerStart(TowerActivityImpl.getInstance().isStart());
//
//
//		return b.build();
//	}
//
//	private void setPropReward(int findNextScore, ActivityPro.Builder b) {
//
//		BossEventIntegralTemplet t = BossEventIntegralTempletConfig.get(findNextScore);
//
//		if(t != null) {
//
//			PrizeSender p = PrizeSenderFactory.getPrizeSender();
//
//			List<Prize> list = p.buildPrizes(t);
//
//			for (Prize prize : list) {
//
//				b.setReward(new PropBuilder().build(prize.getId(), prize.getCount()));
//			}
//		} else {
//
//			Debuger.debug("ActivityBuilder.setPropReward() findNextScore = " + findNextScore);
//		}
//	}
//
//}
