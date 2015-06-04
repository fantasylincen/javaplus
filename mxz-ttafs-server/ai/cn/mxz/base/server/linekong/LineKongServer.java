package cn.mxz.base.server.linekong;

/**
 * 蓝港服务器
 * 
 * @author 林岑
 * @since 2013年12月18日 10:32:17
 */
interface LineKongServer {

	/**
	 * 登陆
	 * 
	 * @param userId
	 *            帐号
	 * @param password
	 *            密码(或者是密码的MD5, 请 LINEKONG 确定)
	 * 
	 * @exception LineKongException
	 * 
	 *                密码错误/帐号不存在 ....
	 */
	void login(String userId, String password);

	/**
	 * 随机生成一个帐户
	 * 
	 * @return User 中包含 帐号和密码
	 */
	User randomUser();

	
}
