package cn.mxz.protocols.user.god;

import "equipment.p";


message TeamPro {

	repeated TeamGrid grids = 10;
}

message TeamGrid {

	required int32 fighterId = 10;
	
    required bool isOpen = 20;

	repeated cn.mxz.protocols.user.equipment.EquipmentPro equipments = 30;
}