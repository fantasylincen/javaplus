//package cn.mxz.fighter;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import cn.mxz.FormationTemplet;
//import cn.mxz.FormationTempletConfig;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.prop.equipment.Equipment;
//import cn.mxz.user.City;
//import cn.mxz.user.team.god.Hero;
//
//import com.google.common.collect.Lists;
//
//import db.domain.UserFormationFighter;
//
//public class TGridImpl implements TGrid {
//
//	private City	city;
//
//	private Hero	hero;
//
//	private int	index;
//
//	public TGridImpl(City city, Hero hero, int index) {
//
//		this.city = city;
//
//		this.hero = hero;
//
//		this.index = index;
//	}
//
//	@Override
//	public boolean isOpen() {
//
//		PlayerCamp selected = city.getFormation().getSelected();
//
//		int id = selected.getId();
//
//		FormationTemplet formationTemplet = FormationTempletConfig.get(id);
//
//		int number = formationTemplet.getNumber();
//
//		index %= (UserFormationFighter.FIGHTER_TYPE_ID_LEN / 2);
//
//		return index < number;
//	}
//
//	@Override
//	public int getFighterId() {
//
//		if(hero == null) {
//
//			return -1;
//		}
//
//		return hero.getTypeId();
//	}
//
//	@Override
//	public List<Equipment> getEquipments() {
//
//		if(hero == null) {
//
//			return Lists.newArrayList();
//		}
//
//		Map<Integer, Equipment> equipmentAll = city.getEquipmentManager().getEquipmentAll(hero);
//
//		return new ArrayList<Equipment>(equipmentAll.values());
//	}
//
//	@Override
//	public Hero getFighter() {
//		return hero;
//	}
//
//}
