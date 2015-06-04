package cn.mxz.protocols.user;
import "user_base.p";
import "attribute.p";

message PracticeAdditionPro {
	
	required AttributePro addition = 10;
	
	required int32 friendCount = 20;   //关注我的好友数量
	
	required int32 abilityAddition = 30;		//能力提升  10% ===> value = 10
}

message PracticeDataPro {

	required bool isAutoAccept = 10;//是否自动接受

	optional int32 lotExpected = 20;
	
	optional int32 roomType = 30;
	
	optional int32 remainingSec = 40;
	
	required int32 qmsaTimes = 60;
	
	required int32 bnhhTimes = 80;
	
	required int32 bysfTimes = 90;
	
	required int32 qmsaRemainTimes = 91;
	
	required int32 bnhhRemainTimes = 92;
	
	required int32 bysfRemainTimes = 93;
	
	required UserBasePro me = 100;
	
	optional UserBasePro friend = 110;
	
	required int32 careForMeCount = 120;	//关注我的好友数量
}

//求关注列表
message AskForCarePro {

	repeated AskUserPro friends = 110;
}

message AskUserPro {

	//是否正在双休
	required bool isPracticeNow = 100;
	required UserBasePro user = 110;
}

message PracticeFriendListPro {

	required int32 pageNow = 3;
	
	required int32 pageAll = 4;
	
	repeated PracticeFriendPro friends = 10;
	
	message PracticeFriendPro{
		
		required UserBasePro user = 10;
		
		required int32 time = 32;
		
		required bool isPractice = 50;
	}
}

message PracticeRequestListPro {

	required int32 pageNow = 3;
	
	required int32 pageAll = 4;
	
	repeated PracticeRequestPro request = 10;
	
	message PracticeRequestPro{
		
		required UserBasePro user = 10;
		
		required int32 roomType = 30;
	}
}

message PracticeLogListPro {
	repeated PracticeLog logs = 1;
}

message PracticeLog {
	required int32 id = 10;
	required string log = 11;
}
