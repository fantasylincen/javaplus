package cn.mxz.city;

import java.util.Collection;

import cn.mxz.equipment.Equipment;
import cn.mxz.fighter.Part;

/**
 * 英雄身上装备空闲格子
 *
 * @author 林岑
 *
 */
public class EmptyGrid {

	private Part	part;

	public EmptyGrid(Part part) {
		this.part = part;
	}

	/**
	 * 是否可以装载某个装备
	 *
	 * @param all
	 * @return
	 */
	public boolean canInsertAnyOne(Collection<? extends Equipment> all) {
		for (Equipment e : all) {
			Part p = e.getPart();
			if (p == part) {
				return true;
			}
		}
		return false;
	}

}
