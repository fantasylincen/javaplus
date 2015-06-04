package cn.mxz.protocols.daji;

import "prop.p";
import "war_situation.p";

message DajiPro {
	required int32		protectCount = 1;					//总的保护的次数（最大8次，即3+5）
	required bool		isProtect = 2;						//今日是否已保护
	required int32		cd=3;								//剩余冷却时间（秒）
	required int32		currentMisiion=4;					//当前关卡
	required cn.mxz.protocols.user.PropsPro	props = 5;		//奖品内容
	 
	
}

message DajiResultPro {

	required cn.mxz.protocols.user.battle.WarSituationPro situation = 10;

	required cn.mxz.protocols.user.PropsPro	props = 20;//夺到的材料列表

}



