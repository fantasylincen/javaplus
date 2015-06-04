
package cn.mxz.protocols.user.log;

import "pvp.p";
import "practice_data.p";
import "equipment.p";
import "firend_list.p";

//日志
message LogsPro {
	repeated LogPro logs = 10;
}

message LogPro {
	required int32 id = 9;
	required string log = 10;
}

message LogsAll {
	required cn.mxz.protocols.user.log.LogsPro pvp = 10;//pvp
	required cn.mxz.protocols.user.equipment.LogSnatchPro snatch = 20;//夺宝
	required cn.mxz.protocols.user.friend.FriendReceiveListPro friend = 30;//好友
	required cn.mxz.protocols.user.PracticeLogListPro practice = 40;//双修
}