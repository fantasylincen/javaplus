package cn.javaplus.system;

/**
 * 系统变量
 * 
 * @author 林岑
 * 
 */
public class SystemEnv {

	/**
	 * 用户目录
	 * 
	 * @return
	 */
	public static final String getHomeDir() {
		return System.getProperty("user.home");
	}
}
