package cn.mxz.user.team;

import java.util.Collection;
import java.util.Map;

import cn.mxz.city.City;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.team.god.Hero;


/**
 * 玩家神将队伍
 * @author 	林岑
 * @since	2013年6月3日 17:10:33
 *
 */
public interface Team {

	/**
	 * 所有的神将
	 * @return
	 */
	Collection<Hero> getAll();

	/**
	 * 获得指定类型ID的战士
	 * @param fighterId
	 * @return
	 * @exception
	 */
	Hero get(int fighterId);

	/**
	 * 主神将
	 * @return
	 */
	PlayerHero getPlayer();

	/**
	 * 放入队伍中
	 * @param fighterId
	 * @param h
	 */
	void put(Hero h);

	/**
	 * 从玩家队伍或者库存里面将指定的神将移除掉
	 *
	 * @param h 一定要是通过getGod方法或者getAll方法取出来的对象
	 */
	void remove(Hero h);

	/**
	 * 获得指定ID的神将的数量
	 * @param tradType
	 * @return
	 */

	int getCount(int tradType);

//	/**
//	 * 将我的队伍里面的指定神将移动到teamAdd中
//	 * @param fighterId 神将唯一ID
//	 * @param teamAdd	别人的队伍
//	 */
//	void move(int fighterId, Team teamAdd);

	/**
	 * 所属的城池
	 * @return
	 */
	City getCity();

	void remove(Integer id);

	boolean contains(int heroId);

	/**
	 * @param find
	 * @return true : 创建了新的战士, false : 创建了一个魂魄
	 */
	boolean createNewHeroAuto(int find);

	/**
	 * 为玩家city新建一个战士, 类型为type, 如果玩家已有type类型的hero, 那么会报错
	 *
	 * @param type
	 * @return
	 */
	Hero createNewHero(int type);


	/**
	 * 创建指定用户的所有神将
	 *
	 * @return
	 */
	Map<Integer, Hero> createFighters();

	/**
	 * 战士数量
	 * @return
	 */
	int size();
}
