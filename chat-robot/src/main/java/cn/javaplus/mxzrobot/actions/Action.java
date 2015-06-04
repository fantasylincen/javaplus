package cn.javaplus.mxzrobot.actions;

/**
 * 一次操作
 */
public interface Action {
	
	/**
	 * 执行操作
	 * 
	 * @param args
	 * @return
	 */
	public String execute(Args args);
}
