//package cn.mxz.log;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.friend.FriendManager;
//import cn.mxz.practice.PracticeLogListBuilder;
//import cn.mxz.prop.equipment.LogSnatchBuilder;
//import cn.mxz.protocols.user.log.LogsP.LogsAll;
//import cn.mxz.pvp.LogsBuilder;
//import cn.mxz.user.City;
//import db.dao.factory.DaoFactory;
//import db.domain.FriendBag;
//import db.domain.UserFriendRequest;
//
//class LogsAllBuilder {
//
//	public LogsAll build(City city) {
//
//		LogsAll.Builder b = LogsAll.newBuilder();
//
//		b.setSnatch(new LogSnatchBuilder().build(1, city));
//
//		DAO2<String, String, UserFriendRequest> d1 = DaoFactory.getUserFriendRequestDAO();
//
//		DAO2<String, String, FriendBag> d2 = DaoFactory.getFriendBagDAO();
//
//		FriendManager fm = city.getFriendManager();
//
//		IFriendApplyListBuilder<FriendBag, UserFriendRequest> builder = new FriendApplyListBuilder<FriendBag, UserFriendRequest>(city, d1, d2, fm);
//
//		b.setFriend(builder.buildReceiveMessage(1));
//
//		b.setPractice(new PracticeLogListBuilder().build(city.getId()));
//
//		b.setPvp(new LogsBuilder().build(city, LogType.PVP));
//
//		return b.build();
//	}
//}
