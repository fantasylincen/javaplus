


package cn.mxz.protocols.user;


message UserBasePro {

	required int32 type = 1;

	required int32 clothes = 2;

	required string nick = 3;

	required int32 level = 4;

	required bool isMan = 5;

	required string userId = 6;

	required int32 fightingCapacity = 7;

	required int32 rank = 8;

	required int32 charm = 9;
	required int32 charmStartCount = 10;
	required int32 totalGold = 11;

	//VIP等级
	required int32 vipLevel = 12487;

	//段位ID
	required int32 danId = 21977;
}