package cn.mxz.mission;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 *  黑市
 * @author 林岑
 *
 */
@Communication
public interface MissionTransform {

	/**
	 * 副本章节完成度
	 * @param chapterId 章节ID
	 * @return
	 */
	MissionCompletenessUI getCompleteness(int chapterId);

	/**
	 * 领取宝箱奖励
	 * @param chapterId 章节ID
	 * @param index 领取哪一个宝箱     宝箱的下标
	 */
	void receiveBox(int chapterId, int index);

	void setUser(City user);
}
