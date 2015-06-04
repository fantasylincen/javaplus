package cn.mxz.mission.old;

import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.user.Player;

/**
 * 地图上出现的宝箱
 * @author 林岑
 *
 */
public interface MapBox {

	/**
	 * 宝箱的ID
	 */
	int getId();

	/**
	 * 开启这个宝箱
	 */
	void open(Player player);

	/**
	 * 所有的宝箱奖励
	 * @return
	 */
	List<Prize> getAll();

	int getIndex();

	int getPath();

}