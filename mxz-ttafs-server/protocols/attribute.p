
package cn.mxz.protocols.user;

message AttributePro {

	required int32 hp = 10;
	required int32 attack = 20;
	required int32 defend = 30;
	required int32 mAttack = 40;
	required int32 mDefend = 50;
	required int32 speed = 60;

	required int32 crit = 70;
	required int32 rCrit = 80;
	required int32 critAddition = 90;
	
	required int32 hit = 100;
	required int32 dodge = 110;

	required int32 block = 120;
	required int32 rBlock = 130;
	required int32 magic = 140;
}