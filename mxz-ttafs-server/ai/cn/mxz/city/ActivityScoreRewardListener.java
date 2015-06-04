//package cn.mxz.city;
//
//import cn.javaplus.common.db.DAO;
//import cn.mxz.BossEventIntegralTemplet;
//import cn.mxz.BossEventIntegralTempletConfig;
//import cn.mxz.activity.friend.ActivityFriendManager;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.user.City;
//import cn.mxz.user.Player;
//import db.dao.factory.CacheDaoFactory;
//import db.domain.ActivityScoreReward;
//import db.domain.ActivityScoreRewardImpl;
//
//
///**
// *
// * 赠送活动积分奖励奖励
// *
// * @author 林岑
// *
// */
//class ActivityScoreRewardListener extends PlayerPropertyListener {
//
//	@Override
//	protected void onAddEvent(PlayerProperty playerProperty, PlayerPropertyAddEvent event) {
//
//		if(playerProperty == PlayerProperty.ACTIVITY_SCORE) {
//
//
//			City city = CityFactory.getCity(((Player)event.getSource()).getId());
//
//			ActivityFriendManager manager = city.getActivityFriendManager();
//
//			int nextRewardScore = manager.findNextScore();
//
//			BossEventIntegralTemplet b = BossEventIntegralTempletConfig.get(nextRewardScore);
//
//			if(b != null) {
//
//				PrizeSender s = PrizeSenderFactory.getPrizeSender();
//
//				s.send(city.getPlayer(), b);
//
//				mark(nextRewardScore, city);//标记领取了
//			}
//		}
//	}
//
//
//	private void mark(int nextRewardScore, City city) {
//
//		DAO<String,ActivityScoreReward> DAO = DaoFactory.getCacheActivityScoreRewardDAO();
//
//		ActivityScoreReward r = DAO.get(city.getId());
//
//		if(r == null) {
//
//			r = new ActivityScoreRewardImpl();
//
//			r.setUname(city.getId());
//
//			r.setScores(nextRewardScore + "");
//
//			DAO.add(r);
//
//		} else {
//
//			r.setScores(r.getScores() + "," + nextRewardScore);
//
//			DAO.update(r);
//		}
//	}
//
//
//	@Override
//	public Class<?> getEventListendClass() {
//		return PlayerPropertyAddEvent.class;
//	}
//}
