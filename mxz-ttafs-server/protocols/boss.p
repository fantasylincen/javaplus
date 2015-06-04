package cn.mxz.protocols.user.boss;

import "user_base.p";
import "fraction.p";

message BossListPro {

	repeated BossLabelPro list = 10;

	message BossLabelPro {

		required int32 id = 9;

		required int32 templetId = 10;

		required int32 level = 20;

		required UserBasePro finder = 30;

		required int32 remainSec = 40;

		required int32 status = 50;
		
		required bool isSuperBoss = 80;
	}
}



message BossPro {

	//BOSS唯一ID
	required int32 id = 9;

	//配置表中的ID
	required int32 templetId = 10;

	required int32 level = 20;

	required int32 remainSec = 30;

	repeated ChallengerPro challengers = 50;
	
	repeated ChallengerPro topData = 51;

	required FractionPro hp = 60;

	required int32 multiple = 70;
	
	required bool isSuperBoss = 80;


	message ChallengerPro {

		required bool isKiller = 9;

		required int32 rank = 10;

		required int32 damage = 11;

		required UserBasePro user = 20;
		
		//类型 1:我 2:MVP 3:JMVP 4:击杀者
		required int32 type = 21;
	}
}


message RewardPanelPro {

	required int32 damageReward = 10;	//伤害奖励

	required int32 attackReward = 20;	//攻击奖励

	required string mvpOrJmvp = 21;	//value == "MVP" || "JMVP"
	
	required int32 mvp = 30;			//MVP||JMVP奖励 if(x3.5) ==> value = 35

	required int32 challengerCount = 40;//连击奖励(value) :当前攻击BOss的人数
	
	required int32 continuousX = 41;		//连击奖励倍数 if(x3.5) ==> value = 35

	required int32 friendCount = 42;	//活动好友加成(value)
	
	required int32 friendAdditionX = 50;	//活动好友加成倍数 if(x3.5) ==> value = 35
	
	required int32 scoreRewardId = 60;		//BossEventIntegralVo的ID, 如果没有获得积分奖励, 返回-1
}


message FriendPointPro {

	required int32 all = 10;

	repeated FriendItemPro friends = 20;

	message FriendItemPro {

		required int32 rank = 10;

		required int32 all = 11;

		required int32 shareToMe = 12;

		required UserBasePro user = 20;
	}
}