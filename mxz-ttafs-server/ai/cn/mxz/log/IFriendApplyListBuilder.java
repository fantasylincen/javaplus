package cn.mxz.log;

import cn.mxz.protocols.user.friend.FirendListP.FriendReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendSendAndReceiveListPro;
import cn.mxz.protocols.user.friend.FirendListP.FriendsAllListPro;
import cn.mxz.protocols.user.friend.FriendAppayMessageListP.ApplyListPro;
import cn.mxz.protocols.user.friend.QueryFriendAppayP.QueryListPro;

public interface IFriendApplyListBuilder {

	FriendsAllListPro buildFriendAllList(int page);

	QueryListPro buildQueryMessage(String userMes, int page);

	FriendSendAndReceiveListPro buildApplyRefuseMessage(int page);

	FriendReceiveListPro buildReceiveMessage(int page);

	ApplyListPro buildApplyMessage(int page);

}