




package cn.mxz.protocols.user;
import "user_base.p";


message LookAroundPro {
	
	required int32 pageNow = 1;
	
	required int32 pageAll = 2;
	
	repeated UserBasePro users = 5;
}


message LookGirlPro {
	
	required int32 pageNow = 1;
	
	required int32 pageAll = 2;
	
	repeated UserBasePro users = 5;
}


message LookBoyPro {
	
	required int32 pageNow = 1;
	
	required int32 pageAll = 2;
	
	repeated UserBasePro users = 5;
}



