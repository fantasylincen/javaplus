




package cn.mxz.protocols.user;


message LevelUpRewardPro {

	repeated LevelUpRewardItemPro rewards = 10;

	message LevelUpRewardItemPro {

	//是否领取了
		required bool hasReceived = 10;

	//等级
		required int32 level = 20;
	}
}