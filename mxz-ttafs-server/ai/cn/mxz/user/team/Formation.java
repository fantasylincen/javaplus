package cn.mxz.user.team;

import java.util.List;

import cn.mxz.Attribute;
import cn.mxz.formation.AlternateFormation;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;
import cn.mxz.user.team.god.ShenJiaAble;
import db.domain.Tactical;

/**
 * 阵形
 *
 * @author 林岑
 *
 */
public interface Formation extends ShenJiaAble{

	/**
	 * 当前选定的阵营, 如果当前选定阵营中前排战士死亡了, 那么替补自动替换到前排
	 *
	 * @return
	 */
	public PlayerCamp getSelected();

	/**
	 * 获得该英雄的阵法加成
	 *
	 * @param hero
	 * @return 如果没有加成, 返回 new EmptyAddition(); 不会返回null
	 */
	public Attribute getAddition(Hero hero);

	/**
	 * 战士在阵形中的位置
	 *
	 * @param f
	 * @return
	 *
	 *         阵形中 9个位置:
	 *        	0 1 2 		2 1 0
	 *         	3 4 5 		5 4 3
	 *         	6 7 8		8 7 6
	 *
	 *         替补5个位置: 100 101 102 103 104
	 *
	 *         如果不在阵形中, 也不在阵形中 : -1
	 */
	public int getPosition(Hero f);

	/**
	 * 替补阵容
	 * @return
	 */
	AlternateFormation getAlternate();

	/**
	 * 新建阵法
	 * @param id
	 */
	public void addNewTactical(Integer id);

	/**
	 *
	 * 获得品级为a0的阵法数量
	 * @param a0 品级
	 * @return
	 */
	public int getTacticalsCount(int a0);



	/**
	 * 获取此战士位于
	 * 阵首  1
	 * 阵中  2
	 * 阵尾  3
	 * @return
	 */
	public int getFormationPart( Hero hero );

	int getMaxCount();

	public List<Tactical> getTacticalsAll();

	public Tactical getCurrentTactical();

	/**
	 * 移动
	 * @param desPos
	 * @param srcHeroId
	 * @return
	 */
	Boolean setHeroPosition(int desPos, int srcHeroId);


}
