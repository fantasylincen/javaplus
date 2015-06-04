package cn.mxz.fighter;

import java.util.List;

import cn.mxz.equipment.Equipment;
import cn.mxz.user.team.god.Hero;

interface TGrid {

	boolean isOpen();

	int getFighterId();

	List<Equipment> getEquipments();

	
	Hero getFighter();

}
