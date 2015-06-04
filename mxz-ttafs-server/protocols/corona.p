package cn.mxz.protocols.corona;

import "prop.p";

message CoronaPro {
	required cn.mxz.protocols.user.PropsPro	props = 1;		//转盘内容
	required int32		refreshTimes=2;						//刷新次数
	required int32		runTimes=3;							//转动次数
	required int32		cd=4;								//冷却时间
	required int32		timeStamp=5;						//转盘时间戳
}

message CoronaMessagePro {

	required string		name = 1;							//玩家姓名
	required int32		pid=2;								//道具id
	required int32		count=3;							//道具数量

}

message CoronaMessageSPro {

	repeated	CoronaMessagePro messages = 1;

}

