//package cn.mxz.fighter;
//
//import java.util.List;
//
//import message.S;
//import templets.FormationTemplet;
//import templets.FormationTempletConfig;
//import cn.javaplus.common.db.DAO;
//import cn.mxz.base.exception.OperationFaildException;
//import cn.mxz.formation.FormationFactory;
//import cn.mxz.user.City;
//import cn.mxz.user.team.god.EquipmentTeam;
//import db.domain.UserEquipmentTeam;
//import db.domain.UserFormationFighter;
//
//public class EquipmentTeamImpl implements EquipmentTeam {
//
//	private DAO<String, UserEquipmentTeam> userEquipmentTeamDAO;
//
//	private DAO<String,UserFormationFighter> userFormationFighterDAO;
//
//	private City city;
//
//	@Override
//	public String moveFighterToDepot(int fighterId) {
//
//		final StringBuilder stringBuilder = new StringBuilder();
//
//		for(UserEquipmentTeam et : userEquipmentTeamDAO.findByUname(getCity().getId())) {
//
//			for(int i = 0; i < UserEquipmentTeam.EQUIPMENT_TEAM_TYPE_ID_LEN; i++) {
//
//				if(et.getEquipmentTeamTypeId(i) == fighterId) {
//
//					et.setEquipmentTeamTypeId(i, -1);
//
//					userEquipmentTeamDAO.update(et);
//
//					stringBuilder.append(i);
//
//					stringBuilder.append(",");
//
//					break;
//				}
//			}
//		}
//
//		for(UserFormationFighter uFighters : userFormationFighterDAO.findByUname(getCity().getId())) {
//
//			for(int i = 0; i < UserFormationFighter.FIGHTER_TYPE_ID_LEN; i++) {
//
//				if(uFighters.getFighterTypeId(i) == fighterId) {
//
//					uFighters.setFighterTypeId(i, 0);
//
//					userFormationFighterDAO.update(uFighters);
//
//					stringBuilder.append(i);
//
//					break;
//				}
//			}
//		}
//
//		return stringBuilder.toString();
//	}
//
//	@Override
//	public void moveFighterToTeam(int fighterId, boolean onlyAdd, String position) {
//
//		int canEnterMax = city.getMission().getMark().getMapId();
//
//		List<FormationTemplet> findByFormationId = null;
//
//		while(findByFormationId == null || findByFormationId.size() == 0) {
//
//			findByFormationId = FormationTempletConfig.findByFormationId(canEnterMax);
//
//			canEnterMax--;
//		}
//
//		final int number = findByFormationId.get(findByFormationId.size() - 1).getNumber();
//
//		// 根据开启的最大地图算出阵型偏移量
//		final int bufferNumber = getBufferNumber(number);
//
//		final List<UserEquipmentTeam> ueTeams = userEquipmentTeamDAO.findByUname(getCity().getId());
//
//		List<UserFormationFighter> fighters = userFormationFighterDAO.findByUname(getCity().getId());
//
//		// 没有队伍时：初始化
//		if(fighters.isEmpty()) {
//
//			FormationFactory.getFighterOnFormationPro(city, userFormationFighterDAO);
//
//			fighters = userFormationFighterDAO.findByUname(getCity().getId());
//		}
//
//		// 只是添加
//		if(onlyAdd) {
//
//			final int position_l = findLastPosition(ueTeams);
//
//			if(position_l < 10) {
//
//				ueTeams.get(0).setEquipmentTeamTypeId(position_l, fighterId);
//
//				boolean noFighterAtNumberBefor = true;
//
//				for(int i = 0; i < number; i++) {
//
//					if(fighters.get(0).getFighterTypeId(i) == 0 && i < position_l) {
//
//						fighters.get(0).setFighterTypeId(i, fighterId);
//
//						noFighterAtNumberBefor = false;
//					}
//				}
//
//				if(noFighterAtNumberBefor) {
//
//					if(position_l >= number) {
//
//						fighters.get(0).setFighterTypeId(position_l - 1 + bufferNumber, fighterId);
//					} else {
//
//						fighters.get(0).setFighterTypeId(position_l, fighterId);
//					}
//				}
//			} else {
//
//				throw new OperationFaildException(S.S10090);
//			}
//
//		// 交换
//		} else {
//
//			final String[] strPosition = position.split(",");
//
//			ueTeams.get(0).setEquipmentTeamTypeId(Integer.parseInt(strPosition[0]), fighterId);
//
//			fighters.get(0).setFighterTypeId(Integer.parseInt(strPosition[1]), fighterId);
//
//
//		}
//
//		userEquipmentTeamDAO.update(ueTeams.get(0));
//
//		userFormationFighterDAO.update(fighters.get(0));
//	}
//
//	/**
//	 * 获取最后一个空位
//	 *
//	 * @param ueTeams 格子信息
//	 *
//	 * @return 空位位置
//	 */
//	private int findLastPosition(List<UserEquipmentTeam> ueTeams) {
//
//		for(int i = UserEquipmentTeam.EQUIPMENT_TEAM_TYPE_ID_LEN - 1; i >= 0 ; i--) {
//
//			if(ueTeams.get(0).getEquipmentTeamTypeId(i) != -1) {
//
//				return i + 1;
//			}
//		}
//
//		return -1;
//	}
//
//	/**
//	 * 根据开启的最大地图算出阵型偏移量
//	 *
//	 * @param number 最大地图ID
//	 *
//	 * @return 偏移量
//	 */
//	private int getBufferNumber(int number) {
//
//		int bufferNumber = 0;
//
//		switch(number) {
//
//		    case 3: bufferNumber = 3; break;
//
//		    case 4: bufferNumber = 2; break;
//
//		    case 5: bufferNumber = 1; break;
//		}
//
//	    return bufferNumber;
//	}
//
//	public DAO<String, UserEquipmentTeam> getUserEquipmentTeamDAO() {
//		return userEquipmentTeamDAO;
//	}
//
//	public void setUserEquipmentTeamDAO(
//			DAO<String, UserEquipmentTeam> userEquipmentTeamDAO) {
//		this.userEquipmentTeamDAO = userEquipmentTeamDAO;
//	}
//
//	public City getCity() {
//		return city;
//	}
//
//	public void setCity(City city) {
//		this.city = city;
//	}
//
//	public DAO<String, UserFormationFighter> getUserFormationFighterDAO() {
//		return userFormationFighterDAO;
//	}
//
//	public void setUserFormationFighterDAO(
//			DAO<String, UserFormationFighter> userFormationFighterDAO) {
//		this.userFormationFighterDAO = userFormationFighterDAO;
//	}
////
//}
