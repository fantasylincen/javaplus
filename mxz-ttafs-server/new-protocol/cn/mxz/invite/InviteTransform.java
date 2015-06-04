package cn.mxz.invite;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

/**
 * 邀请
 * @author 林岑
 *
 */
@Communication
public interface InviteTransform {

	/**
	 * 获取邀请模块界面数据
	 * @return
	 */
	InviteUI getUI();
	
	/**
	 * 获取我的邀请码, 我可以把这个邀请码发给别的玩家
	 * @return
	 */
	CodeUI getMyCode();
	
	/**
	 * 提交邀请码
	 * @param code
	 */
	CommitResult commitCode(String code);
	
	/**
	 * 领取某个礼包
	 * @param id
	 * @return
	 */
	InviteUI receive(int id);
	
	void setUser(City user);
}
