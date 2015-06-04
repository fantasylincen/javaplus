package cn.mxz.mission.old.listeners;

import cn.mxz.mission.old.MissionAdaptor;
import cn.mxz.mission.old.NewPlayerEndEvent;

/**
 * 新手引导结束时 监听器
 * @author 林岑
 *
 */

public class NewPlayerEndListener extends MissionAdaptor {

	@Override
	public void onNewPlayerEnd(NewPlayerEndEvent e) {

//		leave(e.getCity());	//遗弃玩家未遗弃的神将

//		allToFormation(e.getCity());

//		selectFirst3Formation(e.getCity());	//选中第一个3人阵

//		Debuger.debug("NewPlayerEndListener.onNewPlayerEnd() 选定第一个三人阵为默认阵形");
	}

//	private void leave(City city) {
//
//		Collection<Hero> values = city.getTeam().getAll();
//
//		values = new ArrayList<Hero>(values);
//
//		int typeIdLast = 0;
//
//		for (Hero hero : values) {
//
//			if(!(hero instanceof PlayerHero) && (hero.getTypeId() != D.FLAMEN_ID)) {
//
//				typeIdLast = hero.getTypeId();
//			}
//		}
//
////		if(values.size() > 3) {
////
////			leave(typeIdLast, values, city.getTeam());
////		}
//	}

//	/**
//	 * 移除除了玩家和D.FLAMEN_ID和typeId的其他神将
//	 * @param typeId
//	 * @param values
//	 * @param team
//	 */
//	private void leave(int typeId, Collection<Hero> values, Team team) {
//
//		for (Hero hero : values) {
//
//			if(hero.getTypeId() == typeId) {	//哪吒下阵
//				try {
//					Formation f = team.getCity().getFormation();
//					f.remove(f.getIndex(hero));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			if(!(hero instanceof PlayerHero) && hero.getTypeId() != D.FLAMEN_ID && hero.getTypeId() != typeId) {
//
//				team.remove(hero);
//			}
//		}
//	}

//	/**
//	 * 所有战士进入队伍
//	 * @param city
//	 */
//	private void allToFormation(City city) {
//
//		Collection<Hero> values = city.getTeam().getAll();
//
//		for (Hero hero : values) {
//
//			hero.setInTeam(true);
//		}
//	}

//	/**
//	 * 选中第一个3人阵
//	 *
//	 * @param city
//	 */
//	private void selectFirst3Formation(City city) {
//
//		int formationId = FormationTempletConfig.getMinKey();
//
//		Formation<Hero> fm = city.getFormation();
//
//		fm.select(formationId);
//
//		fm.autoFormation();
//	}
}
