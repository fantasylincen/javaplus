package cn.mxz.dogz;

import java.util.Map;

import cn.mxz.user.team.god.ShenJiaAble;

/**
 * 神兽管理器
 *
 * @author 林岑
 *
 */
public interface DogzManager extends ShenJiaAble {

	/**
	 * 所有神兽列表
	 *
	 * @return
	 */
	Map<Integer, Dogz> getDogzAll();

	/**
	 * 当前出战的神兽
	 *
	 * @return
	 */
	Dogz getFighting();

	/**
	 * 獲得指定ID的神獸
	 *
	 * @param dogzTypeId
	 * @return
	 */
	Dogz getDogz(int dogzTypeId);

	/**
	 * 添加神兽
	 *
	 * @param d
	 *            神兽
	 */
	void addDogz(Dogz d);

	/**
	 * 注魂
	 * @param dogzId
	 * @return
	 */
	void zhuHun(int dogzId);

	/**
	 * 神兽等级
	 * @return
	 */
	int getLevel();

	/**
	 * 魂力最大值
	 * @return
	 */
	int getHunLiMax();

	/**
	 * 当前魂力
	 * @return
	 */
	int getHunLiNow();

	/**
	 * 设置等级
	 *
	 * @param level
	 */
	void setLevel(int level);

	/**
	 * 设置魂力
	 * @param hunLi
	 */
	void setHunLi(float hunLi);

	/**
	 * 增加魂力
	 * @param p
	 */
	void addHunLi(float p);

	/**
	 * 当前魂力  不是总魂力, 是当前等级所得的魂力
	 * @return
	 */
	float getHunLi();

//	/**
//	 * 确保玩家有一个白虎神兽, 前提是玩家已经达到可以开启神兽的等级
//	 */
//	void ensureHasFirst();

}
