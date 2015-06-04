package cn.mxz.events.equipment;

import java.util.List;

import cn.mxz.city.City;

/**
 * 天命变化事件
 * 
 * @author 林岑
 * 
 */
public class TianMingChangeEvent {

	private List<Integer> heroTianMingAdd;
	private List<Integer> skillTianMingAdd;
	private List<Integer> equipmentTianMingAdd;
	private City user;
	private List<Integer> heroTianMingRemove;
	private List<Integer> skillTianMingRemove;
	private List<Integer> equipmentTianMingRemove;

	public TianMingChangeEvent(City user, List<Integer> heroTianMingAdd,
			List<Integer> skillTianMingAdd, List<Integer> equipmentTianMingAdd,
			List<Integer> heroTianMingRemove, List<Integer> skillTianMingRemove,
			List<Integer> equipmentTianMingRemove) {
		this.user = user;
		this.heroTianMingAdd = heroTianMingAdd;
		this.skillTianMingAdd = skillTianMingAdd;
		this.equipmentTianMingAdd = equipmentTianMingAdd;
		this.heroTianMingRemove = heroTianMingRemove;
		this.skillTianMingRemove = skillTianMingRemove;
		this.equipmentTianMingRemove = equipmentTianMingRemove;
	}

	public List<Integer> getHeroTianMingRemove() {
		return heroTianMingRemove;
	}

	public List<Integer> getSkillTianMingRemove() {
		return skillTianMingRemove;
	}

	public List<Integer> getEquipmentTianMingRemove() {
		return equipmentTianMingRemove;
	}

	public City getUser() {
		return user;
	}

	public List<Integer> getEquipmentTianMingAdd() {
		return equipmentTianMingAdd;
	}

	public List<Integer> getHeroTianMingAdd() {
		return heroTianMingAdd;
	}

	public List<Integer> getSkillTianMingAdd() {
		return skillTianMingAdd;
	}
}
