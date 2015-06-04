package cn.mxz.protocols.user.mission;

import "prop.p";
import "user.p";
import "equipment.p";
import "fighter.p";


message MissionPro {

	required int32 missionId = 10;
	optional int32 personPath = 12;
	optional int32 personIndex = 13;
	optional bool canJumpBranch = 14;	
	repeated MissionNodePro nodes = 110;
	
}
//巧遇
message RandomAddFriendPro{
	required cn.mxz.protocols.user.UserPro user= 1;
	repeated cn.mxz.protocols.user.god.FighterPro god= 2;
}
message BoxDataPro {
	required int32 id = 30;
	required int32 path = 10;
	required int32 index = 20;
	
}

message RandomPro {
	required int32 id = 30;
	required int32 path = 10;
	required int32 index = 20;
}

message DemonPro {
	required int32 id = 30;
	required int32 path = 10;
	required int32 index = 20;
}
//1、空节点，2、战斗事件,3、开宝箱，4、随机事件
message MissionNodePro{
	required int32 arg = 30;
	required int32 path = 10;
	required int32 index = 20;
	required int32 type = 40;
}


import "war_situation.p";


//副本战斗结果信息
message MissionWarSituationPro {

	//副本奖励
	required MissionPrizePro prize = 9;
	required cn.mxz.protocols.user.battle.WarSituationPro warSituation = 10;
}


message MissionPrizePro {
	repeated FighterPrizePro prizes = 10;
	repeated PropPro props = 20;
	required int32 star = 30;
	message FighterPrizePro{
		required int32 fighter_id = 10;
		required int32 expAdd = 20;
		required int32 expNeed = 21;
		required int32 levelAdd = 30;
	}	
}


message MissionChallengePro{
		required int32 todayChallengeCount = 10;//当日挑战次数,主线
		required int32 todayResetRoundCount = 20;//当日重置次数,主线
		required int32 todayChallengeCount1 = 30;//当日挑战次数,支线
		required int32 todayResetRoundCount1 = 40;//当日重置次数,支线
}
