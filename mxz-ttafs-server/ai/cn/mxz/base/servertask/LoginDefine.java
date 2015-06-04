package cn.mxz.base.servertask;


/**
 * login 基础数据定义
 *
 * */
public class LoginDefine {

	public final static short	LOGIN_REQUEST			= 1;

	public final static short	GET_ZONES				= 2;																								// 请求所有区信息

	public static final short	NOTIFY_SERVER_ONLINE	= 3;																								// AI服务器发来的消息,
																																							// 告诉登陆服务器这个AI服务器在线

	public static final short	CREATE_RANDOM_USER		= 4;

	/***
	 * AI 服务器获取配置信息
	 */
	public static final short	AI_GET_CONFIG	= 5;

	public final static byte	PACKET_TAIL				= 127;

	public final static byte	PACKET_HEAD				= 117;

	public final static int		PACKETS_LENGTH			= 1024;																							// 缓冲区大小

	/****** Login config ****************/
	public static final String	LOGIN_ID				= "20001";

	public static final String	LOGIN_KEY				= "ABCD";

//	public static final int		LOGIN_SERVER_PORT		= new Integer(Util.Property.getProperties(ConfigProperties.PATH).getProperty("loginServerPort"));


	// public static Map<String,PartnerInfo> partner = new
	// ConcurrentHashMap<String,PartnerInfo>();
	// public static Map<String,ServerInfo> serverInfo = new
	// ConcurrentHashMap<String,ServerInfo>();
}
