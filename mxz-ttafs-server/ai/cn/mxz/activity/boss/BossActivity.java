package cn.mxz.activity.boss;

import java.util.Collection;

import cn.mxz.activity.Activity;

/**
 * Boss活动
 * 
 * @author 林岑
 * 
 */
public interface BossActivity extends Activity {

	/**
	 * 
	 * 获得活动中所有的Boss
	 * 
	 * @return
	 */
	Collection<Boss> getBossAll();

	/**
	 * 积分排名
	 * 
	 * @param uanme
	 * @return
	 */
	int getRank(String uanme);

	/**
	 * 检查Boss战是否开始
	 */
	void checkStart();

	/**
	 * 添加一个挑战者
	 * 
	 * @param user
	 */
	void add(BossChallenger user);

	/**
	 * 获得所有挑战者
	 * 
	 * @return
	 */
	Collection<BossChallenger> getAll();

	/**
	 * 延迟启动
	 */
	void startDelay();

	/**
	 * 延迟结束Boss战
	 */
	void stopDelay();
}