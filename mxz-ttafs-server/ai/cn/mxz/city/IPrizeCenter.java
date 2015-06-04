package cn.mxz.city;

import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.prizecenter.IUserPrizePackage;

public interface IPrizeCenter {

	List<IUserPrizePackage> getData();

	




	void getPrize(List<Prize> prize, long activityId);
	
	public int addPrize(int type, String prizeStr, String desc, String title); 
	public int addPrize(int type, List<Prize> prize, String desc, String title); 
	
	/**
	 * 获得非蓝港的奖励
	 * @param activityId
	 */
	void getPrize( int activityId );






	int addPrize(int type, String prizeStr, String title, String desc, int endSecond);
	
	/**
	 * 是否领取过此id的奖励
	 * true    领取过
	 * false   未领取
	 * @param id
	 * @return
	 */
	boolean isGotPrize( int id );

}
