//package cn.mxz.team.builder;
//
//import java.util.List;
//import java.util.Map;
//
//import templets.FormationTemplet;
//import templets.FormationTempletConfig;
//import cn.javaplus.common.db.DAO;
//import cn.mxz.prop.equipment.Equipment;
//import cn.mxz.prop.equipment.EquipmentBuilder;
//import cn.mxz.protocols.user.god.TeamP.TeamGrid;
//import cn.mxz.protocols.user.god.TeamP.TeamPro;
//import cn.mxz.user.City;
//import cn.mxz.user.team.god.Hero;
//
//import com.google.common.collect.Lists;
//
//import db.dao.factory.DaoFactory;
//import db.domain.UserEquipmentTeam;
//
//public class TeamBuilder {
//
//	/**
//	 * 初始化装备队伍信息
//	 *
//	 * @param city 城池信息
//	 *
//	 * @return 装备队伍信息
//	 */
//	public TeamPro build(City city) {
//
//	 	TeamPro.Builder newBuilder = TeamPro.newBuilder();
//
//		DAO<String,UserEquipmentTeam> userEquipmentTeamDAO = DaoFactory.getUserEquipmentTeamDAO();
//
//		newBuilder.addAllGrids(setTeamGrids(city, userEquipmentTeamDAO));
//
//		return newBuilder.build();
//	}
//
//	/**
//	 * 添加格子--数据库中不存在初始化若存在直接反馈
//	 *
//	 * @param city 城池信息
//	 * @param userEquipmentTeamDAO 装备阵型数据库DAO
//	 *
//	 * @return 格子信息
//	 */
//	private List<TeamGrid> setTeamGrids(City city, DAO<String,UserEquipmentTeam> userEquipmentTeamDAO) {
//
//		List<UserEquipmentTeam> userEquipmentTeams = userEquipmentTeamDAO.findByUname(city.getId());
//
//		List<TeamGrid> list = Lists.newArrayList();
//
//		TeamGrid.Builder tg = null;
//
//		int canEnterMax = city.getMission().getMark().getMapId();
//
//		List<FormationTemplet> findByFormationId = null;
//
//		while(findByFormationId == null || findByFormationId.size() == 0) {
//
//			if(canEnterMax <= 3) {
//
//				break;
//			}
//
//			findByFormationId = findIds(canEnterMax);
//
//			canEnterMax--;
//		}
//
//		// 阵型人数
//		int number = canEnterMax;
//
//		if(findByFormationId != null) {
//
//			number = findByFormationId.get(findByFormationId.size() - 1).getNumber();
//		}
//		int hId = 0;
//
//		// 若已经存在装备队伍信息
//		if(!userEquipmentTeams.isEmpty()) {
//
//			for(UserEquipmentTeam uTeam : userEquipmentTeams) {
//
//				for(int i = 0; i < UserEquipmentTeam.EQUIPMENT_TEAM_TYPE_ID_LEN; i++) {
//
//					tg = TeamGrid.newBuilder();
//
//					tg.setFighterId(uTeam.getEquipmentTeamTypeId(i));
//
//					tg.setIsOpen(i < number * 2);
//
//					Map<Integer, Equipment> equipped = city.getEquipmentManager().getEquipped(i);
//
//					for (Equipment e : equipped.values()) {
//
//						tg.addEquipments(new EquipmentBuilder().build(e));
//					}
//
//					list.add(tg.build());
//				}
//			}
//
//		} else {
//
//			Map<Integer, Hero> fightersInTeam = city.getTeam().getFightersInTeam();
//
//			List<Hero> heros = Lists.newArrayList(fightersInTeam.values());
//
//			int buffer = 1;
//
//			UserEquipmentTeam uTeam = new UserEquipmentTeam();
//
//			for(int i = 0; i < UserEquipmentTeam.EQUIPMENT_TEAM_TYPE_ID_LEN; i++) {
//
//				hId = -1;
//
//				tg = TeamGrid.newBuilder();
//
//				if(i == 0) {
//
//					uTeam.setEquipmentTeamTypeId(i, city.getTeam().getPlayer().getFighterId());
//
//					tg.setFighterId(city.getTeam().getPlayer().getFighterId());
//
//				} else {
//
//					if((i - buffer) < heros.size()) {
//
//						hId = heros.get(i - buffer).getFighterId();
//					}
//
//					if(hId == city.getTeam().getPlayer().getFighterId()) {
//
//						buffer--;
//						i--;
//						continue;
//					}
//
//					uTeam.setEquipmentTeamTypeId(i, hId);
//
//					tg.setFighterId(hId);
//				}
//
//				uTeam.setUname(city.getId());
//
//				if(i < number * 2) {
//
//					tg.setIsOpen(true);
//				} else {
//
//					tg.setIsOpen(false);
//				}
//
//				list.add(tg.build());
//			}
//
//			userEquipmentTeamDAO.add(uTeam);
//		}
//
//		return list;
//	}
//
//	private List<FormationTemplet> findIds(int canEnterMax) {
//
//		List<FormationTemplet> ls = Lists.newArrayList();
//
//		for (Integer k : FormationTempletConfig.getKeys()) {
//
//			FormationTemplet temp = FormationTempletConfig.get(k);
//
//			if(canEnterMax > temp.getFormationId() ) {
//
//				ls.add(temp);
//			}
//		}
//
//		return ls;
//	}
//}
