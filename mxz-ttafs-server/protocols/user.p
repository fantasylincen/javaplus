package cn.mxz.protocols.user;
import "user_base.p";
import "fraction.p";


message UserPro {

	required UserBasePro base = 10;
	required int32 gold = 20;
	required int32 gold2 = 21;
	required int32 cash = 30;
	//七彩晶石
	required int32 qiCaiJingShi = 219;
	required FractionPro exp = 40;
	required FractionPro physical = 60;
	required FractionPro power = 80;
	required FractionPro fighter = 90;
	required int32 fightingCapacity = 130;

	//通关了的最大的地图ID
	required int32 missionId = 140;

	//当前正在打的地图的ID
	required int32 currentMissionId = 150;

	//是否需要弹出登陆奖励界面
	required bool needShowLoginRewardPanel = 161;

	required PromptPro prompt = 170;

	//VIP 成长值 分子
	required int32 vipGrowthN = 87411;

	//VIP 成长值 分母
	required int32 vipGrowthD = 87410;

	//兽魂
	required int32 shouHun = 251;

	required int32 reputation = 8723;

	//是否有奖励可以领取(奖励中心的奖励)
	required bool hasPrize = 21388;
	
	required int32 fightingDogzId = 200;
	
	required int32 powerRemainSec = 162;

	required int32 physicalRemainSec = 163;
	
	//历史累计充值的金币
	required int32 rechargeGold = 164;
	
	//是否领取了首冲礼包
	required bool hasReceiveRechargeReward = 298;
	
	required int32 monthCardEndSecond = 1223;
	
	required string firshtRechargeStr = 1224;

	//蓝港ID
	required string lineKongId = 1230;
	
	//在线奖励按钮是否显示
	required bool isShowOnlineRewardButton = 1231;
	
	//是否有在线奖励可以领取
	required bool hasOnlineReward = 4653;
	
}


//用户升级
message UserLevelUpPro {

	required int32 fighterCount= 13248;
	required int32 attack = 11238;
	required int32 defend = 12331;
	required int32 mAttack = 12382;
	required int32 mDefend = 5112;
	required int32 hp = 652;
	required int32 speed = 3652;

	//金币奖励
	required int32 cash = 2132;
}


message PromptPro {

	required bool hpksx = 1;
	required bool hpkzm = 2;
	required bool ssksj = 3;
	required bool ysyktzcs = 4;
	required bool jjjlwlq = 5;
	required bool kdb = 6;
	required bool ymsktz = 7;
	required bool ymsjlklq = 8;
	required bool ydbxxx = 9;
	required bool ydzxxx = 10;
	required bool yxtxxx = 11;
	required bool yhyly = 12;
	required bool sjyxxx = 13;
	required bool lmyxxx = 14;
	required bool slyxxx = 15;
	required bool mbklq = 16;
	required bool cjklq = 17;
	required bool mmbxklq = 991;
	required bool kzm = 18;
	required bool zmhd = 19;
	required bool bbybx = 20;
	required bool yxhb = 21;
	required bool sx = 22;
	
	
	//客服有新消息
	required bool kfyxxx = 195;
	
	
	//开服礼包
	required bool kflb = 23;
	
	//开服礼包按钮是否显示
	required bool kflbOpen = 9921;
	
	//boss战开始的剩余时间
		
	required int32 remainSecFromStart = 24;
}


message AccessResultPro {

	//是否是新用户
	required bool isNewUser = 1;
	
	//错误码    0表示正常
	required int32 errorCode = 2;
}