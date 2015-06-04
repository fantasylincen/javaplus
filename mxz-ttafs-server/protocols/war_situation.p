package cn.mxz.protocols.user.battle;
import "attribute.p";
import "dogz.p";

message WarSituationPro {
	required FormationPro formation = 1;
	repeated ActionPro actions = 10;
	
	//Buff作用效果, 在每一回合开始的时候, 显示.
	repeated BuffEffectPro effects = 110;
	
	required bool isWin = 20;
	required bool isLose = 30;

	//失败指引
	repeated int32 faildGuide = 1234;
}

//Buff作用效果
message BuffEffectPro {

	//回合数
	required int32 round = 1;
	required sint32 position = 2;
	required bool isUnder = 5;
	required int32 bufferId = 13;

	//被Buff作用后的 气血
	required int32 hp = 20;
	//被Buff作用后的 攻击
	required int32 attack = 30;
	//被Buff作用后的 防御
	required int32 defend = 40;
	//被Buff作用后的 法攻
	required int32 mAttack = 50;
	//被Buff作用后的 法防
	required int32 mDefend = 60;
	//被Buff作用后的 暴击
	required int32 crit = 70;
	//被Buff作用后的 闪避
	required int32 dodge = 80;
	//被Buff作用后的 速度
	required int32 speed = 90;
}

message FormationPro {
	required CampPro unders = 10;
	required CampPro uppers = 20;
}



message ActionPro {

	required int32 round = 1;
	required AttackerPositionPro attackerPosition = 2;
	required bool isAttackerUnder = 10;

	//被攻击目标列表
	repeated SkillPointPro point = 20;

	repeated BuffDisappearPro buffDisappears = 21;

	required int32 skillId = 30;
	required int32 hasLethality = 40;
	required int32 upperDogzAngryValue = 50;
	required int32 underDogzAngryValue = 60;

	//此次攻击是否是反击
	required bool isCounterAttack = 70;
}

message AttackerPositionPro {

	//是否是神兽出手
	required bool isDogz = 1;
	required int32 position = 2;
}

message SkillPointPro {

	required sint32 position = 1;

	required bool isUnder = 5;
	required bool isCrit = 7;
	required bool isBlock = 9;
	required bool isHit = 14;


	required int32 bufferId = 13;

	required int32 hp = 20;
	required int32 attack = 30;
	required int32 defend = 40;
	required int32 mAttack = 50;
	required int32 mDefend = 60;
	required int32 crit = 70;
	required int32 dodge = 80;
	required int32 speed = 90;
}

message BuffDisappearPro {
	required int32 buffId = 10;
	required int32 position = 20;
	required bool isUnder = 30;
}

message CampPro {
	optional DogzPro fighting = 1;
	repeated BattleFighterPro fighters = 10;
	optional string nick = 20;
	optional string userId = 111;
	optional int32 level = 21;
	optional int32 capacity = 22;
}

message BattleFighterPro {
	required sint32 position = 10;
	required int32 id = 20;
	required int32 hp = 30;
	required int32 speed = 40;
	required int32 hpMax = 31;
	required int32 step = 32;
}