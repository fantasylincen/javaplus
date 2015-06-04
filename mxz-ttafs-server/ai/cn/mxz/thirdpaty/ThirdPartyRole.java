package cn.mxz.thirdpaty;

/**
 * 第三方平台角色
 * @author 林岑
 *
 */
public interface ThirdPartyRole {

	/**
	 * 帐号
	 * @return
	 */
	String getUserId();

	/**
	 * 角色唯一ID
	 * @return
	 */
	String getRoleId();

	/**
	 * 等级
	 * @return
	 */
	int getLevel();

	/**
	 * 这个角色的性别
	 * @return
	 */
	boolean isMan();

	/**
	 * 角色IP地址
	 * @return
	 */
	String getIp();

	/**
	 * 客户端类型
	 * @return
	 */
	int getClientType();

	/**
	 * 金币
	 * @return
	 */
	int getCash();

	/**
	 * 元宝
	 * @return
	 */
	int getGold();

	/**
	 * 角色类型
	 * @return
	 */
	int getRoleType();

}
