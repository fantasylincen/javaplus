package cn.javaplus.generator.protocol.define;

import java.net.URL;
import java.util.Properties;

import cn.javaplus.util.Util;

public class D {

	/**
	 * JAVA源文件根目录
	 */
	public static String		JAVA_SRC_PATH					= get("JAVA_SRC_PATH");

	/**
	 * AS源文件根目录
	 */
	public static String		AS_SRC_PATH						= get("AS_SRC_PATH");;

	/**
	 * Server.as文件的包路径
	 */
	public static String		SERVER_DOT_AS_PACKET			= get("SERVER_DOT_AS_PACKET");;

	static {
		if(!JAVA_SRC_PATH.endsWith("/")) {
			JAVA_SRC_PATH += "/";
		}
		if(!AS_SRC_PATH.endsWith("/")) {
			AS_SRC_PATH += "/";
		}
	}

	//我现在的工作空间是D:/workspace
	//以上3个路径 比较重要， 关系到生成后的文件放在哪里， 其他的就不用关心了

	/**
	 * 通信接口 包路径
	 */
	public static final String	COMMUNICATION_INTERFACE_PACKET	= SERVER_DOT_AS_PACKET;

	/**
	 * 通信接口的实现 包路径
	 */
	public static final String	COMMUNICATION_CLASS_PACKET		= SERVER_DOT_AS_PACKET;

	/**
	 * DataHandler.as 包路径
	 */
	public static final String	AS_PROTOCOL_HANDLER_PACKET			= SERVER_DOT_AS_PACKET;

	/**
	 * DataHandler.java 包路径
	 */
	public static final String	JAVA_DATAHANDLER_PACKET			= SERVER_DOT_AS_PACKET;

	/**
	 * Methods.java 包路径
	 */
	public static final String	METHODS_JAVA_CLASS_PACKET	= SERVER_DOT_AS_PACKET;

	/**
	 * ASEvent.as 包路径
	 */
	public static final String	AS_EVENT_CLASS_PACKET	= SERVER_DOT_AS_PACKET;

	/**
	 * Responses.java 包路径
	 */
	public static final String PROTOCOLS_JAVA_CLASS_PACKET = SERVER_DOT_AS_PACKET;

	/**
	 * JavaProtocolHander
	 */
	public static final String JAVA_PROTOCOL_HANDLER_PACKET = SERVER_DOT_AS_PACKET;

	/**
	 * 值对象生成包
	 */
	public static final String	VALUE_OBJECTS_PACKET	= SERVER_DOT_AS_PACKET;


	private static String get(String key) {
		URL url = D.class.getResource("/path.properties");
		Properties p = Util.Property.getProperties(url);
		return p.getProperty(key);
	}
}
