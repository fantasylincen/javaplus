//package cn.mxz.mission.old.listeners;
//
//import java.util.List;
//import java.util.Map;
//
//import cn.mxz.ClearanceTemplet;
//import cn.mxz.ClearanceTempletConfig;
//import cn.mxz.DogzTemplet;
//import cn.mxz.DogzTempletConfig;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.dogz.Dogz;
//import cn.mxz.dogz.DogzFactory;
//import cn.mxz.equipment.EquipmentFactory;
//import cn.mxz.fish.Junket;
//import cn.mxz.mission.old.MissionAdaptor;
//import cn.mxz.mission.old.MissionMap;
//import cn.mxz.mission.old.events.EndEvent;
//import cn.mxz.user.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.counter.Marker;
//import define.D;
//
//public class EndListener extends MissionAdaptor {
//
//	@Override
//	public void onEnd(EndEvent e) {
//
//		int mapId = e.getMission().getMark().getMapId();
//
//		sendPassMissionReward(e.getCity(), e.getMap());
//
//		sendEquipment(mapId, e.getCity());	//赠送装备
//
//		//通关后赠送一个神兽
//		for (DogzTemplet dt : DogzTempletConfig.getAll()) {
//
//			int id = dt.getFormationId();
//
//			if(mapId >= id) {
//
//				addDogz(dt.getDogzId(), e.getCity());
//			}
//		}
//
//		e.getCity().getUserCounter().mark(CounterKey.HAS_PASS_MISSION_TODAY);
//
//		e.getCity().getBag().removeAllBySpotId(D.JIN_BAO_XIANG_SI_BIE_ID);
//		e.getCity().getBag().removeAllBySpotId(D.YIN_BAO_XIANG_SI_BIE_ID);
//	}
//
//	/**
//	 * 发放通关奖励
//	 * @param city
//	 * @param map
//	 */
//	private void sendPassMissionReward(City city, MissionMap map) {
//
//		int stepCount = getStepCount(map);	//地图的步数
//
//		List<String> keys = ClearanceTempletConfig.getKeys();
//		for (String key : keys) {
//			ClearanceTemplet temp = ClearanceTempletConfig.get(key);
//			String[] split = temp.getSteps().split("-");
//			int min = new Integer(split[0].trim());
//			int max = new Integer(split[1].trim());
//			if(min <= stepCount && stepCount < max) {
//
//				PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//				ps.send(city.getPlayer(), temp);
//
//				return;
//			}
//		}
//
//	}
//
//	private int getStepCount(MissionMap map) {
//
//		return map.getBigStones().size();
//	}
//
//	private void sendEquipment(int mapId, City city) {
//
//		//		第1张地图结束后送装备:151101
//
//		Marker his = city.getUserCounterHistory();
//
//		CounterKey k = CounterKey.SEND_FIRST_EQUIPMENT_MARK;
//
//		boolean mark = his.isMark(k);
//
//		if(mapId == D.SEND_EQUIPMENT_MAP_ID && !mark) {
//
//			EquipmentFactory.createNewEquipment(D.SEND_EQUIPMENT_ID_151201, city);
//
//			his.mark(k);
//		}
//
//	}
//
//	private void addDogz(int dogzId, City city) {
//
//		Map<Integer, Dogz> all = city.getDogzManager().getDogzAll();
//
//		if(all.get(dogzId) == null) {
//
//			DogzFactory.createNewDogz(city, dogzId);
//
//			Junket junket = city.getJunket();
//
//			junket.addProp(D.DOGZ_ADD_TO_JUNKET_160001, 1);
//			junket.addProp(D.DOGZ_ADD_TO_JUNKET_160002, 1);
//			junket.addProp(D.DOGZ_ADD_TO_JUNKET_160003, 1);
//
//			city.getBag().addProp(D.DOGZ_ADD_TO_BAG_130029, 1);
//		}
//	}
//
//}
