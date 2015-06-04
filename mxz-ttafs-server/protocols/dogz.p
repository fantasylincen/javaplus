package cn.mxz.protocols.user;

import "fraction.p";
import "attribute.p";

message DogzListPro {
	repeated DogzPro dogzs = 40;
}

message DogzPro {
	required int32 typeId = 10;
	required bool isFighting = 21;
	required int32 level = 30;
	required int32 step = 50;
	required FractionPro growth = 60;
	required bool isProtected = 61;
	required int32 dunWuTimes = 90;
	required int32 fishCount = 100;
	required int32 huiHunDanCount = 110;
	required int32 protectProp = 120;
	required DogzSkillPro skill = 130;
}

message DogzSkillPro {
	required int32 id = 120;
	required int32 skillAddition = 140;
	required int32 skillProbability = 150;
}

message DogzFighting {
	optional DogzPro dogz = 10;
}

