package cn.mxz.user.init;

import cn.javaplus.collections.keyvalue.Value;

/**
 * 用户准备信息
 * @author 	林岑
 * @since	2013年6月6日 15:02:19
 *
 */
public interface ReadyUser extends Value{

	/**
	 * @return	帐号
	 */
	String getAccounts();

	/**
	 * @return	昵称
	 */
	String getNick();

	/**
	 * @param nick	设置昵称
	 */
	void setNick(String nick);

	/**
	 * @return	是否创建了昵称
	 */

	boolean hasNick();

	/**
	 * @return	玩家类型
	 */
	int getFighterTypeId();

	/**
	 * 玩家帐号
	 * @param id
	 */
	void setAccounts(String id);

	/**
	 * 设置主角神将ID
	 * @param fighterTypeId
	 */
	void setFighterTypeId(int fighterTypeId);

	/**
	 * 角色ID
	 * @return
	 */
	String getRoleId();

	/**
	 * 设置角色ID
	 * @param roleId
	 */
	void setRoleId(String roleId);

	/**
	 * 客户端类型:1 ios;2 android;3 其它 （手游）

	 * @param clientType
	 */
	void setClientType(int clientType);

	/**
	 * 客户端类型:1 ios;2 android;3 其它 （手游）
	 * @return
	 */
	int getClientType();

	public String getUserName();
	public void setUserName(String userName);
	boolean isMan();

	String getGameId();

	void setGameId(String gameId);

}
