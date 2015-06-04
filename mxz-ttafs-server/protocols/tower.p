//协议主要关键字说明:
//required:必须的		(有且仅有1个值) 	生成的类中, 可以根据getXXX方法获取到对应的成员,且该属性不可能为null
//repeated:多次出现的	(0-n个值) 			生成的类中, 该成员为一个 列表List(Java) 或 数组Array(AS3)
//optional:可有可无的	(0或1个值)			生成的类中, 该成员可能为空, 可通过hasXXX()方法判定是否有该成员

package cn.mxz.protocols.user.tower;

import "user_base.p";

message TowerPro {

	optional TowerBugPro bug = 40;	//裂缝
}

//裂缝协议
message TowerBugPro{

	required int32 remainSec = 40;

	required bool isMine = 50;		//是不是我触发的裂缝

	required UserBasePro finder = 60;//裂缝发现者
}

message FightingRewardPro {

	required int32 score = 10;	//打到奖励

	required int32 catchScore = 20;	//捕获奖励
}