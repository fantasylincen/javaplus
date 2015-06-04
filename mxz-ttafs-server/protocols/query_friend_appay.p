



import "user_base.p";
package cn.mxz.protocols.user.friend;


message QueryListPro {
	required int32 pageNow = 1;
	required int32 pageAll = 2;
	repeated PlaysQueryPro queryMessage = 5;
}

message PlaysQueryPro {
	
	required string nick = 10;
	required bool isMan = 20;
	required int32 level = 30;
	required int32 type = 40;
	required string userId = 50;
	required int32 lastLogin = 60;
	
	required UserBasePro userBase = 99;
}