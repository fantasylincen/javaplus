package cn.mxz.base.telnet;

interface CommandListener {

	/**
	 * 到达命令节点时调用该方法
	 * @param e
	 */
	void onEnterCommandNode(CommandEvent e);

	/**
	 * 当进入某个节点后调用该方法
	 * @param e
	 */
	void onEntered(EnteredEvent e);

	/**
	 * 参数填满的时候调用该方法
	 * @param e
	 */
	void onArgsFull(CommandEvent e);

}
