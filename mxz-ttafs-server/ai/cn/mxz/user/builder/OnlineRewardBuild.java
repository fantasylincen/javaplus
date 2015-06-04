//package cn.mxz.user.builder;
//
//import cn.mxz.onlinereward.OnlineRewardBox;
//import cn.mxz.onlinereward.OnlineRewardManager;
//import cn.mxz.protocols.user.UserP.OnlineReward;
//
//public class OnlineRewardBuild {
//
//	public OnlineReward build(OnlineRewardManager manager) {
//		OnlineRewardBox box = manager.getBoxCanReceive();
//		if(box == null) {
//			return null;
//		}
//		OnlineReward.Builder b = OnlineReward.newBuilder();
//		b.setAwards(box.getReward());
//		b.setDuration(box.getDuration());
//		b.setId(box.getId());
//		return b.build();
//	}
//
//
//}
