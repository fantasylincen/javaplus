package cn.mxz.dogz;

import java.util.List;

/**
 * 神兽开启界面
 * @author 林岑
 *
 */
public interface DogzOpenUI {

	/**
	 * 神兽头像列表
	 */
	List<DogzUI> getHeads();

	/**
	 * 下一神兽开启条件 神兽等级
	 * @return
	 */
	int getNextOpenDogzLevel();

	/**
	 * 下一神兽开启条件  玩家等级
	 * @return
	 */
	int getNextOpenUserLevel();

	/**
	 * 注魂一次所需魂力
	 * @return
	 */
	int getHunLiNeed();

	/**
	 * 神兽魂力
	 * @return
	 */
	int getHunLiNow();

	/**
	 * 魂力最大值
	 * @return
	 */
	int getHunLiMax();

	/**
	 * 等级
	 * @return
	 */
	int getLevel();
}
