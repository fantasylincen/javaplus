package cn.mxz.protocols.heishi;


message HeishiPro {

	//离活动结束时的秒数
	required int32 remainEndSecond = 1;
	//七色晶石的数量
	required int32 qsjs = 2;
	//道具购买情况
	required string buyInfo = 3;
	repeated HeishiMessage messages = 4;
	optional int32 bsj= 5;
	optional int32 hsj= 6;
		
	
}

//公告消息
message HeishiMessage {
	required string propName= 1;
	required string nickName = 2;
	required int32 quality= 3;		
}
