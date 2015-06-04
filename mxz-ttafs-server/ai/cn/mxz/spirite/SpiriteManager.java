package cn.mxz.spirite;

import java.util.List;
import java.util.Set;

import cn.mxz.user.team.god.Hero;

/**
 * 魂魄管理器
 * @author 林岑
 *
 */
public interface SpiriteManager {

	/**
	 * 获得所有魂魄
	 * @return
	 */
	List<Spirite> getSpirites();

	/**
	 * 获得指定类型ID的魂魄
	 * @param typeId
	 * @return
	 */
	Spirite get(int typeId);

	/**
	 * 移除 指定ID 的魂魄
	 * @param id
	 */
	void remove(Integer id);

	/**
	 * 增加一个魂魄
	 * @param find
	 */
	void add(int id);

	/**
	 * 移除指定ID的魂魄 count个
	 * @param typeId	移除类型ID
	 * @param count		移除数量
	 */
	void remove(int typeId, int count);

	/**
	 * 增加N个魂魄
	 * @param all
	 */
	void add(Set<Integer> all);
	/**
	 * 增加count个魂魄
	 */
	void add(int id, int count);

	/**
	 * 获得某个魂魄的数量
	 * @param typeId
	 * @return
	 */
	int getCount(int typeId);

	/**
	 * 进价所需
	 * @param typeId
	 * @return
	 */
	int getJinJieSpiriteNeed(int typeId);

	void checkJinJieDan(Hero hero);

	void checkSpirite(Hero hero);

	void checkMaxQuality(Hero hero);

	int getJinJieDanNeed(Hero hero);

	void checkJingYuan(Hero hero);

	int getJingYuanNeed(Hero hero);


}
