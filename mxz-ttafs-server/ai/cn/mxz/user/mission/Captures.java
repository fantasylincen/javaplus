package cn.mxz.user.mission;

import java.util.List;

import cn.mxz.battle.Camp;
import cn.mxz.fighter.Fighter;
import cn.mxz.user.team.god.Hero;

/**
 * 副本战俘容器
 * @author 林岑
 */
public interface Captures extends Iterable<FighterCapture>{

	/**
	 * 使用甘露进行俘获
	 * @return
	 */
	
	List<Hero> captureByGanLu();

	/**
	 * 使用仙露进行俘获
	 
	 * @return
	 */
	List<Hero> captureByXianLu();

	/**
	 * 生成可俘获的战士列表
	 
	 * @param camp
	 */
	void generate(Camp<? extends Fighter> camp);

}
