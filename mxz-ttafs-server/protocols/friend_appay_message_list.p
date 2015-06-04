package cn.mxz.protocols.user.friend;

import "apply.p";

import "war_situation.p";


message ApplyListPro {

	required int32 pageNow = 3;
	required int32 pageAll = 4;
	repeated ApplyPro applyMessage = 5;
}

message FriendWarSituationPro {
	required cn.mxz.protocols.user.battle.WarSituationPro situation = 12399;

}
