package cn.mxz.equipment;
//package cn.mxz.prop.equipment;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import cn.javaplus.common.db.DAO2;
//import cn.mxz.user.City;
//import db.dao.factory.DaoFactory;
//import db.domain.EquipmentHad;
//
//public class EquipmentsHadBuilder {
//
////	public EquipmentsHadPro build(City city) {
////
////		Set<Integer> allId = findAllId(city);
////
////		EquipmentsHadPro.Builder b = EquipmentsHadPro.newBuilder();
////
////		for (Integer e : allId) {
////
////			b.addEquipmentTypeId(e);
////		}
////
////		return b.build();
////	}
//
//	private Set<Integer> findAllId(City city) {
//
//		DAO2<Integer,String,EquipmentHad> DAO = DaoFactory.getEquipmentHadDAO();
//
//		List<EquipmentHad> findBy = DAO.findByUname(city.getId());
//
//		Set<Integer> set = new HashSet<Integer>();
//
//		Collection<Equipment> values = city.getEquipmentManager().getAll().values();
//
//		for (Equipment equipment : values) {
//
//			set.add(equipment.getTypeId());
//		}
//
//		for (EquipmentHad e : findBy) {
//
//			set.add(e.getEquipmentTypeId());
//		}
//
//		return set;
//
//
//	}
//
//}
