package cn.mxz.protocols.user.loginReward;

import "prop.p";

message LoginRewardPro {

	//领取到的奖励列表
	required cn.mxz.protocols.user.PropsPro received = 9844;

	//没有领取到的奖励列表
	required cn.mxz.protocols.user.PropsPro unReceived = 14365;


	//连续登陆天数
	required int32 continuousDay = 19458;

}

message RewardListPro {
	required bool reqardTypeThree = 10;
	required bool reqardTypeTen = 20;
	required bool reqardTypeFifteen = 30;
	required bool reqardTypeThirty = 40;
}