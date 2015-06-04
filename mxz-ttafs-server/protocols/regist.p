




package cn.mxz.protocols.user;


message CalendarPro {
	
	required int32 start = 10;
	
	required int32 day = 11;
	
	required int32 month = 12;
	
	repeated RegistRecordPro records = 20;
	
}


message RegistRecordPro {

	required int32 type = 10;
	
	optional int32 rewardId = 20;
}