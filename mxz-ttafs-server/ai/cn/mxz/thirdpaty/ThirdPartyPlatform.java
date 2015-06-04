package cn.mxz.thirdpaty;

import java.util.List;

import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.prizecenter.UserPrizePackage;

/**
 * 第三方合作平台
 *
 * @author 林岑
 *
 */
public interface ThirdPartyPlatform {

	/**
	 * 检测token是否可用
	 *
	 * @param userName
	 *            蓝港用户名
	 * @param token
	 * @param otherValue
	 * @param unixTime
	 * @param clientType
	 * @param mac
	 * @return 蓝港ID
	 */
	String checkToken(String userName, String token, String mac, int clientType, int unixTime, String adId, long gameId);

	/**
	 * 登出服务器
	 *
	 * @param city
	 *            玩家唯一标识
	 */
	void logout(ThirdPartyRole city);

	/**
	 * 创建角色
	 *
	 * @param id
	 *            蓝港玩家ID
	 * @param isMan
	 *            是否是男性
	 * @param initialLevel
	 *            初始等级
	 * @param IP
	 *            玩家IP地址
	 * @param roleName
	 *            角色名
	 * @return 创建的 角色ID
	 */
	String createUser(String id, boolean isMan, int initialLevel, String ip, String roleName, long gameId);

	/**
	 * 获得玩家userId在serverId中 创建的角色ID
	 *
	 * @param userId
	 *            玩家ID
	 * @return 角色ID 如果没有找到角色, 返回null
	 */
	String getRoleId(String userId, long gameId);

	/**
	 * 角色进入游戏
	 *
	 * @param user
	 */
	void logIn(ThirdPartyRole user, long gameId);

	/**
	 * 删除角色
	 *
	 * @param userId
	 *            玩家帐号
	 * @param roleId
	 *            玩家角色ID
	 */
	void deleteRole(String userId, String roleId);

	/**
	 * 生成一个角色名字
	 *
	 * @return
	 */
	String generateRoleName(boolean isMan);

	/**
	 * 更新当前在线人数
	 *
	 * @param size
	 */
	void updateOnlineSize(int size);

	/**
	 * 判断该用户帐号是不是这个第三方平台的帐号
	 *
	 * @param userId
	 * @return
	 */
	public boolean isPlatformUserId(String userId);

	/**
	 * 领取奖励
	 *
	 * @param user
	 * @param prize
	 */
	boolean getPrize(ThirdPartyRole user, List<Prize> prize, long activityId );

	/**
	 * @param user
	 *            第三方平台的用户
	 * @param reduce
	 *            最终扣除的第三方平台的货币
	 * @param systemGoldNeed 
	 */
	void pay(ThirdPartyRole user, int reduce, int systemGoldNeed);

	/**
	 * 当前角色拥有多少货币
	 * @return
	 */
	int getGold(ThirdPartyRole user);

	/**
	 * bind
	 */
	void bind();

	void connect();
	
	/**
	 * 查询角色能领取的活动奖励
	 * @param user
	 * @return
	 */
	public List<UserPrizePackage> queryPrize(City user);

//	/**
//	 * 系统赠送元宝消耗
//	 * @param user
//	 * @param reduce
//	 */
//	void payGiftGold(ThirdPartyRole user, int reduce);
}
