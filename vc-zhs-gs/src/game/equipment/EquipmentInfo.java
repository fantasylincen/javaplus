package game.equipment;

import config.equipment.EquipmentTemplet;
import lombok.Data;

/**
 * 一件装备的信息
 * @author DXF
 */
public class EquipmentInfo {
	
	public int getUID() {
		return uID;
	}

	public EquipmentTemplet getTemplet() {
		return templet;
	}

	public void setTemplet(EquipmentTemplet templet) {
		this.templet = templet;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public int getTheirHeroID() {
		return theirHeroID;
	}

	public void setTheirHeroID(int theirHeroID) {
		this.theirHeroID = theirHeroID;
	}

	private int 					uID;
	
	private EquipmentTemplet		templet;
	
	// 所属英雄ID 没有为-1
	private int 					theirHeroID = -1;
	
	public EquipmentInfo( int uid, EquipmentTemplet templet ){
		
		this.uID 		= uid;
		this.templet	= templet;
	}

	public int[] getValue() {
		return templet.getProperty().getValue();
	}
	
	public EColour getColour(){
		return templet.getColor();
	}

	public int getNID() {
		return templet.getNId();
	}

	/** 是否可以合成 */
	public boolean isSynthesis() {
		return templet.getSynthesis() != 0;
	}
	
	public String toString(){
		String temp = (theirHeroID == -1 ? "无" : theirHeroID + "");
		return "[UID：" + uID + 
				",品质：" + getColour().toName() +
				",所属英雄：" + temp +
				"]";
	}
	
}
