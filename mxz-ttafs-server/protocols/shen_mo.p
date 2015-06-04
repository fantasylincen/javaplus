package cn.mxz.protocols.shenmo;

import "war_situation.p";
import "user_base.p";

//副本战斗结果信息
message ShenmoPrizePro {
}

//副本战斗结果信息
message ShenmoWarSituationPro {
	//本次伤害值
	required int32 damage = 8;
	//副本奖励
	required int32 gongde = 9;
	required cn.mxz.protocols.user.battle.WarSituationPro warSituation = 10;
}
//攻击者列表
message AttackPro{
	//伤害
	required int32 damage = 1;
	required cn.mxz.protocols.user.UserBasePro user = 2;
}
message ShenmoPro {
	//神魔的模板id
	required int32 templetId = 1;
	//神魔的hp
	required int32 hp = 2;
	//神魔的等级
	required int32 level = 3;
	//神魔的发现者
	required string founder = 4;
	//神魔剩余的逃跑时间，用秒为单位
	required int32 runSecond = 5;
	//如果神魔事件结束，是否领过奖
	required bool gotPrize = 6;
	//击杀人数
	required int32 killNumber = 7;
	repeated AttackPro attacks = 8;

	//神魔的hpMax
	required int32 hpMax = 11;
	//神魔的唯一id
	required int32 shenmoId = 12;
	
	//神魔的击杀者
	required string killer = 13;		
}

message ShenMoListPro {
	repeated ShenmoPro shenmos = 1;
	//用逗号分隔的13个功德的领奖点，1代表领奖，0代表没领奖
	required string gongdePrize = 2;
	//玩家功德值
	required int32 gongde = 3;
}