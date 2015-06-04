
package cn.mxz.protocols.user.activity;

import "prop.p";

message ActivityPro {

	required int32 score = 10;

	required int32 rank = 20;

	required int32 nextScore = 30;			//下一奖励积分

	optional PropPro reward = 40;
	
	required bool isTowerStart = 50;		//爬塔活动是否开始
	
	required bool isBossStart = 60;			//Boss活动是否开始
	
	required bool isFishActivityStart = 70;	//钓鱼活动是否开始
}