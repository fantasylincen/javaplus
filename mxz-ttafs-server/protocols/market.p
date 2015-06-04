
package cn.mxz.protocols.user.market;

import "fighter.p";

message MyTradePro {

	repeated FighterItemPro fighters = 1;
	
}


message FighterItemPro {

	required FighterMPro mes = 10;

	repeated HerMesPro hero = 20;
}


message FighterMPro {
	
	required string nick = 10;
	required int32 myType = 20;
	required int32 myFighterId = 30;
	required int32 tradeTime = 40;
	
}

message HerMesPro {

	required int32 typeProperty = 10;
	required int32 Nub= 20;
	required bool isEnough= 30;
}

message TradeFighterMessagePro {

	repeated QueryTradeMesPro fighters = 1;
	
}

message QueryTradeMesPro {

	required TradeMes fighter = 1;
	
	repeated TradedGoods types = 60;
}

message TradeMes {
	
	required string nick = 10;
	required int32 myType = 20;
	required int32 tradeTime = 30;
	required bool isContents =40;
	required string belongPlayer =50;
	required cn.mxz.protocols.user.god.FighterPro hero = 70;
}
message TradedGoods {

	required int32 typeProperty = 10;
	required int32 Nub= 20;
	required bool isEnough= 30;
}