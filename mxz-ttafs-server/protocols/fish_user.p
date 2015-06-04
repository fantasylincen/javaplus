




package cn.mxz.protocols.user.fish;

import "user_base.p";


message FishUserPro {

	required int32 freeTimes = 1;
	
	required UserBasePro user = 3;
	
	required JunketPro junket = 4;
}


message JunketPro {
	
	required int32 now = 1;
	
	required int32 max = 2;

	repeated FishPro fishs = 3;
}

message FishPro {
	
	required int32 id = 1;
	
	required int32 type = 3;
	
	required int32 count = 4;
}