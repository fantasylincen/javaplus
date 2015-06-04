




package cn.mxz.protocols.user.friend;

import "apply.p";

import "user_base.p";


message FriendsAllListPro {

	required int32 friendOnline = 1;
	required int32 friendCount = 2;
	required int32 pageNow = 3;
	required int32 pageAll = 4;
	repeated FriendsAllPro friends = 5;

}

message FriendsAllPro {


	required string nick = 10;
	required bool isMan = 20;
	required int32 level = 30;
	required int32 type = 40;
	required int32 lastLogin = 50;
	required string userId = 60;
	required int32 lot = 70;
	required bool isPractice = 90;
	required int32 vipLevel = 123123;
	required UserBasePro base = 100;
	required bool hasNewMessage = 111;

}


message FriendSendAndReceiveListPro {

	required int32 pageNow = 3;
	required int32 pageAll = 4;
	repeated ApplyPro friends = 5;
}


message FriendReceiveListPro {

	required int32 pageNow = 3;
	required int32 pageAll = 4;

	repeated ApplyPro friends = 5;
}

