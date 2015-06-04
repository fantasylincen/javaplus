package cn.mxz.protocols.user.god;

//背包数据
message BagPro {
	required int32 capacity = 10;//背包容量
	repeated GridPro grid = 20;//背包中的所有格子
}

message GridPro {
	required int32 id = 1;			//格子ID
	required int32 typeId = 10;		//格子中的物品ID
	required int32 propType = 20;	//物品的类型
	required int32 count = 40;		//物品的数量
}
