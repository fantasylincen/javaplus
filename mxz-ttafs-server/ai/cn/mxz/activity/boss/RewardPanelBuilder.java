//package cn.mxz.activity.boss;
//
//import cn.mxz.protocols.user.boss.BossP.RewardPanelPro;
//import cn.mxz.util.debuger.Debuger;
//
///**
// * Boss奖励面板数据构建器
// * 
// * @author 林岑
// * 
// */
//
//public class RewardPanelBuilder {
//
//	public RewardPanelPro build(BossReward reward) {
//
//		RewardPanelPro.Builder b = RewardPanelPro.newBuilder();
//
//		int attackReward = reward.getAttackReward();
//
////		Debuger.debug("RewardPanelBuilder.build() 攻击奖励:" + attackReward);
//
//		b.setAttackReward(attackReward);
//
//		int value = (int) (reward.getContinuous() * 10);
//
////		Debuger.debug("RewardPanelBuilder.build() 连击:" + value);
//
//		b.setContinuousX(value);
//
//		int damageReward = reward.getDamageReward();
//
////		Debuger.debug("RewardPanelBuilder.build() 伤害奖励:" + damageReward);
//
//		b.setDamageReward(damageReward);
//
//		int value2 = (int) (reward.getFriendAddition() * 10);
//
////		Debuger.debug("RewardPanelBuilder.build() 好友奖励:" + value2);
//
//		b.setFriendAdditionX(value2);
//
//		int mvp = (int) (reward.getMvp() * 10);
//
//		b.setMvp(mvp);
//
////		Debuger.debug("RewardPanelBuilder.build() MVP奖励:" + mvp);
//
//		String mvpOrJMVP = reward.getMVPOrJMVP();
//
//		b.setMvpOrJmvp(mvpOrJMVP);
//
////		Debuger.debug("RewardPanelBuilder.build() MVP OR JMVP:" + mvpOrJMVP);
//
//		int friendCount = reward.getFriendCount();
//
////		Debuger.debug("RewardPanelBuilder.build() 好友数量:" + mvpOrJMVP);
//
//		b.setFriendCount(friendCount);
//
//		b.setChallengerCount(reward.getChallengerCount());
//
////		Debuger.debug("RewardPanelBuilder.build() 挑战者数量:" + reward.getChallengerCount());
//
//		b.setScoreRewardId(reward.getScoreRewardId());
//
////		Debuger.debug("RewardPanelBuilder.build() 积分奖励ID:" + reward.getScoreRewardId());
//
//		return b.build();
//	}
//
//}
