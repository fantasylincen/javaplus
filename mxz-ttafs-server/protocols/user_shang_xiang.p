




package cn.mxz.protocols.user;


message TodayIsGetPro {

	//是否中午
	required bool noon = 10;

	//是否晚上
	required bool night = 11;

	//到可上香时间的 倒计时 秒
	required int32 countDown = 12;

	//中午是否上过香
	required bool hasReceiveNoon = 13;

	//晚上是否上过香
	required bool hasReceiveNight = 14;

	//小时
	required int32 hour = 15;
}
