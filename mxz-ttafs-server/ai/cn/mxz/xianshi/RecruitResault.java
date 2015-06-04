package cn.mxz.xianshi;

import cn.mxz.user.team.god.Hero;

/**
 * 招募结果
 * @author 林岑
 *
 */
public interface RecruitResault {

	
	/**
	 * 招得的魂魄ID (如果没获得魂魄, 那么该值为-1)
	 * @return
	 */
	int getSpiriteId();

	/**
	 * 招得的战士 (如果没招得战士, 那么该值为null)
	 * @return
	 */
	Hero getFighter();

	/**
	 * 在招募到魂魄的情况下, 当前拥有多少个魂魄
	 * @return
	 */
	int getSpiriteHasNow();

	/**
	 * 招募到的魂魄数量
	 * @return
	 */
	int getSpiriteCount();

	/**
	 * 寻到的品质
	 * @return
	 */
	int getStep();

}
