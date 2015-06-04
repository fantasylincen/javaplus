package cn.mxz.protocols.user.enemy;

import "user_base.p";
import "war_situation.p";

message EnemysPro {
	repeated EnemyPro enemys = 40;
}


message EnemyPro {
	required cn.mxz.protocols.user.UserBasePro user = 10;
}

message EnemyWarSituationPro {
	required cn.mxz.protocols.user.battle.WarSituationPro situation = 12399;

}


message SystemItemsPro {
	repeated SystemItemPro items = 12038;
}




message SystemItemPro {

	//消息类型
	required int32 type = 6987;

	//秒  多少秒以前
	required int32 sec = 12343;


	//多少小时之前?
	required string timeText = 12394;


	optional cn.mxz.protocols.user.UserBasePro user = 123;
}