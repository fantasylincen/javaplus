
package cn.mxz.protocols.user.equipment;

import "attribute.p";
import "user_base.p";
import "war_situation.p";
import "prop.p";

message EquipmentsPro {

	repeated EquipmentPro equipments = 10;
}


//夺宝结果
message SnatchResultPro {

	required cn.mxz.protocols.user.battle.WarSituationPro situation = 10;

	repeated PropPro props = 20;//夺到的材料列表

	required CardRewardPro reward = 1;
}

//翻牌结果
message CardRewardPro {

	//得到的那个奖励
	required string received = 1;

	//没有得到的那个奖励
	required string unreceived1 = 2;

	//没有得到的那个奖励
	required string unreceived2 =3;
}

message EquipmentPro {

	required int32 id = 1;

	required int32 typeId = 10;

	required int32 level = 20;

	required int32 step = 30;

	required int32 price = 31;

	required int32 priceLevelUpHistory = 32;

	required AttributePro attribute = 40;

	required int32 fighterId = 50;

	required int32 fighterTypeId = 51;

	required int32 additionType = 60;

	required int32 teamIndex = 70;

	//强化所需金币
	required int32 levelUpCashNeed = 72;

	//基本加成类型
	required int32 baseAdditionType = 8973;
}

message EquipmentSnatchListPro {

	repeated SnatchUserPro users = 10;

}


message SnatchUserPro {

	required UserBasePro user = 10;

	required int32 pvpDanId = 11;  		//PVP段位ID

	required int32 pvpDanLv = 12;    	//PVP段位等级

	required int32 probability = 20;	//夺宝成功率: 0 - 100;
	
	required int32 probabilityId = 9999; //夺宝成功率ID  对应到SnatchProbability表
	
	required string probabilityText = 9188; // 极低概率 ? 低概率 ? 中概率 ?高概率

	required bool isRobot = 21;	//是否是机器人

	repeated PartnerHeadPro head = 30;	//伙伴头像
}

message PartnerHeadPro {
	required int32 id = 10;
	required int32 level = 20;
}

//夺宝日志信息
message LogSnatchPro {

	repeated LogsNubPro log = 10;

	required int32 protectTime = 20;  //夺宝时间

	message LogsNubPro {

		//消息ID
		required int32 messageId = 998;
		
		//抢夺者是否抢夺成功了
		required bool isSnatchSuccess = 100;

		required int32 id = 9;	//ID

		required string myNice = 10;    //我的昵称

		required int32 dataType = 11;  //材料类型

		required int32 nub = 12;       //材料数量

		required string rooberNice = 20;//强我材料的人的昵称

		required int32 excelnub = 30;       //excel静态内容

		required bool isQuilt= 40; //是否是我抢了别人

		required string snatchTime = 50;//抢夺时间

		required string rooberType = 60;//强我材料的人的类型ID

		required int32 userType = 1;			//角色类型

		required int32 level = 4;			//玩家等级

		required int32 warSituationId = 70;			//战况信息ID

		required bool isWin = 80;					//他是否胜利了
	}
}

//当前拥有的装备数量信息
message EquipmentHadPro {

	required int32 hpHad = 1;
	required int32 attackHad = 2;
	required int32 defendHad = 3;
	required int32 speedHad = 4;

}

message ProtectFighter {

	required int32 countDown = 10;		//倒计时
}