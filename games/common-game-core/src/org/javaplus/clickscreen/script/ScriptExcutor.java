package org.javaplus.clickscreen.script;

public interface ScriptExcutor {

	/**
	 * 执行Java脚本中的指定方法
	 * @param script 脚本内容
	 * @param functionName 方法名
	 * @param args 参数列表
	 * @return
	 */
	Object call(String script, String functionName, Object... args);
	Integer callInt(String script, String functionName, Object... args);
	Double callDouble(String script, String functionName, Object... args);
	String callString(String script, String functionName, Object... args);

}
