package cn.mxz.protocols.xianshi;
import "attribute.p";
import "dogz.p";


message XianShiPresentsPro {
	repeated XianShiPresentPro pros = 12093;
}


message XianShiPresentPro {
	required int32 id = 1;
	
	//已购买次数
	required int32 buyTimes = 10;
	
	//剩余购买次数
	required int32 remianTimes = 12;
	
	required int32 countMax = 13;
	
}