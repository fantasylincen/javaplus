
package cn.mxz.protocols.user.task;

import "fraction.p";

message TaskListPro {
	
	repeated TaskPro tasks = 10;	
	
	//宝箱信息
	optional TaskBoxRewardPro boxReward = 111;
}

message TaskPro {

	required int32 id = 1;
	
	//进度
	required FractionPro schedule = 10;
	
	//任务状态:  0进行中 1可领取 2已领取
	required int32 state = 20;
}


message TaskBoxRewardPro {

	//3个宝箱
	repeated TaskBoxPro boxes = 111;
	
	//当前积分
	required int32 score = 89;
	
	//最大积分
	required int32 maxScore = 99;
}


message TaskBoxPro {

	//是否打开
	required bool isOpen = 11;
	
	//是否可领取
	required bool canReceive = 12;
	
	//该任务对应的活动是否开启
	required bool isActivityOpen = 21;
}