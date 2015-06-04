package cn.mxz.prizecenter;

import java.util.List;

public interface IUserPrizePackage {

	/**
	 * @return prizes
	 */
	List<IPrize> getPrizes();

	/**
	 * @return desc
	 */
	String getDesc();

	/**
	 * @return endTime
	 */
	int getEndTime();

	/**
	 * @return type
	 */
	int getType();

	/**
	 * @return createTime
	 */
	int getCreateTime();

	/**
	 * @return title
	 */
	String getTitle();

	/**
	 * @return id
	 */
	int getId();
	
	/**
	 * 此奖品是否蓝港
	 * @return
	 */
	boolean getIsLingkong();
	

}