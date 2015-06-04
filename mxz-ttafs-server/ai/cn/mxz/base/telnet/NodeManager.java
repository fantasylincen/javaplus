package cn.mxz.base.telnet;

import java.util.List;

import org.dom4j.Element;

interface NodeManager {

	/**
	 * 回退到根节点
	 */
	void back();

	/**
	 * 是否是命令节点
	 * @return
	 */
	boolean isCommandNode();

	/**
	 * 当前所在节点
	 * @return
	 */
	Element getCurrentElement();

	/**
	 * 当前节点的下一个参数节点
	 * @return
	 */
	Element nextArgNode();

	/**
	 * 进入下一节点
	 * @param select	当前节点的后续节点列表的索引
	 */
	void enterNode(int select);

	/**
	 * 增加一个命令监听器,
	 * @param commandListener
	 */
	void addCommandListener(CommandListener commandListener);

	/**
	 * 获得输入参数列表
	 * @return
	 */
	String[] getArgs();

	/**
	 * 接受一个参数
	 * @param arg
	 */
	void addArg(String arg);

	/**
	 * 参数列表是否已经装满了
	 * @return
	 */
	boolean isArgsFull();

	/**
	 * 后续nodes
	 * @return
	 */
	List<Element> nextNodes();
}