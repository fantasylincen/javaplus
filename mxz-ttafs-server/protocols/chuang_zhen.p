package cn.mxz.protocols.user.chuangzhen;

import "war_situation.p";


//副本战斗结果信息
message ChuangZhenWarSituationPro {

	// 胜利:前面显示的星星
	required int32 star1 = 9123;

	// 胜利:倍数
	required int32 X = 993;

	// 胜利:后面显示的星星
	required int32 star2 = 123;

	// 胜利:在打X关加强属性
	required int32 floor1 = 1233;

	// 胜利:在打X关结算奖励
	required int32 floor2 = 8943;

	// 失败:今日还可以打多少场
	required int32 timesRemain = 1239;

	// 失败:第xxx名
	required int32 rank = 1235;

	//N 人阵
	required int32 fighterCount = 2153;

	//铜钱数
	required int32 cash = 213;

	required cn.mxz.protocols.user.battle.WarSituationPro warSituation = 10;
}

