package cn.mxz.protocols.user.god;

import "attribute.p";
import "fraction.p";
import "equipment.p";

message FightersPro {
	repeated FighterPro fighters = 10;
}

message XianShiPro {

	required int32 remainingSec1 = 10;
	required int32 remainingSec2 = 20;
	required int32 remainingSec3 = 30;


	required int32 freeTimes1 = 31;
	required int32 freeTimes2 = 32;
	required int32 freeTimes3 = 33;

	//历史招募次数
	required int32 times1 = 131;
	required int32 times2 = 132;
	required int32 times3 = 133;


	//聚魂冷却时间
	required int32 remainingSecJuHun = 40;


	//聚魂值
	required int32 juHun = 50;
}

message RecruitResaultPro {

	//招得的战士 (如果没招得战士, 那么该值为null)
	optional FighterPro fighter = 1;

	//招得的魂魄ID (如果没获得魂魄, 那么该值为-1)
	required int32 spiriteId = 10;
	
	//当前有多少个魂魄
	required int32 spriteHasNow = 133;

	//额外获得的魂魄奖励  (如果没获得额外的魂魄奖励, 那么该值为-1)
	required int32 extraSpiriteId = 20;
	
	//获得了多少个魂魄
	required int32 spiriteCount = 1133;
}

message RecruitResaultsPro {
	repeated RecruitResaultPro resualts = 111;
}

message FighterPro {

	//天命ID列表
	repeated int32 tianMing = 2;

	//装备天命ID
	repeated int32 equipmentTianMing = 3;

	//专属装备ID列表
	repeated int32 zsEquipments = 4;


	required int32 id = 9;


	//类型ID
	required int32 type = 10;


	//品质ID
	required int32 quality = 11;

	//等级
	required int32 level = 20;

	//品质
	required int32 step = 30;

	//星级
	required int32 star = 40;

	//经验
	required FractionPro exp = 50;

	//法力
	required FractionPro faLi = 60;

	//身价
	required int32 shenJia = 70;

	//阵形中:
	//  0   1   2
	//  3   4   5
	//  6   7   8

	//替补:
	//  100   101   102   103   104

	//如果不在阵形中, 也不在阵形中 : -1
	required int32 position = 80;

	//技能列表
	repeated FighterSkillPro skills = 90;


	//元神
	repeated YuanShenPro yuanShen = 110;

	//装备列表
	repeated cn.mxz.protocols.user.equipment.EquipmentPro equipments = 190;

	//当前气血
	required int32 hpNow = 200;

	//最大气血
	required int32 hpMax = 210;

	//攻击
	required int32 attack = 220;

	//防御
	required int32 defend = 230;

	//法术攻击
	required int32 mAttack = 240;

	//法术防御
	required int32 mDefend = 250;

	//速度
	required int32 speed = 260;

	//暴击
	required int32 crit = 270;

	//抗暴
	required int32 rCrit = 280;

	//会心(暴击加成)
	required int32 critAddition = 290;

	//命中
	required int32 hit = 300;

	//闪避
	required int32 dodge = 310;

	//格挡
	required int32 block = 320;

	//反格挡
	required int32 rBlock = 330;


	//法术值????
	required int32 magic = 340;

	//总经验
	required int32 expAll = 350;
}



//元神
message YuanShenPro {

	//元神类型
	required int32 type = 10;

	//等级
	required int32 level = 20;

	required FractionPro exp = 30;
	required int32 expAll = 40;
	required int32 step = 110;
	required float expPar = 90;
}


message FighterSkillsPro {

	//技能列表
	repeated FighterSkillPro skills = 10;
}

message FighterSkillPro {

	required int32 id = 140;

	required int32 skillId = 150;

	required int32 skillLevel = 160;

	//装备在哪个神将身上
	required int32 fighterId = 170;

	//fighter名字
	required string fighterName = 171;

	//身价
	required int32 price = 180;


	required int32 damage = 190;

	required int32 damageNext = 231230;
}

//魂魄列表
message SpiritesPro {
	repeated SpiritePro sprites = 10;
}

message SpiritePro {

	//品阶
	required int32 step = 10;

	//数量
	required int32 count = 20;

	//魂魄类型Id(战士类型ID)
	required int32 typeId = 30;

	//你是否有这个魂魄对应的战士(用于判断 可招募还是可升星)
	required bool hasFighter = 40;


	//升星所需最大数量
	required int32 countMax = 500;
	
	required bool canLevelUp = 1312;
}
