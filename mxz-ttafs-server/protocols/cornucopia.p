package cn.mxz.protocols.cornucopia;

message CornucopiaPro {

	required int32		times=5;						//今日摇钱次数
	required int32		maxTimes = 31;					//今日最大摇钱次数

	required int32		all = 12398;	//累计横财
	required int32		yunShi = 291;	//今日运势  比如30%的话, 该值为30
	required int32		type = 2130;	//笑脸????

	required string 		cash = 1874;

	required int32 		couponsNeed = 993;
}
