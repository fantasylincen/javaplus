
package cn.mxz.protocols.pvp;


import "war_situation.p";
import "user_base.p";
import "fraction.p";
import "equipment.p";

message PvpRandomResultPro {
	required PvpUsersPro users = 1;
	required int32 refreshCd = 2;
}

message PvpRewardStatusPro {

	//排名奖励是否领取
	required bool hasReceived = 17328;
	
	//排名奖励倒计时
	required int32 rankRewardRemainSec = 12316;

	//段位奖励
	required DanRewardPro reward = 86464;
	
	//荣誉
	required int32 rongYu = 9871;
	
	//20点时的排名
	required int32 rankOnEightClock = 9841;
}

message PvpWarsituationPro {

	//-1: 不播动画 0:晋级成功动画  1 :晋级失败动画
	required int32 upStatus = 111;

	required cn.mxz.protocols.user.battle.WarSituationPro warSituation = 10;
	required cn.mxz.protocols.user.equipment.CardRewardPro reward = 123;

	//获得的胜点
	required int32 winPoint = 1231;
}



message WarSituationsPro{
	repeated VideoPro videos = 10;
}


message VideoPro {

	required int32 videoId = 10;
	required cn.mxz.protocols.user.UserBasePro  user = 20;
	required int32 winPoint = 30;
	required string time = 3122;

	required string text = 2131;

	required bool isWin = 985735;


	//是否被被人打了
	required bool isBeAttack = 198575;
}

message PvpRewardPro{
	required bool hasReceived = 10;
}


message PvpUsersPro {

	required int32 page = 1237182;
	required int32 pageAll = 1238348;

	repeated PvpUser users = 9;
}

message PvpScenePro{

	//段位ID
	required int32 danId = 10;

	//精力
	required int32 power = 30;

	//排名
	required int32 rank = 110;
	
	//20点时的排名
	required int32 rankOnEightClock = 9841;

	//最佳排名
	required int32 bestRank = 12123;

	//胜率
	required float winPercent = 80;

	//当前连胜场次
	required int32 currentWinStreak = 40;

	//最高连胜
	required int32 maxWinStreak = 50;

	//胜利场次
	required int32 winTimes = 60;

	//失败场次
	required int32 loseTimes = 70;

	//被膜拜次数
	required int32 beKowtowTimes = 1239945;

	//战斗力
	required int32 fightingCapacity = 90;

	//剩余次数
	required int32 remainTimes = 100;

	//修行
	required cn.mxz.protocols.user.FractionPro practice = 120;

	//晋级状态:   w:胜利  l: 失败 t:平局
	//例如 :
	// wwwt:  前三场胜利 第四场平局 第五场未开始
	required string status = 130;


	required bool isUp = 12389;

	//今日膜拜次数
	required int32 kowtowTimes = 1230884;

	//剩余符文使用次数
	required int32 remainUserFuWenTimes = 9239;

	//玩家等级
	required int32 userLevel = 8656;


	//排名奖励是否领取
	required bool hasReceived = 17328;
	
	//排名奖励倒计时
	required int32 rankRewardRemainSec = 12316;

	//段位奖励
	required DanRewardPro reward = 86464;
	
	//是否是第一次晋级
	required bool isFirstUp = 8236;
	
	//荣誉
	required int32 rongYu = 9871;
	
	//挑战冷却时间 秒
	required int32 challengeCd = 978;
	
}

message DanRewardPro {

	//奖励内容   格式就是配置表里面的格式
	required string rewards = 12394;

	//是否可以领取这个奖励
	required bool canReceive = 84649;
	
	//段位ID
	required int32 danId = 5462;
}

message PvpUser {

	required int32 rank = 1;

	//主角神将ID
	required int32 playerTypeId = 2;
	required cn.mxz.protocols.user.UserBasePro user = 3;
	//修行
	required cn.mxz.protocols.user.FractionPro practice = 4;
	required int32 danId = 5;
	required int32 power = 6;


	required int32 vipLevel = 123989;//vip等级
	required int32 chiXuShiJianMiao = 148838;//持续秒

	required int32 step = 1239123;
	required int32 upNumber = 1239879;

	//战胜它后, 可以获得的修为值
	required int32 practiceWin = 12390766;


	//晋级时的战况信息ID
	required int32 warsituationId = 2193911;

	//玩家的战士ID列表
	required string fighters = 32998;

}


message TaskRewardsPro {

	repeated TaskRewardPro rewards = 10;
}

message TaskRewardPro {

	required cn.mxz.protocols.user.FractionPro schedule = 10;

	required bool hasReceived = 20;

	required int32 taskId = 30;
}

