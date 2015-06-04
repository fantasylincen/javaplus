package cn.mxz.base.logserver.command;

interface Matcher {

	/**
	 * 从这条文字中, 提取用户
	 * @param line
	 * @return
	 */
	String getUser(String line);

	/**
	 * 是否匹配
	 * @param line
	 * @return
	 */
	boolean isMatches(String line);

}
