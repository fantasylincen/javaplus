package cn.mxz.protocols.vip;

message VipCardPro {
	required int32		type=2;						//当前vip类型
	required int32		remainDay=3;				//剩余天数
	required bool		hasReceived = 4;			//是否领取了奖励
}
