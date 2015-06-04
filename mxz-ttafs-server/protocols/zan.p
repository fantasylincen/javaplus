package cn.mxz.protocols.zan;


message ZanPro {

	required int32		totalCount = 1;							//服务器赞的总数
	required int32		count = 2;								//玩家赞的总数

	required bool		todayIsClick=3;							//今日是否点赞	
	required int32		nextGold=5;								//下一次点赞以前获取的元宝数,无所谓今天明天
	required int32		level=6;								//当前等级		

}
