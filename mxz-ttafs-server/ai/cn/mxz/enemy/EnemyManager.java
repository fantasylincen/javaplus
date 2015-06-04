package cn.mxz.enemy;

import java.util.List;

public interface EnemyManager {

	/**
	 * 添加一个敌人
	 * @param userId
	 */
	void add(String userId);

	/**
	 * 移除一个敌人
	 * @param userId
	 */
	void remove(String userId);

	/**
	 * 获得所有的敌人
	 * @return
	 */
	List<Enemy> getAll();

	boolean isEnemy(String id);

}
