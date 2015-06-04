package cn.mxz.protocols.bossbattle;

import "war_situation.p";



message BossBattleResultPro {

	required cn.mxz.protocols.user.battle.WarSituationPro situation = 10;


	required int32					rank = 11;//我当前的名次
	required int32					damage=12;//本次伤害
	required int32					allDamage=13;//总伤害

}



