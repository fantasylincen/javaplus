package cn.mxz.equipment;

import java.util.Date;

import mongo.gen.MongoGen.LogSnatchDto;
import cn.mxz.city.City;

public interface SnatchLog {

	/**
	 * 材料类型
	 */
	int getDataType();

	/**
	 * excel静态内容
	 */
	int getExcelnub();

	int getId();

	/**
	 * 是否是我抢了别人
	 */
	boolean isQuilt();

	/**
	 * 他是否胜利了
	 */
	boolean isWin();

	/**
	 * 玩家等级
	 */
	int getLevel();

	/**
	 * 我的昵称
	 */
	String getMyNice();

	/**
	 * 材料数量
	 */
	int getNub();

	/**
	 * 抢我材料的人的昵称
	 */
	String getRooberNice();

	/**
	 * 抢我材料的人的类型ID
	 */
	String getRoberType();

	/**
	 * 抢夺时间
	 */
	String getSnatchTime();

	/**
	 * 角色类型
	 */
	int getUserType();

	/**
	 * 战况信息id
	 */
	int getWarsituationId();

	Date getTime();

	LogSnatchDto getDto();

	boolean isSaw();

	void markSaw();

	/**
	 * 是否夺宝成功了
	 * 
	 * @return
	 */
	boolean isSuccess();

	/**
	 * 另外一个人的昵称
	 * 
	 * @return
	 */
	String getOtherNick();

	/**
	 * 另外一个人的等级
	 * 
	 * @return
	 */
	int getOtherLevel();
	
	City getOther();
}
