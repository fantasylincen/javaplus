package cn.mxz.protocols.newformation;


message BackupPositionPro {
	required int32 position = 1;//替补位置，从100-104
	required int32 templet = 10;//模板id
	required int32 hero = 20;//位置上的英雄id，没有则为-1
}

message BackupPositionSPro {
	repeated	BackupPositionPro messages = 1;
}
message TacticalPro{
	required int32 id = 1;//id
	required int32 templet = 2;//模板id
	required int32 level = 3;//等级	
	required float addtion1 = 5;//阵首加成
	required float addtion2 = 6;//阵中加成
	required float addtion3 = 7;//阵尾加成	
	
}

message NewCampPro {
	required string position = 1;//阵型字符串，"英雄id,位置,英雄id,位置"位置从0-8
	required string levels = 7;////等级符串，"英雄id,等级,英雄id,等级"
	required int32 heroMaxNum = 2;//上阵人数上限
	repeated TacticalPro tacticalS = 3;//所有阵法
	required int32 currentTactical = 4;//当前阵法
	required int32 shenjia = 5;//身价
	repeated BackupPositionPro backupPos = 6;//替补信息
}
message OtherCampPro {
	required NewCampPro camp = 6;//替补信息
	required int32 dogz = 7;//神兽模板id
}


