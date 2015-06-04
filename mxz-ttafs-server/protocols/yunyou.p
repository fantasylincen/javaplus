package cn.mxz.protocols.yunyou;

import "prop.p";

message YunyouPro {

	required int32		id = 5;									//唯一id
	required int32		level = 1;								//神仙等级
	required int32		propId=2;								//道具id
	required int32		count=3;								//道具数量,为0意味着已经购买，不能再次购买
	required int32		maxCount=10;							//道具总数量,前端显示用
	required int32		remain=4;								//指点结束时间
	required int32		heroId=6;								//英雄id
	required string		heroName=7;								//英雄名字
	required int32		exp=8;									//单倍经验
	required int32		needWine=9;								//所需酒量
			
}

message YunyouSPro {

	repeated	YunyouPro messages = 1;

}

