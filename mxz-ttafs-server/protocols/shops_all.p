




package cn.mxz.protocols.user.shops;


message ShopsAllPro {

    required int32 playerMoney = 10;

	repeated ShopToolsPro shopTools= 20;
}

message ShopPricePro {

	//元宝价格
	required int32 gold = 2;
	
	//金币价格
	required int32 cash = 3;
	
	//是否是金贝壳
	required bool isJinBeiKe = 4;
	
}

message ShopToolsPro {

   required int32 toolId = 10;
   
   required string toolName = 20;
   
   required string toolType = 30;
   
   required int32 dressLevel = 40;
   
   required string description = 50;
   
   required string road = 60;
   
   required int32 cashOld = 70;
   
   required int32 cashNew = 80;
   
   required int32 couponsOld = 90;

   required int32 couponsNew = 91;
   
   required int32 quality = 92;
   
   required int32 tab = 93;

   required int32 hasCount = 95;
   
   required int32 canBuyCount = 96;
   
   required int32 countMax = 556;
   
   required int32 nowCoupons = 97;
   
   //购买过的次数
   required int32 buyCountToday = 99;
   
   //历史购买过的次数
   required int32 buyCountHistory = 111;
   
   //是否是特价商品
   required bool isSpecial = 1948;
}