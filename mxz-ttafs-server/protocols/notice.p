




package cn.mxz.protocols.user;


message NoticePro {

	repeated TextItemPro items = 10;

}

message TextItemPro {

	//文本
	required string text = 11;

	//标题
	required string title = 1928;


	//0 活动  1 公告  2 温馨提示
	required int32 type = 222;

	//是否重要提示
	required string importentText = 1208;
}


//message GiftPro{
//
//	required int32 id = 1;
//
//	required string htmlText = 10;
//
//	required int32 time	= 20;
//
//	required bool hasReceived = 30;
//
//	required bool isReceivedAble = 40;
//
//	required string url = 50;
//}
