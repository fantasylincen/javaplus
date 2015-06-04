package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.javaplus.common.db.DAO;
//import cn.javaplus.common.db.DAOBase;
//import cn.mxz.mission.events.EnterEvent;
//import cn.mxz.mission.listeners.MissionListener;
//import cn.mxz.mission.map.MapParser;
//import cn.mxz.user.City;
//import cn.mxz.user.mission.Mission;
//import cn.mxz.util.debuger.Debuger;
//
//import com.google.common.collect.Lists;
//
//import db.domain.MissionData;
//import db.domain.MissionMapData;
//
//public abstract class AbstractMission  <MissionDataType extends MissionData, MissionMapType extends MissionMapData> implements Mission {
//
//	protected MissionDataType		dto;
//
//	private MissionMap				currentMap;
//
//
//	protected City					city;
//
//	protected List<MissionListener>	listeners		= Lists.newArrayList();
//
//	public AbstractMission(City city) {
//
//		this.city = city;
//	}
//
//	@Override
//	public void next() {
//
//		if (currentMap.getMark().getMapId() >= getMark().getMapId()) {
//
//			dto.addMissionId(1);
//
//			commit();
//		}
//
//		Debuger.debug("通关到下一关:" + dto.getMissionId());
//	}
//
//	private void commit() {
//		getMissionDAO().update(dto);
//	}
//
//	protected abstract DAOBase<MissionDataType> getMissionDAO();
//
//	public void setDto(MissionDataType dto) {
//		this.dto = dto;
//	}
//
//	@Override
//	public MissionMap getCurrentMap() {
//		return currentMap;
//	}
//
//	@Override
//	public void giveUp() {
//
//		MissionMap map = getCurrentMap();
//
//		deleteCurrentMap();
//
//		if(map != null) {
//
//			for (MissionListener l : listeners) {
//
//				l.onGiveUp(new GiveUpEvent(city, map));
//			}
//		}
//	}
//
//	/**
//	 * 移除当前地图
//	 */
//	@Override
//	public void deleteCurrentMap() {
//
//		setCurrentMap(null);
//
//		getMissionDAO().update(dto);
//
//		getMissionMapDAO().delete(dto.getUname());
//	}
//
//	@Override
//	public boolean hasMap() {
//		return getCurrentMap() != null;
//	}
//
//	@Override
//	public void buildMap(int storyId) {
//
//		final MissionMap map = newMap(storyId);
//
//		this.setCurrentMap(map);
//
//		getMissionMapDAO().add(getDTO());
//
//		for (MissionListener l : listeners) {
//
//			l.onEnter(new EnterEvent(city));
//		}
//	}
//	
//	@Override
//	public void onBeforeEnter(int storyId) {
//		
//		for (MissionListener l : listeners) {
//
//			l.onBeforeEnter(new BeforeEnterEvent(city));
//		}
//	}
//
//	protected abstract MissionMap newMap(int storyId);
//
//	protected abstract DAO<String, MissionMapType> getMissionMapDAO();
//
//	private MissionMapType getDTO() {
//
//		final MapParser<MissionMapType> p = new MapParser<MissionMapType>();
//
//		final MissionMapType dto = p.parse(currentMap, this.dto.getUname(), getMissionMapDAO().createDTO());
//
//		return dto;
//	}
//
//	public void setCurrentMap(MissionMap currentMap) {
//
//		this.currentMap = currentMap;
//
//	}
//
//	@Override
//	public void addListener(MissionListener l) {
//
//		this.listeners.add(l);
//	}
//
//}